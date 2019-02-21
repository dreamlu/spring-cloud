package com.kedacom.order.api;

import com.kedacom.commons.vo.OrderVo;
import com.kedacom.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired DiscoveryClient discoveryClient;

    @PostMapping("/addOrder")
    public Boolean addOrder(@RequestBody OrderVo orderVo){
        orderService.save(orderVo);
        return true;
    }
}

