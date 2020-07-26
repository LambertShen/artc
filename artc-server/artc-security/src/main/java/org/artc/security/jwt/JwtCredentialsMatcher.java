package org.artc.security.jwt;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.artc.core.entity.User;

public class JwtCredentialsMatcher implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        User user = (User) authenticationInfo.getPrincipals().getPrimaryPrincipal();
        String token = authenticationInfo.getCredentials().toString();
        return JwtUtil.verify(token, user.getLoginName(), user.getPassword());
    }
}
