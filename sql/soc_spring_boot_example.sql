/*
 Navicat Premium Data Transfer
 Source Schema         : soc_spring_boot_example
 Date: 24/05/2019 10:34:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_info
-- ----------------------------
DROP TABLE IF EXISTS `app_info`;
CREATE TABLE `app_info` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `app_id` varchar(32) NOT NULL DEFAULT '' COMMENT '应用编号',
  `app_name` varchar(32) NOT NULL DEFAULT '' COMMENT '应用名称',
  `app_secret1` varchar(32) NOT NULL DEFAULT '' COMMENT '秘钥1',
  `app_secret2` varchar(32) NOT NULL DEFAULT '' COMMENT '秘钥2，秘钥替换时使用',
  `owner` varchar(32) NOT NULL DEFAULT '' COMMENT '负责人',
  `app_url` varchar(512) NOT NULL DEFAULT '' COMMENT '项目url',
  `check_status` int(11) NOT NULL COMMENT '是否进行签名校验，1校验，2不校验，3不允许接口调用',
  `status` int(11) NOT NULL COMMENT '状态，1可用，2停止使用',
  `remark` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  `deleted` int(11) NOT NULL COMMENT '是否删除，1已删除，2未删除',
  `create_user` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_app_info_app_id` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='接入应用信息';

-- ----------------------------
-- Records of app_info
-- ----------------------------
BEGIN;
INSERT INTO `app_info` VALUES (1533130987548817783, 'Test', '测试应用', '491f9b14959011e8afb4784f439212b9', '70e7cdb0959011e8977e784f439212b9', 'Tree', '', 1, 1, '', 2, 'sys', '2018-08-01 13:41:07', 'sys', '2018-08-14 15:40:42');
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `sex` tinyint(4) NOT NULL COMMENT '性别，0未知，1男，2女',
  `birthday` datetime NOT NULL COMMENT '生日',
  `weight` double(10,2) NOT NULL COMMENT '体重',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1可用，2不可用',
  `remark` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  `deleted` int(11) NOT NULL COMMENT '是否删除，1删除，2未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES (1, 'name1', 1, '2010-01-01 19:19:19', 1.23, 1, 'remark', 2, '2019-05-21 16:09:30', '2019-05-21 12:42:44');
INSERT INTO `user_info` VALUES (2, 'abaach', 1, '2010-01-01 19:19:19', 1.23, 1, 'remark', 2, '2019-05-21 16:11:47', '2019-05-21 12:42:10');
INSERT INTO `user_info` VALUES (3, 'abaachb', 1, '2010-01-01 19:19:19', 1.23, 1, 'remark', 2, '2019-05-21 16:11:54', '2019-05-21 12:42:11');
INSERT INTO `user_info` VALUES (4, 'name', 1, '2019-05-21 20:41:31', 79.20, 1, 'remark', 2, '2019-05-21 20:41:32', '2019-05-21 12:42:12');
INSERT INTO `user_info` VALUES (1558442798897764705, 'name1558442798636', 1, '2019-05-21 20:46:38', 79.20, 1, 'remark', 2, '2019-05-21 20:46:39', '2019-05-21 20:46:39');
INSERT INTO `user_info` VALUES (1558443093423929938, 'name1558443093422', 1, '2019-05-21 20:51:33', 79.20, 1, 'remark', 2, '2019-05-21 20:51:33', '2019-05-21 20:51:33');
INSERT INTO `user_info` VALUES (1558491663328569693, 'name1558491662873', 1, '2019-05-22 10:21:02', 79.20, 1, 'remark', 2, '2019-05-22 10:21:03', '2019-05-22 10:21:03');
INSERT INTO `user_info` VALUES (1558491672154135872, 'name1558491672154', 1, '2019-05-22 10:21:12', 79.20, 1, 'remark', 2, '2019-05-22 10:21:12', '2019-05-22 10:21:12');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
