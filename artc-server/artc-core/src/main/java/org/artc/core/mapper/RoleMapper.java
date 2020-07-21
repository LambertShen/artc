package org.artc.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.artc.core.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface RoleMapper {

    Set<Role> findRoleSetByUserId(String userId);

}
