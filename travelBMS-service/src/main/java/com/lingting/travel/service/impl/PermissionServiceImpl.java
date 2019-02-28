package com.lingting.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.lingting.travel.dao.PermissionDao;
import com.lingting.travel.domain.Permission;
import com.lingting.travel.domain.ResponseMsg;
import com.lingting.travel.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component("permissionService")
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


    /** 动态权限验证 */
    @Override
    public Boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // 拿到域中的用户信息对象！
        Object principal = authentication.getPrincipal();

        // 要求为Spring Security框架提供的 User 接口实现类 UserDetails
        if(principal instanceof UserDetails) {

            // 获取 用户名 信息、注意，是用户名，不是角色名
            String username = ((UserDetails)principal).getUsername();

            // 当前用户可访问的所有的url
            Set<String> urls = getUserPermission(username);
            if (urls.contains(request.getRequestURI())) {
                return true;
            }
        }
        return false;
    }

    /**
     模拟从数据库中根据对应的【username】拿到该用户
     的角色，以及角色拥有的访问权限的【uri】;实际项目中，
     需要将这些信息放在【redis】缓存中，查询时在redis中查找当对权限进行 增删改 时
     需要同时维护 redis中的对应数据！这里只是简单的模拟一下！
     */
    private Set<String> getUserPermission(String username) {

        // 存放用户角色以及对应的uri
        Map<String, Set<String>> map = new HashMap<>();

        // 006 角色
        Set<String> set006 = new HashSet<>();
        set006.add("/product/findAll");

        // 007 角色
        Set<String> set007 = new HashSet<>();
        set007.add("/order/findAll");

        // 将角色信息加入map中
        map.put("006", set006);
        map.put("007", set007);

        // 根据username 获取对应的 信息
        Set<String> urls = map.get(username);

        // 防止空指针异常
        return urls == null ? new HashSet<>() : urls;
    }
}
