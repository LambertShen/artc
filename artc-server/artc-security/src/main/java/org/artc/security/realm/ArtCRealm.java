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
import org.artc.core.entity.Menu;
import org.artc.core.entity.Role;
import org.artc.core.entity.User;
import org.artc.core.service.MenuService;
import org.artc.security.jwt.JwtToken;
import org.artc.security.jwt.JwtUtil;
import org.artc.core.service.RoleService;
import org.artc.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ArtCRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        User user = (User) principals.getPrimaryPrincipal();
        return user.getAdmin() == 1 || super.isPermitted(principals, permission);
    }

    @Override
    public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
        User user = (User) principals.getPrimaryPrincipal();
        return user.getAdmin() == 1 || super.hasRole(principals, roleIdentifier);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User userPrincipal = (User) principals.getPrimaryPrincipal();
        if (userPrincipal == null) {
            return null;
        }
        User user = userService.findByLoginName(userPrincipal.getLoginName());
        List<Role> roles = roleService.findByUserId(user.getId());
        List<String> roleCodes = roles.stream().map(Role::getCode).collect(Collectors.toList());
        info.addRoles(roleCodes);
        for(Role role : roles) {
            List<Menu> menus = menuService.findByRoleId(role.getId());
            List<String> permissions = menus.stream().map(Menu::getPermission).collect(Collectors.toList());
            info.addStringPermissions(permissions);
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String)authenticationToken.getCredentials();
        String loginName = JwtUtil.getLoginName(token);
        if (StringUtils.isEmpty(loginName)) {
            throw new AuthenticationException("token 非法无效！");
        }
        User user = userService.findByLoginName(loginName);
        if(user == null) {
            throw new AuthenticationException("用户不存在！");
        }
        if(user.getState() == 1) {
            throw new AuthenticationException("账号已被禁用，请联系管理员！");
        }
        return new SimpleAuthenticationInfo(user, token, ByteSource.Util.bytes(user.getSalt()), getName());
    }
}
