package org.artc.core.web;

import org.artc.commom.entity.Result;
import org.artc.commom.entity.ResultCode;
import org.artc.core.entity.Menu;
import org.artc.core.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("")
    public Result findAll() {
        List<Menu> menus = menuService.findAll();
        return new Result(ResultCode.SUCCESS, menus);
    }

    @GetMapping("{id}")
    public Result findById(@PathVariable String id) {
        Menu menu = menuService.findById(id);
        return new Result(ResultCode.SUCCESS, menu);
    }

    @PostMapping("")
    public Result insert(@RequestBody Menu menu) {
        menuService.insert(menu);
        return new Result(ResultCode.SUCCESS);
    }

    @PutMapping("")
    public Result update(@RequestBody Menu menu) {
        menuService.update(menu);
        return new Result(ResultCode.SUCCESS);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        menuService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }
}