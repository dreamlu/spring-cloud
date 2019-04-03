package com.deercoder.shop.service;

import com.deercoder.shop.model.ShopDe;
import com.deercoder.shop.model.Shop;

import java.util.Map;

/**
 * des: 店铺信息
 */

public interface ShopService {

	Object getBySearch(Map<String, Object> params, Shop shop);

	Object getById(Long id);

	Object getByIdDe(Long id);

	Object getBySearchDe(Map<String, Object> params, ShopDe shop);

	Object update(Shop shop);

	Object create(Shop shop);

	Object delete(Long id);

	/**
	 * 根据unionid获得id
	 * @param Unionid
	 * @return
	 */
	Object getIdByUnionid(String Unionid);
}
