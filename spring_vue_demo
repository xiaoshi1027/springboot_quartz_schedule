/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : spring_vue_demo

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2020-03-21 17:59:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product_num
-- ----------------------------
DROP TABLE IF EXISTS `product_num`;
CREATE TABLE `product_num` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bat_no` varchar(60) NOT NULL COMMENT '批次号',
  `pro_num` int(11) NOT NULL COMMENT '产生的随机数字',
  `create_time` datetime NOT NULL COMMENT '产生数字的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of product_num
-- ----------------------------
INSERT INTO `product_num` VALUES ('1', 'aeceaef5-3fa8-468a-90fa-f3c4c6e92c30', '2', '2020-03-21 17:04:00');
INSERT INTO `product_num` VALUES ('2', '3ad00ae1-ad71-438f-b33f-135fbb5b6449', '6', '2020-03-21 17:04:20');
INSERT INTO `product_num` VALUES ('3', '9aab0d30-0794-41a0-8503-ea2a99a28c0c', '3', '2020-03-21 17:04:40');
INSERT INTO `product_num` VALUES ('4', '9aab0d30-0794-41a0-8503-ea2a99a28c0c', '7', '2020-03-21 17:04:40');

-- ----------------------------
-- Table structure for pro_num_statistics
-- ----------------------------
DROP TABLE IF EXISTS `pro_num_statistics`;
CREATE TABLE `pro_num_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count_num` int(11) NOT NULL COMMENT '总记录数',
  `sum_num` int(11) NOT NULL COMMENT '已产生数的和',
  `statistics_time` datetime NOT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pro_num_statistics
-- ----------------------------
INSERT INTO `pro_num_statistics` VALUES ('1', '4', '18', '2020-03-21 17:05:30');

-- ----------------------------
-- Table structure for task_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `task_schedule_job`;
CREATE TABLE `task_schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务类名',
  `job_group任务组名和job_name保持一致即可` varchar(255) DEFAULT NULL,
  `job_status` varchar(255) DEFAULT NULL COMMENT '定时任务是否生效0失效1生效',
  `cron_expression` varchar(255) NOT NULL COMMENT '定时任务cron表达式，推荐使用6域',
  `description` varchar(255) DEFAULT NULL COMMENT '定时任务描述',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '定时任务类名全路径',
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '1',
  `spring_id` varchar(255) DEFAULT NULL COMMENT '注册的Bean，所以与类名保持一致，首字母小写',
  `method_name` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '定时任务的方法名',
  PRIMARY KEY (`job_id`),
  UNIQUE KEY `name_group` (`job_name`,`job_group任务组名和job_name保持一致即可`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_schedule_job
-- ----------------------------
INSERT INTO `task_schedule_job` VALUES ('1', '2020-03-18 15:48:46', '2020-03-21 17:02:33', 'AddNumWorker', 'AddNumWorker', '1', '0/20 4 17 * * ?', '产生随机数定时任务', 'com.crcb.run.AddNumWorker', '1', 'addNumWorker', 'work');
INSERT INTO `task_schedule_job` VALUES ('2', '2020-03-18 15:48:48', '2020-03-21 17:02:37', 'ProStatisticsWorker', 'ProStatisticsWorker', '1', '30 5 17 * * ?', '统计数据定时任务,并将统计结果添加到数据库中', 'com.crcb.run.ProStatisticsWorker', '0', 'proStatisticsWorker', 'work');
