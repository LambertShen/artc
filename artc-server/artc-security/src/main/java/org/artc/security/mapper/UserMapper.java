package org.artc.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.artc.security.entity.User;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User findUserByLoginName(String loginName);

    void insert(User user);
}
