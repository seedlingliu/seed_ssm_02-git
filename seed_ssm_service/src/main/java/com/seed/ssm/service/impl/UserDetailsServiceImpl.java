package com.seed.ssm.service.impl;

import com.seed.ssm.dao.IUserInfoDao;
import com.seed.ssm.domain.Role;
import com.seed.ssm.domain.UserInfo;
import com.seed.ssm.service.IUserDetailsService;
import javafx.beans.binding.ListBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserDetailsServiceImpl implements IUserDetailsService {

    @Autowired
    private IUserInfoDao userInfoDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            /**
             * 当userInfoDao.loadUserByUsername(),使用这个方法名时，会报错，故而修改了方法名为FindByByUsername(),这样就可以正常运行了
             */
            userInfo = userInfoDao.FindByByUsername(username);

            ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
            List<Role> roleList = userInfo != null ? userInfo.getRoleList() : null;
            if (userInfo != null && roleList != null && !roleList.isEmpty()) {
                for (Role role : roleList) {
                    simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
                }
            }

//            User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), simpleGrantedAuthorities);
            User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() != 0,true,true,true,simpleGrantedAuthorities);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        return userInfoDao.findAll();
    }

    @Override
    public UserInfo findById(int id) throws Exception {
        return userInfoDao.findById(id);
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userInfoDao.save(userInfo);
    }

    @Override
    public void addRoleToUser(int userId, int[] roleIds) throws Exception {
        for (int roleId : roleIds) {
            userInfoDao.addRoleToUser(userId,roleId);
        }
    }
}
