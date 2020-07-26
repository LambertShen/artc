package org.artc.core.service;

import org.artc.commom.utils.SnowFlake;
import org.artc.core.entity.Permission;
import org.artc.core.mapper.PermissionMapper;
import org.artc.core.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private SnowFlake snowFlake;

    public Set<Permission> findPermissionSetByRoleId(String roleId) {
        return permissionMapper.findPermissionSetByRoleId(roleId);
    }

    public Permission findById(String id) {
        return permissionMapper.findById(id);
    }

    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    public void update(Permission permission) {
        permission.setEdited(LocalDateTime.now());
        permission.setEditor(UserUtils.getCurrentUserId());
        permissionMapper.update(permission);
    }

    public String insert(Permission permission) {
        String id = snowFlake.nextId();
        permission.setId(id);
        permission.setCreated(LocalDateTime.now());
        permission.setCreator(UserUtils.getCurrentUserId());
        permissionMapper.insert(permission);
        return id;
    }

    public void delete(String id) {
        permissionMapper.delete(id);
    }

}
