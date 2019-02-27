package com.lingting.travel.dao;

import com.lingting.travel.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PermissionDao {

    /** 根据roleId查询permission */
    @Select("select * from permission where id in " +
            "(select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findByRoleId(String roleId);

    /** 分页查询permission */
    @Select("select * from permission")
    public List<Permission> findAll();

    /** 添加权限 permission */
    @Insert("insert into permission(permissionName, url) values(#{permissionName}, #{url})")
    public Integer savePermission(Permission permission);

    /** 根据roleId查询没有关联的permission */
    @Select("select * from permission where id not in " +
            "(select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findByRoleIdNoConnect(String roleId);
}
