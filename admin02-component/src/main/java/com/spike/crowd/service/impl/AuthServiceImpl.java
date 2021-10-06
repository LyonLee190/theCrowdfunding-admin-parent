package com.spike.crowd.service.impl;

import com.spike.crowd.entity.Auth;
import com.spike.crowd.entity.AuthExample;
import com.spike.crowd.mapper.AuthMapper;
import com.spike.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public Auth getAuthByName(String name) {
        return null;
    }

    @Override
    public Integer insertAuth(Auth auth) {
        return null;
    }

    @Override
    public void removeAuthById(List<Integer> authId) {

    }

    @Override
    public Integer updateAuth(Auth auth) {
        return null;
    }

    @Override
    public List<Auth> getAll() {
        AuthExample authExample = new AuthExample();
        authExample.setOrderByClause("id");
        return authMapper.selectByExample(authExample);
    }

    @Override
    public Auth getAuthById(Integer authId) {
        return null;
    }

    @Override
    public List<Auth> getByCategoryId(Integer categoryId) {

        AuthExample authExample = new AuthExample();
        AuthExample.Criteria criteria = authExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        authExample.setOrderByClause("id");
        return authMapper.selectByExample(authExample);
    }

    @Override
    public List<Integer> getAuthIdByAssignedRoleId(Integer roleId) {
        return authMapper.selectByAssignedRoleId(roleId);
    }

    @Override
    public List<Integer> getAuthIdByAssignedMenuId(Integer menuId) {
        return authMapper.selectByAssignedMenuId(menuId);
    }

    @Override
    public void updateRoleAuths(Integer roleId, List<Integer> roleAuths) {
        // 删除该用户的所有权限记录后重新插入
        authMapper.deleteRoleAuthByRoleId(roleId);
        roleAuths.forEach(authId -> authMapper.insertRoleAuth(roleId, authId));
    }

    @Override
    public void updateMenuAuths(Integer menuId, List<Integer> menuAuths) {
        // 删除该用户的所有权限记录后重新插入
        authMapper.deleteMenuAuthByMenuId(menuId);
        menuAuths.forEach(authId -> authMapper.insertMenuAuth(menuId, authId));
    }
}
