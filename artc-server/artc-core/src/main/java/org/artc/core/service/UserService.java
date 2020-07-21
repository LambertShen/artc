package org.artc.core.service;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.artc.commom.utils.SnowFlake;
import org.artc.core.entity.User;
import org.artc.core.mapper.UserMapper;
import org.artc.core.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void insert(User user) {
        String id = String.valueOf(snowFlake.nextId());
        user.setId(id);
        user.setCreated(LocalDateTime.now());
        user.setCreator(UserUtils.getCurrentUserId());
        String salt = UUID.randomUUID().toString().replaceAll("-","");
        SimpleHash simpleHash = new SimpleHash("MD5", user.getPassword(), salt, 1024);
        user.setPassword(simpleHash.toHex());
        user.setSalt(salt);
        userMapper.insert(user);
    }

    public User findById(String id) {
        return userMapper.findById(id);
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public void update(User user) {
        user.setEdited(LocalDateTime.now());
        user.setEditor(UserUtils.getCurrentUserId());
        userMapper.update(user);
    }

    public void delete(String id) {
        userMapper.delete(id);
    }

    public void bindRole(User user) {
        userMapper.unbindRole(user);
        userMapper.bindRole(user);
    }
}
