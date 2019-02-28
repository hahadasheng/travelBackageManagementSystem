package com.lingting.travel.service;

import com.lingting.travel.domain.Permission;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PermissionService {

    /** 分页查询 permission信息 */
    public List<Permission> findAll(Integer pageNum, Integer pageSize);

    /** 添加权限 permission */
    public Integer savePermission(Permission permission);

    /** 动态权限验证 */
    public Boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
