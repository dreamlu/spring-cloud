package com.wbkjcom.shop.api;

import com.wbkjcom.shop.model.Shop;
import com.wbkjcom.shop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.wbkjcom.commons.util.RestUtil.copyNonNullProperties;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private Logger logger = LoggerFactory.getLogger(ShopController.class);

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @Autowired
    private ShopService shopService;

    @Autowired DiscoveryClient discoveryClient;

    @PostMapping(value = "/create")
    public Shop create(@RequestBody Shop shop){
        //@PathVariable是用来获得请求url中的动态参数的，
        // 所以该注解只能支持将参数放在请求url的GET提交方式，所以不管你如何进行设置，@PathVariable都是无法支持Post请求的。
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



//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Shop> save(Shop shop, UriComponentsBuilder ucb) {
//        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
//        logger.info("/shop, host:" + instance.getHost() + ", serviceId:" + instance.getServiceId() + ",shop id:" + shop.getId() + ",shop name:" + shop.getName());
//
//        Shop saved = shopService.save(shop);
//
//        HttpHeaders headers = new HttpHeaders();
//        URI locationUri = ucb.path("/login/")
//                .path(String.valueOf(saved.getId()))
//                .build()
//                .toUri();
//        headers.setLocation(locationUri);
//
//        ResponseEntity<Shop> responseEntity = new ResponseEntity<>(saved, headers, HttpStatus.CREATED);
//
//        return responseEntity;
//
//    }
}
