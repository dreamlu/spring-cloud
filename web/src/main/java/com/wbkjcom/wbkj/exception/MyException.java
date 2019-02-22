package com.wbkjcom.wbkj.exception;

/**
 *
 * com.wbkjcom.wbkj.exception
 * 2017-12-26-18:51
 * 2017
 *  on 2017-12-26.
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
