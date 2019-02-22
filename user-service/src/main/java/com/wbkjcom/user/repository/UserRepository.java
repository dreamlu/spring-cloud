package com.wbkjcom.user.repository;

import com.wbkjcom.user.model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User,Long>{

    @Cacheable
    User findByName(String name);

    @CachePut
    User save(User user);
}
