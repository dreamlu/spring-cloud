package com.deercoder.commons.model.ws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模板消息 {https://developers.weixin.qq.com/miniprogram/dev/api-backend/templateMessage.send.html}
 * @author dreamlu
 * @date 2019/04/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelMsgReturn {

	private Integer errcode;

	private String errmsg;

}
