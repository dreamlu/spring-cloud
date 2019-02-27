package com.wbkjcom.wbkj.controller;

import com.wbkjcom.category.model.Category;
import com.wbkjcom.commons.api.GetInfoN;
import com.wbkjcom.commons.lib.Lib;
import com.wbkjcom.wbkj.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *
 * com.wbkjcom.wbkj.controller
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired CategoryService categoryService;

    @GetMapping("/introduction/{id}")
    public Object home(Map<String, Object> model,@PathVariable long id) {
        Category category = categoryService.getCategory(id);

        model.put("category", category);

        return new GetInfoN<Object>(Lib.CodeSuccess, Lib.MsgSuccess, model);
    }
}
