package com.spike.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spike.crowd.entity.Menu;
import com.spike.crowd.entity.MenuExample;
import com.spike.crowd.mapper.AuthMapper;
import com.spike.crowd.mapper.MenuMapper;
import com.spike.crowd.service.api.MenuService;
import com.spike.crowd.util.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private AuthMapper authMapper;

    @Override
    public Integer insertMenu(Menu menu) {

        Integer count = null;
        // 执行保存
        try {
            menuMapper.insert(menu);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("选项已存在");
        }
        return count;
    }

    @Override
    public void removeMenuById(Integer id) {

        // 找到当前节点的所有子节点
        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        criteria.andPidEqualTo(id);
        menuMapper.selectByExample(menuExample).forEach(menu -> this.removeMenuById(menu.getId()));
        // 删除当前节点
        menuMapper.deleteByPrimaryKey(id);
        authMapper.deleteMenuAuthByMenuId(id);
    }

    @Override
    public Integer updateMenu(Menu menu) {

        Integer count = null;
        // 执行保存
        try {
            menuMapper.updateByPrimaryKeySelective(menu);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("选项已存在");
        }
        return count;
    }

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    @Override
    public Menu getMenuById(Integer menuId) {
        return null;
    }
}
