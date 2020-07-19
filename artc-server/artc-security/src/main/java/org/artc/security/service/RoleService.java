package org.artc.security.service;

import org.artc.security.entity.Role;
import org.artc.security.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public Set<Role> findRoleSetByUserId(String userId) {
        return roleMapper.findRoleSetByUserId(userId);
    }

}
