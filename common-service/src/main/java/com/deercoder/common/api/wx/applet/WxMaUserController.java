package com.deercoder.common.api.wx.applet;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.deercoder.common.config.wx.WxMaConfiguration;
import com.deercoder.common.model.AccessToken;
import com.deercoder.common.model.Applet;
import com.deercoder.common.model.ModelMsg;
import com.deercoder.common.model.ModelMsgReturn;
import com.deercoder.commons.lib.Lib;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 微信小程序用户接口
 */
@RestController
@RequestMapping("/wx/applet")
@Slf4j
public class WxMaUserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 登陆接口
	 */
	@PostMapping("/login")
	public Object login(@RequestBody Applet applet) {

		if (StringUtils.isBlank(applet.getCode())) {
			return Lib.GetMapData(Lib.CodeText, "code不能为空");
		}

		final WxMaService wxService = WxMaConfiguration.getMaService(applet.getAppid());

		try {
			WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(applet.getCode());
			this.logger.info(session.getSessionKey());
			this.logger.info(session.getOpenid());
			//TODO 可以增加自己的逻辑，关联业务相关数据

			return Lib.GetMapData(Lib.CodeSuccess, Lib.MsgSuccess, session);
		} catch (WxErrorException e) {
			this.logger.error(e.getCause().getMessage(), e);
			return Lib.GetMapData(Lib.CodeWx, e.toString());
		}
	}

	/**
	 * <pre>
	 * 获取用户信息接口
	 * </pre>
	 */
	@GetMapping("/info")
	public Object info(Applet applet) {
		final WxMaService wxService = WxMaConfiguration.getMaService(applet.getAppid());

		// 用户信息校验
		if (!wxService.getUserService().checkUserInfo(applet.getSessionKey(), applet.getRawData(), applet.getSignature())) {
			return Lib.GetMapData(Lib.CodeWx, "用户信息校验失败");
		}

		// 解密用户信息
		WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(applet.getSessionKey(), applet.getEncryptedData(), applet.getIv());

		return Lib.GetMapData(Lib.CodeSuccess, Lib.MsgSuccess, userInfo);
	}

	/**
	 * <pre>
	 * 小程序模板消息
	 * spring boot http请求参考：{https://blog.csdn.net/uotail/article/details/86255120}
	 * </pre>
	 */
	@PostMapping("/templateMsg")
	public ModelMsgReturn templateMsg(@RequestBody ModelMsg data) {

		RestTemplate restTemplate = new RestTemplate();

		AccessToken accessToken = getAccessToken(data.getAppid());
		// 模板消息
		String      url     = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + accessToken.getAccess_token();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		data.setAccess_token(accessToken.getAccess_token());

		HttpEntity<ModelMsg>           request  = new HttpEntity<>(data, headers);
		ResponseEntity<ModelMsgReturn> response = restTemplate.postForEntity(url, request, ModelMsgReturn.class);

		return response.getBody();

	}

	public AccessToken getAccessToken(String appid) {
		final WxMaService wxService = WxMaConfiguration.getMaService(appid);
		RestTemplate restTemplate = new RestTemplate();
		String       url          = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+wxService.getWxMaConfig().getSecret();
		//HttpEntity<MultiValueMap<String, String>> request  = new HttpEntity<>();
		ResponseEntity<AccessToken> response = restTemplate.getForEntity(url, AccessToken.class);
		log.info("access_token实体："+response.getBody());
		return response.getBody();
	}

}