package org.artc.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.artc.core.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper {

    List<Menu> findByUserId(String userId);

    List<Menu> findAll();

    List<Menu> findByRoleId(String roleId);

    Menu findById(String id);

    void insert(Menu menu);

    void update(Menu menu);

    void delete(String id);

    Integer findMaxSortByParentId(String parentId);
}