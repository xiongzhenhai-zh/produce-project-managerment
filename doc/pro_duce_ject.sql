CREATE DATABASE If Not Exists pro_duce_ject DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use pro_duce_ject;

-- ----------------------------
-- Table structure for cm_priv
-- ----------------------------
  CREATE TABLE IF NOT EXISTS `cm_priv` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(60) DEFAULT NULL COMMENT '菜单名称',
  `PID` int(11) DEFAULT NULL COMMENT '父权限编号',
  `CATEGORY` varchar(2) DEFAULT NULL COMMENT '权限类别',
  `MENU_ICON` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `MENU_STAT` varchar(10) DEFAULT NULL COMMENT '菜单展开状态 ',
  `MENU_SORT` int(11) DEFAULT NULL COMMENT '菜单显示顺序',
  `OPEN_URL` varchar(200) DEFAULT NULL COMMENT '指向的URL',
  `IDENTIFY_CODE` varchar(50) DEFAULT NULL COMMENT '按钮的标识',
  `REAMRK` varchar(20) DEFAULT NULL COMMENT '备选字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='权限';

-- ----------------------------
-- Records of cm_priv
-- ----------------------------
INSERT INTO `cm_priv` VALUES ('24', '权限管理', null, '1', './resources/img/home/sign_guide.png', '', '5', '', '', '');
INSERT INTO `cm_priv` VALUES ('25', '新建权限', '24', '1', './resources/img/home/sign_guide.png', '', '10', './cmPriv/cmPrivCreate.html', '', '');
INSERT INTO `cm_priv` VALUES ('26', '权限列表', '24', '1', './resources/img/home/sign_guide.png', '', '15', './cmPriv/cmPrivList.html', '', '');
INSERT INTO `cm_priv` VALUES ('27', '角色信息管理', null, '1', './resources/img/home/sign_guide.png', '', '20', '', '', '');
INSERT INTO `cm_priv` VALUES ('28', '新建角色信息', '27', '1', './resources/img/home/sign_guide.png', '', '25', './cmRole/cmRoleCreate.html', '', '');
INSERT INTO `cm_priv` VALUES ('29', '角色信息列表', '27', '1', './resources/img/home/sign_guide.png', '', '30', './cmRole/cmRoleList.html', '', '');
INSERT INTO `cm_priv` VALUES ('30', '角色与权限管理', null, '1', './resources/img/home/sign_guide.png', '', '35', '', '', '');
INSERT INTO `cm_priv` VALUES ('31', '新建角色与权限', '30', '1', './resources/img/home/sign_guide.png', '', '40', './cmRolePrivMap/cmRolePrivMapCreate.html', '', '');
INSERT INTO `cm_priv` VALUES ('32', '角色与权限关系列表', '30', '1', './resources/img/home/sign_guide.png', '', '45', './cmRolePrivMap/cmRolePrivMapList.html', '', '');
INSERT INTO `cm_priv` VALUES ('33', '用户与角色关系管理', null, '1', './resources/img/home/sign_guide.png', '', '50', '', '', '');
INSERT INTO `cm_priv` VALUES ('34', '新建用户与角色关系', '33', '1', './resources/img/home/sign_guide.png', '', '55', './cmUserRoleMap/cmUserRoleMapCreate.html', '', '');
INSERT INTO `cm_priv` VALUES ('35', '用户与角色关系列表', '33', '1', './resources/img/home/sign_guide.png', '', '60', './cmUserRoleMap/cmUserRoleMapList.html', '', '');
INSERT INTO `cm_priv` VALUES ('36', '项目用表管理', null, '1', './resources/img/home/sign_guide.png', '', '80', '', '', '');
INSERT INTO `cm_priv` VALUES ('37', '新建项目用表', '36', '1', './resources/img/home/sign_guide.png', '', '85', './model/modelCreate.html', '', '');
INSERT INTO `cm_priv` VALUES ('38', '项目用表列表', '36', '1', './resources/img/home/sign_guide.png', '', '90', './model/modelList.html', '', '');
INSERT INTO `cm_priv` VALUES ('39', '表字段管理', null, '1', './resources/img/home/sign_guide.png', '', '70', '', '', '');
INSERT INTO `cm_priv` VALUES ('40', '新建表字段', '39', '1', './resources/img/home/sign_guide.png', '', '75', './modelattribute/modelattributeCreate.html', '', '');
INSERT INTO `cm_priv` VALUES ('41', '表字段列表', '39', '1', './resources/img/home/sign_guide.png', '', '90', './modelattribute/modelattributeList.html', '', '');
INSERT INTO `cm_priv` VALUES ('42', '项目管理', null, '1', './resources/img/home/sign_guide.png', '', '95', '', '', '');
INSERT INTO `cm_priv` VALUES ('43', '新建项目', '42', '1', './resources/img/home/sign_guide.png', '', '100', './project/projectCreate.html', '', '');
INSERT INTO `cm_priv` VALUES ('44', '项目列表', '42', '1', './resources/img/home/sign_guide.png', '', '105', './project/projectList.html', '', '');
INSERT INTO `cm_priv` VALUES ('48', '生成记录', '42', '1', './resources/img/home/sign_guide.png', '', '105', './produceRecord/produceRecordList.html', '', '');
INSERT INTO `cm_priv` VALUES ('45', '用户管理', null, '1', './resources/img/home/sign_guide.png', '', '110', '', '', '');
INSERT INTO `cm_priv` VALUES ('46', '新增用户', '45', '1', '', '', '115', './user/userCreate.html', '', '');
INSERT INTO `cm_priv` VALUES ('47', '用户列表', '45', '1', '', '', '120', './user/userList.html', '', '');

-- ----------------------------
-- Table structure for cm_role
-- ----------------------------
  CREATE TABLE IF NOT EXISTS `cm_role` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(80) DEFAULT NULL COMMENT '角色名称',
  `DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of cm_role
-- ----------------------------
INSERT INTO `cm_role` VALUES ('1', '系统管理员', '');
INSERT INTO `cm_role` VALUES ('2', '普通用户', '');

-- ----------------------------
-- Table structure for cm_role_priv_map
-- ----------------------------
  CREATE TABLE IF NOT EXISTS `cm_role_priv_map` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_ID` int(11) DEFAULT NULL COMMENT '角色编号',
  `PRIV_ID` int(11) DEFAULT NULL COMMENT '权限编号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色与权限关系表';

-- ----------------------------
-- Records of cm_role_priv_map
-- ----------------------------
INSERT INTO `cm_role_priv_map` VALUES ('125', '1', '24');
INSERT INTO `cm_role_priv_map` VALUES ('126', '1', '25');
INSERT INTO `cm_role_priv_map` VALUES ('127', '1', '26');
INSERT INTO `cm_role_priv_map` VALUES ('128', '1', '27');
INSERT INTO `cm_role_priv_map` VALUES ('129', '1', '28');
INSERT INTO `cm_role_priv_map` VALUES ('130', '1', '29');
INSERT INTO `cm_role_priv_map` VALUES ('131', '1', '30');
INSERT INTO `cm_role_priv_map` VALUES ('132', '1', '31');
INSERT INTO `cm_role_priv_map` VALUES ('133', '1', '32');
INSERT INTO `cm_role_priv_map` VALUES ('134', '1', '33');
INSERT INTO `cm_role_priv_map` VALUES ('135', '1', '34');
INSERT INTO `cm_role_priv_map` VALUES ('136', '1', '35');
INSERT INTO `cm_role_priv_map` VALUES ('137', '1', '36');
INSERT INTO `cm_role_priv_map` VALUES ('138', '1', '37');
INSERT INTO `cm_role_priv_map` VALUES ('139', '1', '38');
INSERT INTO `cm_role_priv_map` VALUES ('140', '1', '39');
INSERT INTO `cm_role_priv_map` VALUES ('141', '1', '40');
INSERT INTO `cm_role_priv_map` VALUES ('142', '1', '41');
INSERT INTO `cm_role_priv_map` VALUES ('143', '1', '42');
INSERT INTO `cm_role_priv_map` VALUES ('144', '1', '43');
INSERT INTO `cm_role_priv_map` VALUES ('145', '1', '44');
INSERT INTO `cm_role_priv_map` VALUES ('146', '2', '36');
INSERT INTO `cm_role_priv_map` VALUES ('147', '2', '37');
INSERT INTO `cm_role_priv_map` VALUES ('148', '2', '38');
INSERT INTO `cm_role_priv_map` VALUES ('149', '2', '39');
INSERT INTO `cm_role_priv_map` VALUES ('150', '2', '40');
INSERT INTO `cm_role_priv_map` VALUES ('151', '2', '41');
INSERT INTO `cm_role_priv_map` VALUES ('152', '2', '42');
INSERT INTO `cm_role_priv_map` VALUES ('153', '2', '43');
INSERT INTO `cm_role_priv_map` VALUES ('154', '2', '44');
INSERT INTO `cm_role_priv_map` VALUES ('155', '2', '48');

-- ----------------------------
-- Table structure for cm_user_role_map
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `cm_user_role_map` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户编号',
  `ROLE_ID` int(11) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户与角色关系表';

-- ----------------------------
-- Records of cm_user_role_map
-- ----------------------------
INSERT INTO `cm_user_role_map` VALUES ('1', '1', '1');
INSERT INTO `cm_user_role_map` VALUES ('2', '2', '2');

-- ----------------------------
-- Table structure for model
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `model` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '实体名',
  `dbName` varchar(255) DEFAULT NULL COMMENT '对应数据表名',
  `alies` varchar(255) DEFAULT NULL COMMENT '备注',
  `idType` varchar(255) DEFAULT NULL COMMENT 'id类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `attribute_id` varchar(500) DEFAULT NULL COMMENT '关联字段id（多个用逗号隔开）',
  `user_id` bigint(11) DEFAULT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='项目用表';

-- ----------------------------
-- Records of model
-- ----------------------------
INSERT INTO `model` VALUES ('1', 'User', 'user', '用户表', 'auto_increment', now(), '1,2,4,5,6,7,11', '2');
INSERT INTO `model` VALUES ('2', 'Role', 't_role', '角色表', 'auto_increment', now(), '1,2,5,11,13', '2');

-- ----------------------------
-- Table structure for modelattribute
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `modelattribute` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '字段名',
  `dbName` varchar(255) DEFAULT NULL COMMENT '数据库字段名',
  `alies` varchar(255) DEFAULT NULL COMMENT '备注',
  `type` varchar(255) DEFAULT NULL COMMENT '字段类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `user_id` bigint(11) DEFAULT NULL COMMENT '所属用户',
  `column_key` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='表字段';

-- ----------------------------
-- Records of modelattribute
-- ----------------------------
INSERT INTO `modelattribute` VALUES ('1', 'id', 'id', '主键', 'Long', now(), '2',1);
INSERT INTO `modelattribute` VALUES ('2', 'name', 'name', '名字', 'String', now(), '2',0);
INSERT INTO `modelattribute` VALUES ('4', 'password', 'password', '密码', 'String', now(), '2',0);
INSERT INTO `modelattribute` VALUES ('5', 'createTime', 'create_time', '创建时间', 'Date', now(), '2',0);
INSERT INTO `modelattribute` VALUES ('6', 'content', 'content', '内容', 'String', now(), '2',0);
INSERT INTO `modelattribute` VALUES ('7', 'type', 'type', '类型', 'Integer', now(), '2',0);
INSERT INTO `modelattribute` VALUES ('11', 'status', 'status', '状态', 'Integer', now(), '2',0);
INSERT INTO `modelattribute` VALUES ('12', 'imgUrl', 'img_url', '图片地址', 'String', now(), '2',0);
INSERT INTO `modelattribute` VALUES ('13', 'updateTime', 'update_time', '更新时间', 'Date', now(), '2',0);
INSERT INTO `modelattribute` VALUES ('14', '125', '125', '125', 'Integer', now(), '2',0);

-- ----------------------------
-- Table structure for project
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `project` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `groupId` varchar(255) DEFAULT NULL COMMENT 'maven坐标',
  `artifactId` varchar(255) DEFAULT NULL COMMENT 'Maven项目名',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `alies` varchar(255) DEFAULT NULL COMMENT '项目中文名',
  `databaseName` varchar(255) DEFAULT NULL COMMENT '数据库',
  `pkgName` varchar(255) DEFAULT NULL COMMENT '包路径',
  `autor` varchar(255) DEFAULT NULL COMMENT '作者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `exclusions_table` varchar(1000) DEFAULT NULL COMMENT '排除表',
  `produce_type` int(11) DEFAULT NULL COMMENT '生成方式',
  `db_url` varchar(255) DEFAULT NULL COMMENT '数据库连接',
  `db_username` varchar(255) DEFAULT NULL COMMENT '数据库用户名',
  `db_password` varchar(255) DEFAULT NULL COMMENT '数据库密码',
  `modelIds` varchar(500) DEFAULT NULL COMMENT '关联数据库表',
  `user_id` bigint(11) DEFAULT NULL COMMENT '所属用户',
  `server_frame_type` varchar(255) DEFAULT NULL COMMENT '后端框架（SSH、SSM）',
  `client_frame_type` varchar(255) DEFAULT NULL COMMENT '管理平台框架（layout、mime）',
  `mobile_frame_type` varchar(255) DEFAULT NULL COMMENT '移动端框架（mobile）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='项目实体';

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', 'com.carejava', 'test-managerment', 'test-managerment', '项目生成管理平台', 'test', 'com.carejava.test.manage.web', 'zhenhai.xiong', now(), '', '2', 'jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf8', 'root', '123456', '1,2,', '2','ssm','layout','mobile');

-- ----------------------------
-- Table structure for user
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', PASSWORD('admin'), now());
INSERT INTO `user` VALUES ('2', 'demo', PASSWORD('123456'), now());

 CREATE TABLE IF NOT EXISTS `produce_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `file_path` varchar(255) NOT NULL COMMENT '文件路径',
  `sys_type` VARCHAR(255) DEFAULT NULL COMMENT '系统类型',
  `project_id` bigint(20) DEFAULT NULL COMMENT '项目id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生成记录';
