package com.wbkjcom.wbkj.controller;

import com.wbkjcom.commons.lib.Lib;
import com.wbkjcom.commons.util.GetInfoUtil;
import com.wbkjcom.commons.vo.OrderVo;
import com.wbkjcom.wbkj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/order")
public class OrderController{

    @Autowired OrderService orderService;

    @PostMapping("/addOrder")
    public Object addOrder(OrderVo orderVo,HttpSession session) {
        Long userId= (Long) session.getAttribute("userId");
        if(userId==null){
            return Lib.GetMapData(3, "你还未登录");
        }else{
            orderVo.setUserId(userId);
            orderService.addOrder(orderVo);
            return GetInfoUtil.success();
        }
    }

}
