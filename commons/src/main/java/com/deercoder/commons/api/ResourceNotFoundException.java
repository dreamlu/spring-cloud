package com.deercoder.commons.api;


/**
 * @Description:资源未找到异常
 */
public class ResourceNotFoundException extends RuntimeException {

	private Long resourceId;

	public ResourceNotFoundException(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getResourceId() {
		return this.resourceId;
	}

}
