package com.deercoder.shop.service.common;

import com.deercoder.commons.model.ws.ModelMsg;
import com.deercoder.commons.model.ws.ModelMsgReturn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * feign 客户端请求
 * 微服务模块间请求
 * 通过注解使用feign请求 @Autowired private commonService service;
 * @author dreamlu
 * @date 2019/04/25
 */
@FeignClient("common-service")
@RequestMapping("/wx/applet")
public interface commonService {

	// 模板消息发送
	@PostMapping("/templateMsg")
	ModelMsgReturn templateMsg(@RequestBody ModelMsg data);
}
