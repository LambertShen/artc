package org.artc.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.artc.security.entity.Role;
import org.artc.security.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface RoleMapper {

    Set<Role> findRoleListByUserId(String id);

    Set<Role> findRoleListByUserLoginName(String loginName);

}
