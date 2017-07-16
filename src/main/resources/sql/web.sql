/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-16 10:23:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('陈');

-- ----------------------------
-- Table structure for t_sys_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_customer`;
CREATE TABLE `t_sys_customer` (
  `id` varchar(50) NOT NULL,
  `money` bigint(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_customer
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_file
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_file`;
CREATE TABLE `t_sys_file` (
  `id` varchar(50) NOT NULL,
  `content` longtext,
  `fjname` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_file
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `id` varchar(50) NOT NULL,
  `createTime` bigint(20) DEFAULT NULL,
  `cretatePerson` varchar(50) DEFAULT NULL,
  `isLeaf` int(11) DEFAULT NULL,
  `menuDesc` varchar(200) DEFAULT NULL,
  `menuName` varchar(20) DEFAULT NULL,
  `modifyPerson` varchar(50) DEFAULT NULL,
  `modifyTime` bigint(20) DEFAULT NULL,
  `ordor` int(11) DEFAULT NULL,
  `parentId` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('15e62992-ce1c-4e36-8bea-2b8a9a8e6d26', '0', null, '0', '1', '文件管理', null, '0', '2', 'menuroot', null);
INSERT INTO `t_sys_menu` VALUES ('219c1112-b9ca-47d8-8898-b4da50a3b259', '0', null, '1', null, '发送邮件', null, '0', '1', '3350c7dd-4872-404d-af09-e9d6461d639f', 'project/mail/sendMail.jsp');
INSERT INTO `t_sys_menu` VALUES ('2ea49c49-564e-4874-9511-fed8cab83dd7', '0', null, '0', null, '事务管理', null, '0', '5', 'menuroot', '');
INSERT INTO `t_sys_menu` VALUES ('3350c7dd-4872-404d-af09-e9d6461d639f', '0', null, '0', null, '邮件管理', null, '0', '4', 'menuroot', null);
INSERT INTO `t_sys_menu` VALUES ('481cab50-c6e4-48c1-858c-a0365adb7346', '0', null, '1', null, '文件上传', null, '0', '1', '15e62992-ce1c-4e36-8bea-2b8a9a8e6d26', 'project/xzzx/uploadFile.jsp');
INSERT INTO `t_sys_menu` VALUES ('55924c70-19ca-42cf-a74b-3f24c828c2f2', '0', null, '1', '234', '角色分配', null, '0', '3', 'b5b1cd34-61e7-4aeb-b9ad-c987e1f70348', 'project/xtgl/user/assignRole.jsp');
INSERT INTO `t_sys_menu` VALUES ('5d3ac884-7f27-4f70-bae1-152522c03eff', '0', null, '0', null, 'IK分词', null, '0', '3', 'menuroot', null);
INSERT INTO `t_sys_menu` VALUES ('675e0f2b-3f44-4fa1-be7e-fbec5fce63c9', '0', null, '1', '12', '用户管理', null, '0', '1', 'b5b1cd34-61e7-4aeb-b9ad-c987e1f70348', 'project/xtgl/user/editUser.jsp');
INSERT INTO `t_sys_menu` VALUES ('977384de-816f-44b6-8885-aa3e306b96ff', '0', null, '1', null, '文件列表', null, '0', '2', '15e62992-ce1c-4e36-8bea-2b8a9a8e6d26', 'project/xzzx/fileList.jsp');
INSERT INTO `t_sys_menu` VALUES ('9798149d-d4c5-4d61-b4e9-644d1ca7e404', '0', null, '1', '324', '角色管理', null, '0', '2', 'b5b1cd34-61e7-4aeb-b9ad-c987e1f70348', 'project/xtgl/role/editRole.jsp');
INSERT INTO `t_sys_menu` VALUES ('a5011f36-176d-4a95-b6dc-42f7aee62a09', '0', null, '1', '234', '权限分配', null, '0', '6', 'b5b1cd34-61e7-4aeb-b9ad-c987e1f70348', 'project/xtgl/menu/assignMenu.jsp');
INSERT INTO `t_sys_menu` VALUES ('ac3e8b82-6c84-44d7-8c21-bb80e32f2d72', '0', null, '1', '234', '菜单管理', null, '0', '4', 'b5b1cd34-61e7-4aeb-b9ad-c987e1f70348', 'project/xtgl/menu/menuMain.jsp');
INSERT INTO `t_sys_menu` VALUES ('b5b1cd34-61e7-4aeb-b9ad-c987e1f70348', '0', null, '0', '1', '水电费', null, '0', '1', 'menuroot', null);
INSERT INTO `t_sys_menu` VALUES ('cc97e0df-9ca2-43c4-a4fc-cd10d0c27d9c', '0', null, '1', null, '智能搜索', null, '0', '1', '5d3ac884-7f27-4f70-bae1-152522c03eff', 'project/search/listNews.jsp');
INSERT INTO `t_sys_menu` VALUES ('d48c9902-ca10-4cb8-9e54-ae5355e52709', '0', null, '1', null, '事务管理', null, '0', '1', '2ea49c49-564e-4874-9511-fed8cab83dd7', '/project/sw/index.jsp');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `rolename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1a81d163-d017-49cb-a8eb-24999518701a', 'admin', 'admin');

-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu` (
  `id` varchar(50) NOT NULL,
  `menuid` varchar(50) NOT NULL,
  `roleid` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role_menu
-- ----------------------------
INSERT INTO `t_sys_role_menu` VALUES ('14263ad8-09e0-4fe4-9a8c-0d3c4350dfbf', 'ac3e8b82-6c84-44d7-8c21-bb80e32f2d72', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('15107630-d49e-435a-8fca-67a24ae78a60', '481cab50-c6e4-48c1-858c-a0365adb7346', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('3df4575c-eae7-4570-bb5a-1b495cc694d7', 'menuroot', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('5a0301a6-93dd-427d-a7c8-4eb4a7880b75', '219c1112-b9ca-47d8-8898-b4da50a3b259', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('5bca6e92-ba30-4fea-bb8a-ba9a3c73c97d', '675e0f2b-3f44-4fa1-be7e-fbec5fce63c9', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('5db4ebeb-cec8-46cc-9717-967e6c50cc2a', '3350c7dd-4872-404d-af09-e9d6461d639f', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('70a67789-6b71-41fd-857b-8f94070cf160', '55924c70-19ca-42cf-a74b-3f24c828c2f2', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('8050b283-f195-441b-8472-1deea262ab08', '977384de-816f-44b6-8885-aa3e306b96ff', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('8f90af2e-cfcc-4027-b04c-80c43c2175ca', 'a5011f36-176d-4a95-b6dc-42f7aee62a09', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('956a9f97-689b-42b2-9c93-28eeb757be3a', '9798149d-d4c5-4d61-b4e9-644d1ca7e404', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('c210ab9e-e27d-445c-b1c8-be31865e10d7', 'b5b1cd34-61e7-4aeb-b9ad-c987e1f70348', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('ce90045e-495f-4a2d-a137-e35a071b5b18', '15e62992-ce1c-4e36-8bea-2b8a9a8e6d26', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('df9efaee-df42-4797-82ca-4cd9adca2474', '2ea49c49-564e-4874-9511-fed8cab83dd7', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('e925315f-9411-4749-8afd-860bca262df4', 'd48c9902-ca10-4cb8-9e54-ae5355e52709', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('f19172c2-8bb9-4ee8-9af8-6d50fdb5d1f3', '5d3ac884-7f27-4f70-bae1-152522c03eff', '1a81d163-d017-49cb-a8eb-24999518701a');
INSERT INTO `t_sys_role_menu` VALUES ('f2021798-b8f8-40ae-ba69-363c80bdc7bf', 'cc97e0df-9ca2-43c4-a4fc-cd10d0c27d9c', '1a81d163-d017-49cb-a8eb-24999518701a');

-- ----------------------------
-- Table structure for t_sys_sw
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_sw`;
CREATE TABLE `t_sys_sw` (
  `id` varchar(50) NOT NULL,
  `cardNo` varchar(30) NOT NULL,
  `d` double DEFAULT NULL,
  `money` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_sw
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` varchar(50) NOT NULL,
  `email` varchar(200) DEFAULT NULL,
  `empno` varchar(200) NOT NULL,
  `idcard` varchar(200) DEFAULT NULL,
  `isEffect` int(11) DEFAULT NULL,
  `lastLoginIp` varchar(30) DEFAULT NULL,
  `lastLoginTime` bigint(20) DEFAULT NULL,
  `loginIp` varchar(30) DEFAULT NULL,
  `loginSum` int(11) DEFAULT NULL,
  `operateID` varchar(255) DEFAULT NULL,
  `password` varchar(36) NOT NULL,
  `phone` varchar(200) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `username` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('88350ed7-ea04-4943-9aaa-3cb39844be35', '11@qq.com', 'iechenyb', null, '1', '0:0:0:0:0:0:0:1', '0', '0:0:0:0:0:0:0:1', '51', null, '1e0bdf94d25fe971024333b89e08f9f4', '13938469072', '1', 'iechenyb');

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `id` varchar(50) NOT NULL,
  `roleid` varchar(50) NOT NULL,
  `userid` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('e59247c4-2747-4515-897c-4e8b41a0b2b9', '1a81d163-d017-49cb-a8eb-24999518701a', '88350ed7-ea04-4943-9aaa-3cb39844be35');
