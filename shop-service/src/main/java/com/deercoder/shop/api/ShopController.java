package com.deercoder.shop.api;

import com.deercoder.shop.model.ShopDe;
import com.deercoder.shop.service.ShopService;
import com.deercoder.shop.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private ShopService shopService;

	@Autowired
	DiscoveryClient discoveryClient;

	@PostMapping(value = "/register")
	public Object create(@RequestBody Shop shop) {
		// 创建时间
		shop.setCreatetime(Timestamp.valueOf(LocalDateTime.now()));
		return shopService.create(shop);
	}

	@PutMapping(value = "/update")
	public Object update(@RequestBody Shop shop) {

		return shopService.update(shop);
	}

	@DeleteMapping(value = "/delete/{id}")
	public Object delete(@PathVariable(value = "id") Long id) {
		return shopService.delete(id);
	}

	@GetMapping("/id")
	public Object getById(Long id) {

		return shopService.getById(id);
	}

	@GetMapping("/idDe")
	public Object getByIdDe(Long id) {

		return shopService.getByIdDe(id);
	}

	@GetMapping("/search")
	public Object search(@RequestParam(required = false) Map<String, Object> params, Shop shop) {
		return shopService.getBySearch(params, shop);
	}

	@GetMapping("/searchDe")
	public Object searchDe(@RequestParam(required = false) Map<String, Object> params, ShopDe shop) {
		return shopService.getBySearchDe(params, shop);
	}


	@GetMapping("/getId")
	public Object getIdByUnionid(String unionid) {
		return shopService.getIdByUnionid(unionid);
	}
}
