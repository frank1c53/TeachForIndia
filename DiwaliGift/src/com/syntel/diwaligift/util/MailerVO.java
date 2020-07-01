package com.syntel.diwaligift.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MailerVO implements Serializable {

	private String fromAddress;
	private List toAddresses;
	private List ccAddresses;
	private String subject;
	private String body;
	private String bodyEncoding;
	private String headerEncoding;
	private List<String> attachment;
	private int subjectCode;
	private int requestId = 0;
	private String emailLogPathDir;
	private String recipientName;
	private String empName;
	private String ideaNumber;
	private String SelectedSpoc;
	private int userTypeCode;
	private String htmlString;
	private String text;
   
	public String getEmailLogPathDir() {
		return emailLogPathDir;
	}

	public void setEmailLogPathDir(String emailLogPathDir) {
		this.emailLogPathDir = emailLogPathDir;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBodyEncoding() {
		return bodyEncoding;
	}

	public void setBodyEncoding(String bodyEncoding) {
		this.bodyEncoding = bodyEncoding;
	}

	public List getCcAddresses() {
		return ccAddresses;
	}

	public void setCcAddresses(List ccAddresses) {
		this.ccAddresses = ccAddresses;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getHeaderEncoding() {
		return headerEncoding;
	}

	public void setHeaderEncoding(String headerEncoding) {
		this.headerEncoding = headerEncoding;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List getToAddresses() {
		return toAddresses;
	}

	public void setToAddresses(List toAddresses) {
		this.toAddresses = toAddresses;
	}

	/**
	 * TO add or set address to To Address.
	 * 
	 * @param toAddress
	 * 
	 */
	public void addToAddress(String strToAddress) {
		if (toAddresses != null) {
			// Nothing to do.
		} else {
			toAddresses = new ArrayList();
		}
		toAddresses.add(strToAddress);
	}

	/**
	 * To add or set address to CC Address.
	 * 
	 * @param strCcAddress
	 * 
	 */
	public void addCcAddresses(String strCcAddress) {
		if (ccAddresses != null) {
			// Nothing to do.
		} else {
			ccAddresses = new ArrayList();
		}
		ccAddresses.add(strCcAddress);
	}

	/**
	 * To add or set address to BCCAddress.
	 * 
	 * @param strBccAddress
	 * 
	 */
	public int getUserTypeCode() {
		return userTypeCode;
	}

	public void setUserTypeCode(int userTypeCode) {
		this.userTypeCode = userTypeCode;
	}

	public List<String> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<String> attachment) {
		this.attachment = attachment;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getIdeaNumber() {
		return ideaNumber;
	}

	public void setIdeaNumber(String ideaNumber) {
		this.ideaNumber = ideaNumber;
	}

	public void setSelectedSpoc(String selectedSpoc) {
		SelectedSpoc = selectedSpoc;
	}

	public String getSelectedSpoc() {
		// TODO Auto-generated method stub
		return SelectedSpoc;
	}

	public void setHtmlString(String htmlString) {
		this.htmlString = htmlString;
	}

	public String getHtmlString() {
		return htmlString;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getText(){
		return text;
	}
	public void setText(String text) {
		// TODO Auto-generated method stub
        this.text = text;		
	}  

}
