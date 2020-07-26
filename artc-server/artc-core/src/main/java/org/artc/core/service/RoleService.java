package org.artc.core.service;

import org.artc.commom.utils.SnowFlake;
import org.artc.core.entity.Role;
import org.artc.core.mapper.RoleMapper;
import org.artc.core.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private SnowFlake snowFlake;

    public Set<Role> findRoleSetByUserId(String userId) {
        return roleMapper.findRoleSetByUserId(userId);
    }

    public Role findById(String id) {
        return roleMapper.findById(id);
    }

    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    public void insert(Role role) {
        role.setId(snowFlake.nextId());
        role.setCreated(LocalDateTime.now());
        role.setCreator(UserUtils.getCurrentUserId());
        roleMapper.insert(role);
    }

    public void update(Role role) {
        role.setEdited(LocalDateTime.now());
        role.setEditor(UserUtils.getCurrentUserId());
        roleMapper.update(role);
    }


    public void delete(String id) {
        roleMapper.delete(id);
    }

    public void bindPermission(Role role) {
        roleMapper.unbindPermission(role);
        roleMapper.bindPermission(role);
    }
}
