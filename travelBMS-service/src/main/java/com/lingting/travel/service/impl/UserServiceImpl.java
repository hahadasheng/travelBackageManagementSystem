package com.lingting.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.lingting.travel.dao.UserDao;
import com.lingting.travel.domain.Role;
import com.lingting.travel.domain.UserInfo;
import com.lingting.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /** 框架提供的加密工具类，每次加密底层都会产生一个“盐”
     * 每次加密的结果都不同，比md5加密方式更加安全；
     * 虽然每次加密都不同，但是它们之间还是有相互联系的！*/
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {

        UserInfo userInfo = userDao.findByUsername(username);
        List<Role> roles = userInfo.getRoles();
        List<SimpleGrantedAuthority> authorities = getAuthority(roles);
        /** 【注意】：下方使用的User类是框架提供的！
         org.springframework.security.core.userdetails.User
         是UserDetails的实现类，Spring Security 框架从数据库中查询认证信息
         调用的就是这个接口中的方法，既然是框架作者规定好的，开发者照着规则
         将信息封装到这个接口实现类中即可。。。本例使用的构造函数如下：
         public User(
                String username, 用户名
                String password, 密码(如果密码没有加密，需要前缀识别，"{noop}")
                boolean enabled, 用户是否可用？true表示能认证，反之不能！
                boolean accountNonExpired, 账户没有过期？
                boolean credentialsNonExpired, 证书没有过期？
                boolean accountNonLocked, 账户没有被锁？
                Collection<? extends GrantedAuthority> authorities 当前用户拥有的角色
                         GrantedAuthority 接口提供 getAuthority 抽象方法；框架提供了一个实现类
                         SimpleGrantedAuthority；将查询的角色封装到此对象，
                          然后丢给Spring Security 框架 就可以了
         ) ； 注意：前缀不要加上 {noop} !*/
        User user = new User(
                userInfo.getUsername(),
                userInfo.getPassword(),
                userInfo.getStatus() != 0,
                true, true, true,
                authorities);
        return user;
    }

    /** 封装 角色 到Spring Security框架提供的实现类中！ */
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }

        return authorities;
    }

    /** 查询所有用户信息 */
    @Override
    public List<UserInfo> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userDao.findAll();
    }

    /** 插入一条用户信息，密码需要进行加密 */
    @Override
    public Integer saveUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userDao.saveUser(userInfo);
    }

    /** 根据用户id查询用户详情 */
    @Override
    public UserInfo findById(String userId) {
        return userDao.findById(userId);
    }

    /** 根据id查询用户可以添加的角色信息 */
    @Override
    public UserInfo roleCanAddToUser(String userId) {
        return userDao.roleCanAddToUser(userId);
    }

    /** 用户关联角色 */
    @Override
    public Integer userConnectRoles(String userId, String[] rolesId) {
        Integer res = 0;
        for (String roleId : rolesId) {
            res += userDao.userConnectRoles(userId, roleId);

        }
        return res;
    }
}
