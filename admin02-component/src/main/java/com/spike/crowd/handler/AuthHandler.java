package com.spike.crowd.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spike.crowd.entity.Auth;
import com.spike.crowd.service.api.AuthService;
import com.spike.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthHandler {

    @Autowired
    private AuthService authService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("admin/get/auth.json")
    public ResultEntity<List<Auth>> getAuths(Integer categoryId) {

        List<Auth> auths = authService.getByCategoryId(categoryId);

        return ResultEntity.successWithData(auths);
    }

    @RequestMapping("admin/get/role-assigned-auth.json")
    public ResultEntity<List<Integer>> getAssignedRoleAuths(Integer roleId) {

        List<Integer> authsByAssignedRoleId = authService.getAuthIdByAssignedRoleId(roleId);

        return ResultEntity.successWithData(authsByAssignedRoleId);
    }

    @RequestMapping("admin/update/role-assigned-auth.json")
    public ResultEntity<String> updateAssignedRoleAuths(@RequestBody String myJSON) {

        // 解析请求的 json 数据
        // {roleId: 1, assignedAuths: "[2,3,4,9]"}
        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(myJSON, new TypeToken<Map<String, Object>>(){}.getType());
        Integer roleId = ((Double) map.get("roleId")).intValue();
        List<Integer> assignedAuths = gson.fromJson((String) map.get("assignedAuths"), new TypeToken<List<Integer>>(){}.getType());

        authService.updateRoleAuths(roleId, assignedAuths);

        return ResultEntity.successWithoutData();
    }

    @RequestMapping("admin/get/menu-assigned-auth.json")
    public ResultEntity<List<Integer>> getAssignedMenuAuths(Integer menuId) {

        List<Integer> authsByAssignedRoleId = authService.getAuthIdByAssignedMenuId(menuId);

        return ResultEntity.successWithData(authsByAssignedRoleId);
    }

    @RequestMapping("admin/update/menu-assigned-auth.json")
    public ResultEntity<String> updateAssignedMenuAuths(@RequestBody String myJSON) {

        // 解析请求的 json 数据
        // {roleId: 1, assignedAuths: "[2,3,4,9]"}
        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(myJSON, new TypeToken<Map<String, Object>>(){}.getType());
        Integer menuId = ((Double) map.get("menuId")).intValue();
        List<Integer> assignedAuths = gson.fromJson((String) map.get("assignedAuths"), new TypeToken<List<Integer>>(){}.getType());

        authService.updateMenuAuths(menuId, assignedAuths);

        return ResultEntity.successWithoutData();
    }
}
