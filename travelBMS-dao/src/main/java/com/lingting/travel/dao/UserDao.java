package com.lingting.travel.dao;

import com.lingting.travel.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    /** 根据用户名查询用户信息 */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = List.class,
                many = @Many(select = "com.lingting.travel.dao.RoleDao.findByUserId"))
    })
    public UserInfo findByUsername(String username);

    /** 查询所有 用户信息 */
    @Select("select id,username,email,phoneNum,status from users")
    public List<UserInfo> findAll();

    /** 插入一条用户信息 */
    @Insert("insert into users(email,username,password,phoneNum,status) " +
            "values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public Integer saveUser(UserInfo userInfo);

    /** 根据id查询用户信息 */
    @Select("select id,username,email,phoneNum,status from users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.lingting.travel.dao.RoleDao.findByUserIdDetail"))
    })
    public UserInfo findById(String id);


    /** 根据id查询用户可以添加的角色信息 */
    @Select("select * from users where id = #{userId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.lingting.travel.dao.RoleDao.findRoleCanAddToUser"))
    })
    public UserInfo roleCanAddToUser(String userId);

    /** 用户关联角色 */
    @Insert("insert into users_role values(#{userId}, #{roleId})")
    public Integer userConnectRoles(@Param("userId") String userId, @Param("roleId") String roleId);
}
