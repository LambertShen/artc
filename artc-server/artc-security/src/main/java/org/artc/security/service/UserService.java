package org.artc.security.service;

import org.artc.security.entity.User;
//import org.artc.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

//    @Autowired
//    private UserMapper userMapper;

    public User findUserByLoginName(String loginName) {
//        return userMapper.findUserByLoginName(loginName);
        return new User();
    }

}
