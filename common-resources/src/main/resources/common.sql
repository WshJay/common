/*
Navicat MySQL Data Transfer

Source Server         : WSH-DB
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : common

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-11-10 00:47:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '组名称',
  `cover_url` varchar(120) DEFAULT NULL COMMENT '封面地址',
  `status` varchar(16) NOT NULL DEFAULT 'ON_LINE' COMMENT '状态[ON_LINE,OFF_LINE]',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `gmt_created` datetime NOT NULL COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息组表';

-- ----------------------------
-- Records of group
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(60) NOT NULL COMMENT '栏目名称',
  `icon_url` varchar(256) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '图标地址',
  `res_id` bigint(20) NOT NULL COMMENT '资源ID',
  `father_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父栏目ID',
  `status` smallint(2) NOT NULL DEFAULT '0' COMMENT '状态(0表示展示，1表示不展示)',
  `gmt_created` datetime NOT NULL COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='栏目表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '系统管理', 'icon-cogs', '0', '0', '0', '2015-06-08 16:41:40', '2015-06-08 16:41:40');
INSERT INTO `menu` VALUES ('2', '用户管理', null, '1', '1', '0', '2015-06-08 16:41:59', '2015-06-08 16:41:59');
INSERT INTO `menu` VALUES ('3', '角色管理', null, '2', '1', '0', '2015-06-08 16:42:14', '2015-06-08 16:42:14');
INSERT INTO `menu` VALUES ('4', '权限管理', null, '3', '1', '0', '2015-06-08 16:42:25', '2015-06-08 16:42:25');
INSERT INTO `menu` VALUES ('5', '栏目管理', null, '4', '1', '0', '2015-06-08 16:42:42', '2015-06-08 16:42:42');
INSERT INTO `menu` VALUES ('7', '会员管理', 'icon-group', '0', '0', '0', '2015-06-11 17:47:33', '2015-06-11 17:47:33');
INSERT INTO `menu` VALUES ('8', '维护管理', 'icon-lightbulb', '0', '0', '0', '2015-06-11 17:49:17', '2015-06-11 17:49:17');
INSERT INTO `menu` VALUES ('9', '模块管理', null, '5', '8', '0', '2015-06-11 17:49:32', '2015-06-11 17:49:32');
INSERT INTO `menu` VALUES ('10', '会员管理', 'icon-group', '8', '7', '0', '2015-06-15 13:20:48', '2015-06-15 13:20:48');
INSERT INTO `menu` VALUES ('12', '会员审核', null, '12', '7', '0', '2015-06-23 13:54:47', '2015-06-23 13:54:47');
INSERT INTO `menu` VALUES ('13', '义工管理', 'icon-certificate', '0', '0', '0', '2015-06-25 10:48:34', '2015-06-25 10:48:34');
INSERT INTO `menu` VALUES ('14', '义工管理', null, '13', '13', '0', '2015-06-25 10:52:48', '2015-06-25 10:52:48');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` varchar(16) NOT NULL DEFAULT 'TO_ONE' COMMENT '消息类型[TO_ONE,TO_MANY]',
  `from_user_id` bigint(20) NOT NULL COMMENT '发送者ID',
  `to_user_id` bigint(20) NOT NULL COMMENT '接收者ID(可以多个,以逗号隔开)',
  `content` text NOT NULL COMMENT '消息内容',
  `gmt_created` datetime NOT NULL COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='消息表';

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('4', 'TO_ONE', '1', '2', 'Test...', '2016-11-07 16:37:23', '2016-11-07 16:37:23');
INSERT INTO `message` VALUES ('5', 'TO_ONE', '1', '2', 'Test...', '2016-11-07 16:40:34', '2016-11-07 16:40:34');
INSERT INTO `message` VALUES ('6', 'TO_ONE', '1', '2', 'Test...', '2016-11-07 16:44:18', '2016-11-07 16:44:18');
INSERT INTO `message` VALUES ('7', 'TO_ONE', '1', '2', 'Test...', '2016-11-07 17:09:17', '2016-11-07 17:09:17');

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(60) NOT NULL COMMENT '权限描述',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `gmt_created` datetime NOT NULL COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='模块表';

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('1', '系统管理', '1', '2015-06-08 15:17:23', '2015-06-08 15:17:25');
INSERT INTO `module` VALUES ('2', '用户管理', '1', '2015-06-09 10:58:36', '2015-06-09 10:58:36');
INSERT INTO `module` VALUES ('3', '会员管理', '1', '2015-06-15 13:18:28', '2015-06-15 13:18:28');
INSERT INTO `module` VALUES ('4', '义工管理', '1', '2015-06-25 10:52:07', '2015-06-25 10:52:07');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(60) NOT NULL COMMENT '权限描述',
  `code` varchar(30) NOT NULL COMMENT '权限代码',
  `target` varchar(60) NOT NULL COMMENT '目标地址',
  `allow` smallint(2) NOT NULL DEFAULT '0' COMMENT '是否验证(0表示不验证权限，1表示验证权限)',
  `type` smallint(2) NOT NULL DEFAULT '0' COMMENT '类型',
  `module_id` bigint(20) NOT NULL COMMENT '模块ID',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `gmt_created` datetime NOT NULL COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '用户管理', 'YHGL', '/user/list.html', '2', '1', '1', '1', '2015-06-08 15:20:47', '2015-06-08 15:20:47');
INSERT INTO `permission` VALUES ('2', '角色管理', 'JSGL', '/role/list.html', '2', '1', '1', '1', '2015-06-08 15:22:14', '2015-06-08 15:22:14');
INSERT INTO `permission` VALUES ('3', '权限管理', 'QXGL', '/permission/list.html', '2', '1', '1', '1', '2015-06-08 15:22:59', '2015-06-08 15:22:59');
INSERT INTO `permission` VALUES ('4', '栏目管理', 'LMGL', '/menu/list.html', '2', '1', '1', '1', '2015-06-09 10:56:14', '2015-06-09 10:56:14');
INSERT INTO `permission` VALUES ('5', '模块管理', 'MKGL', '/module/list.html', '2', '1', '1', '1', '2015-06-09 10:57:12', '2015-06-09 10:57:12');
INSERT INTO `permission` VALUES ('6', '添加用户', 'TJYH', '/ajax/user/add.html', '2', '2', '1', '1', '2015-06-09 10:57:51', '2015-06-18 11:01:16');
INSERT INTO `permission` VALUES ('7', '删除用户', 'SCYH', '/user/delUser.html', '2', '1', '1', '1', '2015-06-09 10:59:28', '2015-06-18 14:03:35');
INSERT INTO `permission` VALUES ('8', '会员列表', 'HYLB', '/member/list.html', '1', '1', '3', '1', '2015-06-15 13:20:22', '2015-06-15 13:20:22');
INSERT INTO `permission` VALUES ('9', '修改用户', 'XGYH', '/user/editUser.do', '2', '3', '2', '1', '2015-06-18 14:09:43', '2015-06-18 14:09:43');
INSERT INTO `permission` VALUES ('11', '添加角色', 'TJJS', '/role/add.html', '2', '2', '1', '1', '2015-06-23 13:51:24', '2015-06-23 13:51:24');
INSERT INTO `permission` VALUES ('12', '会员审核', 'SHHY', '/member/aduit.html', '2', '1', '3', '1', '2015-06-23 13:54:16', '2015-06-23 13:54:16');
INSERT INTO `permission` VALUES ('13', '义工管理', 'YGGL', '/volunteer/list.htm', '2', '1', '4', '1', '2015-06-25 10:51:52', '2015-06-25 10:52:24');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(60) NOT NULL COMMENT '角色名',
  `role_code` varchar(30) NOT NULL COMMENT '角色code',
  `status` smallint(2) DEFAULT NULL,
  `create_user_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `gmt_created` datetime NOT NULL COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', 'admin', '0', '1', '2015-06-04 16:38:14', '2015-06-04 16:38:14');
INSERT INTO `role` VALUES ('2', '客服', 'costomServer', '0', '1', '2015-06-04 16:38:14', '2015-06-04 16:38:14');
INSERT INTO `role` VALUES ('3', '产品', 'product', '0', '1', '2015-06-04 16:39:28', '2015-06-04 16:39:28');
INSERT INTO `role` VALUES ('4', '运营', 'Operate', '0', '1', '2015-06-08 16:29:20', '2015-06-08 16:29:20');
INSERT INTO `role` VALUES ('5', '人事', 'HR', '0', '1', '2015-06-17 17:31:50', '2015-06-17 17:31:50');
INSERT INTO `role` VALUES ('6', '秘书', 'MS', '0', '1', '2016-10-21 09:42:41', '2016-10-21 09:42:41');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `gmt_created` datetime NOT NULL COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('21', '2', '1', '1', '2015-06-18 10:59:10', '2015-06-18 10:59:10');
INSERT INTO `role_permission` VALUES ('22', '2', '8', '1', '2015-06-18 10:59:10', '2015-06-18 10:59:10');
INSERT INTO `role_permission` VALUES ('23', '1', '1', '1', '2015-06-25 10:53:21', '2015-06-25 10:53:21');
INSERT INTO `role_permission` VALUES ('24', '1', '2', '1', '2015-06-25 10:53:21', '2015-06-25 10:53:21');
INSERT INTO `role_permission` VALUES ('25', '1', '3', '1', '2015-06-25 10:53:21', '2015-06-25 10:53:21');
INSERT INTO `role_permission` VALUES ('26', '1', '4', '1', '2015-06-25 10:53:21', '2015-06-25 10:53:21');
INSERT INTO `role_permission` VALUES ('27', '1', '5', '1', '2015-06-25 10:53:21', '2015-06-25 10:53:21');
INSERT INTO `role_permission` VALUES ('28', '1', '6', '1', '2015-06-25 10:53:21', '2015-06-25 10:53:21');
INSERT INTO `role_permission` VALUES ('29', '1', '7', '1', '2015-06-25 10:53:21', '2015-06-25 10:53:21');
INSERT INTO `role_permission` VALUES ('30', '1', '13', '1', '2015-06-25 10:53:21', '2015-06-25 10:53:21');

-- ----------------------------
-- Table structure for user_basic
-- ----------------------------
DROP TABLE IF EXISTS `user_basic`;
CREATE TABLE `user_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `salt` varchar(30) NOT NULL COMMENT '密钥',
  `real_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `face_url` varchar(256) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '头像地址',
  `phone` varchar(11) NOT NULL COMMENT '手机号码',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `status` smallint(2) NOT NULL COMMENT '用户状态',
  `gmt_created` datetime NOT NULL COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_USERNAME` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

-- ----------------------------
-- Records of user_basic
-- ----------------------------
INSERT INTO `user_basic` VALUES ('1', '管理员', '78eaf4c7f296092a9552b3723c54e71b8d4d57f4', '484d0ae1066154de', 'wsh', null, '15558053971', '675783578@qq.com', '0', '2015-06-04 16:50:32', '2015-06-25 10:44:40');
INSERT INTO `user_basic` VALUES ('2', '张三', '78eaf4c7f296092a9552b3723c54e71b8d4d57f4', '484d0ae1066154de', '张三', null, '15558053971', '12356@qq.com', '0', '2015-06-05 15:50:04', '2015-06-25 10:32:54');
INSERT INTO `user_basic` VALUES ('5', '用户1', '803ff945947af975595fe7d5f0a2eaa1999b79ff', '46ef66f081f2f8c6', '用户1', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('6', '用户2', 'f2bea30b9b7777ddfdbbbe45a354b8b5a415cd07', '13b0a16e13654c46', '用户2', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('7', '用户3', '6709d3ef4514719881c067ec9d3f2752f23e3d25', '9bd0965ca800b437', '用户3', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('8', '用户4', 'b79969331405184d9c5a777a366ffaa522326e1a', 'dcf28929ddd10ad3', '用户4', null, '12356789328', '123@qq.com', '1', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('9', '用户5', 'b15a1065926667820a488eb4b278a082bd6ffa3a', '1432034fb6b8d6d0', '用户5', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('10', '用户6', 'e897f153890059b614605dd70d1b27ab0d80758a', '682e7f907d4fe903', '用户6', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('11', '用户7', 'c622597f7fb643f2e4f2be7d08964bdc9256e570', '997cc12f5b5f81d4', '用户7', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('12', '用户8', 'c88f18667e4203f3105d45641a910d885de4b41b', '502bb997d2d8d3b2', '用户8', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('13', '用户9', '521527d970659cc7a3911a110e7567fd743ab2a8', 'c387efb1e65a2664', '用户9', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('14', '用户10', '0fb0dc920fdc400936a01b0180eb86a8347b4d0b', '7c24d755fb434b2e', '用户10', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('15', '用户11', 'a656f6d2cb82ff2b46a4850d810b5b10be147e4d', '63a635d85838d7dd', '用户11', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('16', '用户12', '86de6fa9bbec25d3df5bea43c44c27292e411100', '1614079890cc6a46', '用户12', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('17', '用户13', 'c5338a99072e2a9ad9202daa1f01f3f1e51c4b6d', '3745cb93762cfc96', '用户13', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('18', '用户14', 'ec3580928dc25aa13e5d0e806a6b1cde498a5be4', 'f764b8a21ee8ce9f', '用户14', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('19', '用户15', 'fa8b4e3197d6de7ca18895e701aae73c8c05d95e', 'd1cd65349e421985', '用户15', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:31', '2015-06-05 15:55:31');
INSERT INTO `user_basic` VALUES ('20', '用户16', '2cd67404945487b53574d9317bf4fbb2fd106036', '69f4eadd7343cdd0', '用户16', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:32', '2015-06-05 15:55:32');
INSERT INTO `user_basic` VALUES ('21', '用户17', '59586a14ef6ba9ac5eae219a4aa696bd4c444b67', '1d305a9dc32c5575', '用户17', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:32', '2015-06-05 15:55:32');
INSERT INTO `user_basic` VALUES ('22', '用户18', 'a004bee031daa1c89062a50b230c2a2989a00987', 'c06d79983146f8bf', '用户18', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:32', '2015-06-05 15:55:32');
INSERT INTO `user_basic` VALUES ('23', '用户19', 'e18c5afe4add93a5340e2c59933f4787f5b2834c', 'dbeedd2de86ced6e', '用户19', null, '12356789328', '123@qq.com', '0', '2015-06-05 15:55:32', '2015-06-05 15:55:32');
INSERT INTO `user_basic` VALUES ('24', '李四', '5b34465bb941831c3067367bed40cea8f8054ffa', '1d839271e96c6126', '李四', null, '15558053971', '12356@qq.com', '0', '2015-06-05 16:00:26', '2015-06-05 16:00:26');
INSERT INTO `user_basic` VALUES ('25', '王五', '509014a7824c044eb37ba5fa2b3239a5d357d391', '94847e5d4e362d0b', '王五', null, '15558053971', '12356@qq.com', '0', '2015-06-05 16:02:13', '2015-06-05 16:02:13');
INSERT INTO `user_basic` VALUES ('26', '赵六', '151130b48e862f632f17eace4159994f2bb8acda', '16366ac5f1bf0301', '赵六', null, '15558053971', '12356@qq.com', '0', '2015-06-05 16:03:46', '2015-06-05 16:03:46');
INSERT INTO `user_basic` VALUES ('27', '孔明', '182fc05e5bfe8412b984a742a40ec559a11d045e', '95cbb5b97f6c86fd', '孔明', null, '15558053971', '12356@qq.com', '0', '2015-06-05 16:04:47', '2015-06-05 16:04:47');
INSERT INTO `user_basic` VALUES ('28', '云长', '4779e2766d96b7183a69e1a5d0ae796c055a849f', 'f0f6171dc1b801c2', '云长', null, '15558053971', '12356@qq.com', '0', '2015-06-05 16:06:10', '2015-06-05 16:06:10');
INSERT INTO `user_basic` VALUES ('29', '钱八', 'c5486fe09cf37e5f38402a12f5ccd4a11d8fd006', '4c56350d019eecbf', '钱八', null, '15558053971', '12356@qq.com', '0', '2015-06-15 15:14:17', '2015-06-15 15:14:17');
INSERT INTO `user_basic` VALUES ('32', '用户100', '390af9f0f8f9dfda6c4ce37d4d28ebd19bd6f118', 'c685929238aa361b', '钱八', null, '15558053971', '12356@qq.com', '0', '2015-06-15 15:20:28', '2015-06-15 15:20:28');
INSERT INTO `user_basic` VALUES ('33', '管理员1', 'b8f555fc5b610964c325ce9a95191c6bb0c422b8', '5b3bc12ac481c4f9', 'wsh', null, '15558053971', '675783578@qq.com', '0', '2015-06-15 15:27:28', '2015-06-15 15:27:28');
INSERT INTO `user_basic` VALUES ('35', '管理员2', '815f3b069eae7cdfe2cfbb18304ccc6a12c4b016', '5e42677f9f58a464', 'wsh', null, '15558053971', '675783578@qq.com', '0', '2015-06-15 15:28:36', '2015-06-15 15:28:36');
INSERT INTO `user_basic` VALUES ('36', '管理员3', '850a1a7af8ad6cd0dc66b1ec85396f31338b59b2', 'a9dcdc7e04f87627', 'wsh', null, '15558053971', '675783578@qq.com', '0', '2015-06-15 15:31:11', '2015-06-15 15:31:11');
INSERT INTO `user_basic` VALUES ('37', '管理员5', '6766d24a58935a7380fa682a279d2c36a4408eea', 'bfe02f3604da6749', 'wsh', null, '15558053971', '675783578@qq.com', '0', '2015-06-15 15:45:46', '2015-06-15 15:45:46');
INSERT INTO `user_basic` VALUES ('38', '管理员6', '6c83144ed8e89b624889c9aa7b678f690afc3207', '1a82e3634ffa494b', 'wsh', null, '15558053971', '675783578@qq.com', '0', '2015-06-15 15:50:20', '2015-06-15 15:50:20');
INSERT INTO `user_basic` VALUES ('40', '管理员7', '10a2266946345bfbfa2d371f341695d3b7150a90', 'cb8a7544c3385ca1', 'wsh', null, '15558053971', '675783578@qq.com', '1', '2015-06-15 15:52:06', '2015-06-15 15:52:06');
INSERT INTO `user_basic` VALUES ('43', '用户28', 'bc81b7a4db6de6f2e8b52fee32407be038785515', 'ee062c53ad3d3ea3', '用户2', null, '15558053971', '675783578@qq.com', '1', '2015-06-17 13:57:28', '2015-06-17 13:57:52');
INSERT INTO `user_basic` VALUES ('44', '孙七', 'e993666541ec2cc3a0e228fbb77c688bd8cb43e8', '84b35e59571a110c', '孙七1', null, '15558053971', '675783578@qq.com', '1', '2015-06-17 17:26:20', '2015-06-17 17:26:39');

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `group_id` bigint(20) NOT NULL COMMENT '组ID',
  `gmt_created` datetime NOT NULL COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息组关联表';

-- ----------------------------
-- Records of user_group
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `gmt_created` datetime NOT NULL COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('3', '4', '2', '1', '2015-06-25 10:32:54', '2015-06-25 10:32:54');
INSERT INTO `user_role` VALUES ('4', '1', '1', '1', '2015-06-25 10:44:41', '2015-06-25 10:44:41');
