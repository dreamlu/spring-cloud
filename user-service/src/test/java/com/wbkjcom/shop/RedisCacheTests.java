package com.wbkjcom.shop;

import com.wbkjcom.shop.model.User;
import com.wbkjcom.shop.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCacheTests {
    @Autowired
    private UserRepository userRepository;

    @Before
    public void before() {
        userRepository.save(new User("AAA","123456","18361246696"));
    }

    @Test
    public void test() throws Exception {
        User u1 = userRepository.findByName("AAA");
        System.out.println("第一次查询：" +u1.getPhone());

        User u2 = userRepository.findByName("AAA");
        System.out.println("第二次查询：" + u2.getPhone());
    }

}
