package org.artc.core.service;

import org.artc.core.entity.Permission;
import org.artc.core.mapper.PermissionMapper;
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
