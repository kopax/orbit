-- MySQL dump 10.13  Distrib 5.5.36, for Win32 (x86)
--
-- Host: localhost    Database: db_orbit
-- ------------------------------------------------------
-- Server version	5.5.36

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
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作人',
  `class_name` varchar(128) DEFAULT NULL COMMENT '类名',
  `method_name` varchar(128) DEFAULT NULL COMMENT '方法名',
  `time` datetime DEFAULT NULL COMMENT '日志时间',
  `is_succeed` varchar(128) DEFAULT NULL COMMENT '是否成功',
  `message` varchar(255) DEFAULT NULL COMMENT '日志信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (359333985492537344,'系统登录',9999,'com.inmaytide.orbit.web.controller.LoginController','login','2017-09-18 13:44:58','登录成功',NULL),(359333988634071040,'系统登录',9999,'com.inmaytide.orbit.web.controller.LoginController','login','2017-09-18 13:44:58','登录成功',NULL),(359333992916455424,'系统登录',9999,'com.inmaytide.orbit.web.controller.LoginController','login','2017-09-18 13:45:00','登录成功',NULL),(359333996456448000,'系统登录',9999,'com.inmaytide.orbit.web.controller.LoginController','login','2017-09-18 13:45:00','登录成功',NULL),(359333999816085504,'系统登录',9999,'com.inmaytide.orbit.web.controller.LoginController','login','2017-09-18 13:45:01','登录成功',NULL),(359346162773397504,'系统登录',9999,'com.inmaytide.orbit.web.controller.LoginController','login','2017-09-18 14:33:21','登录成功',NULL);
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_organization`
--

DROP TABLE IF EXISTS `sys_organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL,
  `name` varchar(128) NOT NULL,
  `category` bigint(20) DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `address` varchar(512) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `is_removed` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `updater` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_organization`
--

LOCK TABLES `sys_organization` WRITE;
/*!40000 ALTER TABLE `sys_organization` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent` bigint(20) NOT NULL DEFAULT '-1',
  `code` varchar(32) NOT NULL,
  `name` varchar(64) NOT NULL,
  `action` varchar(256) DEFAULT NULL,
  `icon` varchar(256) DEFAULT NULL,
  `category` int(11) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `updater` bigint(20) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=10031 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` VALUES (10000,-1,'homepage','首页',NULL,'home',1,NULL,'2017-09-03 14:03:03','2017-09-10 20:27:14',9999,9999,0,1),(10001,-1,'sys:managment','系统管理',NULL,'cog',1,NULL,'2017-09-03 14:05:02','2017-09-10 20:27:14',9999,9999,0,2),(10002,10001,'perm:list','菜单管理','home/permission',NULL,1,NULL,'2017-09-03 14:05:25','2017-09-10 20:29:50',9999,9999,0,4),(10019,10001,'log:list','日志管理','home/log',NULL,1,NULL,'2017-09-03 21:06:27','2017-09-10 20:29:49',9999,9999,0,5),(10030,10001,'role:list','角色管理','home/role',NULL,1,NULL,'2017-09-10 20:29:41','2017-09-10 20:29:50',9999,9999,0,3);
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `updater` bigint(20) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (9999,'admin','Administrator',NULL,'2017-08-22 21:43:16',NULL,9999,NULL,0),(10000,'company-administrator','CompanyAdministrator',NULL,'2017-09-17 21:51:57',NULL,9999,NULL,0),(10001,'organization','organization',NULL,'2017-09-17 21:58:20',NULL,9999,NULL,0),(10002,'23213','12312313',NULL,'2017-09-17 21:59:23',NULL,9999,NULL,0),(10003,'e123123','123123',NULL,'2017-09-17 22:01:47',NULL,9999,NULL,0),(10004,'123123','123123',NULL,'2017-09-17 22:10:43',NULL,9999,NULL,0),(10005,'123123123123','123123',NULL,'2017-09-17 22:15:46',NULL,9999,NULL,0);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_permission`
--

DROP TABLE IF EXISTS `sys_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `r_id` bigint(20) NOT NULL,
  `p_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10009 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` VALUES (10000,9999,10000),(10001,9999,10001),(10002,9999,10002),(10003,9999,10004),(10005,9999,10005),(10006,9999,10019),(10008,9999,10030);
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `name` varchar(64) NOT NULL,
  `password` varchar(32) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `email` varchar(256) DEFAULT NULL,
  `qq` varchar(16) DEFAULT NULL,
  `wechat` varchar(16) DEFAULT NULL,
  `telephone` varchar(16) DEFAULT NULL,
  `cellphone` varchar(16) DEFAULT NULL,
  `photo` varchar(256) DEFAULT NULL,
  `super_admin` int(11) DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `updater` bigint(20) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (9999,'admin','Administrator','431759ab506d1e4af78e7fe08b818edb',1,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,'2017-08-22 21:43:16',NULL,9999,NULL,0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `u_id` bigint(20) NOT NULL,
  `r_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (10000,9999,9999);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-18 14:51:46
