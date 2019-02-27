package com.lingting.travel.controller;

import com.github.pagehelper.PageInfo;
import com.lingting.travel.domain.Product;
import com.lingting.travel.domain.ResponseMsg;
import com.lingting.travel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /** 查询产品 分页 */
    @RequestMapping("/findAll")
    public @ResponseBody ResponseMsg findAll(
            @RequestParam(name = "pageNum", required = true, defaultValue = "1")Integer pageNum,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10")Integer pageSize
    ) {
        List<Product> products = productService.findAll(pageNum, pageSize);
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setMsg(new PageInfo<Product>(products));
        return responseMsg;
    }

    // 新建一个产品
    @RequestMapping("/saveProduct")
    public @ResponseBody ResponseMsg saveProduct(Product product) {
        Integer res = productService.saveProduct(product);
        ResponseMsg responseMsg = new ResponseMsg();

        if (res <= 0) {
            responseMsg.setStatus(500);
            responseMsg.setDesc("error");
        }

        return responseMsg;
    }
}
