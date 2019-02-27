package com.wbkjcom.wbkj.controller;

import com.wbkjcom.category.model.Category;
import com.wbkjcom.commons.lib.Lib;
import com.wbkjcom.commons.util.GetInfoUtil;
import com.wbkjcom.shop.model.User;
import com.wbkjcom.wbkj.service.CategoryService;
import com.wbkjcom.wbkj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping(value="/users")
public class UserController{

    @Autowired UserService userService;

    @Autowired CategoryService categoryService;

    /**
     * 用户登录
     * @param user
     * @param model
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Object login(User user,Model model,HttpSession session) {
        User u = userService.login(user);
        if(u.getPassword().equals(user.getPassword())){
            session.setAttribute("userId",u.getId());
            session.setAttribute("userName",u.getName());

//            Category category = categoryService.getCategory(1L);
//            // 楼层
//            model.addAttribute("category", category);
            return GetInfoUtil.success();
        }
        return Lib.MapCountErr;
    }

    @PostMapping("/register")
    @ResponseBody
    public Object register(User user,Map<String, Object> model) {
        if(userService.register(user)){
            Category category = categoryService.getCategory(1L);
            // 楼层
            model.put("category", category);
            return GetInfoUtil.success();
        }else{
            return Lib.GetMapData(2,"注册失败");
        }
    }
}
