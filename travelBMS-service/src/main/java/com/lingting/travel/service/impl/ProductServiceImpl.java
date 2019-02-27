package com.lingting.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.lingting.travel.dao.ProductDao;
import com.lingting.travel.domain.Product;
import com.lingting.travel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findById(String id) {
        return productDao.findById(id);
    }

    /** 分页查询所有商品 */
    @Override
    public List<Product> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return productDao.findAll();
    }

    /** 插入一条数据 */
    @Override
    public Integer saveProduct(Product product) {
        return productDao.saveProduct(product);
    }
}
