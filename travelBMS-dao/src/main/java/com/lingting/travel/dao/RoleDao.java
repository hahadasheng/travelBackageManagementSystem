package com.lingting.travel.dao;

import com.lingting.travel.domain.Permission;
import com.lingting.travel.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    /** 根据userId查询users_role 的 roleId，再根据
     * roleId 查询role表中的信息*/
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    public List<Role> findByUserId(String userId);


    /** 根据userId查询Role并查询出Permission */
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = List.class,
                    many = @Many(select = "com.lingting.travel.dao.PermissionDao.findByRoleId"))
    })
    public List<Role> findByUserIdDetail(String userId);


    /** 分页查询所有角色信息 */
    @Select("select * from role")
    public List<Role> findAll();


    /** 创建一个Role角色 */
    @Insert("insert into role(roleName, roleDesc) values(#{roleName}, #{roleDesc})")
    public Integer saveRole(Role role);

    /** 根据id查询role角色 */
    @Select("select * from role where id = #{id}")
    public Role findById(String id);

    /** 更新用户信息 */
    @UpdateProvider(type = RoleDaoProvider.class, method = "updateSQL")
    public Integer updateRole(Role role);


    class RoleDaoProvider {
        public String updateSQL(Role role) {
            StringBuffer sb = new StringBuffer("update role set ");
            int flag = 0;

            if(role.getRoleName() != null) {
                sb.append("roleName = #{roleName} ");
                flag ++;
            }

            if (role.getRoleDesc() != null) {
                if (flag != 0) {
                    sb.append(", ");
                }
                sb.append("roleDesc = #{roleDesc} ");
            }
            sb.append("where id = #{id} ");
            return sb.toString();
        }
    }

    /** 根据 userId 在 users_role中查询没有被关联的roleId,然后再role中将这些信息查询出来 */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    public List<Role> findRoleCanAddToUser(String userId);

    /** 角色可以添加的权限 */
    @Select("select * from role where id = #{roleId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = List.class,
                    many = @Many(select = "com.lingting.travel.dao.PermissionDao.findByRoleIdNoConnect"))
    })
    public Role permissionCanAddToRole(String roleId);

    /** 角色和权限关联 */
    @Insert("insert into role_permission(permissionId, roleId) values(#{permissionId}, #{roleId})")
    public Integer roleConnectPermission(@Param("roleId") String roleId, @Param("permissionId") String perId);

}
