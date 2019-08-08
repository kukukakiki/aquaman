/*
Navicat MySQL Data Transfer

Source Server         : leerzzom
Source Server Version : 50616
Source Host           : fanxiweb.mysql.rds.aliyuncs.com:3306
Source Database       : fanxiweb

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2019-03-05 09:40:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ad_role
-- ----------------------------
DROP TABLE IF EXISTS `ad_role`;
CREATE TABLE `ad_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `status` varchar(255) DEFAULT NULL COMMENT '状态',
  `permission_code` varchar(255) DEFAULT NULL,
  `gmt_create` bigint(14) DEFAULT NULL,
  `gmt_modify` bigint(14) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_role_code_name` (`code`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
