package com.wbkjcom.auth.service.impl;

import com.deercoder.commons.lib.Lib;
import com.wbkjcom.auth.model.Admin;
import com.wbkjcom.auth.repository.AdminRepository;
import com.wbkjcom.auth.service.AdminService;
import com.deercoder.commons.util.ende.DESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin findByAccount(String account) {
		return adminRepository.findByAccount(account);
	}

	@Override
	public Admin login(Admin admin) {

		Admin  dbAdmin = findByAccount(admin.getAccount());
		String comPass = DESUtil.encrypt(admin.getPassword(), DESUtil.CONST_DES_KEY_1);
		if (dbAdmin.getPassword().equals(comPass)) {
			return dbAdmin;
		}
		return null;
	}

	@Override
	public Admin register(Admin admin) {
		String password = DESUtil.encrypt(admin.getPassword(), DESUtil.CONST_DES_KEY_1);
		admin.setPassword(password);
		adminRepository.save(admin);
		return login(admin);
	}

	@Override
	public Object update(Admin admin) {
		String password = DESUtil.encrypt(admin.getPassword(), DESUtil.CONST_DES_KEY_1);
		admin.setPassword(password);
		adminRepository.save(admin);
		return Lib.MapUpdate;
	}
}

