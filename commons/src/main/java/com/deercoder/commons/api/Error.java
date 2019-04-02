package com.deercoder.commons.api;

public class Error {
	/**
	 * 自定义错误码
	 */
	private int status;

	private String message;

	public Error(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
