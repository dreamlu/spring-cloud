package com.wbkjcom.wbkj.controller;

import com.wbkjcom.category.model.Category;
import com.wbkjcom.wbkj.service.CategoryService;
import com.wbkjcom.wbkj.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 *
 * com.wbkjcom.wbkj.controller
 * 2018-01-02-11:46
 * 2018
 *  on 2018-01-02.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired CategoryService categoryService;

    @GetMapping("/introduction/{id}")
    public String home(Map<String, Object> model,@PathVariable long id) {
        Category category = categoryService.getCategory(id);

        model.put("category", category);

        return "introduction";
    }
}
