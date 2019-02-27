package com.lingting.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.lingting.travel.dao.RoleDao;
import com.lingting.travel.domain.Role;
import com.lingting.travel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /** 查询所有角色信息 */
    @Override
    public List<Role> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return roleDao.findAll();
    }

    /** 保存role */
    @Override
    public Integer saveRole(Role role) {
        return roleDao.saveRole(role);
    }

    /** 根据id查询 Role */

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    /** 更新用户信息 */
    @Override
    public Integer updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    /** 角色可以添加的权限 */
    @Override
    public Role permissionCanAddToRole(String roleId) {
        return roleDao.permissionCanAddToRole(roleId);
    }

    /** 角色和权限关联 */
    @Override
    public Integer roleConnectPermission(String roleId, String[] permissionId) {
        Integer res = 0;
        for (String perId : permissionId) {
            res += roleDao.roleConnectPermission(roleId, perId);
        }
        return res;
    }
}
