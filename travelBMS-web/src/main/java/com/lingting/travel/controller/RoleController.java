package com.lingting.travel.controller;

import com.github.pagehelper.PageInfo;
import com.lingting.travel.domain.ResponseMsg;
import com.lingting.travel.domain.Role;
import com.lingting.travel.service.RoleService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /** 增删改结果处理 */
    private ResponseMsg handlerResponseMsg(Integer res) {
        ResponseMsg responseMsg = new ResponseMsg();
        if(res <= 0) {
            responseMsg.setStatus(501);
            responseMsg.setDesc("用户保存失败！");
        }
        return responseMsg;
    }

    /** 分页查询角色信息 */
    @RequestMapping("/findAll")
    public @ResponseBody PageInfo<Role> findAll(
            @RequestParam(name = "pageNum", required = true, defaultValue = "1")Integer pageNum,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10")Integer pageSize
            ) {

        List<Role> roles = roleService.findAll(pageNum, pageSize);
        return new PageInfo<Role>(roles);
    }

    /** 保存 role 信息 */
    @RequestMapping("/saveRole")
    public @ResponseBody ResponseMsg saveRole(Role role) {
        Integer res = roleService.saveRole(role);
        return handlerResponseMsg(res);
    }

    /** 修改 role 的信息 */
    @RequestMapping("/updateRole")
    public @ResponseBody ResponseMsg updateRole(Role role) {
        Integer res = roleService.updateRole(role);
        return handlerResponseMsg(res);
    }

    /** 角色可以添加的权限 */
    @RequestMapping("/permissionCanAddToRole")
    public @ResponseBody Role permissionCanAddToRole(String roleId) {
        return roleService.permissionCanAddToRole(roleId);
    }

    /** 角色和权限关联 */
    @RequestMapping("/roleConnectPermission")
    public @ResponseBody ResponseMsg roleConnectPermission(String roleId, String[] permissionId) {
        Integer res = roleService.roleConnectPermission(roleId, permissionId);
        return handlerResponseMsg(res);
    }

}
