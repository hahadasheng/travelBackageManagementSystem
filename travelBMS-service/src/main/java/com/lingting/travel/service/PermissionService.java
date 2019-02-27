package com.lingting.travel.service;

import com.lingting.travel.domain.Permission;

import java.util.List;

public interface PermissionService {

    /** 分页查询 permission信息 */
    public List<Permission> findAll(Integer pageNum, Integer pageSize);

    /** 添加权限 permission */
    public Integer savePermission(Permission permission);
}
