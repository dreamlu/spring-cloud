package com.deercoder.commons.api;

/**
 * com.deercoder.wbkj.model
 */
public class GetInfoN<T> extends MapData {

	private T data;

	public GetInfoN() {
	}

	public GetInfoN(Integer status, String msg, T data) {
		super.setStatus(status);
		super.setMsg(msg);
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
