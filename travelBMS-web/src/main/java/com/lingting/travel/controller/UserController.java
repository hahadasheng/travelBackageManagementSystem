package com.lingting.travel.controller;

import com.github.pagehelper.PageInfo;
import com.lingting.travel.domain.ResponseMsg;
import com.lingting.travel.domain.UserInfo;
import com.lingting.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /** 增删改结果处理 */
    private ResponseMsg handlerResponseMsg(Integer res) {
        ResponseMsg responseMsg = new ResponseMsg();
        if(res <= 0) {
            responseMsg.setStatus(501);
            responseMsg.setDesc("用户保存失败！");
        }
        return responseMsg;
    }

    /** 查询所有的用户信息 */
    @RequestMapping("/findAll")
    public @ResponseBody PageInfo<UserInfo> findAll(
            @RequestParam(name = "pageNum", required = true, defaultValue = "1")Integer pageNum,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10")Integer pageSize
    ) {
        List<UserInfo> users = userService.findAll(pageNum, pageSize);
        return new PageInfo<>(users);
    }

    /** 新增用户,密码需要进行处理！ */
    @RequestMapping("/saveUser")
    public @ResponseBody ResponseMsg saveUser(UserInfo userInfo) {
        Integer res = userService.saveUser(userInfo);
        return handlerResponseMsg(res);
    }

    /** 根据用户id查询用户详情 */
    @RequestMapping("/findById")
    public @ResponseBody UserInfo findById(String userId) {
        return userService.findById(userId);
    }

    /** 根据id查询用户可以添加的角色信息 */
    @RequestMapping("/roleCanAddToUser")
    public @ResponseBody UserInfo roleCanAddToUser(String userId) {
        return userService.roleCanAddToUser(userId);
    }

    /** 用户关联角色 */
    @RequestMapping("/userConnectRoles")
    public @ResponseBody ResponseMsg userConnectRoles(String userId, String[] rolesId) {
        Integer res = userService.userConnectRoles(userId, rolesId);
        return handlerResponseMsg(res);
    }

}
