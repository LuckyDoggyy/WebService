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

 Date: 02/20/2017 22:11:33 PM
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
  `pmenuname` varchar(255) NOT NULL,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `T_Menu`
-- ----------------------------
BEGIN;
INSERT INTO `T_Menu` VALUES ('1', 'usermanage', '用户管理', 'basicinfomanage', '基本信息管理'), ('2', 'servicemanage', 'web服务管理', 'basicinfomanage', '基本信息管理'), ('3', 'rolemanage', '角色管理', 'systemmanage', '系统管理');
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
  `menuname` varchar(255) NOT NULL,
  `pid` varchar(255) NOT NULL,
  `pmenuname` varchar(255) NOT NULL,
  `rid` varchar(255) NOT NULL,
  `orderindex` int(11) NOT NULL DEFAULT '0',
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_RoleMenu`
-- ----------------------------
BEGIN;
INSERT INTO `T_RoleMenu` VALUES ('1', 'usermanage', '用户管理', 'basicinfomanage', '基本信息管理', '1', '0', '0'), ('2', 'servicemanage', 'web服务管理', 'basicinfomanage', '基本信息管理', '1', '0', '0'), ('3', 'rolemanage', '角色管理', 'systemmanage', '系统管理', '1', '0', '0'), ('9', 'servicemanage', 'web服务管理', 'basicinfomanage', '基本信息管理', '2', '0', '0');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_User`
-- ----------------------------
BEGIN;
INSERT INTO `T_User` VALUES ('1', 'admin', 'admin', '0'), ('2', 'test', 'test', '0'), ('3', 'boss', 'boss', '0');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_UserRole`
-- ----------------------------
BEGIN;
INSERT INTO `T_UserRole` VALUES ('1', '1', '1', '0'), ('2', '1', '2', '1'), ('3', '2', '2', '0'), ('7', '1', '2', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
