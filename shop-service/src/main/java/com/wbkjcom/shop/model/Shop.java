package com.wbkjcom.shop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Shop implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", columnDefinition = "varchar(20) default ''")
	private String name;

	// 地区
	@Column(name = "area", columnDefinition = "varchar(50) default ''")
	private String area;
	// 地址
	@Column(name = "address", columnDefinition = "varchar(255) default ''")
	private String address;

	@Column(name = "phone", columnDefinition = "varchar(20) default ''")
	private String phone;

	// 门店照片
	@Column(name = "headimg", columnDefinition = "varchar(255) default ''")
	private String headimg;


	public Shop() {
	}

	public Shop(String name, String phone, String address) {
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public Shop(String name) {
		this.name = name;
	}

	public Shop(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
}
