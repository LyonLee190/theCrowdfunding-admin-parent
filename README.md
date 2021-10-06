## The Crowdfunding

#### 1. 介绍

众筹网站的后台权限管理系统，作为Java EE的练习项目。业务逻辑参考尚筹网](https://www.bilibili.com/video/BV1bE411T7oZ)项目并完善，大方向上的改动包括：

- 前端不再使用JSP+JQuery，转而依赖Vue+ThymeLeaf进行数据交互以及页面渲染。
- 前后端数据传输只依赖JSON通信。

###### 技术依赖

- 前端：HTML5，Vue，Thymeleaf
- 后端：SSM，MySQL

###### 当前进度

- 实现了对用户，角色，菜单选项的增删改查功能，并可以为用户分配角色，为角色和菜单选项分配权限。

###### 下一步计划

- 整合Spring Security，根据当前登录用户分配到的权限进行资源限制。

#### 2. 项目结构

```
|--theCrowdfunding-admin-parent 版本控制

​		|--admin01-webui 视图层

​				|--SSM，数据库配置文件

​				|--web Java Web配置，前端资源

​		|-admin02-component 业务层

​				|--handler 处理前端请求

​				|--interceptor 拦截器

​				|--mapper 映射器

​				|--service 处理业务逻辑

​		|--admin03-entity 物理建模

​				|--Admin用户，Auth权限，Menu菜单项目，Role角色

​		|--common01-util 工具类

​				|--常量定义，数据加密，响应封装

​   |--common02-reverse MyBatis逆向工程
```

