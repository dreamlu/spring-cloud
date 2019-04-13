package com.deercoder.commons.api;

/**
 * com.wbkjcom.wbkj.model
 */
public class GetInfoPager<T> extends GetInfo {

	private Pager pager; // 页码信息

	public GetInfoPager() {
	}

	public GetInfoPager(Integer status, String msg, T data, Pager pager) {
		super.setStatus(status);
		super.setMsg(msg);
		super.setData(data);
		this.pager = pager;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
}
