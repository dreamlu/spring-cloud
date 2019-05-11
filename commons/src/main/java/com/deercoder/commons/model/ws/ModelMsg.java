package com.deercoder.commons.model.ws;

import com.deercoder.commons.lib.json.JpaJson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;

/**
 * 模板消息 {https://developers.weixin.qq.com/miniprogram/dev/api-backend/templateMessage.send.html}
 * @author dreamlu
 * @date 2019/04/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelMsg {

	private String appid;

	private String access_token;

	// 接收者openid
	private String touser;

	private String template_id;

	private String page;

	private String form_id;

	@Convert(converter = JpaJson.class)
	private Object data;

	private String emphasis_keyword;

}
