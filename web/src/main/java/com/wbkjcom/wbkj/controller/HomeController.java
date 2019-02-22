package com.wbkjcom.wbkj.controller;

import com.wbkjcom.category.model.Category;
import com.wbkjcom.wbkj.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 *
 * com.wbkjcom.wbkj.controller
 * 2017-12-28-15:16
 * 2017
 *  on 2017-12-28.
 */
@Controller
@RequestMapping("/")
public class HomeController{

    @Autowired CategoryService categoryService;


    @RequestMapping(method = RequestMethod.GET)
    public String home(Map<String, Object> model,HttpSession session) {
        Category category = categoryService.getCategory(1L);
        model.put("category", category);
        return "home";
    }

    @GetMapping("{url}")
    public String url(@PathVariable String url){
        return url;
    }

}
