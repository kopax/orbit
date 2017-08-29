-- MySQL dump 10.13  Distrib 5.7.18, for osx10.12 (x86_64)
--
-- Host: localhost    Database: db_orbit
-- ------------------------------------------------------
-- Server version	5.7.18

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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作人',
  `log_time` datetime DEFAULT NULL COMMENT '发生时间',
  `category` int(11) DEFAULT NULL COMMENT '类型（1操作成功/2操作失败）',
  `content` varchar(512) DEFAULT NULL COMMENT '日志内容',
  `details` text COMMENT '明细',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (1,NULL,'2017-08-26 15:54:58',2,'系统登录 => 登录失败, username => adfasfdasdf','org.apache.shiro.authc.UnknownAccountException => null'),(2,NULL,'2017-08-26 15:55:15',2,'系统登录 => 登录失败, username => admin','org.apache.shiro.authc.IncorrectCredentialsException => Submitted credentials for token [com.inmaytide.orbit.web.auth.token.UsernamePasswordCaptchaToken - admin, rememberMe=false] did not match the expected credentials.'),(3,9999,NULL,1,'系统登录 => 登录成功',NULL),(4,9999,'2017-08-26 16:06:06',1,'系统登录 => 登录成功',NULL),(5,9999,'2017-08-26 16:29:51',1,'系统登录 => 登录成功',NULL),(6,9999,'2017-08-26 21:28:54',1,'系统登录 => 登录成功',NULL),(7,9999,'2017-08-26 21:29:15',1,'系统登录 => 登录成功',NULL),(8,9999,'2017-08-26 22:16:50',1,'系统登录 => 登录成功',NULL),(9,9999,'2017-08-27 09:39:37',1,'系统登录 => 登录成功',NULL),(10,9999,'2017-08-27 09:42:12',1,'系统登录 => 登录成功',NULL),(11,9999,'2017-08-27 09:42:46',1,'系统登录 => 登录成功',NULL),(12,9999,'2017-08-27 09:43:04',1,'系统登录 => 登录成功',NULL),(13,9999,'2017-08-27 09:47:14',1,'系统登录 => 登录成功',NULL),(14,9999,'2017-08-27 09:47:29',1,'系统登录 => 登录成功',NULL),(15,9999,'2017-08-27 09:52:26',1,'系统登录 => 登录成功',NULL),(16,9999,'2017-08-27 10:16:23',1,'系统登录 => 登录成功',NULL),(17,NULL,'2017-08-27 10:19:53',2,'系统登录 => 登录失败, username => admin','org.apache.shiro.authc.IncorrectCredentialsException => Submitted credentials for token [com.inmaytide.orbit.web.auth.token.UsernamePasswordCaptchaToken - admin, rememberMe=false] did not match the expected credentials.'),(18,9999,'2017-08-27 10:19:59',1,'系统登录 => 登录成功',NULL),(19,NULL,'2017-08-27 10:21:38',2,'系统登录 => 登录失败, username => admin','org.apache.shiro.authc.IncorrectCredentialsException => Submitted credentials for token [com.inmaytide.orbit.web.auth.token.UsernamePasswordCaptchaToken - admin, rememberMe=false] did not match the expected credentials.'),(20,NULL,'2017-08-27 10:21:39',2,'系统登录 => 登录失败, username => admin','org.apache.shiro.authc.IncorrectCredentialsException => Submitted credentials for token [com.inmaytide.orbit.web.auth.token.UsernamePasswordCaptchaToken - admin, rememberMe=false] did not match the expected credentials.'),(21,NULL,'2017-08-27 10:21:39',2,'系统登录 => 登录失败, username => admin','org.apache.shiro.authc.IncorrectCredentialsException => Submitted credentials for token [com.inmaytide.orbit.web.auth.token.UsernamePasswordCaptchaToken - admin, rememberMe=false] did not match the expected credentials.'),(22,NULL,'2017-08-27 10:21:59',2,'系统登录 => 登录失败, username => admin','com.inmaytide.orbit.web.auth.exception.IncorrectCaptchaException => Incorrect captcha.'),(23,NULL,'2017-08-27 10:22:13',2,'系统登录 => 登录失败, username => admin','com.inmaytide.orbit.web.auth.exception.IncorrectCaptchaException => Incorrect captcha.'),(24,9999,'2017-08-27 10:22:22',1,'系统登录 => 登录成功',NULL),(25,9999,'2017-08-27 10:22:30',1,'系统登录 => 登录成功',NULL),(26,9999,'2017-08-27 10:29:55',1,'系统登录 => 登录成功',NULL),(27,9999,'2017-08-27 10:54:32',1,'系统登录 => 登录成功',NULL),(28,9999,'2017-08-27 13:36:13',1,'系统登录 => 登录成功',NULL),(29,9999,'2017-08-27 13:47:31',1,'系统登录 => 登录成功',NULL),(30,9999,'2017-08-27 13:52:33',1,'系统登录 => 登录成功',NULL),(31,9999,'2017-08-27 13:54:22',1,'系统登录 => 登录成功',NULL),(32,9999,'2017-08-27 15:14:24',1,'系统登录 => 登录成功',NULL),(33,9999,'2017-08-27 15:28:37',1,'系统登录 => 登录成功',NULL),(34,9999,'2017-08-27 15:46:54',1,'系统登录 => 登录成功',NULL),(35,9999,'2017-08-27 17:54:15',1,'系统登录 => 登录成功',NULL),(36,9999,'2017-08-27 17:54:30',1,'系统登录 => 登录成功',NULL),(37,9999,'2017-08-27 19:15:54',1,'系统登录 => 登录成功',NULL),(38,9999,'2017-08-27 19:21:26',1,'系统登录 => 登录成功',NULL),(39,9999,'2017-08-27 19:25:19',1,'系统登录 => 登录成功',NULL),(40,9999,'2017-08-28 20:28:42',1,'系统登录 => 登录成功',NULL),(41,9999,'2017-08-28 20:29:08',1,'系统登录 => 登录成功',NULL),(42,9999,'2017-08-28 20:32:05',1,'系统登录 => 登录成功',NULL),(43,9999,'2017-08-29 20:17:04',1,'系统登录 => 登录成功',NULL),(44,9999,'2017-08-29 20:17:05',1,'系统登录 => 登录成功',NULL),(45,9999,'2017-08-29 22:04:24',1,'系统登录 => 登录成功',NULL);
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=10007 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` VALUES (10000,-1,'dashboard','首页','home/content','home',1,NULL,'2017-08-22 21:49:39',NULL,9999,NULL,0,1),(10001,-1,'systemmanagement','系统管理',NULL,'cog',1,NULL,'2017-08-22 21:52:22',NULL,9999,NULL,0,2),(10002,10001,'perm:list','菜单管理','home/permission','',1,NULL,'2017-08-22 21:54:18',NULL,9999,NULL,0,5),(10004,10001,'user:list','用户管理','home/userlist',NULL,1,NULL,'2017-08-24 22:01:40',NULL,9999,NULL,0,4),(10005,-1,'workflow','工作流',NULL,'wrench',1,NULL,'2017-08-24 22:44:53',NULL,9999,NULL,0,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (9999,'admin','Administrator',NULL,'2017-08-22 21:43:16',NULL,9999,NULL,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` VALUES (10000,9999,10000),(10001,9999,10001),(10002,9999,10002),(10003,9999,10004),(10005,9999,10005);
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

-- Dump completed on 2017-08-29 23:10:18
