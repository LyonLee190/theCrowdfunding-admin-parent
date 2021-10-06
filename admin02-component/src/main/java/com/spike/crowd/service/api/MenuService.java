package com.spike.crowd.service.api;

import com.spike.crowd.entity.Menu;

import java.util.List;

public interface MenuService {

    Integer insertMenu(Menu menu);

    void removeMenuById(Integer id);

    Integer updateMenu(Menu menu);

    List<Menu> getAll();

    Menu getMenuById(Integer menuId);
}
