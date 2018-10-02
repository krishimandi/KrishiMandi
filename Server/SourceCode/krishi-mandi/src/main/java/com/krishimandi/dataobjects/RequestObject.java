package com.krishimandi.dataobjects;

public class RequestObject {

	private int requestID;
	private String userID;
	private String language;
	private String requestBody;

	public int getRequestId() {
		return requestID;
	}

	public void setRequestId(int requestID) {
		this.requestID = requestID;
	}

	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public void setLanguage(String language) {

		this.language = language;
	}
	
	public String getLanguage() {
		return language;
	}

	
	public void setRequestBody(String requestBody) {

		this.requestBody = requestBody;
	}

	public String getRequestBody() {
		return requestBody;
	}
}
