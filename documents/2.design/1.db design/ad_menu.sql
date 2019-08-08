/*
Navicat MySQL Data Transfer

Source Server         : leerzzom
Source Server Version : 50616
Source Host           : fanxiweb.mysql.rds.aliyuncs.com:3306
Source Database       : fanxiweb

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2019-03-04 17:18:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ad_menu
-- ----------------------------
DROP TABLE IF EXISTS `ad_menu`;
CREATE TABLE `ad_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `system_id` bigint(11) DEFAULT NULL COMMENT '所属系统id',
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT 'api地址',
  `router` varchar(255) DEFAULT NULL COMMENT '前端路由地址',
  `icon_type` varchar(255) DEFAULT NULL COMMENT '图标类型',
  `type` varchar(255) DEFAULT NULL COMMENT '类型(group:菜单组,menu:菜单,button:按钮,page:页面,)',
  `display` char(1) DEFAULT '1' COMMENT '0:隐藏,1:显示',
  `status` varchar(255) DEFAULT NULL COMMENT '状态',
  `parent_id` bigint(20) DEFAULT '-1' COMMENT '父ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sort` bigint(10) DEFAULT '1' COMMENT '排序号',
  `permission_code` varchar(255) DEFAULT NULL,
  `gmt_create` bigint(14) DEFAULT NULL,
  `gmt_modify` bigint(14) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_menu_code_name` (`code`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
