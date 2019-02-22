package com.wbkjcom.commons.api;

/**
 *
 * com.wbkjcom.wbkj.model
 */
public class GetInfo<T> extends GetInfoN {

    private Pager pager; // 页码信息

    public GetInfo() {
    }

    public GetInfo(Integer status, String msg, T data, Pager pager) {
        super.setStatus(status);
        super.setMsg(msg);
        super.setData(data);
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }
}
