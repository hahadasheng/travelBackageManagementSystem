package com.lingting.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.lingting.travel.dao.PermissionDao;
import com.lingting.travel.domain.Permission;
import com.lingting.travel.domain.ResponseMsg;
import com.lingting.travel.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /** 分页查询 permission信息 */
    @Override
    public List<Permission> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return permissionDao.findAll();
    }

    /** 添加权限 permission */
    @Override
    public Integer savePermission(Permission permission) {
        return permissionDao.savePermission(permission);
    }
}
