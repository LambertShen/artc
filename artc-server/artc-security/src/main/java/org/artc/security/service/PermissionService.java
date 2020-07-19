package org.artc.security.service;

import org.artc.security.entity.Permission;
import org.artc.security.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public Set<Permission> findPermissionSetByRoleId(String roleId) {
        return permissionMapper.findPermissionSetByRoleId(roleId);
    }

}
