package com.wbkjcom.shop.api;

import com.wbkjcom.shop.model.Shop;
import com.wbkjcom.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.deercoder.commons.util.RestUtil.copyNonNullProperties;


@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired DiscoveryClient discoveryClient;

    @PostMapping(value = "/create")
    public Shop create(@RequestBody Shop shop){
        Shop u = shopService.save(shop);
        return u;
    }

    @PutMapping(value = "/update")
    public Shop update(@RequestBody Shop shop){
        Shop existData = shopService.findById(shop.getId());
        copyNonNullProperties(shop, existData);
        shopService.save(shop);
        Shop u = shopService.save(existData);
        return u;
    }

    @DeleteMapping(value = "/delete/{id}")
    public Boolean delete(@PathVariable( value = "id") Long id){
        try {
            shopService.delete(id);
            return  true;
        } catch (Exception e){
            // 异常处理
            return false;
        }
    }

    @GetMapping("/id")
    public Shop getById(Long id){
        return shopService.findById(id);
    }

    @PostMapping("/search")
    public Object search(@RequestParam(required = false) Map<String, Object> params, Shop shop){
        return shopService.findBySearch(params, shop);
    }
}
