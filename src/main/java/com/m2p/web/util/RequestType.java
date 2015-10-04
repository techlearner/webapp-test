package com.m2p.web.util;

public enum RequestType {

	UPLOAD("UPLOAD", ""),
	REGISTRATION("REGISTRATION", ""),
	LOAD("LOAD", ""),
	REFUND("REFUND", ""),
	UPLOAD_LOAD("UPLOAD_LOAD","");
	
	
	
	private RequestType(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	private String name;
	
	private String url;
	
}
