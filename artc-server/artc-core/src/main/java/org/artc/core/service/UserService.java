package org.artc.core.service;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.artc.commom.utils.SnowFlake;
import org.artc.core.entity.User;
import org.artc.core.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SnowFlake snowFlake;

    public User findUserByLoginName(String loginName) {
        return userMapper.findUserByLoginName(loginName);
    }

    public String insert(User user) {
        String id = String.valueOf(snowFlake.nextId());
        user.setId(id);
        user.setCreated(LocalDateTime.now());
        String salt = UUID.randomUUID().toString().replaceAll("-","");
        SimpleHash simpleHash = new SimpleHash("MD5", user.getPassword(), salt, 1024);
        user.setPassword(simpleHash.toHex());
        user.setSalt(salt);
        userMapper.insert(user);
        return id;
    }
}
