package org.artc.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.artc.security.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface RoleMapper {

    Set<Role> findRoleSetByUserId(String userId);

}
