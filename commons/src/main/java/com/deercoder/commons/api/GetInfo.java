package com.deercoder.commons.api;

/**
 * com.wbkjcom.wbkj.model
 */
public class GetInfo<T> extends MapData {

	private T data;

	public GetInfo() {
	}

	public GetInfo(Integer status, String msg, T data) {
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
