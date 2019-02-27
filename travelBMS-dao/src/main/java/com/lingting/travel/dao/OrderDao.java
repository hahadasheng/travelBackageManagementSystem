package com.lingting.travel.dao;

import com.lingting.travel.domain.Member;
import com.lingting.travel.domain.Orders;
import com.lingting.travel.domain.Product;
import com.lingting.travel.domain.Traveller;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 订单管理系统 */
@Repository
public interface OrderDao {

    /** 查询所有订单信息 */
    @Select("select * from orders")
    @Results({
            @Result(id= true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product",
                    javaType = Product.class,
                    one=@One(select = "com.lingting.travel.dao.ProductDao.findById"))
    })
    public List<Orders> findAll();


    /** 查询订单详情 */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id= true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product",
                    javaType = Product.class,
                    one=@One(select = "com.lingting.travel.dao.ProductDao.findById")),
            @Result(column = "memberId", property = "member",
                    javaType = Member.class,
                    one = @One(select = "com.lingting.travel.dao.MemberDao.findById")),
            @Result(column = "id", property = "travellers",
                    many = @Many(select = "com.lingting.travel.dao.TravellerDao.findByOrderId")
            )
    })
    public Orders findById(String id) throws Exception;
}
