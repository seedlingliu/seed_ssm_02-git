package com.seed.ssm.dao;

import com.seed.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {
    @Select("SELECT * FROM permission WHERE id IN (SELECT permissionId FROM role_permission WHERE roleId = #{roleId}) ")
    List<Permission> findByRoleId(int roleId) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("INSERT INTO permission(permissionName,url) VALUES(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;

    @Select("select * from permission where id = #{id}")
    Permission findById(int id) throws Exception;

    @Delete("DELETE FROM Role_Permission WHERE permissionId = #{id}")
    void deleteFromRole_PermissionByPermissionId(int id);

    @Delete("DELETE FROM permission WHERE id = #{id}")
    void deletePermissionById(int id);

    @Select("SELECT * FROM permission WHERE id NOT IN (SELECT permissionId FROM role_permission WHERE roleId = #{roleId})")
    List<Permission> findByNotInRoleId(int roleId) throws Exception;
}
