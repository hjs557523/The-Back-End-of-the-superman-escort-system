-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: runoob
-- ------------------------------------------------------
-- Server version	5.1.73-community

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
-- Table structure for table `login_info`
--

DROP TABLE IF EXISTS `login_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_info` (
  `username` varchar(40) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `password` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` char(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `phone` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_info`
--

LOCK TABLES `login_info` WRITE;
/*!40000 ALTER TABLE `login_info` DISABLE KEYS */;
INSERT INTO `login_info` VALUES ('hjs','557523','男',18,'18767136097','326464623@qq.com');
/*!40000 ALTER TABLE `login_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `physical`
--

DROP TABLE IF EXISTS `physical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `physical` (
  `username` varchar(40) NOT NULL,
  `temperature` double DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `heartbeat` double DEFAULT NULL,
  `systolicPressure` double DEFAULT NULL,
  `diastolicPressure` double DEFAULT NULL,
  `bloodFat` double DEFAULT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`,`datetime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `physical`
--

LOCK TABLES `physical` WRITE;
/*!40000 ALTER TABLE `physical` DISABLE KEYS */;
INSERT INTO `physical` VALUES ('hjs',37.1,54.5,85,138,92,5.1,'2019-03-03 19:45:16'),('hjs',36.4,102.1,91,103,76,3,'2019-03-03 20:02:07'),('hjs',36.9,75,79,124,72,2.5,'2019-03-03 20:02:15'),('hjs',36.5,68.1,59,113,56,4.7,'2019-03-03 20:02:20'),('hjs',37,253.5,59,136,94,5.1,'2019-03-03 20:02:26'),('hjs',37.5,264.8,96,131,89,3.4,'2019-03-03 20:02:30'),('hjs',37.2,215.8,61,115,60,3.5,'2019-03-03 20:02:35'),('hjs',37.3,182.6,58,139,70,5.2,'2019-03-03 20:02:39'),('hjs',37.5,263.2,94,104,56,3.6,'2019-03-03 20:02:45'),('hjs',37,168.4,88,129,71,5.1,'2019-03-03 20:02:49');
/*!40000 ALTER TABLE `physical` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'runoob'
--

--
-- Dumping routines for database 'runoob'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-04 11:08:23
