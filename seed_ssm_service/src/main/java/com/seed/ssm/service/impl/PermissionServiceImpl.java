package com.seed.ssm.service.impl;

import com.seed.ssm.dao.IPermissionDao;
import com.seed.ssm.domain.Permission;
import com.seed.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(int id) throws Exception {
        return permissionDao.findById(id);
    }

    @Override
    public void deletePermissionById(int id) throws Exception {
        permissionDao.deleteFromRole_PermissionByPermissionId(id);
        permissionDao.deletePermissionById(id);
    }

    @Override
    public List<Permission> findByNotInRoleId(int roleId) throws Exception {
        return permissionDao.findByNotInRoleId(roleId);
    }
}
