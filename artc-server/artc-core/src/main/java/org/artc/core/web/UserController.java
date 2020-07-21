package org.artc.core.web;

import org.artc.commom.entity.Result;
import org.artc.commom.entity.ResultCode;
import org.artc.core.entity.User;
import org.artc.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public Result findById(@PathVariable String id) {
        User user = userService.findById(id);
        return new Result(ResultCode.SUCCESS, user);
    }

    @GetMapping("")
    public Result findAll() {
        List<User> users = userService.findAll();
        return new Result(ResultCode.SUCCESS, users);
    }

    @PostMapping("")
    public Result insert(@RequestBody User user) {
        userService.insert(user);
        return new Result(ResultCode.SUCCESS);
    }

    @PutMapping("")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return new Result(ResultCode.SUCCESS);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        userService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("bindRole")
    public Result bindRole(@RequestBody  User user) {
        userService.bindRole(user);
        return new Result(ResultCode.SUCCESS);
    }
}