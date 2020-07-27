package org.artc.core.entity;

import org.artc.commom.entity.BaseEntity;

import java.util.List;

public class Role extends BaseEntity {

    private String code;
    private String name;
    private List<Menu> menus;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
