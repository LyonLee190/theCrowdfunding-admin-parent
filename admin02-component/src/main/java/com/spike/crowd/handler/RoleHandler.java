package com.spike.crowd.handler;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spike.crowd.entity.Role;
import com.spike.crowd.service.api.RoleService;
import com.spike.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class RoleHandler {

    @Autowired
    private RoleService roleService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("admin/get/role-page.json")
    public ResultEntity<PageInfo<Role>> page(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        PageInfo<Role> roles = roleService.getPageInfo(keyword, pageNum, pageSize);
        return ResultEntity.successWithData(roles);
    }

    @RequestMapping("admin/insert/role.json")
    public ResultEntity<String> insert(Role role) {

        role.setId(null);
        roleService.insertRole(role);
        return ResultEntity.successWithoutData();
    }

    @RequestMapping("admin/update/role.json")
    public ResultEntity<String> update(Role role) {

        roleService.updateRole(role);
        return ResultEntity.successWithoutData();
    }

    @RequestMapping("admin/remove/role.json")
    public ResultEntity<String> remove(@RequestBody Integer[] checkedRoleId) {

        roleService.removeRoleById(Arrays.asList(checkedRoleId));
        return ResultEntity.successWithoutData();
    }

    @RequestMapping("admin/get/role.json")
    public ResultEntity<List<Role>> getRoles() {

        List<Role> roles = roleService.getAll();

        return ResultEntity.successWithData(roles);
    }

    @RequestMapping("admin/get/assigned-role.json")
    public ResultEntity<List<Integer>> getAssignedRoles(Integer adminId) {

        List<Integer> rolesByAssignedAdminId = roleService.getRoleIdByAssignedAdminId(adminId);

        return ResultEntity.successWithData(rolesByAssignedAdminId);
    }

    @RequestMapping("admin/update/assigned-role.json")
    public ResultEntity<String> updateAssignedRoles(@RequestBody String myJSON) {

        // 解析请求的 json 数据
        // {adminId: 1, assignedRoles: "[2,3,4,9]"}
        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(myJSON, new TypeToken<Map<String, Object>>(){}.getType());
        Integer adminId = ((Double) map.get("adminId")).intValue();
        List<Integer> assignedRoles = gson.fromJson((String) map.get("assignedRoles"), new TypeToken<List<Integer>>(){}.getType());

        roleService.updateAdminRoles(adminId, assignedRoles);

        return ResultEntity.successWithoutData();
    }
}
