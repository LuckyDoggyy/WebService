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

 Date: 02/27/2017 20:51:44 PM
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
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `T_Menu`
-- ----------------------------
BEGIN;
INSERT INTO `T_Menu` VALUES ('1', 'usermanage', '用户管理', 'basicinfomanage', '0'), ('2', 'rolemanage', '角色管理', 'basicinfomanage', '0'), ('3', 'servicemanage', '服务管理', 'basicinfomanage', '0'), ('4', 'basicinfomanage', '信息管理', '0', '0'), ('6', 'userlist', '用户浏览', 'usermanage', '0'), ('7', 'useradd', '添加用户', 'usermanage', '0'), ('8', 'userupdate', '修改用户', 'usermanage', '0'), ('9', 'userdelete', '删除用户', 'usermanage', '0'), ('10', 'userrolesetting', '用户角色设置', 'usermanage', '0'), ('11', 'rolelist', '角色浏览', 'rolemanage', '0'), ('12', 'roleadd', '添加角色', 'rolemanage', '0'), ('13', 'roledelete', '角色删除', 'rolemanage', '0'), ('14', 'rolemenusetting', '角色菜单设置', 'rolemanage', '0'), ('15', 'roleupdate', '角色修改', 'rolemanage', '0'), ('16', 'servicelist', '查询服务', 'servicemanage', '0'), ('17', 'serviceadd', '增加服务', 'servicemanage', '0'), ('18', 'servicedelete', '删除服务', 'servicemanage', '0'), ('19', 'serviceupdate', '更新服务', 'servicemanage', '0');
COMMIT;

-- ----------------------------
--  Table structure for `T_Role`
-- ----------------------------
DROP TABLE IF EXISTS `T_Role`;
CREATE TABLE `T_Role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
INSERT INTO `T_RoleMenu` VALUES ('1', 'userlist', '1', '0', '0'), ('2', 'useradd', '1', '0', '0'), ('3', 'userupdate', '1', '0', '0'), ('4', 'userdelete', '1', '0', '0'), ('5', 'userrolesetting', '1', '0', '0'), ('6', 'rolelist', '1', '0', '0'), ('7', 'roleadd', '1', '0', '0'), ('8', 'roledelete', '1', '0', '0'), ('9', 'rolemenusetting', '1', '0', '0'), ('10', 'roleupdate', '1', '0', '0'), ('11', 'servicelist', '1', '0', '0'), ('12', 'serviceadd', '1', '0', '0'), ('13', 'servicedelete', '1', '0', '0'), ('14', 'serviceupdate', '1', '0', '0');
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
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `state` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_User`
-- ----------------------------
BEGIN;
INSERT INTO `T_User` VALUES ('1', 'admin', 'admin', '0'), ('2', 'test', 'test', '0'), ('3', 'boss', 'boss', '0'), ('9', 'twogoods', 'twogoods', '0');
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
