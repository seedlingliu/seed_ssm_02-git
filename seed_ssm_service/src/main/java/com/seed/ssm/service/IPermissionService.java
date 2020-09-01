package com.seed.ssm.service;

import com.seed.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;

    Permission findById(int id) throws Exception;

    void deletePermissionById(int id) throws Exception;

    List<Permission> findByNotInRoleId(int roleId) throws Exception;
}
