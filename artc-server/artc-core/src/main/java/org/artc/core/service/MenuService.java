package org.artc.core.service;

import org.artc.commom.utils.SnowFlake;
import org.artc.core.entity.Menu;
import org.artc.core.entity.User;
import org.artc.core.mapper.MenuMapper;
import org.artc.core.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private SnowFlake snowFlake;

    public List<Menu> findAll() {
        List<Menu> menus;
        User user = UserUtils.getCurrentUser();
        if (user.getAdmin() == 0) {
            menus = menuMapper.findAll();
        } else {
            menus = menuMapper.findByUserId(user.getId());
        }
        return dfs(menus, "0");
    }

    public List<Menu> dfs(List<Menu> menus, String parentId) {
        List<Menu> result = new ArrayList<>();
        List<Menu> topMenus = menus.stream().filter(menu -> menu.getParentId().equals(parentId)).collect(Collectors.toList());
        if(topMenus.size() == 0) return null;
        for(Menu menu : topMenus) {
            List<Menu> childrenMenus = dfs(menus, menu.getId());
            if(childrenMenus != null) {
                menu.setChildren(childrenMenus);
            }
            result.add(menu);
        }
        return result;
    }

    public Menu findById(String id) {
        return menuMapper.findById(id);
    }

    public List<Menu> findByRoleId(String roleId) {
        return menuMapper.findByRoleId(roleId);
    }

    public void insert(Menu menu) {
        menu.setCreated(LocalDateTime.now());
        menu.setCreator(UserUtils.getCurrentUserId());
        menu.setId(snowFlake.nextId());
        int sort = menuMapper.findMaxSortByParentId(menu.getParentId());
        menu.setSort(sort + 1);
        menuMapper.insert(menu);
    }

    public void update(Menu menu) {
        menuMapper.update(menu);
    }

    public void delete(String id) {
        menuMapper.delete(id);
    }
}