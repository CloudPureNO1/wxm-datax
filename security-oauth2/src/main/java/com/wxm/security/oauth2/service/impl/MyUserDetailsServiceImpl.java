package com.wxm.security.oauth2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxm.base.exception.DbSvcException;
import com.wxm.druid.entity.master.WxmRole;
import com.wxm.druid.entity.master.WxmUser;
import com.wxm.security.oauth2.bean.MockData.MyUser;
import com.wxm.service.db.master.impl.WxmRoleService;
import com.wxm.service.db.master.impl.WxmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2021/4/29 13:31
 * @since 1.0.0
 */
@Service("myUserDetailService")
public class MyUserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private WxmUserService userService;
    @Autowired
    private WxmRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 模拟一个用户，替代数据库获取逻辑
        QueryWrapper<WxmUser> queryWrapper = new QueryWrapper<>();
        List<WxmUser> userList = userService.list(queryWrapper.eq("username", username));
        if (CollectionUtils.isEmpty(userList)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (userList.size() > 1) {
            throw new RuntimeException("存在多条用户信息");
        }
        WxmUser userModel = userList.get(0);
        MyUser user = new MyUser();
        user.setUsername(username);
        user.setPassword(userModel.getPasswd());
        user.setEnabled(("0".equals(userModel.getUserStatus()) || "2".equals(userModel.getUserStatus())) ? false : true);
        user.setAccountNonLocked("3".equals(userModel.getUserStatus()) ? false : true);
        user.setAccountNonExpired(userModel.getAccountExpiredTime()==null?true:userModel.getAccountExpiredTime().getTime() >= System.currentTimeMillis());
        user.setCredentialsNonExpired(userModel.getPasswordExpiredTime()==null?true:userModel.getPasswordExpiredTime().getTime() >= System.currentTimeMillis());
        //user.setPassword(this.passwordEncoder.encode("123456"));

        List<WxmRole> roleList = null;
        try {
            roleList = roleService.findRolesByUsername(username);
        } catch (DbSvcException e) {
            throw new RuntimeException("获取用户授权信息失败：" + e.getMessage());
        }
        if (CollectionUtils.isEmpty(roleList)) {
            throw new RuntimeException("用户没有授权");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        roleList.stream().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleType())));
        authorities.add(new SimpleGrantedAuthority("login"));
        authorities.add(new SimpleGrantedAuthority("token"));
        return new User(username, user.getPassword(), user.isEnabled(),
                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), authorities);
    }
}
