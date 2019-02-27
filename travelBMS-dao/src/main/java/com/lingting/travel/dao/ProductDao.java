package com.lingting.travel.dao;

import com.lingting.travel.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public interface ProductDao {

    /** 根据id查询产品 */
    @Select("select * from product where id = #{id}")
    public Product findById(String id);

    /** 查询全部商品 */
    @Select("select * from product")
    public List<Product> findAll();

    /** 插入一条数据 */
    @Insert("insert into product" +
            "(productNum, productName, cityName, departureTime, productPrice, productDesc, productStatus)" +
            " values(#{productNum}, #{productName}, #{cityName}, #{departureTime}, #{productPrice}, #{productDesc}, #{productStatus})")
    public Integer saveProduct(Product product);

}
