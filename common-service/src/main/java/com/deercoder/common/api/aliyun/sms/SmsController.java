package com.deercoder.common.api.aliyun.sms;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.deercoder.common.model.Sms;
import com.deercoder.commons.api.MapData;
import com.deercoder.commons.lib.Lib;
import com.deercoder.commons.manager.cache.CacheManager;
import com.deercoder.commons.manager.cache.impl.RedisManager;
import com.deercoder.commons.model.CacheModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.deercoder.commons.util.Util.randNum;

/**
 * des: 阿里云, 短信
 * redis 缓存
 */

@RestController
@RequestMapping("/ali/sms")
public class SmsController {

	private final static String SignName     = "xxx";
	private final static String TemplateCode = "SMS_xxx";
	private final static String accessKeyId  = "xxxxx";
	private final static String secret       = "xxxxxx";


	private CacheManager cacheManager;

	@Autowired
	public void setRedis(RedisConnectionFactory redisConnectionFactory) {
		this.cacheManager = new RedisManager(redisConnectionFactory);
	}

	/**
	 * 短信验证码发送服务
	 *
	 * @param sms 短信参数
	 * @return
	 */
	@PostMapping("/send")
	public MapData send(@RequestBody Sms sms) {
		DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, secret);
		IAcsClient     client  = new DefaultAcsClient(profile);

		CommonRequest request = new CommonRequest();
		//request.setProtocol(ProtocolType.HTTPS);
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setVersion("2017-05-25");
		request.setAction("SendSms");
		request.putQueryParameter("PhoneNumbers", sms.getPhone());
		request.putQueryParameter("SignName", SignName);
		request.putQueryParameter("TemplateCode", TemplateCode);

		String code = randNum(6);
		request.putQueryParameter("TemplateParam", "{code:" + code + "}");

		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
			// 验证码 存储, 5分钟
			cacheManager.set(sms.getPhone(), new CacheModel(5L, code));
			return Lib.GetMapData(Lib.CodeSuccess, "发送完成");

			//if(response.getData().contains("Message\": \"OK\"")){
			//	return Lib.GetMapData(Lib.CodeSuccess, "发送成功");
			//}
		} catch (ServerException e) {
			e.printStackTrace();
			return Lib.GetMapData(Lib.CodeError, e.getErrMsg());
		} catch (ClientException e) {
			e.printStackTrace();
			return Lib.GetMapData(Lib.CodeError, e.getErrMsg());
		}

		//return Lib.MapError;
	}

	/**
	 * 短信验证码验证服务
	 *
	 * @param sms 短信参数
	 * @return
	 */

	@PostMapping("/check")
	public Object check(@RequestBody Sms sms) {

		CacheModel cacheModel = cacheManager.get(sms.getPhone());

		if(cacheModel == null) {
			return Lib.GetMapData(Lib.CodeText, "验证码不存在或已失效");
		}
		// value equals value
		if (String.valueOf(cacheModel.getData()).equals(sms.getCode())) {
			cacheManager.delete(sms.getPhone());
			return Lib.MapValidate;
		}
		return Lib.MapValidateErr;
	}
}
