package com.deercoder.common.config.wx;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 微信支付提现--配置
 */
@Configuration
//@ConditionalOnClass(WxPayService.class)
@EnableConfigurationProperties(WxPayProperties.class)
public class WxPayConfiguration {
	private WxPayProperties properties;

	@Autowired
	public WxPayConfiguration(WxPayProperties properties) {
		this.properties = properties;
	}

	private static Map<String, WxPayService>       maServices = Maps.newHashMap();

	//@Bean
	public static WxPayService getWxPayService(String appid) {

		WxPayService wxPayService = maServices.get(appid);
		if (wxPayService == null) {
			throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
		}
		return wxPayService;
	}

	@Bean
	public Object payServices() {
		maServices = this.properties.getConfigs()
				.stream()
				.map(a -> {
					WxPayConfig config = new WxPayConfig();
					config.setAppId(a.getAppId());
					config.setMchId(a.getMchId());
					config.setMchKey(a.getMchKey());
					config.setSubAppId(a.getSubAppId());
					config.setSubMchId(a.getSubMchId());
					config.setKeyPath(a.getKeyPath());
					// 可以指定是否使用沙箱环境
					config.setUseSandboxEnv(false);

					WxPayService service = new WxPayServiceImpl();
					service.setConfig(config);
//					routers.put(a.getAppId(), this.newRouter(service));
					return service;
				}).collect(Collectors.toMap(s -> s.getConfig().getAppId(), a -> a));

		return Boolean.TRUE;
	}
}