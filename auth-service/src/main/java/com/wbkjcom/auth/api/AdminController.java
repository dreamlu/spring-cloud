package com.wbkjcom.auth.api;

import com.deercoder.commons.lib.Lib;
import com.deercoder.commons.manager.cache.CacheManager;
import com.deercoder.commons.manager.cache.impl.RedisManager;
import com.deercoder.commons.model.CacheModel;
import com.deercoder.commons.model.TokenModel;
import com.wbkjcom.auth.model.Admin;
import com.wbkjcom.auth.service.AdminService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * des: 登录等权限验证操作
 * author: dreamlu
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

	private CacheManager cacheManager;

	@Autowired
	public void setRedis(RedisConnectionFactory redisConnectionFactory) {
		this.cacheManager = new RedisManager(redisConnectionFactory);
	}

	@Autowired
	private AdminService adminService;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@PostMapping(value = "/login")
	@SuppressWarnings("Duplicates")
	public Object login(@RequestBody Admin admin) {
		admin = adminService.login(admin);

		if (admin != null) {
			// 使用 uuid 作为源 token
			String     token = UUID.randomUUID().toString().replace("-", "");
			TokenModel model = new TokenModel(admin.getId(), token);
			// 30 分钟有效期
			cacheManager.set(model.getToken(), new CacheModel(30L, model));


			// 消息队列测试
			rabbitTemplate.convertAndSend("account", admin.getAccount());

			return Lib.GetMapData(Lib.CodeSuccess, Lib.MsgSuccess, model);
		}

		return Lib.MapCountErr;
	}

	@PostMapping(value = "/update")
	public Object register(@RequestBody Admin admin) {
		adminService.update(admin);
		return Lib.MapUpdate;
	}

//	@PostMapping(value = "/register")
//	public Object register(@RequestBody Admin admin) {
//	}

}
