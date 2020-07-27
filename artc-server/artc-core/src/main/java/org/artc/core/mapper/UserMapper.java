package org.artc.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.artc.core.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    User findByLoginName(String loginName);

    void insert(User user);

    User findById(String id);

    List<User> findAll();

    void update(User user);

    void delete(String id);

    void bindRole(User user);

    void unbindRole(User user);
}
