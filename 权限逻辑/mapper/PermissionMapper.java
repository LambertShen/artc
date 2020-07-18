package org.artc.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.artc.security.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface PermissionMapper {

    Set<Permission> findPermissionListByRoleId(String id);

}
