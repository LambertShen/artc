package org.artc.core.service;

import org.artc.core.entity.Menu;
import org.artc.core.entity.User;
import org.artc.core.mapper.MenuMapper;
import org.artc.core.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> findAll() {
        User user = UserUtils.getCurrentUser();
        if(user.getAdmin() == 0) {
            return menuMapper.findAll();
        }
        return menuMapper.findMenusByUserId(user.getId());
    }

    public Menu findById(String id) {
        return menuMapper.findById(id);
    }

    public void insert(Menu menu) {
        menuMapper.insert(menu);
    }

    public void update(Menu menu) {
        menuMapper.update(menu);
    }

    public void delete(String id) {
        menuMapper.delete(id);
    }
}