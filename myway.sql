/*
Navicat MySQL Data Transfer

Source Server         : LOCALHOST
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : myway

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-10-14 16:14:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_org
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_org`;
CREATE TABLE `t_sys_org` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `STATUS` smallint(2) DEFAULT '1' COMMENT '1：有效   0：无效',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '最近更新时间',
  `TYPE` smallint(2) DEFAULT NULL COMMENT '0：系统管理  1：运维研发',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_org
-- ----------------------------
INSERT INTO `t_sys_org` VALUES ('1', '系统管理一组', '1', '2017-08-31 19:33:52', null, '0');
INSERT INTO `t_sys_org` VALUES ('2', '运维一组', '1', '2017-09-05 19:34:08', null, '1');
INSERT INTO `t_sys_org` VALUES ('3', '研发一组', '1', '2017-10-01 17:00:43', null, '1');
INSERT INTO `t_sys_org` VALUES ('4', '运维二组', '1', '2017-09-13 17:02:11', null, '1');
INSERT INTO `t_sys_org` VALUES ('5', '研发二组', '1', '2017-10-19 17:02:30', null, '1');
INSERT INTO `t_sys_org` VALUES ('6', '运维三组', '1', '2017-10-10 17:03:03', null, '1');
INSERT INTO `t_sys_org` VALUES ('7', '研发三组', '1', '2017-10-09 17:03:37', null, '1');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `STATUS` smallint(2) DEFAULT '1' COMMENT '1：有效   0：无效',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', '系统管理', '1', '2017-08-31 19:24:51', null);
INSERT INTO `t_sys_role` VALUES ('2', '运维人员', '1', '2017-08-31 19:25:55', null);
INSERT INTO `t_sys_role` VALUES ('3', '研发人员', '1', '2017-08-31 19:27:39', null);

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户唯一编号',
  `USER_NAME` varchar(20) DEFAULT NULL COMMENT '系统登录名',
  `REAL_NAME` varchar(32) DEFAULT NULL COMMENT '真实名称',
  `PASSWORD` varchar(60) DEFAULT NULL COMMENT '登录密码',
  `MSISDN` varchar(20) DEFAULT NULL COMMENT '手机号',
  `WX` varchar(20) DEFAULT NULL COMMENT '微信号',
  `QQ` varchar(20) DEFAULT NULL COMMENT 'qq号',
  `SEX` smallint(2) DEFAULT '0' COMMENT '性别 0：未公开  1：男  2：女生',
  `ORG_ID` varchar(10) DEFAULT NULL COMMENT '归属组织 表t_sys_org 中的id',
  `ROLE_ID` varchar(10) DEFAULT NULL COMMENT '具有的角色 表t_sys_role 中的id',
  `STATUS` smallint(2) DEFAULT '1' COMMENT '状态 1：正常 0：已注销  2：暂停使用',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ind1_sys_user` (`USER_NAME`),
  KEY `ind2_sys_user` (`MSISDN`),
  KEY `ind3_sys_user` (`WX`),
  KEY `ind4_sys_user` (`QQ`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'Admin', '系统管理员', 'f3161d72f7d622ec8502b1c8d3972364', '13584038740', '1289549214', '1289549214', '1', '1', '1', '1', '2017-08-16 19:16:15');
INSERT INTO `t_sys_user` VALUES ('2', 'yw01', '刘峰', 'f3161d72f7d622ec8502b1c8d3972364', '13584039931', 'bobosj9342', '1254449218', '1', '2', '2', '1', '2017-08-25 19:16:15');
INSERT INTO `t_sys_user` VALUES ('3', 'yw03', '张超', 'f3161d72f7d622ec8502b1c8d3972364', '13884031156', 'Hello89549214', '1209459214', '1', '4', '2', '1', '2017-09-01 19:16:15');
INSERT INTO `t_sys_user` VALUES ('4', 'yw04', '葛菲菲', 'f3161d72f7d622ec8502b1c8d3972364', '13784030141', 'gff234234', '1789559214', '2', '6', '2', '1', '2017-09-01 19:16:15');
INSERT INTO `t_sys_user` VALUES ('5', 'yw02', '刘亚楠', 'f3161d72f7d622ec8502b1c8d3972364', '19084031180', 'liu234034', '1289549833', '2', '2', '2', '1', '2017-09-04 19:16:41');
INSERT INTO `t_sys_user` VALUES ('6', 'yw05', '梅西', 'f3161d72f7d622ec8502b1c8d3972364', '13599038140', 'mx234234', '1284559214', '1', '6', '2', '0', '2017-10-18 19:16:49');
INSERT INTO `t_sys_user` VALUES ('7', 'yw07', '罗斯福', 'f3161d72f7d622ec8502b1c8d3972364', '13532131721', '467234234', '1089559214', '1', '4', '2', '2', '2017-10-24 19:16:53');
INSERT INTO `t_sys_user` VALUES ('8', 'yw06', '张培', 'f3161d72f7d622ec8502b1c8d3972364', '13584030123', '13584030123', '1283219214', '1', '2', '2', '1', '2017-09-24 19:17:07');
INSERT INTO `t_sys_user` VALUES ('9', 'yf01', '张亮', 'f3161d72f7d622ec8502b1c8d3972364', '13584038721', '13584038721', '1254400118', '1', '3', '3', '1', '2017-10-06 19:17:11');
INSERT INTO `t_sys_user` VALUES ('10', 'yf03', '顾云', 'f3161d72f7d622ec8502b1c8d3972364', '13584038722', '13584038722', '1209400214', '1', '5', '3', '1', '2017-09-27 19:17:15');
INSERT INTO `t_sys_user` VALUES ('11', 'yf04', '朱峰', 'f3161d72f7d622ec8502b1c8d3972364', '13884038723', '13884038723', '1700149214', '1', '7', '3', '1', '2017-10-06 19:17:18');
INSERT INTO `t_sys_user` VALUES ('12', 'yf02', '张海峰', 'f3161d72f7d622ec8502b1c8d3972364', '13984038734', '13984038734', '1289500133', '1', '3', '3', '1', '2017-09-17 19:17:22');
INSERT INTO `t_sys_user` VALUES ('13', 'yf05', '陈一凡', 'f3161d72f7d622ec8502b1c8d3972364', '13984038767', 'yahuo02348234', '1221459214', '1', '5', '3', '0', '2017-09-10 19:18:00');
INSERT INTO `t_sys_user` VALUES ('14', 'yf07', '李婷', 'f3161d72f7d622ec8502b1c8d3972364', '13984038713', '13984038713', '114559214', '2', '7', '3', '1', '2017-09-27 19:17:15');
INSERT INTO `t_sys_user` VALUES ('15', 'yf06', '李娜', 'f3161d72f7d622ec8502b1c8d3972364', '13784038778', 'zaifei29342', '114219214', '2', '7', '3', '1', '2017-09-10 19:18:00');
INSERT INTO `t_sys_user` VALUES ('16', 'yw11', '刘天宇', 'f3161d72f7d622ec8502b1c8d3972364', '13584039939', 'bobosj9342', '1254449218', '1', '2', '2', '1', '2017-09-17 19:17:22');
INSERT INTO `t_sys_user` VALUES ('17', 'yw12', '陈静', 'f3161d72f7d622ec8502b1c8d3972364', '13889991159', 'Hello89549214', '1209459214', '1', '4', '2', '1', '2017-09-10 19:18:00');
INSERT INTO `t_sys_user` VALUES ('18', 'yw13', '葛菲菲', 'f3161d72f7d622ec8502b1c8d3972364', '13787630148', 'gff234234', '1789559214', '2', '6', '2', '1', '2017-09-27 19:17:15');
INSERT INTO `t_sys_user` VALUES ('19', 'yw14', '倩云', 'f3161d72f7d622ec8502b1c8d3972364', '19084031188', 'liu234034', '1289549833', '2', '2', '2', '1', '2017-09-25 19:17:55');
INSERT INTO `t_sys_user` VALUES ('20', 'yw32', '梅西', 'f3161d72f7d622ec8502b1c8d3972364', '13599038148', 'mx234234', '1284559214', '1', '6', '2', '0', '2017-09-27 19:17:15');
INSERT INTO `t_sys_user` VALUES ('21', 'yw22', '罗斯福', 'f3161d72f7d622ec8502b1c8d3972364', '13532131728', '467234234', '1089559214', '1', '4', '2', '2', '2017-09-29 19:18:07');
INSERT INTO `t_sys_user` VALUES ('22', 'yw21', '明发', 'f3161d72f7d622ec8502b1c8d3972364', '13584037603', '13584030123', '1283298214', '1', '2', '2', '1', '2017-10-02 19:17:51');
INSERT INTO `t_sys_user` VALUES ('23', 'yf13', '张亮', 'f3161d72f7d622ec8502b1c8d3972364', '13584038721', '13584000721', '1254400118', '1', '3', '3', '1', '2017-09-17 19:17:22');
INSERT INTO `t_sys_user` VALUES ('24', 'yf12', '张灿', 'f3161d72f7d622ec8502b1c8d3972364', '13584038722', '13589938722', '1208900214', '1', '5', '3', '1', '2017-09-29 19:18:07');
INSERT INTO `t_sys_user` VALUES ('25', 'yf53', '唐河', 'f3161d72f7d622ec8502b1c8d3972364', '13884038723', '13884038723', '1700149214', '1', '7', '3', '1', '2017-10-09 19:17:43');
INSERT INTO `t_sys_user` VALUES ('26', 'yf14', '范克子', 'f3161d72f7d622ec8502b1c8d3972364', '13984038734', '13984038734', '1289500133', '1', '3', '3', '1', '2017-09-18 19:18:14');
INSERT INTO `t_sys_user` VALUES ('27', 'yf16', '海名', 'f3161d72f7d622ec8502b1c8d3972364', '13956738767', 'yahuo02348234', '1891459214', '1', '5', '3', '0', '2017-10-03 19:18:19');
INSERT INTO `t_sys_user` VALUES ('28', 'yf17', '天罗子', 'f3161d72f7d622ec8502b1c8d3972364', '13984038713', '13989938713', '189559214', '2', '7', '3', '1', '2017-10-09 19:17:43');
INSERT INTO `t_sys_user` VALUES ('29', 'yf18', '青阳子', 'f3161d72f7d622ec8502b1c8d3972364', '13789938778', 'zaifei29342', '189219214', '2', '7', '3', '1', '2017-10-09 19:17:43');
