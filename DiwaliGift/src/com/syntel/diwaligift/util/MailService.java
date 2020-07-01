package com.syntel.diwaligift.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailService {

	public static void sendMail(MailerVO mailerVO) throws Exception {

		// Properties properties = LoadProps.loadParams("server");
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "10.128.32.187"); //Prod
//		properties.put("mail.smtp.host","10.119.132.34"); //Dev
		System.out.println("Kunal says that the host value is "
				+ properties.getProperty("mail.smtp.host"));

		properties.put("mail.smtp.sendpartial", "true");
//		 properties.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getDefaultInstance(properties);

		// mailerVO.setEmailLogPathDir(properties.getProperty("EMAIL_LOG_PATH"));

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(mailerVO.getFromAddress()));
		message.setSubject(mailerVO.getSubject(), mailerVO.getHeaderEncoding());
		String bodyText = mailerVO.getBody();
		BodyPart htmlBodyContent = new MimeBodyPart();
		String htmlString = mailerVO.getHtmlString();
		htmlBodyContent.setContent(htmlString, mailerVO.getBodyEncoding());
		MimeMultipart multipart = new MimeMultipart("related");
		multipart.addBodyPart(htmlBodyContent);

		String path = "";

		// START of added by Nikita Bangar for image
		
//		MimeBodyPart imagePart = new MimeBodyPart();
//        imagePart.setHeader("Content-ID", "<" + contentId + ">");
//        imagePart.setDisposition(MimeBodyPart.INLINE);
        
        
     // END of added by Nikita Bangar for image
		
		message.setContent(multipart);

		if (mailerVO.getToAddresses() != null) {
			for (Iterator iterator = mailerVO.getToAddresses().iterator(); iterator
					.hasNext();) {
				String nextAddress = (String) iterator.next();
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(nextAddress));
			}
		}

		if (mailerVO.getCcAddresses() != null) {
			for (Iterator iterator = mailerVO.getCcAddresses().iterator(); iterator
					.hasNext();) {
				String nextAddress = (String) iterator.next();
				message.addRecipient(Message.RecipientType.CC,
						new InternetAddress(nextAddress));
			}
		}

		// Commented by kunal
		System.out.println("Before Sending");
		Transport.send(message);
		System.out.println("After sending");
		// StartLog(message,mailerVO.getFromAddress(),mailerVO);

		System.out.println("StartLogStartLogStartLogStart....................");
	}

	public static void StartLog(MimeMessage message, String from,
			MailerVO mailerVO) throws Exception {
		String sBatchSubDir = null;
		String sBMailMainDir = mailerVO.getEmailLogPathDir();
		// System.out.println("sBMailMainDir : "+sBMailMainDir);
		String startDate = null;
		String startTime = null;
		String fileDate = null;
		String fileTime = null;
		String filePrefix = "BL";
		String ext = ".txt";
		String errFileName = null;
		String errFileName1 = null;

		BufferedWriter out = null;

		try {

			String subject = "E_MAIL_TYPE_" + mailerVO.getSubjectCode();
			Format dateFormat = new SimpleDateFormat("MMM d yyyy");
			Format fileDateFormat = new SimpleDateFormat("MM/dd/yy");
			Format formatter = new SimpleDateFormat("HH.mm.ss");
			Date date = new Date();
			startDate = dateFormat.format(date);
			startTime = formatter.format(date);
			fileDate = fileDateFormat.format(date);
			fileTime = formatter.format(date);

			sBatchSubDir = new StringBuffer().append(sBMailMainDir)
					.append(subject).toString();
			errFileName = new StringBuffer().append(subject).toString();
			errFileName1 = new StringBuffer().append(subject).append("-")
					.append(startDate).append("-").append(startTime)
					.append(ext).toString();
			sBatchSubDir = new StringBuffer().append(sBMailMainDir)
					.append(errFileName).toString();
			if (new File(sBMailMainDir).exists()) {
			} else {
				(new File(sBMailMainDir)).mkdirs();
			}
			if (new File(sBatchSubDir).exists()) {
			} else {
				(new File(sBatchSubDir)).mkdirs();
			}
			out = new BufferedWriter(new FileWriter(sBatchSubDir + "/"
					+ errFileName1, true));
			out.write("From : " + from);
			out.write("\n");
			out.write("TO : ");
			Address[] To = message.getRecipients(Message.RecipientType.TO);
			// Modifications for issue # D_28 - start
			// if condition added by pratul to put a check when there is email
			// address in TO field
			if (message.getRecipients(Message.RecipientType.TO) != null
					&& (message.getRecipients(Message.RecipientType.TO)).length > 0) {
				// Modifications for issue # D_28 - end
				// System.out.println("length of To in start log***************88888 : "+To.length);
				for (int i = 0; i < To.length; i++) {
					out.write(To[i].toString());
					out.write(" ; ");
				}
				// Modifications for issue # D_28 - start
			}
			// Modifications for issue # D_28 - end
			out.write("\n");
			out.write("CC : ");
			if (message.getRecipients(Message.RecipientType.CC) != null
					&& (message.getRecipients(Message.RecipientType.CC)).length > 0) {
				Address[] CC = message.getRecipients(Message.RecipientType.CC);
				for (int i = 0; i < CC.length; i++) {
					out.write(CC[i].toString());
					out.write(" ; ");
				}
			}
			out.write("\n");
			out.write("BCC : ");
			if (message.getRecipients(Message.RecipientType.BCC) != null
					&& (message.getRecipients(Message.RecipientType.BCC)).length > 0) {
				Address[] BCC = message
						.getRecipients(Message.RecipientType.BCC);
				for (int i = 0; i < BCC.length; i++) {
					out.write(BCC[i].toString());
					out.write(" ; ");
				}
			}
			out.write("\n");
			out.write("Subject :" + (message.getSubject()).toString());
			out.write("\n");
			out.write((message.getContent()).toString());
			out.write("\n");

			// Transport.send(message);
			out.write("......................................................\n");
			out.write("*********  Note : Mail sent **************");
			out.write("\n......................................................\n");

		} catch (Exception e) {
			out.write("......................................................\n");
			out.write("*********  Note : Mail not sent **************");
			out.write("\n......................................................\n");
			out.write("\n");
			e.printStackTrace();

		} finally {
			out.write("\n");
			out.write("-------------------------------------------------------------------------------");
			out.write("\n--------------------------------------------------------------------------------\n");
			out.write("\n");
			out.close();
		}
	}

}
