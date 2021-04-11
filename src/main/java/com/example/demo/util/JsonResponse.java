package com.example.demo.util;

import java.util.List;

public class JsonResponse<T> {
	private String status;
	private String message;
	private List<T> data;
	public JsonResponse(String message) {
		this.message = message;
	}
	
	public JsonResponse(String status, String message, List<T> data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	

	public JsonResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
	
}
