package com.deercoder.shop.repository;

import com.deercoder.shop.model.Shop;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@CacheConfig(cacheNames = "shops")
public interface ShopRepository extends JpaRepository<Shop, Long> {

	@Query(value = "select id from Shop where unionid=?1")
	List getIdByUnionid(String unionid);
}
