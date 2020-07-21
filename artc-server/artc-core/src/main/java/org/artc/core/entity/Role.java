package org.artc.core.entity;

import org.artc.commom.entity.BaseEntity;

import java.util.Set;

public class Role extends BaseEntity {

    private String code;
    private String name;
    private Set<Permission> permissions;

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

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
