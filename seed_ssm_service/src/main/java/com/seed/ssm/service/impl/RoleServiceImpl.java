package com.seed.ssm.service.impl;

import com.seed.ssm.dao.IPermissionDao;
import com.seed.ssm.dao.IRoleDao;
import com.seed.ssm.dao.IUserInfoDao;
import com.seed.ssm.domain.Role;
import com.seed.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(int id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public void deleteRoleById(int id) throws Exception {
        //users_role
        roleDao.deleteFromUsers_RoleByRoleId(id);
        //role_permission
        roleDao.deleteFromRole_PermissionByRoleId(id);
        //role
        roleDao.deleteRoleById(id);
    }

    @Override
    public List<Role> findByNotInUserId(int UserId) throws Exception {
        return roleDao.findByNotInUserId(UserId);
    }

    @Override
    public void addPermissionToRole(int roleId, int[] ids) throws Exception {
        for (int id : ids) {
            roleDao.addPermissionToRole(roleId,id);
        }
    }
}
