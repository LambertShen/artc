package org.artc.core.web;

import org.artc.commom.entity.Result;
import org.artc.commom.entity.ResultCode;
import org.artc.core.entity.Permission;
import org.artc.core.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("{id}")
    public Result findById(@PathVariable String id) {
        Permission permission = permissionService.findById(id);
        return new Result(ResultCode.SUCCESS, permission);
    }

    @GetMapping("")
    public Result findAll() {
        List<Permission> permissions =  permissionService.findAll();
        return new Result(ResultCode.SUCCESS, permissions);
    }

    @PostMapping("")
    public Result insert(@RequestBody Permission permission) {
        permissionService.insert(permission);
        return new Result(ResultCode.SUCCESS);
    }

    @PutMapping("")
    public Result update(@RequestBody Permission permission) {
        permissionService.update(permission);
        return new Result(ResultCode.SUCCESS);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        permissionService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

}
