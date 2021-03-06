/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : webservice

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 07/12/2017 22:56:28 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `T_Flow`
-- ----------------------------
DROP TABLE IF EXISTS `T_Flow`;
CREATE TABLE `T_Flow` (
  `autoid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `flowid` varchar(11) NOT NULL,
  `flowname` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `flowjson` text NOT NULL,
  `input` varchar(255) NOT NULL DEFAULT '',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0启用，1停用，2删除',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `T_Flow`
-- ----------------------------
BEGIN;
INSERT INTO `T_Flow` VALUES ('1', '1', '手机天气查询', 'weatherCheckByMobile', '通过手机号查询天气', '{\"states\":{\"rect1\":{\"type\":\"start\",\"text\":{\"text\":\"开始\"},\"attr\":{\"x\":380,\"y\":29,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"开始\"}}},\"rect2\":{\"type\":\"end\",\"text\":{\"text\":\"结束\"},\"attr\":{\"x\":381,\"y\":448,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"结束\"}}},\"rect3\":{\"type\":\"invoke\",\"text\":{\"text\":\"手机\"},\"attr\":{\"x\":381,\"y\":227,\"width\":100,\"height\":50},\"props\":{\"serviceId\":{\"value\":\"4\"},\"text\":{\"value\":\"手机\"},\"desc\":{\"value\":\"\"},\"input\":{\"value\":\"mobile:\"},\"output\":{\"value\":\"city:\"}}},\"rect4\":{\"type\":\"invoke\",\"text\":{\"text\":\"天气\"},\"attr\":{\"x\":381,\"y\":334,\"width\":100,\"height\":50},\"props\":{\"serviceId\":{\"value\":\"1\"},\"text\":{\"value\":\"天气\"},\"desc\":{\"value\":\"\"},\"input\":{\"value\":\"city:\"},\"output\":{\"value\":\"weathers:\"}}},\"rect5\":{\"type\":\"receive\",\"text\":{\"text\":\"接收\"},\"attr\":{\"x\":380,\"y\":126,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"接收\"},\"desc\":{\"value\":\"输入\"},\"input\":{\"value\":\"mobile:\"}}}},\"paths\":{\"path6\":{\"from\":\"rect1\",\"to\":\"rect5\",\"dots\":[],\"text\":{\"text\":\"TO 接收\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}},\"path7\":{\"from\":\"rect5\",\"to\":\"rect3\",\"dots\":[],\"text\":{\"text\":\"TO 手机\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}},\"path8\":{\"from\":\"rect3\",\"to\":\"rect4\",\"dots\":[],\"text\":{\"text\":\"TO 天气\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}},\"path9\":{\"from\":\"rect4\",\"to\":\"rect2\",\"dots\":[],\"text\":{\"text\":\"TO 结束\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}}},\"props\":{\"props\":{\"name\":{\"value\":\"手机天气查询\"},\"flowid\":{\"value\":\"weatherCheckByMobile\"},\"desc\":{\"value\":\"通过手机号查询天气\"}}}}', 'mobile', '0', '2017-04-14 21:22:43', '2017-07-12 19:36:54'), ('2', '1', '出行', '航班火车', '出行', '{\"states\":{\"rect1\":{\"type\":\"start\",\"text\":{\"text\":\"开始\"},\"attr\":{\"x\":419,\"y\":39,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"开始\"}}},\"rect2\":{\"type\":\"receive\",\"text\":{\"text\":\"接收\"},\"attr\":{\"x\":419,\"y\":118,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"接收\"},\"desc\":{\"value\":\"\"},\"input\":{\"value\":\"mobile:,destination:\"}}},\"rect3\":{\"type\":\"invoke\",\"text\":{\"text\":\"手机归属地\"},\"attr\":{\"x\":421,\"y\":203,\"width\":100,\"height\":50},\"props\":{\"serviceId\":{\"value\":\"4\"},\"text\":{\"value\":\"手机归属地\"},\"desc\":{\"value\":\"\"},\"input\":{\"value\":\"mobile:\"},\"output\":{\"value\":\"city:\"}}},\"rect4\":{\"type\":\"if\",\"text\":{\"text\":\"判断\"},\"attr\":{\"x\":422,\"y\":294,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"判断地区\"},\"desc\":{\"value\":\"\"},\"input\":{\"value\":\"city:\"},\"judge\":{\"value\":\"city==杭州\"}}},\"rect5\":{\"type\":\"invoke\",\"text\":{\"text\":\"航班查询\"},\"attr\":{\"x\":334,\"y\":386,\"width\":100,\"height\":50},\"props\":{\"serviceId\":{\"value\":\"6\"},\"text\":{\"value\":\"航班查询\"},\"desc\":{\"value\":\"\"},\"input\":{\"value\":\"city:,destination:\"},\"output\":{\"value\":\"AirlinesTime:\"}}},\"rect6\":{\"type\":\"invoke\",\"text\":{\"text\":\"火车票查询\"},\"attr\":{\"x\":516,\"y\":392,\"width\":100,\"height\":50},\"props\":{\"serviceId\":{\"value\":\"7\"},\"text\":{\"value\":\"火车票查询\"},\"desc\":{\"value\":\"\"},\"input\":{\"value\":\"city:,destination:\"},\"output\":{\"value\":\"TimeTable:\"}}},\"rect7\":{\"type\":\"reply\",\"text\":{\"text\":\"返回\"},\"attr\":{\"x\":425,\"y\":495,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"返回\"},\"desc\":{\"value\":\"\"},\"output\":{\"value\":\"AirlinesTime:,TimeTable:\"}}},\"rect8\":{\"type\":\"end\",\"text\":{\"text\":\"结束\"},\"attr\":{\"x\":425,\"y\":593,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"结束\"}}}},\"paths\":{\"path9\":{\"from\":\"rect1\",\"to\":\"rect2\",\"dots\":[],\"text\":{\"text\":\"TO 接收\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}},\"path10\":{\"from\":\"rect2\",\"to\":\"rect3\",\"dots\":[],\"text\":{\"text\":\"TO 调用\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}},\"path11\":{\"from\":\"rect3\",\"to\":\"rect4\",\"dots\":[],\"text\":{\"text\":\"TO 判断\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}},\"path12\":{\"from\":\"rect4\",\"to\":\"rect6\",\"dots\":[],\"text\":{\"text\":\"false\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"false\"}}},\"path13\":{\"from\":\"rect6\",\"to\":\"rect7\",\"dots\":[],\"text\":{\"text\":\"TO 返回\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}},\"path14\":{\"from\":\"rect7\",\"to\":\"rect8\",\"dots\":[],\"text\":{\"text\":\"TO 结束\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}},\"path15\":{\"from\":\"rect5\",\"to\":\"rect7\",\"dots\":[],\"text\":{\"text\":\"TO 返回\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}},\"path16\":{\"from\":\"rect4\",\"to\":\"rect5\",\"dots\":[],\"text\":{\"text\":\"true\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"true\"}}}},\"props\":{\"props\":{\"name\":{\"value\":\"新建流程\"},\"flowid\":{\"value\":\"标识\"},\"desc\":{\"value\":\"描述\"}}}}', 'mobile,destination', '0', '2017-05-23 16:51:02', '2017-07-12 19:36:55'), ('3', '1', '测试', '测试', '测试', '{\"states\":{\"rect1\":{\"type\":\"start\",\"text\":{\"text\":\"开始\"},\"attr\":{\"x\":437,\"y\":110,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"开始\"}}},\"rect2\":{\"type\":\"end\",\"text\":{\"text\":\"结束\"},\"attr\":{\"x\":476,\"y\":266,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"结束\"}}}},\"paths\":{\"path3\":{\"from\":\"rect1\",\"to\":\"rect2\",\"dots\":[],\"text\":{\"text\":\"TO 结束\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}}},\"props\":{\"props\":{\"name\":{\"value\":\"新建流程\"},\"flowid\":{\"value\":\"标识\"},\"desc\":{\"value\":\"描述\"}}}}', 'test', '0', '2017-06-14 22:24:05', '2017-07-12 19:36:56'), ('4', '1', '天气copy', '天气', '天气', '{\"states\":{\"rect1\":{\"type\":\"start\",\"text\":{\"text\":\"开始\"},\"attr\":{\"x\":265,\"y\":44,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"开始\"}}},\"rect2\":{\"type\":\"receive\",\"text\":{\"text\":\"接收\"},\"attr\":{\"x\":257,\"y\":152,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"接收\"},\"desc\":{\"value\":\"\"},\"input\":{\"value\":\"mobile:\"}}},\"rect3\":{\"type\":\"invoke\",\"text\":{\"text\":\"shouji\"},\"attr\":{\"x\":270,\"y\":263,\"width\":100,\"height\":50},\"props\":{\"serviceId\":{\"value\":\"4\"},\"text\":{\"value\":\"shouji\"},\"desc\":{\"value\":\"shouji\"},\"input\":{\"value\":\"mobile:\"},\"output\":{\"value\":\"city:\"}}},\"rect4\":{\"type\":\"end\",\"text\":{\"text\":\"结束\"},\"attr\":{\"x\":270,\"y\":493,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"结束\"}}},\"rect5\":{\"type\":\"invoke\",\"text\":{\"text\":\"tianqi\"},\"attr\":{\"x\":273,\"y\":382,\"width\":100,\"height\":50},\"props\":{\"serviceId\":{\"value\":\"1\"},\"text\":{\"value\":\"tianqi\"},\"desc\":{\"value\":\"\"},\"input\":{\"value\":\"city:\"},\"output\":{\"value\":\"weathers:\"}}}},\"paths\":{\"path6\":{\"from\":\"rect1\",\"to\":\"rect2\",\"dots\":[],\"text\":{\"text\":\"TO 接收\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}},\"path7\":{\"from\":\"rect2\",\"to\":\"rect3\",\"dots\":[],\"text\":{\"text\":\"TO shouji\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}},\"path8\":{\"from\":\"rect3\",\"to\":\"rect5\",\"dots\":[],\"text\":{\"text\":\"TO tianqi\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}},\"path9\":{\"from\":\"rect5\",\"to\":\"rect4\",\"dots\":[],\"text\":{\"text\":\"TO 结束\"},\"textPos\":{\"x\":0,\"y\":-10},\"props\":{\"text\":{\"value\":\"\"}}}},\"props\":{\"props\":{\"name\":{\"value\":\"新建流程\"},\"flowid\":{\"value\":\"标识\"},\"desc\":{\"value\":\"描述\"}}}}', 'mobile', '0', '2017-06-25 10:11:28', '2017-07-12 20:29:34'), ('5', '1', '新建流程', '标识', '描述', '{\"states\":{\"rect1\":{\"type\":\"start\",\"text\":{\"text\":\"开始\"},\"attr\":{\"x\":236,\"y\":97,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"开始\"}}}},\"paths\":{},\"props\":{\"props\":{\"name\":{\"value\":\"新建流程\"},\"flowid\":{\"value\":\"标识\"},\"tagid\":{\"value\":\"2\"},\"desc\":{\"value\":\"描述\"}}}}', '', '0', '2017-07-12 11:16:37', '2017-07-12 20:29:29'), ('6', '1', '新建流程', '标识', '描述', '{\"states\":{\"rect1\":{\"type\":\"start\",\"text\":{\"text\":\"开始\"},\"attr\":{\"x\":260,\"y\":65,\"width\":100,\"height\":50},\"props\":{\"text\":{\"value\":\"开始\"}}}},\"paths\":{},\"props\":{\"props\":{\"name\":{\"value\":\"新建流程\"},\"flowid\":{\"value\":\"标识\"},\"tagid\":{\"value\":\"1\"},\"desc\":{\"value\":\"描述\"}}}}', '', '1', '2017-07-12 12:33:47', '2017-07-12 19:36:59');
COMMIT;

-- ----------------------------
--  Table structure for `T_FlowTag`
-- ----------------------------
DROP TABLE IF EXISTS `T_FlowTag`;
CREATE TABLE `T_FlowTag` (
  `autoid` int(11) NOT NULL AUTO_INCREMENT,
  `flowid` int(11) NOT NULL,
  `tagid` int(11) NOT NULL,
  `state` tinyint(4) DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastUpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`autoid`),
  UNIQUE KEY `unique` (`flowid`,`tagid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `T_FlowTag`
-- ----------------------------
BEGIN;
INSERT INTO `T_FlowTag` VALUES ('1', '1', '2', '0', '2017-07-12 21:51:18', '2017-07-12 21:51:18'), ('7', '6', '2', '0', '2017-07-12 22:40:43', '2017-07-12 22:40:43'), ('8', '5', '2', '0', '2017-07-12 22:40:35', '2017-07-12 22:40:35'), ('9', '4', '2', '0', '2017-07-12 22:40:26', '2017-07-12 22:40:26'), ('10', '1', '3', '1', '2017-07-12 22:34:39', '2017-07-12 22:34:42'), ('11', '2', '2', '0', '2017-07-12 22:39:51', '2017-07-12 22:39:51'), ('12', '3', '2', '0', '2017-07-12 22:39:57', '2017-07-12 22:39:57');
COMMIT;

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
  `fid` int(11) NOT NULL DEFAULT '0',
  `state` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `T_Menu`
-- ----------------------------
BEGIN;
INSERT INTO `T_Menu` VALUES ('1', '001001', '用户管理', '001', '0', '', '', '', '0', '0'), ('2', '001002', '角色管理', '001', '0', '', '', '', '0', '0'), ('3', '001003', '菜单管理', '001', '0', '', '', '', '0', '0'), ('4', '001', '信息管理', 'root', '0', '', '', '', '0', '0'), ('6', '001001001', '用户浏览', '001001', '0', 'peoplegrid', 'core.basicinfomanage.peoplemanage.view.PeopleGrid', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0', '0'), ('7', '001001002', '添加用户', '001001', '0', 'addpeople', 'core.basicinfomanage.peoplemanage.view.AddPeople', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0', '0'), ('8', '001001003', '修改用户', '001001', '0', 'updatepeoplegrid', 'core.basicinfomanage.peoplemanage.view.UpdatePeopleGrid', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0', '0'), ('9', '001001004', '删除用户', '001001', '0', 'deletepeoplegrid', 'core.basicinfomanage.peoplemanage.view.DeletePeopleGrid', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0', '0'), ('10', '001001005', '用户角色设置', '001001', '0', 'setrolepeoplegrid', 'core.basicinfomanage.peoplemanage.view.SetRolePeopleGrid', 'core.basicinfomanage.peoplemanage.controller.PeopleController', '0', '0'), ('11', '001002001', '角色浏览', '001002', '0', 'rolegrid', 'core.basicinfomanage.rolemanage.view.RoleGrid', 'core.basicinfomanage.rolemanage.controller.RoleController', '0', '0'), ('12', '001002002', '添加角色', '001002', '0', 'addrole', 'core.basicinfomanage.rolemanage.view.AddRole', 'core.basicinfomanage.rolemanage.controller.RoleController', '0', '0'), ('13', '001002003', '角色删除', '001002', '0', 'deleterolegrid', 'core.basicinfomanage.rolemanage.view.DeleteRoleGrid', 'core.basicinfomanage.rolemanage.controller.RoleController', '0', '0'), ('14', '001002004', '角色菜单设置', '001002', '0', 'setrolemenugrid', 'core.basicinfomanage.rolemanage.view.SetRoleMenuGrid', 'core.basicinfomanage.rolemanage.controller.RoleController', '0', '0'), ('15', '001002005', '角色修改', '001002', '0', 'updaterolegrid', 'core.basicinfomanage.rolemanage.view.UpdateRoleGrid', 'core.basicinfomanage.rolemanage.controller.RoleController', '0', '0'), ('16', '001003001', '菜单浏览', '001003', '0', 'menugrid', 'core.basicinfomanage.menumanage.view.MenuGrid', 'core.basicinfomanage.menumanage.controller.MenuController', '0', '0'), ('17', '001003002', '增加菜单', '001003', '0', 'addmenu', 'core.basicinfomanage.menumanage.view.AddMenu', 'core.basicinfomanage.menumanage.controller.MenuController', '0', '0'), ('18', '001003003', '删除菜单', '001003', '0', 'deletemenugrid', 'core.basicinfomanage.menumanage.view.DeleteMenuGrid', 'core.basicinfomanage.menumanage.controller.MenuController', '0', '0'), ('19', '001003004', '更新菜单', '001003', '0', 'updatemenugrid', 'core.basicinfomanage.menumanage.view.UpdateMenuGrid', 'core.basicinfomanage.menumanage.controller.MenuController', '0', '0'), ('20', 'root', 'root', 'null', '0', '', '', '', '0', '0'), ('21', '002', '服务管理', 'root', '0', '', '', '', '0', '0'), ('22', '002001', '基础服务管理', '002', '0', '', '', '', '0', '0'), ('23', '002001001', '服务浏览', '002001', '0', 'wsgrid', 'core.basicinfomanage.wsmanage.view.WsGrid', 'core.basicinfomanage.wsmanage.controller.WsController', '0', '0'), ('24', '002001002', '服务添加', '002001', '0', 'addws', 'core.basicinfomanage.wsmanage.view.AddWS', 'core.basicinfomanage.wsmanage.controller.WsController', '0', '0'), ('25', '002001003', '服务修改', '002001', '0', 'updatewsgrid', 'core.basicinfomanage.wsmanage.view.UpdateWsGrid', 'core.basicinfomanage.wsmanage.controller.WsController', '0', '0'), ('26', '002001004', '服务删除', '002001', '0', 'deletewsgrid', 'core.basicinfomanage.wsmanage.view.DeleteWsGrid', 'core.basicinfomanage.wsmanage.controller.WsController', '0', '0'), ('27', '002002', '业务流程管理', '002', '0', '', '', '', '0', '0'), ('28', '002002001', '流程查看', '002002', '0', 'bpgrid', 'core.servicemanage.bpelmanage.view.BPGrid', 'core.servicemanage.bpelmanage.controller.BpelController', '0', '0'), ('29', '002002002', '添加流程', '002002', '0', 'addbp', 'core.servicemanage.bpelmanage.view.AddBP', 'core.servicemanage.bpelmanage.controller.BpelController', '0', '0'), ('30', '002002003', '修改流程', '002002', '0', 'updatebpgrid', 'core.servicemanage.bpelmanage.view.UpdateBPGrid', 'core.servicemanage.bpelmanage.controller.BpelController', '0', '0'), ('31', '002002004', '启用流程', '002002', '0', 'deletebpgrid', 'core.servicemanage.bpelmanage.view.DeleteBPGrid', 'core.servicemanage.bpelmanage.controller.BpelController', '0', '0'), ('35', '002002005', '用户禁用', '002002', '0', 'bpusergrid', 'core.servicemanage.bpelmanage.view.BPUserGrid', 'core.servicemanage.bpelmanage.controller.BpelController', '0', '0'), ('36', '003', '应用服务', 'root', '0', '', '', '', '0', '0'), ('37', '003001', '生活服务', '003', '0', 'serviceitem', 'core.service.view.ServiceItem', 'core.service.controller.ServiceController', '0', '0'), ('38', '003001001', '天气服务', '003001', '0', 'serviceitem', 'core.service.view.ServiceItem', 'core.service.controller.ServiceController', '1', '0'), ('39', '002003', '流程分类管理', '002', '0', '', '', '', '0', '0'), ('40', '002003001', '分类查看', '002003', '0', 'taggrid', 'core.servicemanage.tagmanage.view.TagGrid', 'core.servicemanage.tagmanage.controller.TagController', '0', '0'), ('41', '002003002', '添加分类', '002003', '0', 'addtag', 'core.servicemanage.tagmanage.view.AddTag', 'core.servicemanage.tagmanage.controller.TagController', '0', '0'), ('42', '002003003', '更新分类', '002003', '0', 'updatetaggrid', 'core.servicemanage.tagmanage.view.UpdateTagGrid', 'core.servicemanage.tagmanage.controller.TagController', '0', '0'), ('43', '002003004', '删除分类', '002003', '0', 'deletetaggrid', 'core.servicemanage.tagmanage.view.DeleteTagGrid', 'core.servicemanage.tagmanage.controller.TagController', '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `T_Role`
-- ----------------------------
DROP TABLE IF EXISTS `T_Role`;
CREATE TABLE `T_Role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_Role`
-- ----------------------------
BEGIN;
INSERT INTO `T_Role` VALUES ('1', '终端用户'), ('2', '系统管理员'), ('3', '测试人员'), ('4', '经理'), ('5', '组长');
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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_RoleMenu`
-- ----------------------------
BEGIN;
INSERT INTO `T_RoleMenu` VALUES ('27', '001001001', '2', '0', '0'), ('28', '001001002', '2', '0', '0'), ('29', '001001003', '2', '0', '0'), ('30', '001001004', '2', '0', '0'), ('31', '001001005', '2', '0', '0'), ('32', '001002001', '2', '0', '0'), ('33', '001002002', '2', '0', '0'), ('34', '001002003', '2', '0', '0'), ('35', '001002004', '2', '0', '0'), ('36', '001002005', '2', '0', '0'), ('37', '001003001', '2', '0', '0'), ('38', '001003002', '2', '0', '0'), ('39', '001003003', '2', '0', '0'), ('40', '001003004', '2', '0', '0'), ('41', '002001001', '2', '0', '0'), ('42', '002001002', '2', '0', '0'), ('43', '002001003', '2', '0', '0'), ('44', '002001004', '2', '0', '0'), ('45', '002002001', '2', '0', '0'), ('46', '002002002', '2', '0', '0'), ('47', '002002003', '2', '0', '0'), ('48', '002002004', '2', '0', '0'), ('51', '002002005', '2', '0', '0'), ('52', '002003001', '2', '0', '0'), ('53', '002003002', '2', '0', '0'), ('54', '002003003', '2', '0', '0'), ('55', '002003004', '2', '0', '0');
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
  `output` text,
  `version` tinyint(4) NOT NULL DEFAULT '1',
  `state` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_Service`
-- ----------------------------
BEGIN;
INSERT INTO `T_Service` VALUES ('1', 'weather', '天气服务', 'http://ws.webxml.com.cn/WebServices/WeatherWS.asmx', 'http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl', 'http://WebXml.com.cn/', 'getWeather', 'root.soap:Envelope.soap:Envelope\nsoap:Envelope.soap:Body.soap:Body\nsoap:Body.getWeatherResponse.getWeatherResponse\ngetWeatherResponse.getWeatherResult.getWeatherResult\ngetWeatherResult.string(weathers).string', '2', '0'), ('4', 'MobileCodeInfo', '手机归属地服务', 'http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx', 'http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl', 'http://WebXml.com.cn/', 'getMobileCodeInfo', 'root.soap:Envelope.soap:Envelope\nsoap:Envelope.soap:Body.soap:Body\nsoap:Body.getMobileCodeInfoResponse.getMobileCodeInfoResponse\ngetMobileCodeInfoResponse.getMobileCodeInfoResult.string.[{mobile}：{province} {city} {property}]', '2', '0'), ('5', '中英文翻译', '中英文翻译', 'http://fy.webxml.com.cn/webservices/EnglishChinese.asmx', 'http://fy.webxml.com.cn/webservices/EnglishChinese.asmx?wsdl', 'http://WebXml.com.cn/', 'TranslatorString', '', '2', '0'), ('6', '航班查询', '航班查询服务', 'http://ws.webxml.com.cn/webservices/DomesticAirline.asmx', 'http://ws.webxml.com.cn/webservices/DomesticAirline.asmx?wsdl', 'http://WebXml.com.cn/', 'getDomesticAirlinesTime', 'root.soap:Envelope.soap:Envelope\nsoap:Envelope.soap:Body.soap:Body\nsoap:Body.getDomesticAirlinesTimeResponse.getDomesticAirlinesTimeResponse\ngetDomesticAirlinesTimeResponse.getDomesticAirlinesTimeResult.getDomesticAirlinesTimeResult\ngetDomesticAirlinesTimeResult.diffgr:diffgram.diffgr:diffgram\ndiffgr:diffgram.Airlines.Airlines\nAirlines.AirlinesTime.AirlinesTime\nAirlinesTime.Company.string\nAirlinesTime.AirlineCode.string\nAirlinesTime.StartDrome.string\nAirlinesTime.ArriveDrome.string\nAirlinesTime.StartTime.string\nAirlinesTime.ArriveTime.string\nAirlinesTime.Mode.string\nAirlinesTime.AirlineStop.string\nAirlinesTime.Week.string', '2', '0'), ('7', '火车票查询', '火车票查询', 'http://ws.webxml.com.cn/WebServices/TrainTimeWebService.asmx', 'http://ws.webxml.com.cn/WebServices/TrainTimeWebService.asmx?wsdl', 'http://WebXml.com.cn/', 'getStationAndTimeByStationName', 'root.soap:Envelope.soap:Envelope\nsoap:Envelope.soap:Body.soap:Body\nsoap:Body.getStationAndTimeByStationNameResponse.getStationAndTimeByStationNameResponse\ngetStationAndTimeByStationNameResponse.getStationAndTimeByStationNameResult.getStationAndTimeByStationNameResult\ngetStationAndTimeByStationNameResult.diffgr:diffgram.diffgr:diffgram\ndiffgr:diffgram.getStationAndTime.getStationAndTime\ngetStationAndTime.TimeTable.TimeTable\nTimeTable.TrainCode.string\nTimeTable.FirstStation.string\nTimeTable.LastStation.string\nTimeTable.StartStation.string\nTimeTable.StartTime.string\nTimeTable.ArriveStation.string\nTimeTable.ArriveTime.string\nTimeTable.KM.string\nTimeTable.UseData.string', '2', '0'), ('8', '快递查询', '', 'http://www.gpsso.com/webservice/kuaidi/kuaidi.asmx', 'http://www.gpsso.com/webservice/kuaidi/kuaidi.asmx?wsdl', 'http://gpsso.com/', 'KuaidiQuery', '', '2', '0');
COMMIT;

-- ----------------------------
--  Table structure for `T_ServiceOwner`
-- ----------------------------
DROP TABLE IF EXISTS `T_ServiceOwner`;
CREATE TABLE `T_ServiceOwner` (
  `autoid` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` bigint(20) NOT NULL,
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
  `paramname` varchar(25) NOT NULL,
  `alies` varchar(25) NOT NULL DEFAULT '',
  `remark` varchar(25) NOT NULL DEFAULT '',
  `state` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `T_ServiceParam`
-- ----------------------------
BEGIN;
INSERT INTO `T_ServiceParam` VALUES ('23', '1', 'theCityCode', 'city', '城市名', '0'), ('24', '1', 'theUserID', 'userid', '用户id', '0'), ('33', '4', 'userID', 'userid', '用户id', '0'), ('34', '4', 'mobileCode', 'mobile', '手机号', '0'), ('35', '5', 'wordKey', 'word', '关键字', '0'), ('36', '6', 'startCity', 'origin,city', '出发地', '0'), ('37', '6', 'lastCity', 'destination', '目的地', '0'), ('38', '6', 'theDate', '', '格式2007-07-02', '0'), ('39', '6', 'userID', '', '', '0'), ('40', '7', 'StartStation', 'origin,city', '始发站', '0'), ('41', '7', 'ArriveStation', 'destination', '目的地', '0'), ('42', '7', 'UserID', '', '', '0'), ('43', '8', 'Compay', '', '快递公司', '0'), ('44', '8', 'OrderNo', '', '单号', '0');
COMMIT;

-- ----------------------------
--  Table structure for `T_Tag`
-- ----------------------------
DROP TABLE IF EXISTS `T_Tag`;
CREATE TABLE `T_Tag` (
  `autoid` int(11) NOT NULL AUTO_INCREMENT,
  `tagName` varchar(255) NOT NULL,
  `orderIndex` int(11) NOT NULL DEFAULT '0',
  `pid` int(11) NOT NULL DEFAULT '0',
  `state` tinyint(4) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastUpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `T_Tag`
-- ----------------------------
BEGIN;
INSERT INTO `T_Tag` VALUES ('1', 'root', '0', '0', '0', '2017-07-12 19:27:07', '2017-07-12 19:27:07'), ('2', '生活服务', '0', '4', '0', '2017-07-11 19:36:24', '2017-07-12 19:48:13'), ('3', 'test-1', '0', '1', '0', '2017-07-12 10:30:50', '2017-07-12 20:45:55'), ('4', 'test-2', '0', '3', '0', '2017-07-12 19:30:56', '2017-07-12 20:46:19');
COMMIT;

-- ----------------------------
--  Table structure for `T_UnableUserInFlow`
-- ----------------------------
DROP TABLE IF EXISTS `T_UnableUserInFlow`;
CREATE TABLE `T_UnableUserInFlow` (
  `autoid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL,
  `flowid` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT '0',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `lastUpdateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_User`
-- ----------------------------
BEGIN;
INSERT INTO `T_User` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', '管理员', '0'), ('2', 'test', '098f6bcd4621d373cade4e832627b4f6', 'test', '测试', '0'), ('3', 'boss', 'ceb8447cc4ab78d2ec34cd9f11e4bed2', 'boss', '领导', '0'), ('9', 'twogoods', '228ba6f6384004be1473b9eee85be9ac', 'twogoods', '开发', '0'), ('10', '1271314078@qq.com', '21232f297a57a5a743894a0e4a801fc3', 'twogoods', '', '0'), ('11', '1271314078@163.com', '21232f297a57a5a743894a0e4a801fc3', 'test', '', '0');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `T_UserRole`
-- ----------------------------
BEGIN;
INSERT INTO `T_UserRole` VALUES ('1', '1', '2', '0'), ('2', '1', '3', '0'), ('4', '2', '4', '0'), ('5', '9', '5', '0'), ('6', '10', '1', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
