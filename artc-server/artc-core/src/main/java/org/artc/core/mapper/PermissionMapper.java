package org.artc.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.artc.core.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface PermissionMapper {

    Set<Permission> findPermissionSetByRoleId(String roleId);

    Permission findById(String id);

    List<Permission> findAll();

    void update(Permission role);

    void insert(Permission role);

    void delete(String id);
}
