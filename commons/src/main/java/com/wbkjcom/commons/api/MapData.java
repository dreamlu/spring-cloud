package com.wbkjcom.commons.api;

public class MapData {

	private Integer status;

	private String msg;

	public MapData() {
	}

	public MapData(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}
}
