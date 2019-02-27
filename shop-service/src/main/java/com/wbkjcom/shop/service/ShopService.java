package com.wbkjcom.shop.service;

import com.wbkjcom.shop.model.Shop;

import java.util.Map;

/**
 * 店铺信息
 *
 * */

public interface ShopService {

    Object findBySearch(Map<String, Object> params, Shop shop);

    Shop findById(Long id);

    Shop save(Shop shop);

    void delete(Long id);
}
