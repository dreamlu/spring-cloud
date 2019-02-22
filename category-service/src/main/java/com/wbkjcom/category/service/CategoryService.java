package com.wbkjcom.category.service;



import com.wbkjcom.category.model.Category;

import java.util.List;

/**
 *
 * com.wbkjcom.goods.service
 * 2017-12-28-14:56
 * 2017
 *  on 2017-12-28.
 */
public interface CategoryService {
    Category findOne(Long id);

    List<Category> findByLevelAndName(Integer level, String name);

    Category save(Category category);
}
