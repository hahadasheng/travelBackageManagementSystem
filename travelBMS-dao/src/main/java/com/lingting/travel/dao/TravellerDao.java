package com.lingting.travel.dao;

import com.lingting.travel.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellerDao {

    /** 查询所有 */
    @Select("select * from traveller")
    public List<TravellerDao> findAll();

    /** 根据id查询 */
    @Select("select * from traveller where id= #{id}")
    public Traveller findById(String id);

    /** 根据ordersId进行查询 @@@*/
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{id})")
    public List<Traveller> findByOrderId(String id);
}
