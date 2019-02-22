package com.wbkjcom.commons.api;

/**
 * @Description:服务不可用异常
 * @Date:15:02 2017-12-28
 */
public class ServiceUnAvailableException extends RuntimeException {

    private String serviceId;

    public ServiceUnAvailableException(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return serviceId;
    }
}

