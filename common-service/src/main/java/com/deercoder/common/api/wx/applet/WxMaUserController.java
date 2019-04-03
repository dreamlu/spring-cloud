package com.deercoder.common.api.wx.applet;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.deercoder.common.config.wx.WxMaConfiguration;
import com.deercoder.common.model.Applet;
import com.deercoder.commons.lib.Lib;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * 微信小程序用户接口
 */
@RestController
@RequestMapping("/wx/applet")
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
	 * 获取用户绑定手机号信息, 微信不准确, 利用短信验证
	 * </pre>
	 */
//    @GetMapping("/phone")
//    public String phone(@PathVariable String appid, String sessionKey, String signature,
//                        String rawData, String encryptedData, String iv) {
//        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
//
//        // 用户信息校验
//        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
//            return "user check failed";
//        }
//
//        // 解密
//        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
//
//        return JsonUtils.toJson(phoneNoInfo);
//    }

}