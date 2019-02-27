package com.lingting.travel.service;

import com.lingting.travel.domain.Product;

import java.util.List;

public interface ProductService {

    public Product findById(String id);

    /** 查询所有的商品 分页查询 */
    public List<Product> findAll(Integer pageNum, Integer pageSize);

    /** 插入一条数据 */
    public Integer saveProduct(Product product);
}
