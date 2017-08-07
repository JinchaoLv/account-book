-- MySQL dump 10.13  Distrib 5.5.55, for Win64 (AMD64)
--
-- Host: localhost    Database: account-book
-- ------------------------------------------------------
-- Server version	5.5.55

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_admin`
--

DROP TABLE IF EXISTS `t_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_admin` (
  `admin_name` varchar(255) NOT NULL,
  `admin_password` varchar(255) NOT NULL,
  PRIMARY KEY (`admin_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_admin`
--

LOCK TABLES `t_admin` WRITE;
/*!40000 ALTER TABLE `t_admin` DISABLE KEYS */;
INSERT INTO `t_admin` VALUES ('admin','a8sd23ao0gjhas9k');
/*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_id_generator`
--

DROP TABLE IF EXISTS `t_id_generator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_id_generator` (
  `gen_sequenceId` varchar(255) NOT NULL,
  `gen_sequenceValue` int(11) DEFAULT NULL,
  PRIMARY KEY (`gen_sequenceId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_id_generator`
--

LOCK TABLES `t_id_generator` WRITE;
/*!40000 ALTER TABLE `t_id_generator` DISABLE KEYS */;
INSERT INTO `t_id_generator` VALUES ('U',2);
/*!40000 ALTER TABLE `t_id_generator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_id_generator_u1`
--

DROP TABLE IF EXISTS `t_id_generator_u1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_id_generator_u1` (
  `gen_sequenceId` varchar(255) NOT NULL,
  `gen_sequenceValue` int(11) DEFAULT NULL,
  PRIMARY KEY (`gen_sequenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_id_generator_u1`
--

LOCK TABLES `t_id_generator_u1` WRITE;
/*!40000 ALTER TABLE `t_id_generator_u1` DISABLE KEYS */;
INSERT INTO `t_id_generator_u1` VALUES ('TF',12),('TM',3),('TX',29);
/*!40000 ALTER TABLE `t_id_generator_u1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_table_info_u1`
--

DROP TABLE IF EXISTS `t_table_info_u1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_table_info_u1` (
  `table_info_tableName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_table_info_u1`
--

LOCK TABLES `t_table_info_u1` WRITE;
/*!40000 ALTER TABLE `t_table_info_u1` DISABLE KEYS */;
INSERT INTO `t_table_info_u1` VALUES ('t_transaction_2017_U1'),('t_transaction_mode_U1'),('t_transaction_field_U1'),('t_id_generator_U1');
/*!40000 ALTER TABLE `t_table_info_u1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_transaction_2017_u1`
--

DROP TABLE IF EXISTS `t_transaction_2017_u1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_transaction_2017_u1` (
  `tx_id` varchar(255) NOT NULL,
  `tx_date` date DEFAULT NULL,
  `tx_modeId` varchar(255) DEFAULT NULL,
  `tx_amount` decimal(8,2) DEFAULT NULL,
  `tx_fieldId` varchar(255) DEFAULT NULL,
  `tx_introduction` varchar(255) DEFAULT NULL,
  `tx_detail` text,
  `tx_secret` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tx_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_transaction_2017_u1`
--

LOCK TABLES `t_transaction_2017_u1` WRITE;
/*!40000 ALTER TABLE `t_transaction_2017_u1` DISABLE KEYS */;
INSERT INTO `t_transaction_2017_u1` VALUES ('TX201706011','2017-06-01','TM1',-25.00,'TF1','午饭','午饭。','No'),('TX201706012','2017-06-01','TM1',-15.00,'TF1','晚饭','晚饭。','No'),('TX201706023','2017-06-02','TM1',-28.00,'TF1','午饭','午饭。','No'),('TX201706024','2017-06-02','TM1',-100.00,'TF2','话费','手机话费','No'),('TX201706035','2017-06-03','TM1',-849.00,'TF4','显示器','三星显示器26寸，京东。','No'),('TX201706036','2017-06-03','TM1',-152.00,'TF4','键鼠','牧马人键鼠套装，赠送耳机，京东，149；\r\n京准达，3。','No'),('TX201706037','2017-06-03','TM1',-480.00,'TF3','端午旅游结算','端午假日旅游，杭州千岛湖，朱丽博同行。','No'),('TX2017060410','2017-06-04','TM1',-15.00,'TF1','鸡蛋','土鸡蛋1斤，小区超市。','No'),('TX2017060411','2017-06-04','TM1',-18.00,'TF8','铁观音','茶叶，铁观音，1袋，小区超市。','No'),('TX2017060412','2017-06-04','TM1',-5.00,'TF8','冷饮','冷饮，小区超市。','No'),('TX2017060413','2017-06-04','TM1',-5.00,'TF8','方便面','红烧牛肉面，2包，小区超市。','No'),('TX2017060414','2017-06-04','TM1',-15.00,'TF1','晚饭','晚饭，水饺，小区门口山东水饺。','No'),('TX201706048','2017-06-04','TM1',-45.00,'TF9','炉石','炉石传说，充值冒险模式。','No'),('TX201706049','2017-06-04','TM1',-55.00,'TF9','侠客前传激活码','侠客风云传前传，激活码，数字版。','No'),('TX2017060515','2017-06-05','TM1',-98.00,'TF7','晴雨两用伞','晴雨两用伞，京东。','No'),('TX2017060516','2017-06-05','TM1',-20.00,'TF1','午饭','午饭。','No'),('TX2017060517','2017-06-05','TM1',-16.00,'TF1','晚饭','晚饭。','No'),('TX2017060618','2017-06-06','TM1',-16.00,'TF1','午饭','午饭。','No'),('TX2017060619','2017-06-06','TM1',-11.00,'TF1','晚饭','晚饭。','No'),('TX2017060620','2017-06-06','TM1',-84.75,'TF7','床单','床单，京东。','No'),('TX2017060721','2017-06-07','TM1',-30.00,'TF1','午饭','午饭。','No'),('TX2017060722','2017-06-07','TM1',-8.40,'TF1','晚饭','晚饭。','No'),('TX2017060824','2017-06-08','TM1',-11.00,'TF7','牙膏','牙膏。','No'),('TX2017060925','2017-06-09','TM1',-26.00,'TF1','午饭','午饭。','No'),('TX2017060926','2017-06-09','TM1',-16.00,'TF1','鸡蛋、韭菜','鸡蛋，1斤，15，小区超市；\r\n韭菜，1把，1，小区超市。','No'),('TX2017060927','2017-06-09','TM1',6836.00,'TF10','工资','工资，广联达，补交4月社保。','Yes'),('TX2017061028','2017-06-10','TM1',-252.75,'TF7','雷霆','飞机杯，雷霆，京东。','Yes'),('TX2017070823','2017-06-08','TM1',-15.00,'TF1','晚饭','晚饭。','No'),('TX2017072030','2017-07-20','TM4',-21.73,'TF1','午饭','午饭，永和大食堂张江店。','No');
/*!40000 ALTER TABLE `t_transaction_2017_u1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_transaction_field_u1`
--

DROP TABLE IF EXISTS `t_transaction_field_u1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_transaction_field_u1` (
  `tx_field_id` varchar(255) NOT NULL,
  `tx_field_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tx_field_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_transaction_field_u1`
--

LOCK TABLES `t_transaction_field_u1` WRITE;
/*!40000 ALTER TABLE `t_transaction_field_u1` DISABLE KEYS */;
INSERT INTO `t_transaction_field_u1` VALUES ('TF1','正餐'),('TF10','工资'),('TF2','通讯'),('TF3','旅游'),('TF4','电子数码'),('TF6','社交'),('TF7','生活用品'),('TF8','零食'),('TF9','游戏娱乐');
/*!40000 ALTER TABLE `t_transaction_field_u1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_transaction_mode_u1`
--

DROP TABLE IF EXISTS `t_transaction_mode_u1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_transaction_mode_u1` (
  `tx_mode_id` varchar(255) NOT NULL,
  `tx_mode_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tx_mode_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_transaction_mode_u1`
--

LOCK TABLES `t_transaction_mode_u1` WRITE;
/*!40000 ALTER TABLE `t_transaction_mode_u1` DISABLE KEYS */;
INSERT INTO `t_transaction_mode_u1` VALUES ('TM1','转账'),('TM4','花呗'),('TM5','信用卡');
/*!40000 ALTER TABLE `t_transaction_mode_u1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_sex` varchar(10) DEFAULT NULL,
  `user_age` int(11) DEFAULT NULL,
  `user_phone` varchar(20) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_icon` blob,
  `user_signature` varchar(255) DEFAULT NULL,
  `user_tableInfo` varchar(255) NOT NULL,
  `user_state` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES ('U1','lvjc','lvjinchao','男',24,'17621145395','jinchao.lv@foxmail.com','Koala.jpg',NULL,'t_table_info_U1','离线');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-20 15:46:26
