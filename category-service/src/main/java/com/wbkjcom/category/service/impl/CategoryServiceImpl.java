package com.wbkjcom.category.service.impl;

import com.wbkjcom.category.model.Category;
import com.wbkjcom.commons.api.ResourceNotFoundException;
import com.wbkjcom.category.repository.CategoryRepository;
import com.wbkjcom.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * com.wbkjcom.goods.service.impl
 * 2017-12-28-14:57
 * 2017
 *  on 2017-12-28.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository goodRepository;

    @Override
    public Category findOne(Long id) {
        Category category = goodRepository.findOne(id);
        if (category == null)
            throw new ResourceNotFoundException(id);
        return category;
    }

    @Override
    public List<Category> findByLevelAndName(Integer level, String name) {
        return goodRepository.findByLevelAndName(level, name);
    }

    @Override
    public Category save(Category category) {
        return goodRepository.save(category);
    }
}
