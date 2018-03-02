# produce-project-managerment
项目代码自动生成系统，自定义java web项目系统模板，生成的代码maven打包后可直接运行
## 简介
此项目可以自动生成SSM(Spring+SpringMVC+Mybatis)项目，后端代码采用了Maven，生成好的源码可直接打包成war运行，主要源码有：java文件，包含分层结构、自动生成controller、business、service、dao、mapper；内置生成增删改查，以及主键id查询方法；Mybatis的xml映射文件；管理平台页面，针对单表的管理。
## 运行起来
注意：本项目需要JDK1.7及以上版本、Mysql。
1. clone 项目代码；
2. 配置数据源，applicationContext-dao.xml设置对应自己的数据库地址，账户及密码；
3. 初始化数据库，执行doc文件夹下sql脚本；
4. 打包成war运行；默认有两个登陆账号：admin\admin、demo\123456；
## 扩展
你可以实现自己的一套模板；本系统采用了Bettl模板引擎，可访问其官网 http://ibeetl.com 查看文档。
代码实现：
1. 实现IBuild里面的build方法；
2. 在applicationContext-os-type.xml里添加上对应的实现类；
