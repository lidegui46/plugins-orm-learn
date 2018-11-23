SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for player
-- ----------------------------
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player` (
  `id` bigint(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `platform` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of player
-- ----------------------------
INSERT INTO `player` VALUES ('1', 'kingston', 'ANDROID');
INSERT INTO `player` VALUES ('2', 'Jack', 'ANDROID');
INSERT INTO `player` VALUES ('3', 'younger', 'IOS');