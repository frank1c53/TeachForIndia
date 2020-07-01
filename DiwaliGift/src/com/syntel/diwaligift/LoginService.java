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

@Path("/login")
public class LoginService extends JdbcDaoSupport{
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String GetLoginDetails(String reqsparams) {
		System.out.println("reqParams in Registration " + reqsparams);

		JSONObject finalResultObj = new JSONObject();
		JSONObject resultObj = new JSONObject();
		JSONArray resultArr = new JSONArray();

	

		String adminId = "";
		String pwd = "";
		String login="false";
		JSONObject myobj;
		String AdminID="SynAdmin";
		String PAsswd="admin123";

		try {
			JSONObject jsonObj = new JSONObject(reqsparams);
			adminId = jsonObj.getString("adminId");
			pwd = jsonObj.getString("pwd");
			
		} catch (JSONException e) {
			e.printStackTrace();

			System.out.println("Returning json pasing exception");
			finalResultObj = RespBuilder.buildFailedResponse("400",
					"JSON PARSING EXCEPTION");
			return login;
		}
		//resultArr = JSONConverter.convertToJSON(login);
		
		if(adminId.equalsIgnoreCase(AdminID) & pwd.equals(PAsswd))
		{
			login="true";
			try {
				resultObj.put("Result_Arr", login);
				resultArr.put(resultObj);
				finalResultObj = RespBuilder.buildSuccessResponse("200", "SUCCESS", resultArr);
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			}
			
		}
		else
		{
			login="false";
			try {
				resultObj.put("Result_Arr", login);
				finalResultObj = RespBuilder.buildFailedResponse("300",
						"FAILED");
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			}
		
		}
		

		return finalResultObj.toString();
}
	}


