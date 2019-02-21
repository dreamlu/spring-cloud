package com.kedacom.keda.controller;

import com.kedacom.commons.api.Result;
import com.kedacom.commons.util.ResultUtil;
import com.kedacom.commons.vo.OrderVo;
import com.kedacom.keda.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/order")
public class OrderController{

    @Autowired OrderService orderService;

    @PostMapping("/addOrder")
    public Result addOrder(OrderVo orderVo,HttpSession session) {
        Long userId= (Long) session.getAttribute("userId");
        if(userId==null){
            return ResultUtil.error(3,"你还未登录");
        }else{
            orderVo.setUserId(userId);
            orderService.addOrder(orderVo);
            return ResultUtil.success();
        }
    }

}
