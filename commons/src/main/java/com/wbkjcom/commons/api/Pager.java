package com.wbkjcom.commons.api;

/**
 * author: dreamlu
 * introduce: 页码等数据
 */

public class Pager {

	private Integer clientPage; //当前页码
	private Integer sumPage;    //数据总数量
	private Integer everyPage;  //每一页显示的数量

	public Pager() {
	}

	public Pager(Integer clientPage, Integer sumPage, Integer everyPage) {
		this.clientPage = clientPage;
		this.sumPage = sumPage;
		this.everyPage = everyPage;
	}

	public void setClientPage(Integer clientPage) {
		this.clientPage = clientPage;
	}

	public void setSumPage(Integer sumPage) {
		this.sumPage = sumPage;
	}

	public void setEveryPage(Integer everyPage) {
		this.everyPage = everyPage;
	}

	public Integer getClientPage() {
		return clientPage;
	}

	public Integer getSumPage() {
		return sumPage;
	}

	public Integer getEveryPage() {
		return everyPage;
	}
}
