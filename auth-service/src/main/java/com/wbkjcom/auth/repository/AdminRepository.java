package com.wbkjcom.auth.repository;

import com.wbkjcom.auth.model.Admin;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames = "backs")
public interface AdminRepository extends JpaRepository<Admin, Long> {

//    @CachePut
//    Admin save(Admin user);

	//    @CacheEvict
//    void delete(Long id);
//    @Cacheable
	Admin findByAccount(String account);
}
