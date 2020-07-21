package org.artc.security.web;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.artc.commom.entity.Result;
import org.artc.commom.entity.ResultCode;
import org.artc.commom.entity.ResultMap;
import org.artc.core.entity.User;
import org.artc.core.service.UserService;
import org.artc.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "login")
    public Result login(@RequestBody User loginUser) {
        User user = userService.findUserByLoginName(loginUser.getLoginName());
        if (user == null) {
            return new Result(ResultCode.USER_NOT_EXIST);
        }
        if (user.getState() == 1) {
            return new Result(ResultCode.USER_ACCOUNT_DISABLED);
        }
        SimpleHash simpleHash = new SimpleHash("MD5", loginUser.getPassword(), user.getSalt(), 1024);
        if (!simpleHash.toHex().equals(user.getPassword())) {
            return new Result(ResultCode.USER_PASSWORD_ERROR);
        }
        String token = JwtUtil.sign(loginUser.getLoginName(), loginUser.getPassword());
        return new Result(ResultCode.SUCCESS,
                ResultMap.create()
                        .put("token", token)
                        .put("user", user)
                        .build()
        );
    }
}
