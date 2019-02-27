package com.lingting.travel.controller;

import com.github.pagehelper.PageInfo;
import com.lingting.travel.domain.Permission;
import com.lingting.travel.domain.ResponseMsg;
import com.lingting.travel.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /** 增删改结果处理 */
    private ResponseMsg handlerResponseMsg(Integer res) {
        ResponseMsg responseMsg = new ResponseMsg();
        if(res <= 0) {
            responseMsg.setStatus(501);
            responseMsg.setDesc("用户保存失败！");
        }
        return responseMsg;
    }

    /** 分页查询 permission */
    @RequestMapping("/findAll")
    public @ResponseBody PageInfo<Permission> findAll(
            @RequestParam(name = "pageNum", required = true, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") Integer pageSize
    ) {
        List<Permission> permissions = permissionService.findAll(pageNum, pageSize);
        return new PageInfo<Permission>(permissions);
    }

    /** 添加权限信息 permission */
    @RequestMapping("/savePermission")
    public @ResponseBody ResponseMsg savePermission(Permission permission) {
        Integer res = permissionService.savePermission(permission);
        return handlerResponseMsg(res);
    }

}
