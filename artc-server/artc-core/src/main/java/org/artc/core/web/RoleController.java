package org.artc.core.web;

import org.artc.commom.entity.Result;
import org.artc.commom.entity.ResultCode;
import org.artc.core.entity.Role;
import org.artc.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("{id}")
    public Result findById(@PathVariable String id) {
        Role role = roleService.findById(id);
        return new Result(ResultCode.SUCCESS, role);
    }

    @GetMapping("")
    public Result findAll() {
        List<Role> roles =  roleService.findAll();
        return new Result(ResultCode.SUCCESS, roles);
    }

    @PostMapping("")
    public Result insert(@RequestBody Role role) {
        roleService.insert(role);
        return new Result(ResultCode.SUCCESS);
    }

    @PutMapping("")
    public Result update(@RequestBody Role role) {
        roleService.update(role);
        return new Result(ResultCode.SUCCESS);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        roleService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/bindPermission")
    public Result bindPermission(@RequestBody Role role) {
        roleService.bindPermission(role);
        return new Result(ResultCode.SUCCESS);
    }
}
