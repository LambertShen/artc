package org.artc.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.artc.core.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface RoleMapper {

    Set<Role> findRoleSetByUserId(String userId);

    Role findById(String id);

    List<Role> findAll();

    void update(Role role);

    void insert(Role role);

    void delete(String id);

    void bindPermission(Role role);

    void unbindPermission(Role role);
}
