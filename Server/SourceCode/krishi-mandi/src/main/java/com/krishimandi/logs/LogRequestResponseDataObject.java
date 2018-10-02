package com.krishimandi.logs;

public class LogRequestResponseDataObject {

	private String userID; 
	private int requestType;
	private String requestTime;
	private String requestData;
	private String responseData;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getRequestType() {
		return requestType;
	}
	public void setRequestType(int requeestType) {
		this.requestType = requeestType;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getRequestData() {
		return requestData;
	}
	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}
	public String getResponseData() {
		return responseData;
	}
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
}
