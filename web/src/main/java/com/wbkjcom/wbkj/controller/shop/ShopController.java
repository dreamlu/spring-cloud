package com.wbkjcom.wbkj.controller.shop;

import com.wbkjcom.commons.lib.Lib;
import com.wbkjcom.shop.model.Shop;
import com.wbkjcom.wbkj.service.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping(value="/shops")
public class ShopController {

    @Autowired
    ShopService shopService;

    /**
     * 创建
     * @param shop
     * @return
     */
    @PostMapping("/create")
    @ResponseBody
    public Object create(Shop shop) {
        Shop u = shopService.create(shop);
        return Lib.GetMapData(Lib.CodeCreate, Lib.MsgCreate);
    }

    /**
     * 修改
     * @param shop
     * @return
     */

    @PutMapping("/update")
    @ResponseBody
    public Object update(Shop shop) {
        Shop u = shopService.update(shop);
        return Lib.GetMapData(Lib.CodeUpdate, Lib.MsgUpdate);
    }

    /**
     * 删除
     * @param id
     * @return
     */

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object delete(@PathVariable( value = "id")Long id) {
        if(shopService.delete(id)) {
            return Lib.GetMapData(Lib.CodeDelete, Lib.MsgDelete);
        }
        return Lib.GetMapData(Lib.CodeDelete, Lib.MsgDelete);
    }

    /**
     * 根据id
     * @param id
     * @return
     */

    @GetMapping("/id")
    @ResponseBody
    public Object getById(Long id) {
        Shop u = shopService.getById(id);
        return Lib.GetMapData(Lib.CodeSuccess, Lib.MsgSuccess, u);
    }

    /**
     * 分页搜索
     * @param shop
     * @return
     */

    @GetMapping("/search")
    @ResponseBody
    public Object search(@RequestParam(required = false) Map<String, Object> params, Shop shop) {
        return shopService.search(params, shop);
    }
}
