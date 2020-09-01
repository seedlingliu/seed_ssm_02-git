package com.seed.ssm.dao;

import com.seed.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserInfoDao {

    @Select("Select * from users where username = #{username} ")
    @Results({
            @Result(id = true, property = "email", column = "email"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roleList",column = "id",javaType = List.class,many = @Many(select = "com.seed.ssm.dao.IRoleDao.findByUsersId"))
    })
    UserInfo FindByByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, property = "email", column = "email"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roleList",column = "id",javaType = List.class,many = @Many(select = "com.seed.ssm.dao.IRoleDao.findByUsersId"))
    })
    UserInfo findById(int id) throws Exception;

    @Insert("INSERT INTO users(id,email,username,PASSWORD,phoneNum,status) VALUES (#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    @Insert("INSERT INTO users_role(usersId,roleId) VALUES(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") int userId,@Param("roleId") int roleId) throws Exception;
}
