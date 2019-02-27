package com.wbkjcom.wbkj.exception;

/**
 * com.wbkjcom.wbkj.exception
 */
//自定义异常对象
public class MyException extends RuntimeException {

    private Integer status;

    public MyException(Integer status,String message) {
        super(message);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
