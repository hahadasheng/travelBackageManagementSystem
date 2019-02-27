package com.lingting.travel.service;

import com.lingting.travel.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    /** 查询所有用户信息 */
    public List<UserInfo> findAll(Integer pageNum, Integer pageSize);

    /** 插入一个用户信息，需要进行加密 */
    public Integer saveUser(UserInfo userInfo);

    /** 根据用户id查询用户详情 */
    public UserInfo findById(String userId);

    /** 根据id查询用户可以添加的角色信息 */
    public UserInfo roleCanAddToUser(String userId);

    /** 用户关联角色 */
    public Integer userConnectRoles(String userId, String[] rolesId);

}
