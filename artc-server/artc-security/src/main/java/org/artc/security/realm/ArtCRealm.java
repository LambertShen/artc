package org.artc.security.realm;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.artc.security.entity.Permission;
import org.artc.security.entity.Role;
import org.artc.security.entity.User;
import org.artc.security.jwt.JwtToken;
import org.artc.security.jwt.JwtUtil;
import org.artc.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

public class ArtCRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User userPrincipal = (User) principals.getPrimaryPrincipal();
        if (userPrincipal == null) {
            return null;
        }
        User user = userService.findUserByLoginName(userPrincipal.getLoginName());
        Set<Role> roles = user.getRoles();
        Set<String> roleStrings = roles.stream().map(Role::getCode).collect(Collectors.toSet());
        info.addRoles(roleStrings);
        for (Role role : roles) {
            Set<Permission> permissions = role.getPermissions();
            Set<String> permissionStrings = permissions.stream().map(Permission::getCode).collect(Collectors.toSet());
            info.addStringPermissions(permissionStrings);
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = authenticationToken.getCredentials().toString();
        String loginName = JwtUtil.getLoginName(token);
        if (StringUtils.isEmpty(loginName)) {
            throw new AuthenticationException("token 非法无效！");
        }
        User user = userService.findUserByLoginName(loginName);
        if(user == null) {
            throw new AuthenticationException("用户不存在！");
        }
        if(user.getState() == 0) {
            throw new AuthenticationException("账号已被禁用，请联系管理员！");
        }
        return new SimpleAuthenticationInfo(user, token, ByteSource.Util.bytes(user.getSalt()), getName());
    }
}