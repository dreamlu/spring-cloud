package com.deercoder.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDe implements Serializable {


	private Long id;

	// openid
	private String openid;

	// 门店名称
	private String name;

	// 联系人
	private String contactName;

	// 地区
	private String area;

	// 地址
	private String address;

	// 电话
	private String phone;

	// 门店头像
	private String headimg;

	// vip 试用截止日期
	private String vipTryDate;

	// 会员截至日期
	private String vipDate;

	// 资产
	private Double assets;

	// 提现权限, 0(无需审核), 1(需审核)
	private Integer isWithDraw;

	// 推广分成比例
	private Double sharePro;

	// 是否为代理, 0(否), 1(是)
	private Integer isProxy;

	// 代理分成比例
	private Double proxyPro;

	// 创建时间
	private String createtime;

	// 该区域代理分成比例
	private Double areaProxyPro;

	// 账号状态,1会员,0过期会员,2未购买
	private Short status;

	// 历史活动数
	private Integer hisNum;

	// 历史参与数
	private Integer hisJoinNum;

	// 历史预估收益
	private Double hisMoney;

	// 是否存在提现待审核状态, 0 无,1有
	private Integer hasWithDraw;

	// 推广商家数,简单点,加一
	private Integer shareShopNum;

	// 代理区商家数
	private Long proxyShopNum;

	// 代理佣金
	private Long proxyMoney;
}
