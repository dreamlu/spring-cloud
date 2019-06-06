package com.deercoder.auth.service;

import com.deercoder.auth.model.Admin;

/**
 * admin
 */

public interface AdminService {

	/**
	 * 根据账号查找数据
	 * @param account
	 * @return
	 */
	Admin findByAccount(String account);

	/**
	 * 登陆
	 * @param admin
	 * @return
	 */
	Admin login(Admin admin);

	/**
	 * 注册
	 * @param admin
	 * @return
	 */
	Admin register(Admin admin);

	/**
	 * 修改账号密码
	 * @param admin
	 * @return
	 */
	Object update(Admin admin);
}
