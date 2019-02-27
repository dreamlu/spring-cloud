package com.wbkjcom.shop.service.impl;

import com.wbkjcom.commons.api.GetInfo;
import com.wbkjcom.commons.api.Pager;
import com.wbkjcom.commons.lib.Lib;
import com.wbkjcom.shop.model.Shop;
import com.wbkjcom.shop.repository.ShopRepository;
import com.wbkjcom.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopRepository shopRepository;

	@Override
	public Object findBySearch(Map<String, Object> params, Shop shop) {

		// 创建 ExampleMatcher。
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				// 忽略 id 和 createTime 字段。
				//.withIgnorePaths("id")
				// 忽略大小写。
				.withIgnoreCase()
				// 忽略空字符串
				//.withStringMatcher()
				// 忽略为空字段。
				.withIgnoreNullValues();

		// 携带 ExampleMatcher。
		Example<Shop> shopExample = Example.of(shop, exampleMatcher);
		// List<Shop> data = shopRepository.findAll(shopExample);

		// 分页查询，从 0 页开始查询 5 个。
		int        clientPage = Integer.parseInt((String) params.get("clientPage"));
		int        everyPage  = Integer.parseInt((String) params.get("everyPage"));
		Page<Shop> page       = shopRepository.findAll(new PageRequest(clientPage - 1, everyPage, new Sort(Sort.Direction.DESC,"id")));

		// 分页表。
		List<Shop> content = page.getContent();
		if (content.size() == 0) {
			return Lib.GetMapData(Lib.CodeNoResult, Lib.MsgNoResult);
		}
		// 总页数。
		int sumPage = page.getTotalPages();

		return new GetInfo<Object>(Lib.CodeSuccess, Lib.MsgSuccess, content, new Pager(clientPage, sumPage, everyPage));
	}

	@Override
	public Shop findById(Long id) {
		return shopRepository.findById(id);
	}

	@Override
	public Shop save(Shop shop) {
		return shopRepository.save(shop);
	}

	@Override
	public void delete(Long id) {
		shopRepository.delete(id);
	}
}
