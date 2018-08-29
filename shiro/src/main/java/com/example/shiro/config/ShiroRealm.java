package com.example.shiro.config;

import com.example.shiro.business.Permission;
import com.example.shiro.business.Role;
import com.example.shiro.business.UserInfo;
import com.example.shiro.business.UserInfoDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author WangKun
 * @create 2018-08-27
 * @desc
 **/
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoDao userService;

    /**
     * 功能描述: 角色权限和对应权限添加
     *
     * @param:
     * @return:
     * @auther: WangKun
     * @date: 2018-08-27 17:38
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserInfo usertoken = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        List<UserInfo> list = userService.findByNameAndPassword(usertoken.getName(), usertoken.getPassword());
        if (CollectionUtils.isEmpty(list))
            return null;
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (UserInfo userInfo : list) {
//            for (Role role : userInfo.getRoles()) {
//                //添加角色
//                simpleAuthorizationInfo.addRole(role.getRoleName());
//                for (Permission permission : role.getPermissions()) {
//                    //添加权限
//                    simpleAuthorizationInfo.addStringPermission(permission.getPermission());
//                }
//            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 功能描述:用户认证
     *
     * @param:
     * @return:
     * @auther: WangKun
     * @date: 2018-08-27 17:38
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        List<UserInfo> list = userService.findByNameAndPassword(token.getUsername(), String.valueOf(token.getPassword()));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            AuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
