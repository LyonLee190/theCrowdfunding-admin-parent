package com.spike.crowd.handler;

import com.spike.crowd.entity.Menu;
import com.spike.crowd.service.api.MenuService;
import com.spike.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class MenuHandler {

    @Autowired
    private MenuService menuService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("admin/get/menu-tree.json")
    public ResultEntity<Menu> tree() {

        // 创建 Map 对象存储 id 和 Menu 对象的对应关系便于查找父节点
        HashMap<Integer, Menu> hm = new HashMap<>();
        // 查询全部的 Menu 对象
        List<Menu> menus = menuService.getAll();
        // 遍历 menuList 填充 menuMap
        menus.forEach(menu -> hm.put(menu.getId(), menu));
        // 再次遍历 menuList 查找根节点，组装父子节点
        menus.forEach(menu -> {
            Integer pid = menu.getPid();
            if (pid != null) {
                hm.get(pid).getChildren().add(menu);
            }
        });

        // 根节点包含了整个树形结构，返回根节点就是返回整个树形结构
        return ResultEntity.successWithData(hm.get(1));
    }

    @RequestMapping("admin/insert/menu.json")
    public ResultEntity<String> insert(Menu menu) {

        menu.setId(null);
        menuService.insertMenu(menu);
        return ResultEntity.successWithoutData();
    }

    @RequestMapping("admin/update/menu.json")
    public ResultEntity<String> update(Menu menu) {

        menuService.updateMenu(menu);
        return ResultEntity.successWithoutData();
    }

    @RequestMapping("admin/remove/menu.json")
    public ResultEntity<String> remove(Integer id) {

        menuService.removeMenuById(id);
        return ResultEntity.successWithoutData();
    }
}
