/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : 65001

Date: 2017-03-14 13:51:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `lxr` varchar(255) DEFAULT NULL,
  `zylxr` varchar(255) DEFAULT NULL,
  `cz` varchar(255) DEFAULT NULL COMMENT '转诊',
  `telphone` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `classes` varchar(255) DEFAULT NULL,
  `ly` varchar(255) DEFAULT NULL,
  `czsj` varchar(255) DEFAULT NULL COMMENT '最近操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` int(11) DEFAULT NULL,
  `lb` varchar(255) DEFAULT NULL,
  `lbmc` varchar(255) DEFAULT NULL,
  `parentlb` varchar(255) DEFAULT NULL,
  `lbvalue` varchar(255) DEFAULT NULL,
  `xh` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(60) DEFAULT NULL,
  `clientname` varchar(500) DEFAULT NULL COMMENT '客户名称（公司或者个人）',
  `qyzy` varchar(255) NOT NULL COMMENT '签约专员',
  `jhjd` varchar(255) NOT NULL COMMENT '机会阶段',
  `fzr` varchar(255) DEFAULT NULL COMMENT '负责人',
  `yjcgl` varchar(255) DEFAULT NULL COMMENT '预计成功率',
  `zt` varchar(255) DEFAULT NULL COMMENT '状态 进行中，已签约等',
  `xqms` varchar(255) DEFAULT NULL COMMENT '需求描述',
  `jhlx` varchar(255) DEFAULT NULL COMMENT '机会类型',
  `yjcje` varchar(255) DEFAULT NULL COMMENT '预计成交额',
  `bmrq` varchar(255) DEFAULT NULL COMMENT '报名日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menubs` varchar(100) NOT NULL,
  `menuname` varchar(255) NOT NULL,
  `menuid` varchar(60) NOT NULL,
  `parentid` varchar(60) NOT NULL,
  `url` varchar(200) NOT NULL,
  `level` int(1) NOT NULL,
  `rank` int(1) NOT NULL,
  `path` varchar(100) DEFAULT NULL,
  `createtime` varchar(14) DEFAULT NULL,
  `isleaf` int(1) NOT NULL,
  `bz` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('menuroot', 'menuroot', '4028905353ea391c0153ea3da8000000', '7ca93b129c1345da8cb5c4a94c1c2d8e', '', '1', '1', 'ls', '20160406142342', '0', 'no forget');
INSERT INTO `sys_menu` VALUES ('', '??', '4028905353ee45ec0153ee4cdf7f0001', '7ca93b129c1345da8cb5c4a94c1c2d8e', '', '1', '1', 'ls', '20160407091848', '-1', '');
INSERT INTO `sys_menu` VALUES ('??', '??', '4028905353ee45ec0153ee4ed78d0002', '7ca93b129c1345da8cb5c4a94c1c2d8e', '/xsgl/main.jsp', '1', '2', 'ls', '20160407092057', '1', '???');
INSERT INTO `sys_menu` VALUES ('?? ', '???', '4028905353ee58c50153ee5a45000000', '7ca93b129c1345da8cb5c4a94c1c2d8e', '??', '1', '1', 'ls', '20160407093326', '-1', '??');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `roleid` int(11) DEFAULT NULL,
  `menuid` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `userid` varchar(60) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_type`;
CREATE TABLE `sys_user_type` (
  `groupid` int(11) NOT NULL,
  `groupname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`groupid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_user_type
-- ----------------------------
INSERT INTO `sys_user_type` VALUES ('1', 'superadmin');
INSERT INTO `sys_user_type` VALUES ('2', 'admin');
INSERT INTO `sys_user_type` VALUES ('3', 'comuser');

-- ----------------------------
-- Table structure for t_sys_file
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_file`;
CREATE TABLE `t_sys_file` (
  `id` varchar(50) NOT NULL,
  `content` longtext,
  `time` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `fjname` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_file
-- ----------------------------
INSERT INTO `t_sys_file` VALUES ('2555f6cf-55a9-42cc-b3ea-24a6518c763f', '<p>sdf&nbsp;<img width=\"530\" height=\"340\" src=\"http://api.map.baidu.com/staticimage?center=116.404,39.915&zoom=10&width=530&height=340&markers=116.404,39.915\"/></p>', '20170309', '1', '1yn5JktBwb.jpg', '93871');
INSERT INTO `t_sys_file` VALUES ('413a0ab7-ea24-4b63-bfe7-e0331f52b2ec', '<p>12312132</p>', '20170308', '12123', null, '0');
INSERT INTO `t_sys_file` VALUES ('6a874909-6160-4830-8dd7-33c463bb021f', '<p>13211123123</p>', '20170309', '123', 'data.csv', '967');
INSERT INTO `t_sys_file` VALUES ('722d36d0-75f1-4ab8-b190-b40e09023f72', '<p>????????<br/></p>', '20170308', '??????????????', null, '0');
INSERT INTO `t_sys_file` VALUES ('8275b1b2-3acb-4984-8e5c-68a8bb9ccae7', '<p>12312132??????</p>', '20170308', '12123????', null, '0');
INSERT INTO `t_sys_file` VALUES ('a5a29e3d-a0fd-44a3-9cb7-b7e7cadd5348', '<p>1213123<img src=\"http://localhost:8088/web//upload/image/20170308/1488964155189087044.png\" title=\"1488964155189087044.png\" alt=\"??1.png\"/></p>', '20170308', '1231', null, '0');
INSERT INTO `t_sys_file` VALUES ('d04442c0-a56a-4cb9-9a01-abdf7b5123d0', '<p>12wrewerwr</p>', '20170309', null, 'CSS Satyr.png', '341692');
INSERT INTO `t_sys_file` VALUES ('d929e5ef-2b24-4b23-8b64-7a01a0479f26', '<p>sdfsfsdfsf<img src=\"http://localhost:8088/web//upload/image/20170309/1489034695709083657.png\" title=\"1489034695709083657.png\" alt=\"231951107414.png\"/></p>', '20170309', null, 'CSS Satyr.png', '341692');

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
INSERT INTO `t_sys_menu` VALUES ('15e62992-ce1c-4e36-8bea-2b8a9a8e6d26', '0', null, '0', '1', '????', null, '0', '2', 'menuroot', null);
INSERT INTO `t_sys_menu` VALUES ('219c1112-b9ca-47d8-8898-b4da50a3b259', '0', null, '1', null, '????', null, '0', '1', '3350c7dd-4872-404d-af09-e9d6461d639f', 'project/mail/sendMail.jsp');
INSERT INTO `t_sys_menu` VALUES ('2ea49c49-564e-4874-9511-fed8cab83dd7', '0', null, '0', null, '????', null, '0', '5', 'menuroot', '');
INSERT INTO `t_sys_menu` VALUES ('3350c7dd-4872-404d-af09-e9d6461d639f', '0', null, '0', null, '????', null, '0', '4', 'menuroot', null);
INSERT INTO `t_sys_menu` VALUES ('481cab50-c6e4-48c1-858c-a0365adb7346', '0', null, '1', null, '????', null, '0', '1', '15e62992-ce1c-4e36-8bea-2b8a9a8e6d26', 'project/xzzx/uploadFile.jsp');
INSERT INTO `t_sys_menu` VALUES ('55924c70-19ca-42cf-a74b-3f24c828c2f2', '0', null, '1', '234', '????', null, '0', '3', 'b5b1cd34-61e7-4aeb-b9ad-c987e1f70348', 'project/xtgl/user/assignRole.jsp');
INSERT INTO `t_sys_menu` VALUES ('5d3ac884-7f27-4f70-bae1-152522c03eff', '0', null, '0', null, '????', null, '0', '3', 'menuroot', null);
INSERT INTO `t_sys_menu` VALUES ('675e0f2b-3f44-4fa1-be7e-fbec5fce63c9', '0', null, '1', '12', '????', null, '0', '1', 'b5b1cd34-61e7-4aeb-b9ad-c987e1f70348', 'project/xtgl/user/editUser.jsp');
INSERT INTO `t_sys_menu` VALUES ('977384de-816f-44b6-8885-aa3e306b96ff', '0', null, '1', null, '????', null, '0', '2', '15e62992-ce1c-4e36-8bea-2b8a9a8e6d26', 'project/xzzx/fileList.jsp');
INSERT INTO `t_sys_menu` VALUES ('9798149d-d4c5-4d61-b4e9-644d1ca7e404', '0', null, '1', '324', '????', null, '0', '2', 'b5b1cd34-61e7-4aeb-b9ad-c987e1f70348', 'project/xtgl/role/editRole.jsp');
INSERT INTO `t_sys_menu` VALUES ('a5011f36-176d-4a95-b6dc-42f7aee62a09', '0', null, '1', '234', '????', null, '0', '6', 'b5b1cd34-61e7-4aeb-b9ad-c987e1f70348', 'project/xtgl/menu/assignMenu.jsp');
INSERT INTO `t_sys_menu` VALUES ('ac3e8b82-6c84-44d7-8c21-bb80e32f2d72', '0', null, '1', '234', '????', null, '0', '4', 'b5b1cd34-61e7-4aeb-b9ad-c987e1f70348', 'project/xtgl/menu/menuMain.jsp');
INSERT INTO `t_sys_menu` VALUES ('b5b1cd34-61e7-4aeb-b9ad-c987e1f70348', '0', null, '0', '1', '????', null, '0', '1', 'menuroot', null);
INSERT INTO `t_sys_menu` VALUES ('cc97e0df-9ca2-43c4-a4fc-cd10d0c27d9c', '0', null, '1', null, 'IK????', null, '0', '1', '5d3ac884-7f27-4f70-bae1-152522c03eff', 'project/search/listNews.jsp');
INSERT INTO `t_sys_menu` VALUES ('d48c9902-ca10-4cb8-9e54-ae5355e52709', '0', null, '1', null, '????', null, '0', '1', '2ea49c49-564e-4874-9511-fed8cab83dd7', '/project/sw/index.jsp');

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
  `money` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_sw
-- ----------------------------
INSERT INTO `t_sys_sw` VALUES ('b3629034-6d37-489d-bf04-cd6b89a01f45', '1', '-1900');
INSERT INTO `t_sys_sw` VALUES ('eb5231e2-b4ff-4fe3-8b66-9c7fc43ddcad', '2', '2000');

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
INSERT INTO `t_sys_user` VALUES ('88350ed7-ea04-4943-9aaa-3cb39844be35', '11@qq.com', '1', null, '1', '0:0:0:0:0:0:0:1', '0', '0:0:0:0:0:0:0:1', '50', null, '96e79218965eb72c92a549dd5a330112', '13938469072', '1', 'iechenyb');

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

-- ----------------------------
-- Table structure for usr
-- ----------------------------
DROP TABLE IF EXISTS `usr`;
CREATE TABLE `usr` (
  `id` varchar(60) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `zt` int(11) DEFAULT NULL,
  `czsj` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of usr
-- ----------------------------
INSERT INTO `usr` VALUES ('123', 'chenyb', null, '1', '1', null);
