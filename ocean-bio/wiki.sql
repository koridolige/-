/*
 Navicat Premium Data Transfer

 Source Server         : 海洋生物电子书以及分析系统数据库
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : wiki

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 10/07/2025 18:23:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent` bigint NOT NULL COMMENT '父id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `sort` int NULL DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 261 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (239, 0, '海洋生物', 0);
INSERT INTO `category` VALUES (240, 239, '动物界', 1);
INSERT INTO `category` VALUES (241, 239, '植物界', 2);
INSERT INTO `category` VALUES (242, 239, '原生生物界', 3);
INSERT INTO `category` VALUES (243, 239, '原核生物界', 4);
INSERT INTO `category` VALUES (244, 240, '脊索动物门', 10);
INSERT INTO `category` VALUES (245, 240, '节肢动物门', 11);
INSERT INTO `category` VALUES (246, 240, '软体动物门', 12);
INSERT INTO `category` VALUES (247, 240, '棘皮动物门', 13);
INSERT INTO `category` VALUES (248, 240, '刺胞动物门', 14);
INSERT INTO `category` VALUES (249, 241, '绿藻门', 20);
INSERT INTO `category` VALUES (250, 241, '红藻门', 21);
INSERT INTO `category` VALUES (251, 242, '甲藻门', 30);
INSERT INTO `category` VALUES (252, 242, '硅藻门', 31);
INSERT INTO `category` VALUES (253, 243, '蓝藻门', 0);
INSERT INTO `category` VALUES (254, 244, '蓝鲸', 100);
INSERT INTO `category` VALUES (255, 244, '虎鲸', 101);
INSERT INTO `category` VALUES (256, 244, '海豚', 102);
INSERT INTO `category` VALUES (257, 245, '梭子蟹', 103);
INSERT INTO `category` VALUES (258, 245, '对虾', 104);
INSERT INTO `category` VALUES (259, 246, '章鱼', 105);
INSERT INTO `category` VALUES (260, 247, '海星', 106);

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `doc_id` bigint NOT NULL COMMENT 'doc id',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'content',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES (1, 1, '海豚是一种聪明的海洋哺乳动物，属于鲸目齿鲸亚目海豚科。海豚是群居动物，通常生活在海洋中，但也有部分种类生活在河流中。海豚的智商很高，被认为是地球上最聪明的动物之一。');
INSERT INTO `content` VALUES (2, 2, '虎鲸是虎鲸属中仅有公认的物种，也是林奈于1758年在《自然系统》中最初描述的许多动物物种之一。因其头大，黑白色相间，视若虎，故名。');
INSERT INTO `content` VALUES (4, 30, '虎鲸\n品　种:巨型\n学　名:Orcinus orca\n英文名:Killer Whale\n中文名:逆戟鲸、杀人鲸\n\n描　述:\n是一种大型齿鲸，身长为8-10米，体重9吨左右，头部略圆，具有不明显的喙；背鳍高而直立，弯曲长达1米；身体黑、白两色。嘴巴细长，牙齿锋利，性情凶猛，食肉动物，善于进攻猎物，是企鹅、海豹等动物的天敌。有时它们还袭击其它鲸类，甚至是大白鲨，可称得上是海上霸王。\n\n');
INSERT INTO `content` VALUES (5, 106, '这是测试文档的内容，用于测试文档功能是否正常工作。');
INSERT INTO `content` VALUES (6, 74, '[object Object]1111');
INSERT INTO `content` VALUES (7, 75, '[object Object]2222222222222');
INSERT INTO `content` VALUES (8, 108, '品　种:巨型\n学　名:Balaenoptera musculus\n英文名:Blue Whale\n中文名:剃刀鲸\n深　度:0 - 100米\n描　述:\n蓝鲸被认为是已知的地球上生存过的体积最大的动物，也是现存最大的动物，迄今为止最大的海洋哺乳动物，长可达33米，重达200吨。蓝鲸的身躯瘦长，背部是青灰色的，不过在水中看起来有时颜色会比较淡。 温暖海水与冰冷海水的交汇处，是蓝鲸绝佳的栖息地，冰冷的海水通常富含浮游生物和磷虾，蓝鲸通常就以这两种生物为食，蓝鲸呈世界性分布，以南极海域数量为最多，主要是水温5-20℃的温带和寒带冷水域，有少数鲸曾来游于黄海和台湾海域。\n');
INSERT INTO `content` VALUES (9, 109, '抹香鲸\n品　种:巨型\n学　名:Physeter macrocephalus\n英文名:Sperm whale\n中文名:巨抹香鲸、卡切拉特鲸\n\n描　述:\n体长可达18米，体重超过50吨，是体型最大的齿鲸，头部可占身体的1/3，无背鳍；潜水能力极强，是潜水最深，潜水时间最长的哺乳动物。抹香鲸广泛分布于全世界不结冰的海域，由赤道一直到两极的不结冰的海域都可发现它们的踪迹。\n\n');
INSERT INTO `content` VALUES (10, 32, '钵水母\n品　种:无脊椎\n学　名:Scyphozoa\n英文名:Jellyfish\n中文名:海蜇、霞水母、海月水母\n\n描　述:\n钵水母的外形与水螅虫纲的水母型极相似，其区别是：多数体大,伞径最大达2米以上；有胃膜和胃丝，体呈四辐对称；生殖腺多位于生殖下穴的底部；水管系统复杂，辐管4条或4的倍数，常彼此构成网状。 水母内伞有发达肌纤维，借此收缩以排出胃腔内的水使水母向上或者向侧方运动。刺细胞分布在外伞表面、口腕、缘触手和胃丝上。中国沿海常见种有海蜇、霞水母、海月水母等。除十字水母营附着生活外，都是浮游生活，广布于各大洋，尤以热带海区为多。\n');

-- ----------------------------
-- Table structure for doc
-- ----------------------------
DROP TABLE IF EXISTS `doc`;
CREATE TABLE `doc`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ebook_id` bigint NOT NULL DEFAULT 0 COMMENT '电子书id',
  `parent` bigint NOT NULL DEFAULT 0 COMMENT '父id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `sort` int NULL DEFAULT NULL COMMENT '顺序',
  `view_count` int UNSIGNED NULL DEFAULT 0 COMMENT '阅读数',
  `vote_count` int UNSIGNED NULL DEFAULT 0 COMMENT '点赞数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of doc
-- ----------------------------
INSERT INTO `doc` VALUES (23, 2, 0, '海藻的形态特征', 1, 13, 4);
INSERT INTO `doc` VALUES (24, 0, 0, '测试18', 5, 0, 0);
INSERT INTO `doc` VALUES (27, 4, 0, '海神草的分布范围', 1, 6, 2);
INSERT INTO `doc` VALUES (28, 1, 1, '形态特征', 2, 20, 3);
INSERT INTO `doc` VALUES (30, 1, 0, '虎鲸简介', 1, 2, 0);
INSERT INTO `doc` VALUES (32, 3, 0, '钵水母简介', 1, 0, 0);
INSERT INTO `doc` VALUES (33, 4, 0, '海神草', NULL, 0, 0);
INSERT INTO `doc` VALUES (36, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (41, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (42, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (43, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (44, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (45, 2, 33, '海藻', 1, 0, 0);
INSERT INTO `doc` VALUES (46, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (47, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (48, 2, 0, '海藻1', NULL, 0, 0);
INSERT INTO `doc` VALUES (49, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (50, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (51, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (52, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (53, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (54, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (55, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (56, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (60, 6, 33, '虎鲸', 2, 0, 0);
INSERT INTO `doc` VALUES (61, 6, 0, '虎鲸', NULL, 0, 0);
INSERT INTO `doc` VALUES (62, 6, 0, '虎鲸', NULL, 0, 0);
INSERT INTO `doc` VALUES (66, 4, 0, '123', NULL, 0, 0);
INSERT INTO `doc` VALUES (68, 6, 0, '虎鲸', NULL, 0, 0);
INSERT INTO `doc` VALUES (71, 2, 0, '海藻', NULL, 0, 0);
INSERT INTO `doc` VALUES (73, 0, 33, '草', 111, 0, 0);
INSERT INTO `doc` VALUES (74, 30, 0, '11', 1, 0, 0);
INSERT INTO `doc` VALUES (75, 30, 0, '11', 1, 0, 0);
INSERT INTO `doc` VALUES (103, 30, 0, '111', 1, 0, 0);
INSERT INTO `doc` VALUES (104, 30, 0, '1111', 2, 0, 0);
INSERT INTO `doc` VALUES (105, 30, 0, '111', 1, 0, 0);
INSERT INTO `doc` VALUES (106, 1111, 0, '测试文档', 1, 0, 0);
INSERT INTO `doc` VALUES (107, 28, 0, '212', 1, 0, 0);
INSERT INTO `doc` VALUES (108, 31, 0, '蓝鲸简介', 0, 0, 0);
INSERT INTO `doc` VALUES (109, 5, 0, '抹香鲸简介', 0, 0, 0);

-- ----------------------------
-- Table structure for ebook
-- ----------------------------
DROP TABLE IF EXISTS `ebook`;
CREATE TABLE `ebook`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `category_id` bigint NULL DEFAULT NULL,
  `category1_id` bigint NULL DEFAULT NULL COMMENT '分类1',
  `category2_id` bigint NULL DEFAULT NULL COMMENT '分类2',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `cover` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面',
  `doc_count` int NULL DEFAULT NULL COMMENT '文档数',
  `view_count` int NULL DEFAULT NULL COMMENT '阅读数',
  `vote_count` int NULL DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '电子书' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ebook
-- ----------------------------
INSERT INTO `ebook` VALUES (1, '虎鲸', 244, 1, 2, '虎皮鲨鱼', '93a1cf8c7a144a2a83f65d96d2fc8c12.jpg', 6, 789, 488);
INSERT INTO `ebook` VALUES (2, '海藻', 253, 1, 2, '海产藻类（Algae）的统称，通常固着于海底或某种固体结构上，是基础细胞所构成的单株或一长串的简单植物。', '3f135e7bb24b47939c027ad5aeb07b56.jpg', 19, 13, 4);
INSERT INTO `ebook` VALUES (3, '钵水母', 246, 1, 3, '', 'f3bf33852c7844eb92051281cbf6c372.jpg', 3, 6, 3);
INSERT INTO `ebook` VALUES (5, '抹香鲸', 244, 1, 2, '鲸鱼', '9e350614777a49adb2bd0cf740cbccd8.jpg', 6, 789, 488);
INSERT INTO `ebook` VALUES (31, '蓝鲸', 244, NULL, NULL, '', 'dcfbd64b67724e34b0e8a1f68991b290.jpg', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for ebook_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `ebook_snapshot`;
CREATE TABLE `ebook_snapshot`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ebook_id` bigint NOT NULL COMMENT '电子书id',
  `date` date NOT NULL COMMENT '快照日期\r\n',
  `view_count` int NULL DEFAULT 0 COMMENT '阅读数',
  `vote_count` int NULL DEFAULT 0 COMMENT '点赞数',
  `view_increase` int NULL DEFAULT 0 COMMENT '阅读增长',
  `vote_increase` int NULL DEFAULT 0 COMMENT '点赞增长',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ebook_id_date_unique`(`ebook_id` ASC, `date` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 470 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ebook_snapshot
-- ----------------------------
INSERT INTO `ebook_snapshot` VALUES (239, 1, '2022-12-27', 1202, 480, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (240, 2, '2022-12-27', 6, 2, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (241, 3, '2022-12-27', 3, 1, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (242, 4, '2022-12-27', 4, 1, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (243, 5, '2022-12-27', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (244, 6, '2022-12-27', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (245, 7, '2022-12-27', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (246, 8, '2022-12-27', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (247, 9, '2022-12-27', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (248, 10, '2022-12-27', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (249, 11, '2022-12-27', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (250, 12, '2022-12-27', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (251, 16, '2022-12-27', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (254, 1, '2022-12-28', 1406, 771, 204, 291);
INSERT INTO `ebook_snapshot` VALUES (255, 2, '2022-12-28', 6, 2, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (256, 3, '2022-12-28', 3, 1, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (257, 4, '2022-12-28', 4, 1, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (258, 5, '2022-12-28', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (259, 6, '2022-12-28', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (260, 7, '2022-12-28', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (261, 8, '2022-12-28', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (262, 9, '2023-08-26', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (263, 10, '2023-08-27', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (264, 11, '2023-08-27', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (265, 12, '2023-08-27', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (266, 16, '2023-08-27', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (267, 1, '2023-08-31', 1406, 771, 1406, 771);
INSERT INTO `ebook_snapshot` VALUES (268, 2, '2023-08-31', 6, 2, 6, 2);
INSERT INTO `ebook_snapshot` VALUES (269, 3, '2023-08-31', 3, 1, 3, 1);
INSERT INTO `ebook_snapshot` VALUES (270, 4, '2023-08-31', 4, 1, 4, 1);
INSERT INTO `ebook_snapshot` VALUES (271, 5, '2023-08-31', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (272, 6, '2023-08-31', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (273, 7, '2023-08-31', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (274, 8, '2023-08-31', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (275, 9, '2023-08-31', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (276, 10, '2023-08-31', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (277, 11, '2023-08-31', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (278, 12, '2023-08-31', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (279, 16, '2023-08-31', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (280, 1, '2023-09-01', 729, 483, -677, -288);
INSERT INTO `ebook_snapshot` VALUES (281, 2, '2023-09-01', 10, 3, 4, 1);
INSERT INTO `ebook_snapshot` VALUES (282, 3, '2023-09-01', 5, 2, 2, 1);
INSERT INTO `ebook_snapshot` VALUES (283, 4, '2023-09-01', 5, 2, 1, 1);
INSERT INTO `ebook_snapshot` VALUES (284, 5, '2023-09-01', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (285, 6, '2023-09-01', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (286, 7, '2023-09-01', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (287, 8, '2023-09-01', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (288, 9, '2023-09-01', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (289, 10, '2023-09-01', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (290, 11, '2023-09-01', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (291, 12, '2023-09-01', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (292, 16, '2023-09-01', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (293, 1, '2023-09-04', 729, 483, 729, 483);
INSERT INTO `ebook_snapshot` VALUES (294, 2, '2023-09-04', 10, 3, 10, 3);
INSERT INTO `ebook_snapshot` VALUES (295, 3, '2023-09-04', 5, 2, 5, 2);
INSERT INTO `ebook_snapshot` VALUES (296, 4, '2023-09-04', 5, 2, 5, 2);
INSERT INTO `ebook_snapshot` VALUES (300, 5, '2023-09-04', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (301, 6, '2023-09-04', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (302, 1, '2023-09-05', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (303, 2, '2023-09-05', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (304, 3, '2023-09-05', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (305, 4, '2023-09-05', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (306, 5, '2023-09-05', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (307, 6, '2023-09-05', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (308, 1, '2023-09-06', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (309, 2, '2023-09-06', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (310, 3, '2023-09-06', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (311, 4, '2023-09-06', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (312, 5, '2023-09-06', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (313, 6, '2023-09-06', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (314, 1, '2023-09-13', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (315, 2, '2023-09-13', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (316, 3, '2023-09-13', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (317, 4, '2023-09-13', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (318, 5, '2023-09-13', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (319, 6, '2023-09-13', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (320, 1, '2023-09-18', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (321, 2, '2023-09-18', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (322, 3, '2023-09-18', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (323, 4, '2023-09-18', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (324, 5, '2023-09-18', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (325, 6, '2023-09-18', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (326, 1, '2023-09-21', 789, 488, 789, 488);
INSERT INTO `ebook_snapshot` VALUES (327, 2, '2023-09-21', 13, 4, 13, 4);
INSERT INTO `ebook_snapshot` VALUES (328, 3, '2023-09-21', 6, 3, 6, 3);
INSERT INTO `ebook_snapshot` VALUES (329, 4, '2023-09-21', 6, 2, 6, 2);
INSERT INTO `ebook_snapshot` VALUES (330, 5, '2023-09-21', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (331, 6, '2023-09-21', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (332, 1, '2025-06-30', 789, 488, 789, 488);
INSERT INTO `ebook_snapshot` VALUES (333, 2, '2025-06-30', 13, 4, 13, 4);
INSERT INTO `ebook_snapshot` VALUES (334, 3, '2025-06-30', 6, 3, 6, 3);
INSERT INTO `ebook_snapshot` VALUES (335, 5, '2025-06-30', 789, 488, 789, 488);
INSERT INTO `ebook_snapshot` VALUES (336, 7, '2025-06-30', 13, 4, 13, 4);
INSERT INTO `ebook_snapshot` VALUES (337, 8, '2025-06-30', 6, 3, 6, 3);
INSERT INTO `ebook_snapshot` VALUES (338, 9, '2025-06-30', 6, 2, 6, 2);
INSERT INTO `ebook_snapshot` VALUES (339, 10, '2025-06-30', 789, 488, 789, 488);
INSERT INTO `ebook_snapshot` VALUES (340, 11, '2025-06-30', 13, 4, 13, 4);
INSERT INTO `ebook_snapshot` VALUES (341, 12, '2025-06-30', 6, 3, 6, 3);
INSERT INTO `ebook_snapshot` VALUES (342, 18, '2025-06-30', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (352, 19, '2025-06-30', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (353, 20, '2025-06-30', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (354, 1, '2025-07-02', 789, 488, 789, 488);
INSERT INTO `ebook_snapshot` VALUES (355, 2, '2025-07-02', 13, 4, 13, 4);
INSERT INTO `ebook_snapshot` VALUES (356, 3, '2025-07-02', 6, 3, 6, 3);
INSERT INTO `ebook_snapshot` VALUES (357, 5, '2025-07-02', 789, 488, 789, 488);
INSERT INTO `ebook_snapshot` VALUES (358, 7, '2025-07-02', 13, 4, 13, 4);
INSERT INTO `ebook_snapshot` VALUES (359, 8, '2025-07-02', 6, 3, 6, 3);
INSERT INTO `ebook_snapshot` VALUES (360, 9, '2025-07-02', 6, 2, 6, 2);
INSERT INTO `ebook_snapshot` VALUES (361, 10, '2025-07-02', 789, 488, 789, 488);
INSERT INTO `ebook_snapshot` VALUES (362, 11, '2025-07-02', 13, 4, 13, 4);
INSERT INTO `ebook_snapshot` VALUES (363, 12, '2025-07-02', 6, 3, 6, 3);
INSERT INTO `ebook_snapshot` VALUES (364, 21, '2025-07-02', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (365, 23, '2025-07-02', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (366, 25, '2025-07-02', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (367, 26, '2025-07-02', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (368, 27, '2025-07-02', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (369, 28, '2025-07-02', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (370, 29, '2025-07-02', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (371, 30, '2025-07-02', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (372, 1, '2025-07-04', 789, 488, 789, 488);
INSERT INTO `ebook_snapshot` VALUES (373, 2, '2025-07-04', 13, 4, 13, 4);
INSERT INTO `ebook_snapshot` VALUES (374, 3, '2025-07-04', 6, 3, 6, 3);
INSERT INTO `ebook_snapshot` VALUES (375, 5, '2025-07-04', 789, 488, 789, 488);
INSERT INTO `ebook_snapshot` VALUES (376, 7, '2025-07-04', 13, 4, 13, 4);
INSERT INTO `ebook_snapshot` VALUES (377, 8, '2025-07-04', 6, 3, 6, 3);
INSERT INTO `ebook_snapshot` VALUES (378, 9, '2025-07-04', 6, 2, 6, 2);
INSERT INTO `ebook_snapshot` VALUES (379, 10, '2025-07-04', 789, 488, 789, 488);
INSERT INTO `ebook_snapshot` VALUES (380, 11, '2025-07-04', 13, 4, 13, 4);
INSERT INTO `ebook_snapshot` VALUES (381, 12, '2025-07-04', 6, 3, 6, 3);
INSERT INTO `ebook_snapshot` VALUES (382, 21, '2025-07-04', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (383, 23, '2025-07-04', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (384, 25, '2025-07-04', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (385, 26, '2025-07-04', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (386, 27, '2025-07-04', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (387, 28, '2025-07-04', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (388, 29, '2025-07-04', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (389, 30, '2025-07-04', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (390, 1, '2025-07-08', 789, 488, 789, 488);
INSERT INTO `ebook_snapshot` VALUES (391, 2, '2025-07-08', 13, 4, 13, 4);
INSERT INTO `ebook_snapshot` VALUES (392, 3, '2025-07-08', 6, 3, 6, 3);
INSERT INTO `ebook_snapshot` VALUES (393, 5, '2025-07-08', 789, 488, 789, 488);
INSERT INTO `ebook_snapshot` VALUES (394, 7, '2025-07-08', 13, 4, 13, 4);
INSERT INTO `ebook_snapshot` VALUES (395, 8, '2025-07-08', 6, 3, 6, 3);
INSERT INTO `ebook_snapshot` VALUES (396, 9, '2025-07-08', 6, 2, 6, 2);
INSERT INTO `ebook_snapshot` VALUES (397, 10, '2025-07-08', 789, 488, 789, 488);
INSERT INTO `ebook_snapshot` VALUES (398, 11, '2025-07-08', 13, 4, 13, 4);
INSERT INTO `ebook_snapshot` VALUES (399, 12, '2025-07-08', 6, 3, 6, 3);
INSERT INTO `ebook_snapshot` VALUES (400, 21, '2025-07-08', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (401, 23, '2025-07-08', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (402, 25, '2025-07-08', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (403, 26, '2025-07-08', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (404, 27, '2025-07-08', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (405, 28, '2025-07-08', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (406, 30, '2025-07-08', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (407, 1, '2025-07-09', 789, 488, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (408, 2, '2025-07-09', 13, 4, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (409, 3, '2025-07-09', 6, 3, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (410, 5, '2025-07-09', 789, 488, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (411, 7, '2025-07-09', 13, 4, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (412, 8, '2025-07-09', 6, 3, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (413, 9, '2025-07-09', 6, 2, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (414, 10, '2025-07-09', 789, 488, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (415, 11, '2025-07-09', 13, 4, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (416, 12, '2025-07-09', 6, 3, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (417, 21, '2025-07-09', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (418, 23, '2025-07-09', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (419, 25, '2025-07-09', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (420, 26, '2025-07-09', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (421, 27, '2025-07-09', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (422, 28, '2025-07-09', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (423, 30, '2025-07-09', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (438, 1, '2025-07-10', 789, 488, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (439, 2, '2025-07-10', 13, 4, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (440, 3, '2025-07-10', 6, 3, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (441, 5, '2025-07-10', 789, 488, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (442, 7, '2025-07-10', 13, 4, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (443, 8, '2025-07-10', 6, 3, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (444, 9, '2025-07-10', 6, 2, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (445, 10, '2025-07-10', 789, 488, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (446, 11, '2025-07-10', 13, 4, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (447, 12, '2025-07-10', 6, 3, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (448, 21, '2025-07-10', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (449, 23, '2025-07-10', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (450, 25, '2025-07-10', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (451, 26, '2025-07-10', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (452, 27, '2025-07-10', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (453, 28, '2025-07-10', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (454, 30, '2025-07-10', NULL, NULL, NULL, NULL);
INSERT INTO `ebook_snapshot` VALUES (469, 31, '2025-07-10', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `password` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name_unique`(`login_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (29, 'aaaa', 'aaaa', '$2a$10$I4038BQDMLuxFKjSZ6irl.vpstzlqjgGI8DLZPjTzNnpTYY77B2gC');

-- ----------------------------
-- Table structure for wx_banner
-- ----------------------------
DROP TABLE IF EXISTS `wx_banner`;
CREATE TABLE `wx_banner`  (
  `banner_id` int NOT NULL AUTO_INCREMENT,
  `imgurl` varchar(510) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`banner_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_banner
-- ----------------------------
INSERT INTO `wx_banner` VALUES (1, 'https://imgcps.jd.com/img-cubic/creative_server_cia_jdcloud/v2/2000366/55575915140/FocusFullshop/CkJqZnMvdDEvMTgyNDU3LzgvMzM2MzYvMjY2NDAvNjU2MjYzOWFGODA4NzdjYWIvMDFhNGJiNzMxZGRmYWRkMS5wbmcSCTItdHlfMF81MzACOO6LekIQCgznvZfmioDpvKDmoIcQAUIQCgzlpb3otKfmsYfogZoQAkIQCgznq4vljbPmiqLotK0QBkIKCgbkvJjotKgQB1iE1dSEzwE/cr/s/q.jpg');
INSERT INTO `wx_banner` VALUES (2, 'https://imgcps.jd.com/img-cubic/creative_server_cia_jdcloud/v2/2000366/100058343669/FocusFullshop/CkJqZnMvdDEvMTk1NDAyLzcvNDA5NjIvMzY1NjYvNjU2MjUyODhGYmVhZjcwZmUvZjQ3ODRhYzg1Zjg0YWU4Yi5wbmcSCTMtdHlfMF81NDACOO6LekIUChBpUU9P5bmz5p2_55S16ISREAFCEwoP5LyY5oOg5Lqr5LiN5YGcEAJCEAoM56uL5Y2z5oqi6LStEAZCCgoG5Yqb6I2QEAdY9dHE3_QC/cr/s/q.jpg');
INSERT INTO `wx_banner` VALUES (3, 'https://imgcps.jd.com/ling-cubic/ling4/lab/amZzL3QxLzE0NzI1NC8yOS8xNzc4MS8xODg0NjUvNWZkMDg1NzNFMDVlY2RjNTUvMjBjNjZmYTllZmJmZmY2NC5wbmc/5Lqs6YCJ5aW96LSn/5L2g5YC85b6X5oul5pyJ/1635183047968657409/cr/s/q.jpg');
INSERT INTO `wx_banner` VALUES (4, 'https://imgcps.jd.com/ling-cubic/ling4/lab/amZzL3QxLzQ5MTQzLzI4LzE3MDM2LzIxOTg4OS82MTM4NDhkNkU2MDY1ZWM5OC9iM2I4ZWE1ODE5MmQzNmI0LnBuZw/5Lqs6YCJ5aW96LSn/5L2g5YC85b6X5oul5pyJ/1635185254164787202/cr/s/q.jpg');
INSERT INTO `wx_banner` VALUES (5, 'https://tse3-mm.cn.bing.net/th/id/OIP-C.wuVW4A7-SWGWydWU8mSWaQAAAA?w=284&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7');
INSERT INTO `wx_banner` VALUES (9, 'https://s2.51cto.com/images/avater/202304/8590a0e000c11755d363567aabeaa9d542c00c.jpg?x-oss-process=image/format,webp');
INSERT INTO `wx_banner` VALUES (10, 'https://s2.51cto.com/images/avater/202304/668c87f261f3d759e9631274ed74d6888553df.jpg?x-oss-process=image/format,webp');

-- ----------------------------
-- Table structure for wx_cart
-- ----------------------------
DROP TABLE IF EXISTS `wx_cart`;
CREATE TABLE `wx_cart`  (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `goods_id` int NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cart_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `goods_id`(`goods_id` ASC) USING BTREE,
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `wx_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `wx_goods` (`goods_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_cart
-- ----------------------------
INSERT INTO `wx_cart` VALUES (59, 21, 3, 4, '2023-12-13 19:17:58');
INSERT INTO `wx_cart` VALUES (60, 21, 18, 20, '2023-12-13 19:21:55');
INSERT INTO `wx_cart` VALUES (61, 1, 7, 1, '2023-12-23 17:37:34');
INSERT INTO `wx_cart` VALUES (65, 37, 2, 1, '2024-03-01 19:24:00');
INSERT INTO `wx_cart` VALUES (69, 1652555806, 1, 4, '2024-03-24 01:56:42');
INSERT INTO `wx_cart` VALUES (70, 1652555806, 5, 18, '2024-03-24 02:07:23');
INSERT INTO `wx_cart` VALUES (71, 1652555806, 2, 2, '2024-03-24 03:20:27');
INSERT INTO `wx_cart` VALUES (72, 1652555806, 2, 1, '2024-03-24 03:20:29');
INSERT INTO `wx_cart` VALUES (73, 1652555806, 4, 2, '2024-03-24 17:15:50');
INSERT INTO `wx_cart` VALUES (88, 1652555795, 3, 1, '2024-08-27 09:52:28');

-- ----------------------------
-- Table structure for wx_goods
-- ----------------------------
DROP TABLE IF EXISTS `wx_goods`;
CREATE TABLE `wx_goods`  (
  `goods_id` int NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `brand` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `specifications` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `stock` int NULL DEFAULT NULL,
  `sales` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_goods
-- ----------------------------
INSERT INTO `wx_goods` VALUES (1, 'MacBook Air', 7999.00, '电脑', 'Apple', '屏幕尺寸：13.3英寸', '轻薄便携的笔记本电脑，适合办公和娱乐使用', 'https://img10.360buyimg.com/n7/jfs/t1/76383/6/20261/11371/643608dbF8ff3a940/961b34ab0dde6d7b.png', 60, 107, '2023-11-23 10:30:00', '2023-11-23 15:45:00');
INSERT INTO `wx_goods` VALUES (2, 'Sony 65寸电视', 5999.00, '家电', 'Sony', '屏幕尺寸：65英寸，分辨率：4K', '高清晰度的大屏幕电视，享受沉浸式观影体验', 'https://img11.360buyimg.com/n7/jfs/t1/210005/1/32851/118378/6440d5d3Fe8d0ae37/a4b76bd1f4020872.jpg', 265, 57, '2023-11-22 14:20:00', '2023-11-23 09:10:00');
INSERT INTO `wx_goods` VALUES (3, 'Nike Air Max', 899.00, '运动鞋', 'Nike', '尺码：US 9，颜色：黑白', '舒适耐用的运动鞋，适合跑步和健身等活动', 'https://img11.360buyimg.com/n7/jfs/t1/92043/38/38142/29651/6551bb72F1b368a87/2d4ac30f537d5e8f.jpg', 77, 73, '2023-11-20 16:45:00', '2023-11-22 11:30:00');
INSERT INTO `wx_goods` VALUES (4, 'Canon 相机套装', 2999.00, '摄影器材', 'Canon', '像素：2400万，镜头：18-55mm', '入门级单反相机套装，拍摄高质量的照片和视频', 'https://img14.360buyimg.com/n7/jfs/t1/103043/21/37519/83714/63fedea7F4fc4fb28/ae73831ffb62f080.jpg', 65, 45, '2023-11-19 09:00:00', '2023-11-21 13:15:00');
INSERT INTO `wx_goods` VALUES (5, 'Adidas 运动裤', 199.00, '运动服', 'Adidas', '尺码：L，颜色：灰色', '舒适透气的运动裤，适合各种户外运动和健身', 'https://img12.360buyimg.com/n7/jfs/t1/150933/29/24690/424482/62b17447E535eb60c/67c606da23776ea5.png', 186, 94, '2023-11-18 11:30:00', '2023-11-20 16:20:00');
INSERT INTO `wx_goods` VALUES (6, 'Philips 咖啡机', 399.00, '厨房电器', 'Philips', '功率：1200W，容量：1.5升', '自动咖啡机，方便快捷地制作美味的咖啡', 'https://img11.360buyimg.com/n7/jfs/t1/154201/21/31483/94910/654fa554Fc321a460/f26e41652f561f62.jpg', 139, 71, '2023-11-17 14:20:00', '2023-11-19 10:45:00');
INSERT INTO `wx_goods` VALUES (7, 'Gucci 皮包', 2599.00, '时尚配饰', 'Gucci', '尺寸：30x25x12cm，颜色：红色', '精致的皮质手提包，展现时尚品味', 'https://img14.360buyimg.com/n7/jfs/t1/93749/20/39835/156434/64ab70a8Fab2bbf77/c1d931b1ca2e6772.jpg', 38, 17, '2023-11-16 09:10:00', '2023-11-18 13:30:00');
INSERT INTO `wx_goods` VALUES (8, 'LG 冰箱', 3999.00, '厨卫电器', 'LG', '容量：400升，能效等级：A+', '大容量的冰箱，保持食物新鲜和冷藏储存', 'https://img13.360buyimg.com/n7/jfs/t1/187060/11/40123/5620/652cfda9F7048f4c9/1864239fc0f281c8.jpg', 54, 31, '2023-11-15 12:20:00', '2023-11-17 08:40:00');
INSERT INTO `wx_goods` VALUES (9, 'Samsung Galaxy S21', 5999.00, '手机', 'Samsung', '屏幕尺寸：6.2英寸，内存：128GB', '高性能的智能手机，拍摄精美照片和流畅游戏体验', 'https://img11.360buyimg.com/n7/jfs/t1/200363/34/24734/91161/62a05c39E159d5ce1/db345059a880e046.jpg', 43, 27, '2023-11-14 10:30:00', '2023-11-16 15:45:00');
INSERT INTO `wx_goods` VALUES (10, 'HUAWEI MateBook X Pro', 8999.00, '电脑', 'HUAWEI', '屏幕尺寸：13.9英寸，内存：16GB', '轻薄便携的高性能笔记本电脑，适合专业使用', 'https://img11.360buyimg.com/n7/jfs/t1/197658/2/35633/29092/647d75b6F073efa0e/0898a081a30173f1.jpg', 28, 12, '2023-11-13 14:20:00', '2023-11-15 09:10:00');
INSERT INTO `wx_goods` VALUES (11, 'Sony PlayStation 5', 2999.00, '游戏机', 'Sony', '存储容量：825GB，支持4K游戏', '次世代游戏机，享受沉浸式游戏体验和流畅操作', 'https://img10.360buyimg.com/n7/jfs/t1/188034/28/13138/213175/60ee5744E99e76742/3ddd8e3198d64ba6.png', 37, 113, '2023-11-12 16:45:00', '2023-11-14 11:30:00');
INSERT INTO `wx_goods` VALUES (12, 'Canon EOS R5', 14999.00, '摄影器材', 'Canon', '像素：4500万，镜头：24-105mm', '专业级全画幅无反相机，拍摄高清晰度照片和视频', 'https://img14.360buyimg.com/n7/jfs/t1/187923/25/41460/74452/6552033fFc14563d6/63eb504c83e18baa.jpg', 80, 30, '2023-11-11 09:00:00', '2023-11-13 13:15:00');
INSERT INTO `wx_goods` VALUES (13, 'Nike Air Jordan 1', 1299.00, '运动鞋', 'Nike', '尺码：US 10，颜色：红黑', '经典的篮球鞋款，具有时尚和耐用的特点', 'https://img14.360buyimg.com/n7/jfs/t1/196313/29/41799/55466/65504a0fF0c51d8ee/04e20b0dd8667ddb.jpg', 196, 84, '2023-11-10 11:30:00', '2023-11-12 16:20:00');
INSERT INTO `wx_goods` VALUES (14, 'Adidas Ultraboost', 899.00, '运动鞋', 'Adidas', '尺码：US 8.5，颜色：白色', '舒适缓震的跑步鞋，提供理想的脚感和支撑', 'https://img10.360buyimg.com/n7/jfs/t1/111773/37/34503/39168/63830e45E0636a708/f29754c7fd7e2e02.jpg', 150, 60, '2023-11-09 14:20:00', '2023-11-11 10:45:00');
INSERT INTO `wx_goods` VALUES (15, 'Bose QuietComfort 35 II', 1999.00, '音频设备', 'Bose', '无线耳机，主动降噪技术', '高品质的音频体验，享受清晰的音乐和沉浸式声音', 'https://img12.360buyimg.com/n7/jfs/t1/102874/30/15698/56046/5e74361cE6b03907b/0e2d232e9436aecb.jpg', 37, 18, '2023-11-08 09:10:00', '2023-11-10 13:30:00');
INSERT INTO `wx_goods` VALUES (16, 'Samsung 65寸电视', 6999.00, '家电', 'Samsung', '屏幕尺寸：65英寸，分辨率：8K', '超高清大屏电视，呈现出细腻逼真的画面效果', 'https://img13.360buyimg.com/n7/jfs/t1/118143/31/39212/83860/654d8fb7F79a258a7/44d909a95c8aad43.jpg', 60, 25, '2023-11-07 12:20:00', '2023-11-09 08:40:00');
INSERT INTO `wx_goods` VALUES (17, 'Apple AirPods Pro', 1599.00, '音频设备', 'Apple', '无线耳机，主动降噪技术', '智能降噪耳机，提供清晰音质和舒适佩戴体验', 'https://img14.360buyimg.com/n7/jfs/t1/237807/22/427/16244/65364e5dF924d94d5/fec6eac9d9801770.jpg', 80, 35, '2023-11-06 10:15:00', '2023-11-08 15:20:00');
INSERT INTO `wx_goods` VALUES (18, 'Lenovo ThinkPad X1 Carbon', 7999.00, '电脑', 'Lenovo', '屏幕尺寸：14英寸，内存：16GB', '商务领域的高性能笔记本电脑，轻薄便携且功能强大', 'https://img14.360buyimg.com/n7/jfs/t1/174007/5/38618/50458/646ecae5F35a37771/339959c1bb02c110.jpg', 46, 24, '2023-11-05 13:40:00', '2023-11-07 09:55:00');
INSERT INTO `wx_goods` VALUES (19, 'Canon EOS 5D Mark IV', 13999.00, '摄影器材', 'Canon', '像素：3000万，镜头：24-70mm', '专业级全画幅单反相机，适用于各种拍摄场景', 'https://img12.360buyimg.com/n7/jfs/t1/6200/24/25366/60553/64fac3dfFfcb43c8e/02fa88225bccd305.jpg', 96, 44, '2023-11-04 15:30:00', '2023-11-06 11:15:00');
INSERT INTO `wx_goods` VALUES (20, 'Sony BRAVIA OLED 4K电视', 9999.00, '家电', 'Sony', '屏幕尺寸：55英寸，分辨率：4K', '高级OLED电视，提供逼真的图像和震撼的音效', 'https://img1.360buyimg.com/n6/jfs/t1/89341/4/40173/73936/648ab8e5Fcd89348d/880804e70a337dd2.jpg', 70, 30, '2023-11-03 09:50:00', '2023-11-05 14:35:00');
INSERT INTO `wx_goods` VALUES (21, 'Nike Air Max 90', 899.00, '运动鞋', 'Nike', '尺码：US 9.5，颜色：黑白', '经典的跑步鞋款，具有时尚外观和舒适感', 'https://img11.360buyimg.com/n7/jfs/t1/89276/38/35861/70611/65517700F85a4e363/a91d303eabbe408d.png', 200, 90, '2023-11-02 12:25:00', '2023-11-04 08:30:00');
INSERT INTO `wx_goods` VALUES (22, 'Adidas Superstar', 699.00, '运动鞋', 'Adidas', '尺码：US 7，颜色：白金', '经典的休闲鞋款，时尚简约且易于搭配', 'https://img13.360buyimg.com/n7/jfs/t1/22619/26/18456/63842/62dfa94aE9453eea1/ec599205d69b879f.jpg', 150, 75, '2023-11-01 15:20:00', '2023-11-03 10:40:00');
INSERT INTO `wx_goods` VALUES (23, 'Beats Solo Pro 头戴式耳机', 1999.00, '音频设备', 'Beats', '无线耳机，噪音控制功能', '高保真音质和舒适佩戴，适用于音乐和通话', 'https://img11.360buyimg.com/n7/jfs/t1/100105/22/20729/31047/641e66ccFe5dd7f93/db02c34c5b7f1ea3.jpg', 60, 25, '2023-10-31 08:30:00', '2023-11-02 13:15:00');
INSERT INTO `wx_goods` VALUES (24, 'LG 55寸智能电视', 4499.00, '家电', 'LG', '屏幕尺寸：55英寸，分辨率：4K', '智能功能丰富的电视，提供优质的影音娱乐体验', 'https://img12.360buyimg.com/n7/jfs/t1/231388/40/3757/92068/655b1d10F3de95956/35b60e2e28b48607.jpg', 80, 35, '2023-10-30 11:10:00', '2023-11-01 09:25:00');

-- ----------------------------
-- Table structure for wx_order_item
-- ----------------------------
DROP TABLE IF EXISTS `wx_order_item`;
CREATE TABLE `wx_order_item`  (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NULL DEFAULT NULL,
  `goods_id` int NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `price` double(10, 0) NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`item_id`) USING BTREE,
  INDEX `goods_id`(`goods_id` ASC) USING BTREE,
  INDEX `order_id`(`order_id` ASC) USING BTREE,
  CONSTRAINT `goods_id` FOREIGN KEY (`goods_id`) REFERENCES `wx_goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `wx_orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 123 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_order_item
-- ----------------------------
INSERT INTO `wx_order_item` VALUES (13, 10, 1, 3, 7999, '2023-12-02 23:21:01');
INSERT INTO `wx_order_item` VALUES (14, 10, 5, 2, 199, '2023-12-02 23:21:01');
INSERT INTO `wx_order_item` VALUES (15, 11, 1, 1, 7999, '2023-12-02 23:23:53');
INSERT INTO `wx_order_item` VALUES (16, 12, 2, 4, 5999, '2023-12-02 23:31:40');
INSERT INTO `wx_order_item` VALUES (17, 12, 8, 3, 3999, '2023-12-02 23:31:40');
INSERT INTO `wx_order_item` VALUES (18, 13, 3, 3, 899, '2023-12-03 00:14:38');
INSERT INTO `wx_order_item` VALUES (19, 13, 1, 8, 7999, '2023-12-03 00:14:38');
INSERT INTO `wx_order_item` VALUES (20, 13, 18, 1, 7999, '2023-12-03 00:14:38');
INSERT INTO `wx_order_item` VALUES (22, 15, 6, 1, 399, '2023-12-03 00:58:06');
INSERT INTO `wx_order_item` VALUES (32, 28, 1, 4, 7999, '2023-12-09 00:41:02');
INSERT INTO `wx_order_item` VALUES (33, 31, 18, 1, 7999, '2023-12-09 13:39:03');
INSERT INTO `wx_order_item` VALUES (39, 36, 5, 1, 199, '2023-12-13 10:52:58');
INSERT INTO `wx_order_item` VALUES (40, 37, 1, 1, 7999, '2023-12-13 10:53:19');
INSERT INTO `wx_order_item` VALUES (41, 38, 2, 1, 5999, '2023-12-13 11:00:50');
INSERT INTO `wx_order_item` VALUES (42, 39, 2, 1, 5999, '2023-12-13 11:04:18');
INSERT INTO `wx_order_item` VALUES (43, 40, 2, 1, 5999, '2023-12-13 11:04:25');
INSERT INTO `wx_order_item` VALUES (46, 42, 3, 4, 899, '2023-12-13 19:17:59');
INSERT INTO `wx_order_item` VALUES (47, 43, 1, 3, 7999, '2023-12-13 19:26:40');
INSERT INTO `wx_order_item` VALUES (48, 44, 1, 4, 7999, '2023-12-13 19:33:16');
INSERT INTO `wx_order_item` VALUES (49, 45, 1, 5, 7999, '2023-12-13 19:39:32');
INSERT INTO `wx_order_item` VALUES (50, 46, 4, 1, 2999, '2024-03-01 19:24:09');
INSERT INTO `wx_order_item` VALUES (51, 47, 4, 1, 2999, '2024-03-01 19:24:10');
INSERT INTO `wx_order_item` VALUES (52, 48, 4, 1, 2999, '2024-03-01 19:25:44');
INSERT INTO `wx_order_item` VALUES (53, 49, 1, 2, 10, '2024-03-23 23:51:56');
INSERT INTO `wx_order_item` VALUES (54, 49, 2, 3, 20, '2024-03-23 23:51:56');
INSERT INTO `wx_order_item` VALUES (55, 50, 1, 2, 10, '2024-03-24 00:16:38');
INSERT INTO `wx_order_item` VALUES (56, 50, 2, 3, 20, '2024-03-24 00:16:38');
INSERT INTO `wx_order_item` VALUES (57, 51, 1, 2, 10, '2024-03-24 01:30:36');
INSERT INTO `wx_order_item` VALUES (58, 51, 2, 3, 20, '2024-03-24 01:30:36');
INSERT INTO `wx_order_item` VALUES (69, 57, 1, 1, 10, '2024-03-24 18:00:25');
INSERT INTO `wx_order_item` VALUES (70, 57, 2, 1, 20, '2024-03-24 18:00:26');
INSERT INTO `wx_order_item` VALUES (71, 58, 1, 2, 10, '2024-03-24 18:19:51');
INSERT INTO `wx_order_item` VALUES (72, 58, 2, 3, 20, '2024-03-24 18:19:51');
INSERT INTO `wx_order_item` VALUES (73, 59, 4, 1, 2999, '2024-03-24 18:21:16');
INSERT INTO `wx_order_item` VALUES (74, 60, 4, 1, 2999, '2024-03-24 18:30:24');
INSERT INTO `wx_order_item` VALUES (75, 61, 4, 1, 2999, '2024-04-27 11:20:39');
INSERT INTO `wx_order_item` VALUES (76, 62, 2, 1, 5999, '2024-08-23 16:08:11');
INSERT INTO `wx_order_item` VALUES (77, 63, 1, 1, 7999, '2024-08-23 16:10:06');
INSERT INTO `wx_order_item` VALUES (78, 64, 1, 1, 7999, '2024-08-23 16:12:34');
INSERT INTO `wx_order_item` VALUES (79, 65, 1, 1, 7999, '2024-08-23 16:16:17');
INSERT INTO `wx_order_item` VALUES (80, 66, 3, 1, 899, '2024-08-23 16:42:59');
INSERT INTO `wx_order_item` VALUES (81, 67, 3, 1, 899, '2024-08-23 16:45:35');
INSERT INTO `wx_order_item` VALUES (82, 68, 3, 1, 899, '2024-08-23 16:48:56');
INSERT INTO `wx_order_item` VALUES (83, 69, 2, 1, 5999, '2024-08-23 16:53:48');
INSERT INTO `wx_order_item` VALUES (84, 70, 2, 1, 5999, '2024-08-23 17:24:37');
INSERT INTO `wx_order_item` VALUES (85, 71, 2, 1, 5999, '2024-08-23 17:36:37');
INSERT INTO `wx_order_item` VALUES (86, 71, 3, 2, 1798, '2024-08-26 10:43:30');
INSERT INTO `wx_order_item` VALUES (87, 72, 2, 1, 5999, '2024-08-23 17:48:21');
INSERT INTO `wx_order_item` VALUES (88, 73, 2, 1, 5999, '2024-08-23 18:02:29');
INSERT INTO `wx_order_item` VALUES (89, 74, 2, 1, 5999, '2024-08-23 18:08:08');
INSERT INTO `wx_order_item` VALUES (90, 75, 4, 1, 2999, '2024-08-23 19:41:35');
INSERT INTO `wx_order_item` VALUES (91, 76, 2, 1, 5999, '2024-08-24 17:41:40');
INSERT INTO `wx_order_item` VALUES (94, 79, 18, 1, 7999, '2024-08-26 14:33:12');
INSERT INTO `wx_order_item` VALUES (95, 80, 18, 1, 7999, '2024-08-26 15:16:08');
INSERT INTO `wx_order_item` VALUES (96, 81, 10, 1, 8999, '2024-08-26 15:28:22');
INSERT INTO `wx_order_item` VALUES (97, 82, 1, 1, 7999, '2024-08-26 15:40:59');
INSERT INTO `wx_order_item` VALUES (99, 84, 3, 1, 899, '2024-08-26 15:59:45');
INSERT INTO `wx_order_item` VALUES (100, 85, 10, 1, 8999, '2024-08-26 16:13:00');
INSERT INTO `wx_order_item` VALUES (101, 86, 2, 1, 5999, '2024-08-26 16:23:04');
INSERT INTO `wx_order_item` VALUES (102, 87, 3, 1, 899, '2024-08-26 16:24:31');
INSERT INTO `wx_order_item` VALUES (103, 88, 2, 1, 5999, '2024-08-26 16:30:22');
INSERT INTO `wx_order_item` VALUES (104, 89, 2, 1, 5999, '2024-08-26 16:44:09');
INSERT INTO `wx_order_item` VALUES (105, 90, 2, 1, 5999, '2024-08-26 16:46:13');
INSERT INTO `wx_order_item` VALUES (106, 91, 6, 1, 399, '2024-08-26 17:00:03');
INSERT INTO `wx_order_item` VALUES (107, 92, 3, 1, 899, '2024-08-26 17:08:57');
INSERT INTO `wx_order_item` VALUES (108, 93, 5, 1, 199, '2024-08-26 17:11:27');
INSERT INTO `wx_order_item` VALUES (109, 94, 3, 1, 899, '2024-08-26 17:18:35');
INSERT INTO `wx_order_item` VALUES (110, 95, 3, 1, 899, '2024-08-26 17:26:17');
INSERT INTO `wx_order_item` VALUES (111, 96, 5, 1, 199, '2024-08-26 17:32:55');
INSERT INTO `wx_order_item` VALUES (112, 97, 3, 1, 899, '2024-08-26 17:45:27');
INSERT INTO `wx_order_item` VALUES (113, 98, 4, 1, 2999, '2024-08-26 18:04:33');
INSERT INTO `wx_order_item` VALUES (115, 100, 3, 2, 899, '2024-08-27 01:25:28');
INSERT INTO `wx_order_item` VALUES (116, 101, 4, 1, 2999, '2024-08-27 01:34:33');
INSERT INTO `wx_order_item` VALUES (117, 102, 1, 1, 7999, '2024-08-27 09:39:42');
INSERT INTO `wx_order_item` VALUES (119, 104, 4, 2, 2999, '2024-08-27 09:41:45');
INSERT INTO `wx_order_item` VALUES (120, 105, 5, 2, 199, '2024-08-27 09:52:36');
INSERT INTO `wx_order_item` VALUES (121, 106, 1, 1, 7999, '2024-08-27 10:03:28');
INSERT INTO `wx_order_item` VALUES (122, 107, 1, 1, 7999, '2024-08-27 10:59:06');

-- ----------------------------
-- Table structure for wx_orders
-- ----------------------------
DROP TABLE IF EXISTS `wx_orders`;
CREATE TABLE `wx_orders`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `total_price` decimal(10, 2) NULL DEFAULT NULL,
  `creat_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `payment_status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `wx_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_orders
-- ----------------------------
INSERT INTO `wx_orders` VALUES (10, 1, 24395.00, '2023-12-02 23:21:57', 'success');
INSERT INTO `wx_orders` VALUES (11, 1, 7999.00, '2023-12-02 23:24:20', 'success');
INSERT INTO `wx_orders` VALUES (12, 1, 35993.00, '2023-12-02 23:32:22', 'success');
INSERT INTO `wx_orders` VALUES (13, 1, 74688.00, '2023-12-03 00:16:44', 'success');
INSERT INTO `wx_orders` VALUES (15, 1, 399.00, '2023-12-03 01:52:32', 'success');
INSERT INTO `wx_orders` VALUES (28, 1, 31996.00, '2023-12-09 13:27:14', 'success');
INSERT INTO `wx_orders` VALUES (31, 1, 7999.00, '2023-12-09 13:42:52', 'success');
INSERT INTO `wx_orders` VALUES (36, 1, 199.00, '2023-12-13 10:52:58', 'fail');
INSERT INTO `wx_orders` VALUES (37, 1, 7999.00, '2023-12-13 10:53:19', 'fail');
INSERT INTO `wx_orders` VALUES (38, 1, 5999.00, '2023-12-13 11:00:50', 'fail');
INSERT INTO `wx_orders` VALUES (39, 1, 5999.00, '2023-12-13 11:04:18', 'fail');
INSERT INTO `wx_orders` VALUES (40, 1, 5999.00, '2023-12-13 11:04:25', 'fail');
INSERT INTO `wx_orders` VALUES (42, 21, 899.00, '2023-12-13 19:33:29', 'success');
INSERT INTO `wx_orders` VALUES (43, 21, 7999.00, '2023-12-13 19:26:40', 'fail');
INSERT INTO `wx_orders` VALUES (44, 21, 31996.00, '2023-12-13 19:33:16', 'fail');
INSERT INTO `wx_orders` VALUES (45, 21, 39995.00, '2023-12-13 19:39:32', 'fail');
INSERT INTO `wx_orders` VALUES (46, 37, 2999.00, '2024-03-01 19:24:08', 'fail');
INSERT INTO `wx_orders` VALUES (47, 37, 2999.00, '2024-03-01 19:24:10', 'fail');
INSERT INTO `wx_orders` VALUES (48, 37, 2999.00, '2024-03-01 19:25:44', 'fail');
INSERT INTO `wx_orders` VALUES (49, 1652555795, 11.00, '2024-03-23 23:51:56', 'fail');
INSERT INTO `wx_orders` VALUES (50, 1652555795, 11.00, '2024-03-24 00:16:37', 'fail');
INSERT INTO `wx_orders` VALUES (51, 1652555795, 11.00, '2024-03-24 01:30:35', 'fail');
INSERT INTO `wx_orders` VALUES (57, 1652555795, 12.00, '2024-03-24 18:00:25', 'fail');
INSERT INTO `wx_orders` VALUES (58, 1652555795, 12.00, '2024-03-24 18:19:51', 'fail');
INSERT INTO `wx_orders` VALUES (59, 1652555806, 2999.00, '2024-03-24 18:21:16', 'fail');
INSERT INTO `wx_orders` VALUES (60, 1652555806, 2999.00, '2024-03-24 18:30:23', 'fail');
INSERT INTO `wx_orders` VALUES (61, 1652555795, 2999.00, '2024-04-27 11:20:38', 'fail');
INSERT INTO `wx_orders` VALUES (62, 1652555795, 5999.00, '2024-08-23 16:08:11', 'fail');
INSERT INTO `wx_orders` VALUES (63, 1652555795, 7999.00, '2024-08-23 16:10:06', 'fail');
INSERT INTO `wx_orders` VALUES (64, 1652555795, 7999.00, '2024-08-23 16:12:34', 'fail');
INSERT INTO `wx_orders` VALUES (65, 1652555795, 7999.00, '2024-08-23 16:16:17', 'fail');
INSERT INTO `wx_orders` VALUES (66, 1652555795, 899.00, '2024-08-23 16:42:59', 'fail');
INSERT INTO `wx_orders` VALUES (67, 1652555795, 899.00, '2024-08-23 16:45:35', 'fail');
INSERT INTO `wx_orders` VALUES (68, 1652555795, 899.00, '2024-08-23 16:48:55', 'fail');
INSERT INTO `wx_orders` VALUES (69, 1652555795, 5999.00, '2024-08-23 16:53:48', 'fail');
INSERT INTO `wx_orders` VALUES (70, 1652555795, 5999.00, '2024-08-23 17:24:36', 'fail');
INSERT INTO `wx_orders` VALUES (71, 1652555795, 100.00, '2024-08-23 17:36:37', 'fail');
INSERT INTO `wx_orders` VALUES (72, 1652555795, 5999.00, '2024-08-23 17:48:21', 'fail');
INSERT INTO `wx_orders` VALUES (73, 1652555795, 100.00, '2024-08-23 18:02:29', 'fail');
INSERT INTO `wx_orders` VALUES (74, 1652555795, 5999.00, '2024-08-23 18:08:08', 'fail');
INSERT INTO `wx_orders` VALUES (75, 1652555795, 2999.00, '2024-08-23 19:41:35', 'fail');
INSERT INTO `wx_orders` VALUES (76, 1652555795, 100.00, '2024-08-24 17:41:40', 'fail');
INSERT INTO `wx_orders` VALUES (79, 1652555795, 7999.00, '2024-08-26 14:33:12', 'fail');
INSERT INTO `wx_orders` VALUES (80, 1652555795, 7999.00, '2024-08-27 09:54:43', 'success');
INSERT INTO `wx_orders` VALUES (81, 1652555795, 8999.00, '2024-08-26 15:36:33', 'success');
INSERT INTO `wx_orders` VALUES (82, 1652555795, 7999.00, '2024-08-26 15:58:32', 'success');
INSERT INTO `wx_orders` VALUES (84, 1652555795, 899.00, '2024-08-26 16:09:13', 'success');
INSERT INTO `wx_orders` VALUES (85, 1652555795, 8999.00, '2024-08-26 16:22:41', 'success');
INSERT INTO `wx_orders` VALUES (86, 1652555795, 5999.00, '2024-08-27 09:54:43', 'success');
INSERT INTO `wx_orders` VALUES (87, 1652555795, 899.00, '2024-08-27 09:54:43', 'success');
INSERT INTO `wx_orders` VALUES (88, 1652555795, 5999.00, '2024-08-27 09:54:44', 'success');
INSERT INTO `wx_orders` VALUES (89, 1652555795, 5999.00, '2024-08-26 16:44:09', 'fail');
INSERT INTO `wx_orders` VALUES (90, 1652555795, 5999.00, '2024-08-26 16:56:47', 'success');
INSERT INTO `wx_orders` VALUES (91, 1652555795, 399.00, '2024-08-27 09:54:44', 'success');
INSERT INTO `wx_orders` VALUES (92, 1652555795, 899.00, '2024-08-26 17:09:51', 'success');
INSERT INTO `wx_orders` VALUES (93, 1652555795, 199.00, '2024-08-27 09:54:44', 'success');
INSERT INTO `wx_orders` VALUES (94, 1652555795, 899.00, '2024-08-27 09:54:44', 'success');
INSERT INTO `wx_orders` VALUES (95, 1652555795, 899.00, '2024-08-27 09:54:44', 'success');
INSERT INTO `wx_orders` VALUES (96, 1652555795, 199.00, '2024-08-27 09:54:44', 'success');
INSERT INTO `wx_orders` VALUES (97, 1652555795, 899.00, '2024-08-27 09:54:44', 'success');
INSERT INTO `wx_orders` VALUES (98, 1652555795, 2999.00, '2024-08-26 18:13:16', 'success');
INSERT INTO `wx_orders` VALUES (100, 1652555795, 1798.00, '2024-08-27 09:54:44', 'success');
INSERT INTO `wx_orders` VALUES (101, 1652555795, 2999.00, '2024-08-27 09:54:44', 'success');
INSERT INTO `wx_orders` VALUES (102, 1652555795, 7999.00, '2024-08-27 09:42:11', 'success');
INSERT INTO `wx_orders` VALUES (104, 1652555795, 5998.00, '2024-08-27 09:41:44', 'fail');
INSERT INTO `wx_orders` VALUES (105, 1652555795, 398.00, '2024-08-27 10:23:31', 'success');
INSERT INTO `wx_orders` VALUES (106, 1652555795, 7999.00, '2024-08-27 10:23:32', 'success');
INSERT INTO `wx_orders` VALUES (107, 1652555795, 7999.00, '2024-08-27 11:00:39', 'success');

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `user_img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500',
  `flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1652555853 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_user
-- ----------------------------
INSERT INTO `wx_user` VALUES (1, 'zhangsan', '12345678', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', NULL);
INSERT INTO `wx_user` VALUES (2, 'lisi', '12345678', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', NULL);
INSERT INTO `wx_user` VALUES (3, 'wangwu', '12345678', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', NULL);
INSERT INTO `wx_user` VALUES (21, 'test123456', '123456', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', '1');
INSERT INTO `wx_user` VALUES (37, '张三丰', '111', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', '1');
INSERT INTO `wx_user` VALUES (40, 'admin', 'admin', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', '0');
INSERT INTO `wx_user` VALUES (1652555794, 'mark11', '$2a$10$OWBkCcN5AuhpWUO7W.6iuuXZs9FTIbPwpPppcjcB1cW92G0aIt9YS', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', '1');
INSERT INTO `wx_user` VALUES (1652555795, 'mark12', '$2a$10$9syY45ySVtUYNH/EPCtcEur1kuZp.xhgWBr7tJaQ8xM.fRRbUuR6a', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', NULL);
INSERT INTO `wx_user` VALUES (1652555806, 'lll', '$2a$10$33J8NMjwmHPXAzpotUJNjO5Z1b6oNXnEftlvsDSBcWFUF2DuFezL6', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', NULL);
INSERT INTO `wx_user` VALUES (1652555807, '丽丽1', '$2a$10$Hqz2oDpepVdSix6lEbd4guKTevjZEB6J0IHS5drqtlpTixbvAfuJG', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', '1');
INSERT INTO `wx_user` VALUES (1652555840, 'ccc', '$2a$10$Jfkhakl68wrU9Ta9Ox6WQueB28E3PqKZ6kK1EUjybk7VMM5Di7v0i', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', NULL);
INSERT INTO `wx_user` VALUES (1652555841, '丽丽', '123', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', NULL);
INSERT INTO `wx_user` VALUES (1652555844, '莉莎', '666', 'https://pic1.zhimg.com/80/v2-6afa72220d29f045c15217aa6b275808_720w.webp?source=1def8aca', NULL);
INSERT INTO `wx_user` VALUES (1652555845, '莉莎1', '666', 'https://pic1.zhimg.com/80/v2-6afa72220d29f045c15217aa6b275808_720w.webp?source=1def8aca', NULL);
INSERT INTO `wx_user` VALUES (1652555850, '莉莎123', '$2a$10$bb89lPnJv.9z9q1piF0.j.yI94jltACQ4tqDCQTK6EUDVzBV5UCNS', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', NULL);
INSERT INTO `wx_user` VALUES (1652555851, '莉莎456', '$2a$10$93lqfM/NOp3TseB29dOkceaUBKuUMr3Hcv4kIml2lh4yumJmvOQW.', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', NULL);
INSERT INTO `wx_user` VALUES (1652555852, '飞飞1', '$2a$10$CRWJXq5JnhlejPQ7ORzwBuQ8H3V4kNv9kZbopD9JeJ5MDrEFA4ade', 'https://img2.baidu.com/it/u=3571084029,1631856297&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', NULL);

SET FOREIGN_KEY_CHECKS = 1;
