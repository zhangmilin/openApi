SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for plat_client_token
-- ----------------------------
DROP TABLE IF EXISTS `plat_client_token`;
CREATE TABLE `plat_client_token` (
  `client_id` varchar(45) NOT NULL,
  `token` text NOT NULL COMMENT '当前有效的token',
  `refresh_token` varchar(45) DEFAULT NULL COMMENT '刷新的token',
  `create_dts` datetime DEFAULT NULL,
  `update_dts` datetime DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='etl服务客户端保存的有效的token';

-- ----------------------------
-- Table structure for plat_client
-- ----------------------------
DROP TABLE IF EXISTS `plat_client`;
CREATE TABLE `plat_client` (
  `client_id` varchar(45) NOT NULL,
  `client_secret` varchar(500) NOT NULL,
  `client_name` varchar(45) DEFAULT NULL COMMENT '客户端名',
  `remarks` varchar(300) DEFAULT NULL COMMENT '备注',
  `status` int(1) NOT NULL COMMENT '状态 0有效/1失效',
  `create_dts` datetime DEFAULT NULL,
  `update_dts` datetime DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='etl服务客户端';

-- ----------------------------
-- Records of plat_client
-- ----------------------------
BEGIN;
INSERT INTO `plat_client` VALUES ('test', '1234567', 'test111', NULL, 0, '2020-04-29 17:51:39', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
