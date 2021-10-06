package com.spike.crowd.service.api;

import com.spike.crowd.entity.Auth;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthService {

    Auth getAuthByName(String name);

    Integer insertAuth(Auth auth);

    void removeAuthById(List<Integer> authId);

    Integer updateAuth(Auth auth);

    List<Auth> getAll();

    Auth getAuthById(Integer authId);

    List<Auth> getByCategoryId(Integer categoryId);

    List<Integer> getAuthIdByAssignedRoleId(Integer roleId);

    List<Integer> getAuthIdByAssignedMenuId(Integer menuId);

    void updateRoleAuths(Integer roleId, List<Integer> roleAuths);

    void updateMenuAuths(Integer menuId, List<Integer> menuAuths);
}
