package com.wbkjcom.commons.vo;

import java.math.BigDecimal;

public class OrderVo {
    private BigDecimal summoney;

    private Long categoryId;

    private Long userId;

    public OrderVo(BigDecimal summoney, Long categoryId, Long userId) {
        this.summoney = summoney;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public OrderVo() {
    }

    public BigDecimal getSummoney() {
        return summoney;
    }

    public void setSummoney(BigDecimal summoney) {
        this.summoney = summoney;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override public String toString() {
        return "OrderVo{" + "summoney=" + summoney + ", categoryId=" + categoryId + ", userId=" + userId + '}';
    }
}
