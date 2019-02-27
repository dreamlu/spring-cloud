package com.wbkjcom.wbkj.service.shop;

import com.wbkjcom.shop.model.Shop;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@FeignClient("shop-service")
public interface ShopService {

    @PostMapping("/shop/create")
    Shop create(@RequestBody Shop shop);

    @PutMapping("/shop/update")
    Shop update(@RequestBody Shop shop);

    @DeleteMapping("/shop/delete/{id}")
    Boolean delete(@PathVariable( value = "id") Long id);

    @GetMapping("/shop/id")
    Shop getById(@RequestParam("id") Long id); // must "id"

    // feign get多参数问题, 内部转post替代
    @PostMapping("/shop/search")
    Object search(@RequestParam(required = false) Map<String, Object> params, Shop shop);
}
