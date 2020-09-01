package com.seed.ssm.controller;

import com.seed.ssm.dao.IUserInfoDao;
import com.seed.ssm.domain.Role;
import com.seed.ssm.domain.UserInfo;
import com.seed.ssm.service.IRoleService;
import com.seed.ssm.service.IUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private IUserDetailsService userDetailsService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    @RolesAllowed("USER")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userDetailsService.findAll();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(required = true,name ="id",defaultValue = "1") Integer id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userDetailsService.findById(id);
        mv.addObject("user",user);
        mv.setViewName("user-show");
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        userDetailsService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(Integer id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userDetailsService.findById(id);
        List<Role> roleList = roleService.findByNotInUserId(id);
        mv.addObject("user",user);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId") int userId,@RequestParam(name = "ids") int[] ids) throws Exception {
        userDetailsService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }
}
