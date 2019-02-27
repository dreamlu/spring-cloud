package com.wbkjcom.wbkj.service;

import com.wbkjcom.commons.vo.OrderVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * com.wbkjcom.service
 */
@FeignClient("order-service")
public interface OrderService {

    @PostMapping("/order/addOrder") boolean addOrder(@RequestBody OrderVo orderVo);

}
