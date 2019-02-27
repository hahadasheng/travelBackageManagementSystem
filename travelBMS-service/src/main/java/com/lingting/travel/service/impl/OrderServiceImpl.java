package com.lingting.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingting.travel.dao.OrderDao;
import com.lingting.travel.domain.Orders;
import com.lingting.travel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    /** 查询所有订单信息 */
    @Override
    public List<Orders> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return orderDao.findAll();
    }

    /** 根据id查询订单详情 */
    @Override
    public Orders findById(String id) throws Exception {
        return orderDao.findById(id);
    }
}
