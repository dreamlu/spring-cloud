package com.wbkjcom.commons.lib;

import com.wbkjcom.commons.api.GetInfoN;
import com.wbkjcom.commons.api.MapData;

/**
 * author : dreaamlu
 * 沿袭golang状态码
 * url : https://github.com/dreamlu/deercoder-gin/blob/master/util/lib/lib.go
 */

//final修饰符
public final class Lib {

	// 约定状态码
	public static final int
			CodeSuccess    = 200, // 请求成功
			CodeCreate     = 201, // 创建成功
			CodeNoAuth     = 203, // 请求非法
			CodeNoResult   = 204, // 暂无数据
			CodeUpdate     = 206, // 修改成功
			CodeDelete     = 209, // 删除成功
			CodeValidator  = 210, // 字段验证
			CodeCount      = 211, // 账号相关
			CodeCaptcha    = 214, // 验证码
			CodeValidate   = 217, // 验证成功
			CodeExistOrNo  = 220, // 数据无变化
			CodeSql        = 222, // 数据库错误统一状态
			CodeLackArgs   = 223, // 缺少参数
			CodeFile       = 224, // 文件上传相关
			CodeNoDelete   = 225, // 存在外健约束(逻辑或数据库约束)
			CodeEcrypt     = 230, // 数据解密失败
			CodeWx         = 240, // 微信小程序相关
			CodeWxPay      = 242, // 微信支付相关
			CodeWxWithDraw = 243, // 微信提现相关
			CodeOrder      = 251, // 订单相关
			CodeAliPay     = 262, // 支付宝支付相关
			CodeText       = 271, // 全局文字提示
			CodeChat       = 280, // chat相关
			CodeNoRoute    = 404, // 系统繁忙
			CodeError      = 500; // 系统繁忙

	// 约定提示信息
	public static final String
			MsgSuccess   = "请求成功",
			MsgCreate    = "创建成功",
			MsgNoAuth    = "请求非法",
			MsgNoResult  = "暂无数据",
			MsgDelete    = "删除成功",
			MsgUpdate    = "修改成功",
			MsgError     = "系统繁忙",
			MsgCaptcha   = "验证码验证失败",
			MsgExistOrNo = "数据无变化",
			MsgCountErr  = "用户账号或密码错误",
			MsgNoCount   = "用户账号不存在",
			MsgLackArgs  = "缺少参数",
			MsgValidate  = "验证成功",
			MsgFile      = "上传成功";

	// 约定提示信息
	public static final MapData
			MapSuccess   = new MapData(CodeSuccess, MsgSuccess),     // 统一成功
			MapError     = new MapData(CodeError, MsgError),       // 统一错误
			MapUpdate    = new MapData(CodeUpdate, MsgUpdate),     // 修改成功
			MapDelete    = new MapData(CodeDelete, MsgDelete),     // 删除成功
			MapCreate    = new MapData(CodeCreate, MsgCreate),     // 创建成功
			MapNoResult  = new MapData(CodeNoResult, MsgNoResult), // 暂无数据
			MapNoAuth    = new MapData(CodeNoAuth, MsgNoAuth),     // 请求非法
			MapCaptcha   = new MapData(CodeCaptcha, MsgCaptcha),   // 验证码失败
			MapExistOrNo = new MapData(CodeExistOrNo, MsgExistOrNo),// 指数据修改没有变化 或者 给的条件值不存在
			MapCountErr  = new MapData(CodeCount, MsgCountErr),     // 用户或账号名失败
			MapNoCount   = new MapData(CodeCount, MsgNoCount),      // 账号不存在
			MapLackArgs  = new MapData(CodeLackArgs, MsgLackArgs),
			MapValidate  = new MapData(CodeValidate, MsgValidate);

	// 信息通用,状态码及信息提示
	public static final MapData GetMapData(Integer status, String msg) {
		return new MapData(status, msg);
	}

	// 信息通用,状态码及信息提示
	public static final Object GetMapData(Integer status, String msg, Object data) {
		return new GetInfoN<Object>(status, msg, data);
	}
}