package com.wbkjcom.wbkj.service;

import com.wbkjcom.category.model.Category;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * com.wbkjcom.wbkj.service
 * 2018-01-02-19:53
 * 2018
 *  on 2018-01-02.
 */
@FeignClient("category-service")
public interface CategoryService {

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable("id") Long id);

}
