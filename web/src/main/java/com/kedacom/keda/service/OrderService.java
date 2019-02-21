package com.kedacom.keda.service;

import com.kedacom.commons.vo.OrderVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * com.kedacom.consumer
 * 2018-01-02-19:05
 * 2018
 *  on 2018-01-02.
 */
@FeignClient("order-service")
public interface OrderService {

    @PostMapping("/order/addOrder") boolean addOrder(@RequestBody OrderVo orderVo);

}
