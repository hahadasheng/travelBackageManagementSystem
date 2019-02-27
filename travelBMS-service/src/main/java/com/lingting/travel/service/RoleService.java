package com.lingting.travel.service;

import com.lingting.travel.domain.Role;

import java.util.List;

public interface RoleService {

    /** 查询所有角色 */
    public List<Role> findAll(Integer pageNum, Integer pageSize);

    /** 保存用户 */
    public Integer saveRole(Role role);

    /** 根据id查询 Role */
    public Role findById(String id);

    /** 更新用户信息 */
    public Integer updateRole(Role role);

    /** 角色可以添加的权限 */
    public Role permissionCanAddToRole(String roleId);

    /** 角色和权限关联 */
    public Integer roleConnectPermission(String roleId, String[] permissionId);
}
