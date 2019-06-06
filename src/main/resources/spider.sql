/*
 Navicat Premium Data Transfer

 Source Server         : AlibabaMysql
 Source Server Type    : MySQL
 Source Server Version : 50644
 Source Host           : 47.101.148.216:3306
 Source Schema         : spider

 Target Server Type    : MySQL
 Target Server Version : 50644
 File Encoding         : 65001

 Date: 30/05/2019 09:38:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for spider
-- ----------------------------
DROP TABLE IF EXISTS `spider`;
CREATE TABLE `spider` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `baby_age` varchar(255) DEFAULT NULL COMMENT '宝宝年龄',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `reply_and_view` varchar(255) DEFAULT NULL COMMENT '回复数量与查看数量',
  `last_active_time` varchar(255) DEFAULT NULL COMMENT '上次活跃时间',
  `insert_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99772 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
