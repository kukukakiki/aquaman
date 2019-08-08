/*
Navicat MySQL Data Transfer

Source Server         : leerzzom
Source Server Version : 50616
Source Host           : fanxiweb.mysql.rds.aliyuncs.com:3306
Source Database       : fanxiweb

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2019-03-04 17:18:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ad_user
-- ----------------------------
DROP TABLE IF EXISTS `ad_user`;
CREATE TABLE `ad_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `dept_id` bigint(14) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL COMMENT '状态',
  `type` varchar(255) DEFAULT NULL COMMENT '会员类型',
  `login_time` bigint(14) DEFAULT NULL COMMENT '登录时间',
  `permission_code` varchar(255) DEFAULT NULL,
  `gmt_create` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` bigint(14) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_account` (`account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
