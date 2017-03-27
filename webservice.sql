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

 Date: 03/27/2017 22:44:52 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `T_Menu`
-- ----------------------------
BEGIN;
INSERT INTO `T_Menu` VALUES ('1', '001001', '用户管理', '001', '0', '', '', '', '0'), ('2', '001002', '角色管理', '001', '0', '', '', '', '0'), ('3', '001003', '菜单管理', '001', '0', '', '', '', '0'), ('4', '001', '信息管理', 'root', '0', '', '', '', '0'), ('6', '001001001', '用户浏览', '001001', '0', 'peoplegrid', 'core.basicinfomanage.peoplemanage.view.PeopleGrid', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0'), ('7', '001001002', '添加用户', '001001', '0', 'addpeople', 'core.basicinfomanage.peoplemanage.view.AddPeople', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0'), ('8', '001001003', '修改用户', '001001', '0', 'updatepeoplegrid', 'core.basicinfomanage.peoplemanage.view.UpdatePeopleGrid', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0'), ('9', '001001004', '删除用户', '001001', '0', 'deletepeoplegrid', 'core.basicinfomanage.peoplemanage.view.DeletePeopleGrid', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0'), ('10', '001001005', '用户角色设置', '001001', '0', 'setrolepeoplegrid', 'core.basicinfomanage.peoplemanage.view.SetRolePeopleGrid', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0'), ('11', '001002001', '角色浏览', '001002', '0', 'rolegrid', 'core.basicinfomanage.rolemanage.view.RoleGrid', 'core.basicinfomanage.rolemanage.controller.RoleController', '0'), ('12', '001002002', '添加角色', '001002', '0', 'addrole', 'core.basicinfomanage.rolemanage.view.AddRole', 'core.basicinfomanage.rolemanage.controller.RoleController', '0'), ('13', '001002003', '角色删除', '001002', '0', 'deleterolegrid', 'core.basicinfomanage.rolemanage.view.DeleteRoleGrid', 'core.basicinfomanage.rolemanage.controller.RoleController', '0'), ('14', '001002004', '角色菜单设置', '001002', '0', 'setrolemenugrid', 'core.basicinfomanage.rolemanage.view.SetRoleMenuGrid', 'core.basicinfomanage.rolemanage.controller.RoleController', '0'), ('15', '001002005', '角色修改', '001002', '0', 'updaterolegrid', 'core.basicinfomanage.rolemanage.view.UpdateRoleGrid', 'core.basicinfomanage.rolemanage.controller.RoleController', '0'), ('16', '001003001', '菜单浏览', '001003', '0', 'menugrid', 'core.basicinfomanage.menumanage.view.MenuGrid', 'core.basicinfomanage.menumanage.controller.MenuController', '0'), ('17', '001003002', '增加菜单', '001003', '0', 'addmenu', 'core.basicinfomanage.menumanage.view.AddMenu', 'core.basicinfomanage.menumanage.controller.MenuController', '0'), ('18', '001003003', '删除菜单', '001003', '0', 'deletemenugrid', 'core.basicinfomanage.menumanage.view.DeleteMenuGrid', 'core.basicinfomanage.menumanage.controller.MenuController', '0'), ('19', '001003004', '更新菜单', '001003', '0', 'updatemenugrid', 'core.basicinfomanage.menumanage.view.UpdateMenuGrid', 'core.basicinfomanage.menumanage.controller.MenuController', '0'), ('20', 'root', 'root', 'null', '0', '', '', '', '0'), ('21', '002', '服务管理', 'root', '0', '', '', '', '0'), ('22', '002001', '基础服务管理', '002', '0', '', '', '', '0'), ('23', '002001001', '服务浏览', '002001', '0', 'wsgrid', 'core.basicinfomanage.wsmanage.view.WsGrid', 'core.basicinfomanage.wsmanage.controller.WsController', '0'), ('24', '002001002', '服务添加', '002001', '0', 'addws', 'core.basicinfomanage.wsmanage.view.AddWS', 'core.basicinfomanage.wsmanage.controller.WsController', '0'), ('25', '002001003', '服务修改', '002001', '0', 'updatewsgrid', 'core.basicinfomanage.wsmanage.view.UpdateWsGrid', 'core.basicinfomanage.wsmanage.controller.WsController', '0'), ('26', '002001004', '服务删除', '002001', '0', 'deletewsgrid', 'core.basicinfomanage.wsmanage.view.DeleteWsGrid', 'core.basicinfomanage.wsmanage.controller.WsController', '0');
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_RoleMenu`
-- ----------------------------
BEGIN;
INSERT INTO `T_RoleMenu` VALUES ('2', '001001001', '1', '0', '0'), ('3', '001001002', '1', '0', '0'), ('4', '001001003', '1', '0', '0'), ('5', '001001004', '1', '0', '0'), ('6', '001001005', '1', '0', '0'), ('7', '001002001', '1', '0', '0'), ('8', '001002002', '1', '0', '0'), ('9', '001002003', '1', '0', '0'), ('10', '001002004', '1', '0', '0'), ('11', '001002005', '1', '0', '0'), ('12', '001003001', '1', '0', '0'), ('13', '001003002', '1', '0', '0'), ('14', '001003003', '1', '0', '0'), ('15', '001003004', '1', '0', '0'), ('16', '002001001', '1', '0', '0'), ('17', '002001002', '1', '0', '0'), ('18', '002001003', '1', '0', '0'), ('19', '002001004', '1', '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `T_Service`
-- ----------------------------
DROP TABLE IF EXISTS `T_Service`;
CREATE TABLE `T_Service` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `servicename` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `wsdlurl` varchar(255) NOT NULL,
  `targetnamespace` varchar(255) NOT NULL,
  `method` varchar(255) NOT NULL,
  `version` tinyint(4) NOT NULL DEFAULT '1',
  `state` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_Service`
-- ----------------------------
BEGIN;
INSERT INTO `T_Service` VALUES ('1', 'weather', '天气服务', 'http://ws.webxml.com.cn/WebServices/WeatherWS.asmx', 'http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl', 'http://WebXml.com.cn/', 'getWeather', '2', '0'), ('4', 'MobileCodeInfo', '手机归属地服务', 'http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx', 'http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl', 'http://WebXml.com.cn/', 'getMobileCodeInfo', '2', '0');
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
--  Table structure for `T_ServiceParam`
-- ----------------------------
DROP TABLE IF EXISTS `T_ServiceParam`;
CREATE TABLE `T_ServiceParam` (
  `autoid` bigint(20) NOT NULL AUTO_INCREMENT,
  `sid` bigint(20) NOT NULL,
  `paramname` varchar(255) NOT NULL,
  `remark` varchar(255) NOT NULL DEFAULT '',
  `state` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `T_ServiceParam`
-- ----------------------------
BEGIN;
INSERT INTO `T_ServiceParam` VALUES ('8', '1', 'theCityCode', '城市名', '0'), ('9', '1', 'theUserID', '用户id', '0');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_User`
-- ----------------------------
BEGIN;
INSERT INTO `T_User` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', '管理员账号', '0'), ('2', 'test', '098f6bcd4621d373cade4e832627b4f6', 'test', '测试账号', '0'), ('3', 'boss', 'ceb8447cc4ab78d2ec34cd9f11e4bed2', 'boss', '领导', '0'), ('9', 'twogoods', '228ba6f6384004be1473b9eee85be9ac', 'twogoods', '开发。', '0');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_UserRole`
-- ----------------------------
BEGIN;
INSERT INTO `T_UserRole` VALUES ('1', '1', '1', '0'), ('2', '1', '2', '0'), ('4', '2', '2', '0'), ('5', '9', '4', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
