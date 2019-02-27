package com.lingting.travel.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingting.travel.domain.Orders;
import com.lingting.travel.domain.ResponseMsg;
import com.lingting.travel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /** 查询所有订单信息，包含分页功能 */
    @RequestMapping("/findAll")
    public @ResponseBody ResponseMsg findAll(
            @RequestParam(name = "pageNum", required = true, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") Integer pageSize
            ) throws Exception {
        List<Orders> orders = orderService.findAll(pageNum, pageSize);
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setMsg(new PageInfo<Orders>(orders));
        return responseMsg;
    }

    /** 根据id查询订单详情 */
    @RequestMapping("/findById")
    public @ResponseBody Orders findById(@RequestParam(name = "id", required = true)String id) throws Exception {
        return orderService.findById(id);
    }
}
