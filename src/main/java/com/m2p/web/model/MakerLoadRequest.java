package com.m2p.web.model;

import com.m2p.web.util.RequestType;

public class MakerLoadRequest {
	
	private String requestData;
	
	private String comment;
	
	private RequestType requestType;

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}
/*
	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatus status) {
		this.requestStatus = status;
	}*/

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}
	
}