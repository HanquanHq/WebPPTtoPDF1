## WebPPTtoPDF
在线将PPT转化为PDF的工具

2019-9-12 更新：添加了评论区，重构了以前的代码

待添加功能：用户注册、登录

CSDN地址：https://blog.csdn.net/sinat_42483341/article/details/100774691

本项目是使用B/S模式的JavaWeb项目，实现：用户自定义排列方式，将PPT转换为PDF。采用MVC设计思想，前后端分离。

### 软件需求
开发工具：Eclipse

数据库：MySQL

服务器：Tomcat

浏览器：Firefox，Chrome等主流浏览器

#### 功能设计

核心功能：PPT到PDF的在线转换、选择排列方式

附加功能：（后台管理）用户留言

#### 主要技术
##### 使用MVC思想：
M：Model模型

-	java实体类pojo：用于存储用户信息的JavaBeans
-	业务层service：包含用户登录校验、评论管理等业务，以及核心业务PPT到PDF的转换
-	数据库访问层dao：使用MyBatis框架操作数据库

V： view 视图.

-	用户界面：JSP（使用了EL,JSTL,Ajax等技术），使用bootstrap框架进行前端页面的美化

C：Controller 控制器

-	Servlet：处理并分发客户端浏览器发送的请求，并将响应结果返回给浏览器
