package com.lingting.travel.service;

import com.lingting.travel.domain.Orders;

import java.util.List;

public interface OrderService {

    /** 查询所有订单 */
    public List<Orders> findAll(Integer pageNum, Integer pageSize);

    /** 根据id查询订单详情 */
    public Orders findById(String id) throws Exception;
}
