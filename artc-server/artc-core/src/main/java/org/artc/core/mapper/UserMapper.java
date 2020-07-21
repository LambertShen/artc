package org.artc.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.artc.core.entity.User;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User findUserByLoginName(String loginName);

    void insert(User user);
}
