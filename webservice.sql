/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : webservice

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 03/13/2017 19:51:34 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `T_Menu`
-- ----------------------------
DROP TABLE IF EXISTS `T_Menu`;
CREATE TABLE `T_Menu` (
  `autoid` int(11) NOT NULL AUTO_INCREMENT,
  `mid` varchar(255) NOT NULL,
  `menuname` varchar(255) NOT NULL,
  `pid` varchar(255) NOT NULL,
  `order` int(11) NOT NULL DEFAULT '0',
  `viewid` varchar(255) NOT NULL DEFAULT '',
  `viewname` varchar(255) NOT NULL DEFAULT '',
  `viewcontroller` varchar(255) NOT NULL DEFAULT '',
  `state` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `T_Menu`
-- ----------------------------
BEGIN;
INSERT INTO `T_Menu` VALUES ('1', 'usermanage', '用户管理', 'basicinfomanage', '0', '', '', '', '0'), ('2', 'rolemanage', '角色管理', 'basicinfomanage', '0', '', '', '', '0'), ('3', 'menumanage', '菜单管理', 'basicinfomanage', '0', '', '', '', '0'), ('4', 'basicinfomanage', '信息管理', 'root', '0', '', '', '', '0'), ('6', 'userlist', '用户浏览', 'usermanage', '0', 'peoplegrid', 'core.basicinfomanage.peoplemanage.view.PeopleGrid', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0'), ('7', 'useradd', '添加用户', 'usermanage', '0', 'addpeople', 'core.basicinfomanage.peoplemanage.view.AddPeople', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0'), ('8', 'userupdate', '修改用户', 'usermanage', '0', 'updatepeoplegrid', 'core.basicinfomanage.peoplemanage.view.UpdatePeopleGrid', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0'), ('9', 'userdelete', '删除用户', 'usermanage', '0', 'deletepeoplegrid', 'core.basicinfomanage.peoplemanage.view.DeletePeopleGrid', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0'), ('10', 'userrolesetting', '用户角色设置', 'usermanage', '0', 'setrolepeoplegrid', 'core.basicinfomanage.peoplemanage.view.SetRolePeopleGrid', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0'), ('11', 'rolelist', '角色浏览', 'rolemanage', '0', 'rolegrid', 'core.systemmanage.rolemanage.view.RoleGrid', 'core.systemmanage.rolemanage.controller.RoleController', '0'), ('12', 'roleadd', '添加角色', 'rolemanage', '0', 'addrole', 'core.systemmanage.rolemanage.view.AddRole', 'core.systemmanage.rolemanage.controller.RoleController', '0'), ('13', 'roledelete', '角色删除', 'rolemanage', '0', 'deleterolegrid', 'core.systemmanage.rolemanage.view.DeleteRoleGrid', 'core.systemmanage.rolemanage.controller.RoleController', '0'), ('14', 'rolemenusetting', '角色菜单设置', 'rolemanage', '0', 'setrolemenugrid', 'core.systemmanage.rolemanage.view.SetRoleMenuGrid', 'core.systemmanage.rolemanage.controller.RoleController', '0'), ('15', 'roleupdate', '角色修改', 'rolemanage', '0', 'updaterolegrid', 'core.systemmanage.rolemanage.view.UpdateRoleGrid', 'core.systemmanage.rolemanage.controller.RoleController', '0'), ('16', 'menulist', '菜单浏览', 'menumanage', '0', 'menugrid', 'core.basicinfomanage.menumanage.view.MenuGrid', 'core.basicinfomanage.menumanage.controller.MenuController', '0'), ('17', 'menuadd', '增加菜单', 'menumanage', '0', 'addmenu', 'core.basicinfomanage.menumanage.view.AddMenu', 'core.basicinfomanage.menumanage.controller.MenuController', '0'), ('18', 'menudelete', '删除菜单', 'menumanage', '0', 'deletemenugrid', 'core.basicinfomanage.menumanage.view.DeleteMenuGrid', 'core.basicinfomanage.menumanage.controller.MenuController', '0'), ('19', 'menuupdate', '更新菜单', 'menumanage', '0', 'updatemenugrid', 'core.basicinfomanage.menumanage.view.UpdateMenuGrid', 'core.basicinfomanage.menumanage.controller.MenuController', '0'), ('20', 'root', 'root', 'null', '0', '', '', '', '0');
COMMIT;

-- ----------------------------
--  Table structure for `T_Role`
-- ----------------------------
DROP TABLE IF EXISTS `T_Role`;
CREATE TABLE `T_Role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_Role`
-- ----------------------------
BEGIN;
INSERT INTO `T_Role` VALUES ('1', '系统管理员'), ('2', '测试人员'), ('3', '经理'), ('4', '组长');
COMMIT;

-- ----------------------------
--  Table structure for `T_RoleMenu`
-- ----------------------------
DROP TABLE IF EXISTS `T_RoleMenu`;
CREATE TABLE `T_RoleMenu` (
  `autoid` bigint(255) NOT NULL AUTO_INCREMENT,
  `mid` varchar(255) NOT NULL,
  `rid` varchar(255) NOT NULL,
  `order` int(11) NOT NULL DEFAULT '0',
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_RoleMenu`
-- ----------------------------
BEGIN;
INSERT INTO `T_RoleMenu` VALUES ('1', 'userlist', '1', '0', '0'), ('2', 'useradd', '1', '0', '0'), ('3', 'userupdate', '1', '0', '0'), ('4', 'userdelete', '1', '0', '0'), ('5', 'userrolesetting', '1', '0', '0'), ('6', 'rolelist', '1', '0', '0'), ('7', 'roleadd', '1', '0', '0'), ('8', 'roledelete', '1', '0', '0'), ('9', 'rolemenusetting', '1', '0', '0'), ('10', 'roleupdate', '1', '0', '0'), ('11', 'menulist', '1', '0', '0'), ('12', 'menuadd', '1', '0', '0'), ('13', 'menudelete', '1', '0', '0'), ('14', 'menuupdate', '1', '0', '0'), ('15', 'userlist', '2', '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `T_ServiceInfo`
-- ----------------------------
DROP TABLE IF EXISTS `T_ServiceInfo`;
CREATE TABLE `T_ServiceInfo` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `port` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_ServiceInfo`
-- ----------------------------
BEGIN;
INSERT INTO `T_ServiceInfo` VALUES ('1', 'test', '127.0.0.1', '8808', 'test'), ('2', 'test2', '127.0.0.1', '9001', 'test2'), ('3', 'test3', '127.0.0.1', '8000', 'test3');
COMMIT;

-- ----------------------------
--  Table structure for `T_ServiceOwner`
-- ----------------------------
DROP TABLE IF EXISTS `T_ServiceOwner`;
CREATE TABLE `T_ServiceOwner` (
  `autoid` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL,
  `sid` bigint(20) NOT NULL,
  `state` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_ServiceOwner`
-- ----------------------------
BEGIN;
INSERT INTO `T_ServiceOwner` VALUES ('1', '1', '1', '0'), ('2', '1', '2', '1'), ('3', '1', '2', '0'), ('4', '2', '3', '0');
COMMIT;

-- ----------------------------
--  Table structure for `T_User`
-- ----------------------------
DROP TABLE IF EXISTS `T_User`;
CREATE TABLE `T_User` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickName` varchar(255) NOT NULL DEFAULT '',
  `remarks` varchar(255) NOT NULL DEFAULT '',
  `state` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`),
  KEY `name` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=138252 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_User`
-- ----------------------------
BEGIN;
INSERT INTO `T_User` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', '管理员账号', '0'), ('2', 'test', '098f6bcd4621d373cade4e832627b4f6', 'test', '测试账号', '0'), ('3', 'boss', 'ceb8447cc4ab78d2ec34cd9f11e4bed2', 'boss', '领导', '0'), ('9', 'twogoods', '228ba6f6384004be1473b9eee85be9ac', 'twogoods', '开发', '0');
COMMIT;

-- ----------------------------
--  Table structure for `T_UserRole`
-- ----------------------------
DROP TABLE IF EXISTS `T_UserRole`;
CREATE TABLE `T_UserRole` (
  `autoid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  `state` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_UserRole`
-- ----------------------------
BEGIN;
INSERT INTO `T_UserRole` VALUES ('1', '1', '1', '0'), ('2', '1', '2', '0'), ('4', '2', '2', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
