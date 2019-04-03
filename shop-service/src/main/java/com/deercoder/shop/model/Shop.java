package com.deercoder.shop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
//@Table(uniqueConstraints = {@UniqueConstraint(name = "同一区域代理唯一性",columnNames={"area", "isProxy"})})
public class Shop implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 微信unionId
	@Column(columnDefinition = "varchar(50)", unique = true)
	private String unionid;

	// 微信openid
	@Column(columnDefinition = "varchar(50)", unique = true)
	private String openid;

	// 门店名称
	@Column(columnDefinition = "varchar(50) default ''")
	private String name;

	// 联系人
	@Column(columnDefinition = "varchar(50)")
	private String contactName;

	// 地区
	@Column( columnDefinition = "varchar(50) default ''")
	private String area;

	// 地址
	@Column( columnDefinition = "varchar(255) default ''")
	private String address;

	// 电话
	@Column(columnDefinition = "varchar(20) default ''")
	private String phone;

	// 门店头像
	@Column( columnDefinition = "varchar(255) default ''")
	private String headimg;

	// vip 试用截止日期
	@Column(columnDefinition = "datetime")
	private Timestamp vipTryDate;

	// 会员剩余日期
	@Column(columnDefinition = "datetime")
	private Timestamp vipDate;

	// 资产
	@Column( columnDefinition = "decimal(10,2)")
	private Double assets;

	// 提现权限, 0(无需审核), 1(需审核), 2(待审核), 3(审核通过), 4(审核不通过)
	@Column(columnDefinition = "tinyint(1)")
	private Integer isWithDraw;

	// 推广分成比例
	@Column(columnDefinition = "double(10,2)")
	private Double sharePro;

	// 推广商家数
//	@Transient
//	private Integer shareShopNum;

	// 是否为代理, 0(否), 1(是)
	@Column(columnDefinition = "tinyint(1)")
	private Integer isProxy;

	// 代理分成比例
	@Column(columnDefinition = "double(10,2)")
	private Double proxyPro;

	// 创建时间
	@Column(columnDefinition = "datetime")
	private Timestamp createtime;

}
