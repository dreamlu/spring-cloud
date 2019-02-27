package com.wbkjcom.commons.api;

import java.io.Serializable;
import java.util.Map;

/**
 * description: 通用分页搜索模块
 */

public class Search  implements Serializable {

	private static final long serialVersionUID = 1L;

	// 页码数据
	private Pager pager;

	// 其他参数
	private Map<String, Object> other;

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public void setOther(Map<String, Object> other) {
		this.other = other;
	}

	public Pager getPager() {
		return pager;
	}

	public Map<String, Object> getOther() {
		return other;
	}
}
