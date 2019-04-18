package com.wbkjcom.auth.api;

import com.deercoder.commons.lib.Lib;
import com.deercoder.commons.manager.cache.CacheManager;
import com.deercoder.commons.manager.cache.impl.RedisManager;
import com.deercoder.commons.model.CacheModel;
import com.deercoder.commons.model.TokenModel;
import com.wbkjcom.auth.model.Admin;
import com.wbkjcom.auth.service.AdminService;
import com.wbkjcom.auth.stream.StreamClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.deercoder.commons.lib.Constants.TOKEN_MINUTE;

/**
 * des: 登录等权限验证操作
 * @author dreamlu
 */

@RestController
@RequestMapping("/admin")
//@EnableBinding(Source.class)
@EnableBinding(StreamClient.class)
public class AdminController {

	private CacheManager cacheManager;

	@Autowired
	public void setRedis(RedisConnectionFactory redisConnectionFactory) {
		this.cacheManager = new RedisManager(redisConnectionFactory);
	}

	@Autowired
	private AdminService adminService;

	// 只能针对单个服务
	// 微服务用stream
	//@Autowired
	//private RabbitTemplate rabbitTemplate;

	@Autowired
	private StreamClient streamClient;

	/**
	 * 登陆操作
	 * 消息队列测试:
	 * 消息发送： login() 注释逻辑
	 * 接受消息：{@link com.deercoder.shop.stream}
	 * 配置查看application-dev.yaml
	 */
	@PostMapping(value = "/login")
	@SuppressWarnings("Duplicates")
	public Object login(@RequestBody Admin admin) {
		admin = adminService.login(admin);

		if (admin != null) {
			// 使用 uuid 作为源 token
			String     token = UUID.randomUUID().toString().replace("-", "");
			TokenModel model = new TokenModel(admin.getId(), token);
			// 30 分钟有效期
			cacheManager.set(model.getToken(), new CacheModel(TOKEN_MINUTE, model));


			// 这里处理非必须的操作逻辑
			// 如通知、统计等
			// 消息队列测试
			//rabbitTemplate.convertAndSend("account", admin.getAccount());

			// stream--rabbitmq 消息队列测试
			// spring cloud 发送队列消息
			MessageBuilder<TokenModel> messageBuilder = MessageBuilder.withPayload(model);
			streamClient.output().send(messageBuilder.build());

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
