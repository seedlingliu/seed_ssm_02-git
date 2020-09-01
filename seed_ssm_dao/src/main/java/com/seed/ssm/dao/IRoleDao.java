package com.seed.ssm.dao;

import com.seed.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {
    @Select(" SELECT * FROM role WHERE id IN (SELECT roleId FROM users_role WHERE usersId = #{usersId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissionList",column = "id",javaType = List.class,many = @Many(select = "com.seed.ssm.dao.IPermissionDao.findByRoleId"))
    })
    List<Role> findByUsersId(String usersId) throws Exception;

    @Select("select * from role")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissionList",column = "id",javaType = List.class,many = @Many(select = "com.seed.ssm.dao.IPermissionDao.findByRoleId"))
    })
    List<Role> findAll() throws Exception;

    @Insert("INSERT INTO role(roleName,roleDesc) VALUES(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissionList",column = "id",javaType = List.class,many = @Many(select = "com.seed.ssm.dao.IPermissionDao.findByRoleId"))
    })
    Role findById(int id) throws Exception;

    @Delete("DELETE FROM Users_Role WHERE roleId = #{id}")
    void deleteFromUsers_RoleByRoleId(int id) throws Exception;

    @Delete("DELETE FROM Role_Permission WHERE roleId = #{id}")
    void deleteFromRole_PermissionByRoleId(int id) throws Exception;

    @Delete("DELETE FROM role WHERE id = #{id}")
    void deleteRoleById(int id) throws Exception;

    @Select("SELECT * FROM role WHERE id NOT IN (SELECT roleId FROM users_role WHERE usersId = #{UserId})")
    List<Role> findByNotInUserId(int UserId) throws Exception;

    @Insert("INSERT INTO role_permission(roleId,permissionId) VALUES(#{roleId},#{id})")
    void addPermissionToRole(@Param("roleId") int roleId,@Param("id") int id) throws Exception;
}
