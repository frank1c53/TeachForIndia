package com.syntel.diwaligift;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.syntel.diwaligift.util.MailService;
import com.syntel.diwaligift.util.MailerVO;
import com.syntel.diwaligift.util.RespBuilder;
import com.syntel.diwaligift.util.JSONConverter;

@Path("/diwaligift")
public class DiwaliGift extends JdbcDaoSupport {

	// @Path("/insert")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String GetEmpDetail(String reqParams) {
		System.out.println("reqParams in diwaligift " + reqParams);

		JSONArray empDetailArr = new JSONArray();
		JSONObject empDetailObj = new JSONObject();
		JSONObject resultObj = new JSONObject();
		ResultSet rs, rsTemp;
		String cardno, empid, adminId, adminName;

		String empName = "";
		String empEmail = "";

		try {
			JSONObject jsonObj = new JSONObject(reqParams);
			cardno = jsonObj.getString("cardid");
			empid = jsonObj.getString("empid");
			adminId = jsonObj.getString("adminId");
			adminName = jsonObj.getString("adminName");

		} catch (JSONException e) {
			e.printStackTrace();

			System.out.println("Returning json parsing exception");
			resultObj = RespBuilder.buildFailedResponse("400",
					"JSON PARSING EXCEPTION");
			return resultObj.toString();
		}

		try {
			Connection con = getJdbcTemplate().getDataSource().getConnection();
			PreparedStatement pstmt = null;

			try {

				pstmt = con
						.prepareStatement("{call [synprod].[sp_DiwlaiGiftDataInsert] (?,?,?,?)}");
				pstmt.setString(1, cardno);
				pstmt.setString(2, empid);
				pstmt.setString(3, adminName);
				pstmt.setString(4, adminId);

				rs = pstmt.executeQuery();
				rsTemp = rs;
//				System.out.println("rsTemp = " + rsTemp);

				// while (rs.next()) {
				// System.out.println("Inside while loop");
				//
				// // empId = rs.getString("empid");
				// empName = rs.getString("name");
				// empEmail = rs.getString("email");
				//
				// System.out.println("empid " + empid + " empName " + empName
				// + " empEmail " + empEmail );
				// }

				empDetailArr = JSONConverter.convertToJSON(rs);

				empDetailObj.put("empData", empDetailArr);
				resultObj = RespBuilder.buildSuccessResponseR5("200",
						"SUCCESS", empDetailObj);
				// resultObj = RespBuilder.buildSuccessResponse("200",
				// "SUCCESS",
				// empDetailArr);
				if (empDetailArr.length() > 0) {
					empEmail = empDetailArr.getJSONObject(0).getString("email");
					empName = empDetailArr.getJSONObject(0).getString("name");
					String flag = empDetailArr.getJSONObject(0).getString(
							"existflag");
					System.out.println(" empName " + empName + " empEmail "
							+ empEmail + " existflag = " + flag);

					if (flag.equalsIgnoreCase("N"))
						sendMail(empid, empName, empEmail);

				}
			} catch (Exception e) {

				e.printStackTrace();
				System.out.println("Returning SQL Exception!!!!!!!!!!!!!!!!");
				resultObj = RespBuilder.buildFailedResponse("300", "Failed");
				return resultObj.toString();
			} finally {
				System.out.println("");
				con.close();
				pstmt.close();
			}

			return resultObj.toString();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connection Error...!!!!!!!!");
			resultObj = RespBuilder.buildFailedResponse("300", "Failed");
			return resultObj.toString();
		}

	}

	/*
	 * This method is used to send mail for New registration
	 */

	public void sendMail(String empid, String empName, String empEmail) {

		// empEmail = "Syntranet_Dev@tsyntelorg.com";

		try {
			MailerVO mv = new MailerVO();
			mv.addToAddress(empEmail);
//			 mv.addToAddress("nikita_bangar@syntelinc.com");
//			 mv.addCcAddresses("nikita_bangar@syntelinc.com");
			mv.setEmpName(empName);
			mv.setRecipientName(empName);
//			 mv.setFromAddress("vaishali_sonavale@syntelinc.com");
//			 mv.setFromAddress("Syntranet_Dev@tsyntelorg.com");
			mv.setFromAddress("mysyntel@Syntelinc.com");
			mv.setHeaderEncoding("text/html");
			mv.setBodyEncoding("text/html");

			mv.setSubject("Happy Diwali!!!");
			
			String htmlString = "<!DOCTYPE html><html><head>" +
			"<meta http-equiv='Content-Type' content='text/html;  charset=UTF-8'>" +
			"<meta name='viewport' content='width=device-width, initial-scale=1'></head>" +
			"<body  style = 'width:800px;  font-family: Verdana;'> " +
			
			"<div style = ' font-size: 12px;  margin-bottom: 5px; display: block;'>" +
			"<p style= ' color:#0160a0; font-family: verdana; font-size: 16px; font-weight: bold;'>" +
			"Dear  "+empName+",</p>" +
			"<p style = ' color:#0160a0;  font-size: 20px; margin-bottom:0px;'>" +
			"Atos Syntel wishes you and your Family a very Happy and Prosperous Diwali!!!</p>" +
			"</div>" +
			"<br>"+
//			"<img src='https://10.128.10.236/Nikita/DiwaliGiftImg/diwali1.jpg'/>" +
			 "<img src='https://mobi.syntel.in/MyCab/img/diwaliMail_Img.jpg'/>" +
			 
			"<p style='  color: black;  font-family: times new roman;'>" +
			"Please do not respond to this email, as this email address is for outbound messages " +
			"only. </p>" +
			"</body></html>";

			

			// Map<String, String> inlineImages = new HashMap<String, String>();
			// inlineImages.put("image1", "E:/Test/chart.png");

			mv.setHtmlString(htmlString);
			MailService.sendMail(mv);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
