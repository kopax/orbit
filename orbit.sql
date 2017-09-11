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
  `content` varchar(512) DEFAULT NULL COMMENT '日志内容',
  `details` text COMMENT '明细',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (1,9999,'2017-09-02 15:13:18','添加菜单 => 操作失败','org.mybatis.spring.MyBatisSystemException => nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error querying database.  Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.dao.sys.PermissionRepository.getSort\n### Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.dao.sys.PermissionRepository.getSort'),(2,9999,'2017-09-02 15:15:23','添加菜单 => 操作失败','org.mybatis.spring.MyBatisSystemException => nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error querying database.  Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.dao.sys.PermissionRepository.getSort\n### Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.dao.sys.PermissionRepository.getSort'),(3,9999,'2017-09-02 15:40:38','添加菜单 => 操作失败','org.mybatis.spring.MyBatisSystemException => nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error querying database.  Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.dao.sys.PermissionRepository.getSort\n### Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.dao.sys.PermissionRepository.getSort'),(4,9999,'2017-09-02 15:47:46','添加菜单 => 操作失败','org.mybatis.spring.MyBatisSystemException => nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error querying database.  Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.model.sys.Permission.getSort\n### Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.model.sys.Permission.getSort'),(5,9999,'2017-09-02 16:04:35','添加菜单 => 操作失败','org.mybatis.spring.MyBatisSystemException => nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error querying database.  Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.model.sys.Permission.getSort\n### Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.model.sys.Permission.getSort'),(6,9999,'2017-09-02 16:08:49','添加菜单 => 操作失败','org.mybatis.spring.MyBatisSystemException => nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error querying database.  Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.model.sys.Permission.getSort\n### Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.model.sys.Permission.getSort'),(7,9999,'2017-09-02 16:10:15','添加菜单 => 操作失败','org.mybatis.spring.MyBatisSystemException => nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error querying database.  Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.model.sys.Permission.getSort\n### Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.model.sys.Permission.getSort'),(8,9999,'2017-09-02 16:13:19','添加菜单 => 操作成功',NULL),(9,9999,'2017-09-02 16:16:33','添加菜单 => 操作失败','org.mybatis.spring.MyBatisSystemException => nested exception is org.apache.ibatis.exceptions.PersistenceException: \n### Error querying database.  Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.model.sys.Permission.getSort\n### Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for com.inmaytide.orbit.model.sys.Permission.getSort'),(10,9999,'2017-09-02 16:18:02','添加菜单 => 操作失败','org.springframework.dao.DuplicateKeyException => \n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry \'workflow\' for key \'code\'\n### The error may involve com.inmaytide.orbit.model.sys.Permission._insert-Inline\n### The error occurred while setting parameters\n### SQL: insert into sys_permission(action,code,category,create_time,creator,description,icon,name,parent,sort,update_time,updater,version) values(?,?,?,?,?,?,?,?,?,?,?,?,?)\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry \'workflow\' for key \'code\'\n; SQL []; Duplicate entry \'workflow\' for key \'code\'; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry \'workflow\' for key \'code\''),(11,9999,'2017-09-02 18:38:30','系统登录 => 登录成功',NULL),(12,NULL,'2017-09-02 19:18:41','系统登录 => 登录失败, username => admin','org.apache.shiro.authc.IncorrectCredentialsException => Submitted credentials for token [com.inmaytide.orbit.web.auth.token.UsernamePasswordCaptchaToken - admin, rememberMe=false] did not match the expected credentials.'),(13,9999,'2017-09-02 19:18:51','系统登录 => 登录成功',NULL),(14,9999,'2017-09-02 20:25:03','系统登录 => 登录成功',NULL),(15,9999,'2017-09-02 22:40:50','添加菜单 => 操作成功',NULL),(16,9999,'2017-09-02 22:41:46','删除菜单 => 操作成功',NULL),(17,9999,'2017-09-02 22:50:16','添加菜单 => 操作成功',NULL),(18,9999,'2017-09-02 22:55:55','添加菜单 => 操作成功',NULL),(19,9999,'2017-09-02 22:56:17','删除菜单 => 操作成功',NULL),(20,9999,'2017-09-02 23:00:12','添加菜单 => 操作成功',NULL),(21,9999,'2017-09-02 23:00:37','添加菜单 => 操作成功',NULL),(22,9999,'2017-09-02 23:02:15','删除菜单 => 操作成功',NULL),(23,9999,'2017-09-02 23:02:34','添加菜单 => 操作成功',NULL),(24,9999,'2017-09-02 23:03:51','删除菜单 => 操作成功',NULL),(25,9999,'2017-09-02 23:04:05','添加菜单 => 操作成功',NULL),(26,9999,'2017-09-02 23:04:15','添加菜单 => 操作成功',NULL),(27,9999,'2017-09-02 23:22:08','系统登录 => 登录成功',NULL),(28,9999,'2017-09-03 12:35:53','系统登录 => 登录成功',NULL),(29,9999,'2017-09-03 13:12:52','系统登录 => 登录成功',NULL),(30,9999,'2017-09-03 13:13:02','系统登录 => 登录成功',NULL),(31,9999,'2017-09-03 13:15:07','添加菜单 => 操作成功',NULL),(32,9999,'2017-09-03 13:15:19','添加菜单 => 操作成功',NULL),(33,9999,'2017-09-03 13:15:36','删除菜单 => 操作成功',NULL),(34,9999,'2017-09-03 13:18:08','添加菜单 => 操作成功',NULL),(35,9999,'2017-09-03 13:18:11','删除菜单 => 操作成功',NULL),(36,9999,'2017-09-03 13:21:02','添加菜单 => 操作成功',NULL),(37,9999,'2017-09-03 13:22:01','添加菜单 => 操作成功',NULL),(38,9999,'2017-09-03 13:25:17','删除菜单 => 操作成功',NULL),(39,9999,'2017-09-03 13:55:46','删除菜单 => 操作成功',NULL),(40,9999,'2017-09-03 13:55:57','删除菜单 => 操作成功',NULL),(41,9999,'2017-09-03 14:01:15','添加菜单 => 操作成功',NULL),(42,9999,'2017-09-03 14:03:03','添加菜单 => 操作成功',NULL),(43,9999,'2017-09-03 14:05:02','添加菜单 => 操作成功',NULL),(44,9999,'2017-09-03 14:05:25','添加菜单 => 操作成功',NULL),(45,9999,'2017-09-03 15:02:16','添加菜单 => 操作成功',NULL),(46,9999,'2017-09-03 15:02:22','添加菜单 => 操作成功',NULL),(47,9999,'2017-09-03 15:02:28','删除菜单 => 操作成功',NULL),(48,9999,'2017-09-03 15:02:31','删除菜单 => 操作成功',NULL),(49,9999,'2017-09-03 17:40:40','系统登录 => 登录成功',NULL),(50,9999,'2017-09-03 17:41:19','添加菜单 => 操作成功',NULL),(51,9999,'2017-09-03 17:41:24','删除菜单 => 操作成功',NULL),(52,9999,'2017-09-03 17:42:01','添加菜单 => 操作成功',NULL),(53,9999,'2017-09-03 17:42:13','添加菜单 => 操作失败','com.inmaytide.orbit.web.exception.InvalidParameterException => 参数验证失败'),(54,9999,'2017-09-03 17:54:13','添加菜单 => 操作成功',NULL),(55,9999,'2017-09-03 17:54:21','添加菜单 => 操作成功',NULL),(56,9999,'2017-09-03 17:54:25','添加菜单 => 操作成功',NULL),(57,9999,'2017-09-03 17:54:37','删除菜单 => 操作失败','org.springframework.dao.DataIntegrityViolationException => \n### Error updating database.  Cause: com.mysql.jdbc.MysqlDataTruncation: Data truncation: Truncated incorrect DOUBLE value: \'\\xAC\\xED\\x00\\x05ur\\x00\\x11[Ljava.lang.Long;}\\xE1\\x0A\\xB2\\xBB\\xBCc+\\x02\\x00\\x00xp\\x00\\x00\\x00\\x03sr\\x00\\x0Ejava.lang.Long;\\x8B\\xE\'\n### The error may involve defaultParameterMap\n### The error occurred while setting parameters\n### SQL: delete `permission` from sys_permission `permission`   where   (  `permission`.id=? )\n### Cause: com.mysql.jdbc.MysqlDataTruncation: Data truncation: Truncated incorrect DOUBLE value: \'\\xAC\\xED\\x00\\x05ur\\x00\\x11[Ljava.lang.Long;}\\xE1\\x0A\\xB2\\xBB\\xBCc+\\x02\\x00\\x00xp\\x00\\x00\\x00\\x03sr\\x00\\x0Ejava.lang.Long;\\x8B\\xE\'\n; SQL []; Data truncation: Truncated incorrect DOUBLE value: \'\\xAC\\xED\\x00\\x05ur\\x00\\x11[Ljava.lang.Long;}\\xE1\\x0A\\xB2\\xBB\\xBCc+\\x02\\x00\\x00xp\\x00\\x00\\x00\\x03sr\\x00\\x0Ejava.lang.Long;\\x8B\\xE\'; nested exception is com.mysql.jdbc.MysqlDataTruncation: Data truncation: Truncated incorrect DOUBLE value: \'\\xAC\\xED\\x00\\x05ur\\x00\\x11[Ljava.lang.Long;}\\xE1\\x0A\\xB2\\xBB\\xBCc+\\x02\\x00\\x00xp\\x00\\x00\\x00\\x03sr\\x00\\x0Ejava.lang.Long;\\x8B\\xE\''),(58,9999,'2017-09-03 17:58:25','删除菜单 => 操作成功',NULL),(59,9999,'2017-09-03 18:47:57','修改菜单 => 操作失败','com.inmaytide.orbit.web.exception.InvalidParameterException => 参数验证失败'),(60,9999,'2017-09-03 18:49:21','修改菜单 => 操作成功',NULL),(61,9999,'2017-09-03 19:26:34','系统登录 => 登录成功',NULL),(62,9999,'2017-09-03 19:27:13','修改菜单 => 操作成功',NULL),(63,9999,'2017-09-03 19:33:50','删除菜单 => 操作成功',NULL),(64,9999,'2017-09-03 19:35:25','添加菜单 => 操作成功',NULL),(65,9999,'2017-09-03 19:35:30','删除菜单 => 操作成功',NULL),(66,9999,'2017-09-03 19:43:13','添加菜单 => 操作成功',NULL),(67,9999,'2017-09-03 19:43:19','删除菜单 => 操作成功',NULL),(68,9999,'2017-09-03 19:50:45','添加菜单 => 操作成功',NULL),(69,9999,'2017-09-03 19:50:49','删除菜单 => 操作成功',NULL),(70,9999,'2017-09-03 19:54:22','添加菜单 => 操作成功',NULL),(71,9999,'2017-09-03 19:54:31','删除菜单 => 操作成功',NULL),(72,9999,'2017-09-03 19:55:29','修改菜单 => 操作成功',NULL),(73,NULL,'2017-09-03 19:58:21','系统登录 => 登录失败, username => admin','org.apache.shiro.authc.IncorrectCredentialsException => Submitted credentials for token [com.inmaytide.orbit.web.auth.token.UsernamePasswordCaptchaToken - admin, rememberMe=false] did not match the expected credentials.'),(74,9999,'2017-09-03 19:58:36','系统登录 => 登录成功',NULL),(75,9999,'2017-09-03 19:58:53','系统登录 => 登录成功',NULL),(76,9999,'2017-09-03 19:59:16','修改菜单 => 操作成功',NULL),(77,9999,'2017-09-03 19:59:41','添加菜单 => 操作成功',NULL),(78,9999,'2017-09-03 19:59:48','删除菜单 => 操作成功',NULL),(79,9999,'2017-09-03 20:05:50','添加菜单 => 操作成功',NULL),(80,9999,'2017-09-03 20:43:14','系统登录 => 登录成功',NULL),(81,9999,'2017-09-03 20:47:51','删除菜单 => 操作成功',NULL),(82,9999,'2017-09-03 20:48:02','添加菜单 => 操作成功',NULL),(83,9999,'2017-09-03 20:51:04','删除菜单 => 操作成功',NULL),(84,9999,'2017-09-03 20:57:54','添加菜单 => 操作成功',NULL),(85,9999,'2017-09-03 20:57:59','删除菜单 => 操作成功',NULL),(86,9999,'2017-09-03 21:00:03','添加菜单 => 操作成功',NULL),(87,9999,'2017-09-03 21:00:09','删除菜单 => 操作成功',NULL),(88,9999,'2017-09-03 21:02:04','系统登录 => 登录成功',NULL),(89,9999,'2017-09-03 21:02:24','修改菜单 => 操作成功',NULL),(90,9999,'2017-09-03 21:06:27','添加菜单 => 操作成功',NULL),(91,9999,'2017-09-03 22:23:36','系统登录 => 登录成功',NULL),(92,9999,'2017-09-03 23:40:32','添加菜单 => 操作成功',NULL),(93,9999,'2017-09-03 23:46:10','修改排序 => 操作成功',NULL),(94,9999,'2017-09-03 23:46:20','修改排序 => 操作成功',NULL),(95,9999,'2017-09-03 23:46:21','修改排序 => 操作成功',NULL),(96,9999,'2017-09-03 23:46:22','修改排序 => 操作成功',NULL),(97,9999,'2017-09-03 23:46:22','修改排序 => 操作成功',NULL),(98,9999,'2017-09-03 23:46:22','修改排序 => 操作成功',NULL),(99,9999,'2017-09-03 23:46:23','修改排序 => 操作成功',NULL),(100,9999,'2017-09-03 23:46:23','修改排序 => 操作成功',NULL),(101,9999,'2017-09-03 23:46:24','修改排序 => 操作成功',NULL),(102,9999,'2017-09-03 23:46:25','修改排序 => 操作成功',NULL),(103,9999,'2017-09-03 23:46:25','修改排序 => 操作成功',NULL),(104,9999,'2017-09-03 23:46:26','修改排序 => 操作成功',NULL),(105,9999,'2017-09-03 23:46:26','修改排序 => 操作成功',NULL),(106,9999,'2017-09-03 23:46:31','修改排序 => 操作成功',NULL),(107,9999,'2017-09-03 23:46:35','修改排序 => 操作成功',NULL),(108,9999,'2017-09-03 23:46:35','修改排序 => 操作成功',NULL),(109,9999,'2017-09-03 23:46:36','修改排序 => 操作成功',NULL),(110,9999,'2017-09-03 23:46:39','修改排序 => 操作成功',NULL),(111,9999,'2017-09-04 19:47:12','系统登录 => 登录成功',NULL),(112,9999,'2017-09-04 19:47:26','修改排序 => 操作成功',NULL),(113,9999,'2017-09-04 19:47:26','修改排序 => 操作成功',NULL),(114,9999,'2017-09-04 19:47:33','修改排序 => 操作成功',NULL),(115,9999,'2017-09-04 19:47:33','修改排序 => 操作成功',NULL),(116,9999,'2017-09-04 19:47:38','删除菜单 => 操作成功',NULL),(117,9999,'2017-09-04 19:48:41','修改排序 => 操作成功',NULL),(118,9999,'2017-09-04 19:49:50','修改排序 => 操作成功',NULL),(119,9999,'2017-09-04 19:50:04','修改排序 => 操作成功',NULL),(120,9999,'2017-09-04 19:53:04','修改排序 => 操作成功',NULL),(121,9999,'2017-09-04 19:53:16','修改排序 => 操作成功',NULL),(122,9999,'2017-09-04 19:54:54','修改排序 => 操作成功',NULL),(123,9999,'2017-09-04 20:04:04','系统登录 => 登录成功',NULL),(124,9999,'2017-09-04 21:32:14','系统登录 => 登录成功',NULL),(125,9999,'2017-09-04 22:00:02','系统登录 => 登录成功',NULL),(126,9999,'2017-09-04 22:02:22','系统登录 => 登录成功',NULL),(127,9999,'2017-09-04 22:04:24','系统登录 => 登录成功',NULL),(128,9999,'2017-09-05 19:57:03','系统登录 => 登录成功',NULL),(129,9999,'2017-09-05 19:57:19','修改排序 => 操作成功',NULL),(130,9999,'2017-09-05 19:57:20','修改排序 => 操作成功',NULL),(131,9999,'2017-09-05 21:48:30','修改排序 => 操作成功',NULL),(132,9999,'2017-09-05 21:49:18','添加菜单 => 操作成功',NULL),(133,9999,'2017-09-05 21:49:24','删除菜单 => 操作失败','org.springframework.dao.TransientDataAccessResourceException => \n### Error updating database.  Cause: java.sql.SQLException: Connection is read-only. Queries leading to data modification are not allowed\n### The error may involve defaultParameterMap\n### The error occurred while setting parameters\n### SQL: delete `permission` from sys_permission `permission`   where   (  `permission`.id in (?) )\n### Cause: java.sql.SQLException: Connection is read-only. Queries leading to data modification are not allowed\n; SQL []; Connection is read-only. Queries leading to data modification are not allowed; nested exception is java.sql.SQLException: Connection is read-only. Queries leading to data modification are not allowed'),(134,9999,'2017-09-05 22:59:26','系统登录 => 登录成功',NULL),(135,9999,'2017-09-05 22:59:37','删除菜单 => 操作成功',NULL),(136,9999,'2017-09-06 20:23:17','系统登录 => 登录成功',NULL),(137,9999,'2017-09-06 22:32:34','系统登录 => 登录成功',NULL),(138,9999,'2017-09-06 22:49:46','系统登录 => 登录成功',NULL),(139,9999,'2017-09-07 20:27:27','系统登录 => 登录成功',NULL),(140,9999,'2017-09-07 22:07:14','系统登录 => 登录成功',NULL),(141,9999,'2017-09-07 22:17:39','系统登录 => 登录成功',NULL),(142,9999,'2017-09-07 22:20:27','系统登录 => 登录成功',NULL),(143,9999,'2017-09-07 22:21:07','添加菜单 => 操作成功',NULL),(144,9999,'2017-09-08 20:47:14','系统登录 => 登录成功',NULL),(145,9999,'2017-09-09 21:59:29','系统登录 => 登录成功',NULL),(146,9999,'2017-09-10 19:35:48','系统登录 => 登录成功',NULL),(147,9999,'2017-09-10 19:37:44','系统登录 => 登录成功',NULL),(148,9999,'2017-09-10 19:38:22','添加菜单 => 操作成功',NULL),(149,9999,'2017-09-10 19:38:33','修改菜单 => 操作失败','com.inmaytide.orbit.exceptions.VersionMatchedException => null'),(150,9999,'2017-09-10 19:38:35','修改菜单 => 操作失败','com.inmaytide.orbit.exceptions.VersionMatchedException => null'),(151,9999,'2017-09-10 19:38:56','修改菜单 => 操作失败','com.inmaytide.orbit.exceptions.VersionMatchedException => null'),(152,9999,'2017-09-10 19:39:03','修改菜单 => 操作失败','com.inmaytide.orbit.exceptions.VersionMatchedException => null'),(153,9999,'2017-09-10 19:40:01','修改菜单 => 操作失败','com.inmaytide.orbit.exceptions.VersionMatchedException => null'),(154,9999,'2017-09-10 19:45:45','修改菜单 => 操作成功',NULL),(155,9999,'2017-09-10 19:46:35','修改菜单 => 操作成功',NULL),(156,9999,'2017-09-10 20:00:09','删除菜单 => 操作失败','java.lang.IllegalArgumentException => [Assertion failed] - this String argument must have text; it must not be null, empty, or blank'),(157,9999,'2017-09-10 20:00:25','删除菜单 => 操作失败','java.lang.IllegalArgumentException => [Assertion failed] - this String argument must have text; it must not be null, empty, or blank'),(158,9999,'2017-09-10 20:00:44','删除菜单 => 操作失败','java.lang.IllegalArgumentException => [Assertion failed] - this String argument must have text; it must not be null, empty, or blank'),(159,9999,'2017-09-10 20:01:54','删除菜单 => 操作失败','java.lang.IllegalArgumentException => [Assertion failed] - this String argument must have text; it must not be null, empty, or blank'),(160,9999,'2017-09-10 20:02:41','删除菜单 => 操作失败','java.lang.IllegalArgumentException => [Assertion failed] - this String argument must have text; it must not be null, empty, or blank'),(161,9999,'2017-09-10 20:03:52','删除菜单 => 操作失败','java.lang.IllegalArgumentException => [Assertion failed] - this String argument must have text; it must not be null, empty, or blank'),(162,9999,'2017-09-10 20:04:26','删除菜单 => 操作失败','java.lang.IllegalArgumentException => [Assertion failed] - this String argument must have text; it must not be null, empty, or blank'),(163,9999,'2017-09-10 20:05:48','删除菜单 => 操作成功',NULL),(164,9999,'2017-09-10 20:12:39','修改排序 => 操作成功',NULL),(165,9999,'2017-09-10 20:12:39','修改排序 => 操作成功',NULL),(166,9999,'2017-09-10 20:12:40','修改排序 => 操作成功',NULL),(167,9999,'2017-09-10 20:12:40','修改排序 => 操作成功',NULL),(168,9999,'2017-09-10 20:12:41','修改排序 => 操作成功',NULL),(169,9999,'2017-09-10 20:12:41','修改排序 => 操作成功',NULL),(170,9999,'2017-09-10 20:13:14','修改排序 => 操作成功',NULL),(171,9999,'2017-09-10 20:14:46','添加菜单 => 操作成功',NULL),(172,9999,'2017-09-10 20:14:52','删除菜单 => 操作成功',NULL),(173,9999,'2017-09-10 20:22:06','删除菜单 => 操作成功',NULL),(174,9999,'2017-09-10 20:26:07','添加菜单 => 操作成功',NULL),(175,9999,'2017-09-10 20:26:17','添加菜单 => 操作成功',NULL),(176,9999,'2017-09-10 20:26:24','添加菜单 => 操作成功',NULL),(177,9999,'2017-09-10 20:26:44','删除菜单 => 操作成功',NULL),(178,9999,'2017-09-10 20:26:57','删除菜单 => 操作成功',NULL),(179,9999,'2017-09-10 20:27:10','添加菜单 => 操作成功',NULL),(180,9999,'2017-09-10 20:27:14','修改排序 => 操作成功',NULL),(181,9999,'2017-09-10 20:27:14','修改排序 => 操作成功',NULL),(182,9999,'2017-09-10 20:27:25','删除菜单 => 操作成功',NULL),(183,9999,'2017-09-10 20:28:28','导出日志 => 操作成功',NULL),(184,9999,'2017-09-10 20:29:41','添加菜单 => 操作成功',NULL),(185,9999,'2017-09-10 20:29:49','修改排序 => 操作成功',NULL),(186,9999,'2017-09-10 20:29:50','修改排序 => 操作成功',NULL),(187,9999,'2017-09-10 20:43:54','系统登录 => 登录成功',NULL),(188,9999,'2017-09-10 20:46:46','系统登录 => 登录成功',NULL),(189,9999,'2017-09-10 21:26:59','系统登录 => 登录成功',NULL),(190,NULL,'2017-09-10 22:04:13','系统登录 => 登录失败, username => qweqwe','org.apache.shiro.authc.UnknownAccountException => null'),(191,NULL,'2017-09-10 22:04:14','系统登录 => 登录失败, username => qweqwe','org.apache.shiro.authc.UnknownAccountException => null'),(192,NULL,'2017-09-10 22:04:59','系统登录 => 登录失败, username => 234234','org.apache.shiro.authc.UnknownAccountException => null'),(193,NULL,'2017-09-10 22:05:56','系统登录 => 登录失败, username => 123213','org.apache.shiro.authc.UnknownAccountException => null'),(194,NULL,'2017-09-10 22:06:59','系统登录 => 登录失败, username => 123123','org.apache.shiro.authc.UnknownAccountException => null'),(195,NULL,'2017-09-10 22:07:34','系统登录 => 登录失败, username => 123123','org.apache.shiro.authc.UnknownAccountException => null'),(196,NULL,'2017-09-10 22:09:16','系统登录 => 登录失败, username => 234234','org.apache.shiro.authc.UnknownAccountException => null'),(197,NULL,'2017-09-10 22:09:23','系统登录 => 登录失败, username => admin','org.apache.shiro.authc.IncorrectCredentialsException => Submitted credentials for token [com.inmaytide.orbit.web.auth.token.UsernamePasswordCaptchaToken - admin, rememberMe=false] did not match the expected credentials.'),(198,NULL,'2017-09-10 22:09:26','系统登录 => 登录失败, username => admin123123','org.apache.shiro.authc.UnknownAccountException => null'),(199,NULL,'2017-09-10 22:09:33','系统登录 => 登录失败, username => admin123123','com.inmaytide.orbit.web.auth.exception.IncorrectCaptchaException => Incorrect captcha.'),(200,NULL,'2017-09-10 22:09:46','系统登录 => 登录失败, username => admin','com.inmaytide.orbit.web.auth.exception.IncorrectCaptchaException => Incorrect captcha.'),(201,9999,'2017-09-10 22:09:51','系统登录 => 登录成功',NULL),(202,9999,'2017-09-10 23:04:50','系统登录 => 登录成功',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=10007 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` VALUES (10000,9999,10000),(10001,9999,10001),(10002,9999,10002),(10003,9999,10004),(10005,9999,10005),(10006,9999,10019);
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

-- Dump completed on 2017-09-11 22:19:53
