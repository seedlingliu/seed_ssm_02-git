package com.seed.ssm.service;

import com.seed.ssm.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(int id) throws Exception;

    void deleteRoleById(int id) throws Exception;

    List<Role> findByNotInUserId(int UserId) throws Exception;

    void addPermissionToRole(int roleId, int[] ids) throws Exception;
}
