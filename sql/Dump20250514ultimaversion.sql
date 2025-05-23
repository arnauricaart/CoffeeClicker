-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cofeeclicker_schema
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `generador`
--

DROP TABLE IF EXISTS `generador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generador` (
  `IdGenerador` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) DEFAULT NULL,
  `Coste` decimal(10,2) DEFAULT NULL,
  `Produccion` int DEFAULT NULL,
  `Incr_Coste` decimal(10,2) DEFAULT NULL,
  `Imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IdGenerador`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generador`
--

LOCK TABLES `generador` WRITE;
/*!40000 ALTER TABLE `generador` DISABLE KEYS */;
INSERT INTO `generador` VALUES (1,'gen',2.30,4,1.50,'imagen');
/*!40000 ALTER TABLE `generador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partida`
--

DROP TABLE IF EXISTS `partida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partida` (
  `IdPartida` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) NOT NULL,
  `Cafes` double DEFAULT NULL,
  `Correo` varchar(255) DEFAULT NULL,
  `Terminada` tinyint DEFAULT NULL,
  `UltimoAcceso` datetime DEFAULT NULL,
  `numCoffeeMachine` int DEFAULT '0',
  `numBarista` int DEFAULT '0',
  `numCafe` int DEFAULT '0',
  `numUpgradeCoffeeMachine` int DEFAULT '0',
  `numUpgradeBarista` int DEFAULT '0',
  `numUpgradeCafe` int DEFAULT '0',
  `minDuration` int DEFAULT '0',
  PRIMARY KEY (`IdPartida`),
  UNIQUE KEY `partida_nombre_uk` (`Correo`, `Nombre`),
  KEY `Correo` (`Correo`),
  CONSTRAINT `partida_ibfk_1` FOREIGN KEY (`Correo`) REFERENCES `users` (`Correo`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partida`
--

LOCK TABLES `partida` WRITE;
/*!40000 ALTER TABLE `partida` DISABLE KEYS */;
INSERT INTO `partida` VALUES (3,'Hola',30,'tassani@gmail.com',1,'2025-04-20 13:13:30',0,0,0,0,0,0,0),(4,'gay',12,'tassani@gmail.com',1,'2025-04-20 13:13:30',0,0,0,0,0,0,0),(6,'gluyer',3450,'tassani@gmail.com',1,'2025-04-20 13:13:30',0,0,0,0,0,0,0),(8,'nini',0,'tassani@gmail.com',1,'2025-04-20 13:13:30',0,0,0,0,0,0,0),(10,'hombre',11,'tassani@gmail.com',1,'2025-04-20 13:13:30',0,0,0,0,0,0,0),(13,'tigre',90,'tassani@gmail.com',1,'2025-04-20 13:13:30',0,0,0,0,0,0,0),(14,'dks',380,'tassani@gmail.com',1,'2025-04-20 13:13:30',0,0,0,0,0,0,0),(15,'ffdf',1230,'tassani@gmail.com',1,'2025-04-20 13:13:30',0,0,0,0,0,0,0),(16,'fdfdf',870,'tassani@gmail.com',1,'2025-04-20 13:13:30',0,0,0,0,0,0,0),(17,'Amapola',30,'tassani@gmail.com',1,'2025-04-26 20:46:40',0,0,0,0,0,0,0),(18,'new game',0,'tassani@gmail.com',1,'2025-04-20 22:44:57',0,0,0,0,0,0,0),(19,'gluyer mari',0,'tassani@gmail.com',1,'2025-04-22 22:17:19',0,0,0,0,0,0,0),(20,'iaa',3450,'tassani@gmail.com',1,'2025-04-26 20:47:30',0,0,0,0,0,0,0),(21,'rei',12,'tassani@gmail.com',1,'2025-04-26 20:47:10',0,0,0,0,0,0,0),(22,'arnau bot',0,'tassani@gmail.com',1,'2025-04-23 08:16:04',0,0,0,0,0,0,0),(24,'partida',0,'tassani@gmail.com',1,'2025-04-23 08:30:12',0,0,0,0,0,0,0),(26,'fkrofrf',0,'tassani@gmail.com',1,'2025-04-30 08:49:22',0,0,0,0,0,0,0),(29,'LaNovaPartida',0,'tassani@gmail.com',1,'2025-05-03 14:41:58',0,0,0,0,0,0,0),(30,'QWERTY',0,'tassani@gmail.com',1,'2025-05-03 14:46:47',0,0,0,0,0,0,0),(31,'ddeded',0,'tassani@gmail.com',1,'2025-05-04 12:10:41',0,0,0,0,0,0,0),(32,'Red',450,'dummy@nodomain.com',1,'2024-04-28 10:15:30',0,0,0,0,0,0,0),(33,'Blue',780,'dummy@nodomain.com',1,'2024-04-29 15:45:20',0,0,0,0,0,0,0),(34,'Green',920,'dummy@nodomain.com',1,'2024-04-30 09:30:45',0,0,0,0,0,0,0),(35,'Yellow',1200,'dummy@nodomain.com',1,'2024-05-01 14:20:15',0,0,0,0,0,0,0),(36,'Purple',550,'ducky@nodomain.com',1,'2024-05-01 16:40:10',0,0,0,0,0,0,0),(37,'Pink',670,'ducky@nodomain.com',1,'2024-05-03 08:55:30',0,0,0,0,0,0,0),(38,'Brown',1500,'ducky@nodomain.com',1,'2024-05-03 17:10:25',0,0,0,0,0,0,0),(39,'Cyan',820,'peip@nodomain.com',1,'2024-05-02 13:35:50',0,0,0,0,0,0,0),(40,'Magenta',990,'peip@nodomain.com',1,'2024-05-03 10:45:15',0,0,0,0,0,0,0),(41,'Gold',1100,'peip@nodomain.com',1,'2024-05-04 09:15:30',0,0,0,0,0,0,0),(42,'Silver',750,'peip@nodomain.com',1,'2024-05-04 15:50:20',0,0,0,0,0,0,0),(43,'frfmrl',0,'tassani@gmail.com',1,'2025-05-05 19:01:09',0,0,0,0,0,0,0),(69,'KO',10,'tassani@gmail.com',0,'2025-05-11 17:56:05',5,0,0,0,0,0,0),(70,'NEW_GAME',24,'botbot@gmail.com',1,'2025-05-11 19:35:56',2,0,0,1,0,0,0),(71,'BOOOOOT',279,'botbot@gmail.com',1,'2025-05-13 10:14:47',6,0,1,1,0,0,0),(72,'GAME_ARNAU',15,'botbot@gmail.com',1,'2025-05-13 15:34:13',2,1,0,1,0,0,0),(73,'NEW_GAME_PROVA',0,'botbot@gmail.com',1,'2025-05-13 18:09:36',0,0,0,0,0,0,0),(75,'prova_ng',9,'botbot@gmail.com',1,'2025-05-13 18:10:43',0,0,0,0,0,0,0),(77,'FDAFAS',0,'botbot@gmail.com',1,'2025-05-13 18:12:12',0,0,0,0,0,0,0),(79,'FDASFJKLÑ',3,'botbot@gmail.com',1,'2025-05-13 20:01:25',0,0,0,0,0,0,0),(81,'nuevoprueva',7,'prova@gmail.com',0,'2025-05-13 18:15:36',0,0,0,0,0,0,0),(82,'jkng',0,'botbot@gmail.com',1,'2025-05-13 20:02:24',0,0,0,0,0,0,0),(84,'HOLA2',3,'botbot@gmail.com',1,'2025-05-14 08:15:16',1,0,0,0,0,0,0),(86,'DFAÑJLFLADSK',0,'botbot@gmail.com',0,'2025-05-14 08:15:20',0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `partida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partida_generador`
--

DROP TABLE IF EXISTS `partida_generador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partida_generador` (
  `IdPartida_Generador` int NOT NULL AUTO_INCREMENT,
  `IdPartida` int DEFAULT NULL,
  `IdGenerador` int DEFAULT NULL,
  PRIMARY KEY (`IdPartida_Generador`),
  KEY `IdPartida` (`IdPartida`),
  KEY `IdGenerador` (`IdGenerador`),
  CONSTRAINT `partida_generador_ibfk_1` FOREIGN KEY (`IdPartida`) REFERENCES `partida` (`IdPartida`),
  CONSTRAINT `partida_generador_ibfk_2` FOREIGN KEY (`IdGenerador`) REFERENCES `generador` (`IdGenerador`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partida_generador`
--

LOCK TABLES `partida_generador` WRITE;
/*!40000 ALTER TABLE `partida_generador` DISABLE KEYS */;
INSERT INTO `partida_generador` VALUES (2,3,1);
/*!40000 ALTER TABLE `partida_generador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stats`
--

DROP TABLE IF EXISTS `stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stats` (
  `SubStats` int NOT NULL AUTO_INCREMENT,
  `Cafes` int DEFAULT NULL,
  `Minuto` int DEFAULT NULL,
  `IdPartida` int DEFAULT NULL,
  PRIMARY KEY (`SubStats`),
  KEY `IdPartida` (`IdPartida`),
  CONSTRAINT `stats_ibfk_1` FOREIGN KEY (`IdPartida`) REFERENCES `partida` (`IdPartida`)
) ENGINE=InnoDB AUTO_INCREMENT=369 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stats`
--

LOCK TABLES `stats` WRITE;
/*!40000 ALTER TABLE `stats` DISABLE KEYS */;
INSERT INTO `stats` VALUES (1,1,1,3),(2,10,1,3),(3,20,2,3),(4,50,3,3),(5,100,4,3),(6,200,5,3),(7,5,1,4),(8,8,2,4),(9,12,3,4),(10,15,4,4),(11,18,5,4),(12,22,6,4),(13,25,7,4),(14,30,8,4),(15,35,9,4),(16,40,10,4),(17,50,11,4),(18,70,12,4),(19,100,13,4),(20,130,14,4),(21,150,15,4),(23,10,1,3),(24,20,2,3),(25,50,3,3),(26,100,4,3),(27,200,5,3),(28,5,1,4),(29,8,2,4),(30,12,3,4),(31,15,4,4),(32,18,5,4),(33,22,6,4),(34,25,7,4),(35,30,8,4),(36,35,9,4),(37,40,10,4),(38,50,11,4),(39,70,12,4),(40,100,13,4),(41,130,14,4),(42,150,15,4),(43,1000,1,6),(44,105,2,6),(45,370,3,6),(46,70,4,6),(47,500,5,6),(49,1000,1,6),(50,105,2,6),(51,370,3,6),(52,70,4,6),(53,500,5,6),(55,5,1,17),(56,8,2,17),(57,12,3,17),(58,15,4,17),(59,18,5,17),(60,22,6,17),(61,25,7,17),(62,30,8,17),(63,35,9,17),(64,40,10,17),(65,50,11,17),(66,70,12,17),(67,100,13,17),(68,130,14,17),(69,150,15,17),(70,10,1,20),(71,20,2,20),(72,40,3,20),(73,80,4,20),(74,160,5,20),(75,320,6,20),(76,640,7,20),(77,1280,8,20),(78,2560,9,20),(79,5120,10,20),(80,10240,11,20),(81,20480,12,20),(82,40960,13,20),(83,81920,14,20),(84,163840,15,20),(85,327680,16,20),(86,655360,17,20),(87,1310720,18,20),(88,2621440,19,20),(89,5242880,20,20),(90,10485760,21,20),(91,20971520,22,20),(92,100,1,32),(93,150,2,32),(94,200,3,32),(95,250,4,32),(96,300,5,32),(97,350,6,32),(98,400,7,32),(99,450,8,32),(100,500,9,32),(101,550,10,32),(102,600,11,32),(103,650,12,32),(104,700,13,32),(105,750,14,32),(106,800,15,32),(107,850,16,32),(108,900,17,32),(109,950,18,32),(110,1000,19,32),(111,1050,20,32),(112,1100,21,32),(113,1150,22,32),(114,1200,23,32),(115,1250,24,32),(116,1300,25,32),(117,1350,26,32),(118,1400,27,32),(119,1450,28,32),(120,1500,29,32),(121,1550,30,32),(122,1600,31,32),(123,1650,32,32),(124,1700,33,32),(125,1750,34,32),(126,1800,35,32),(127,1850,36,32),(128,1900,37,32),(129,1950,38,32),(130,2000,39,32),(131,2050,40,32),(132,2100,41,32),(133,2150,42,32),(134,2200,43,32),(135,2250,44,32),(136,2300,45,32),(137,2350,46,32),(138,2400,47,32),(139,2450,48,32),(140,2500,49,32),(141,2550,50,32),(142,2600,51,32),(143,2650,52,32),(144,2700,53,32),(145,2750,54,32),(146,2800,55,32),(147,2850,56,32),(148,2900,57,32),(149,2950,58,32),(150,3000,59,32),(151,3050,60,32),(152,3100,61,32),(153,3150,62,32),(154,3200,63,32),(155,3250,64,32),(156,3300,65,32),(157,3350,66,32),(158,3400,67,32),(159,3450,68,32),(160,3500,69,32),(161,3550,70,32),(162,3600,71,32),(163,3650,72,32),(164,3700,73,32),(165,3750,74,32),(166,3800,75,32),(167,3850,76,32),(168,3900,77,32),(169,3950,78,32),(170,4000,79,32),(171,4050,80,32),(172,4100,81,32),(173,4150,82,32),(174,4200,83,32),(175,4250,84,32),(176,4300,85,32),(177,4350,86,32),(178,4400,87,32),(179,4450,88,32),(180,4500,89,32),(181,4550,90,32),(182,4600,91,32),(183,4650,92,32),(184,4700,93,32),(185,4750,94,32),(186,4800,95,32),(187,4850,96,32),(188,4900,97,32),(189,4950,98,32),(190,5000,99,32),(191,5050,100,32),(192,5100,101,32),(193,5150,102,32),(194,5200,103,32),(195,5250,104,32),(196,100,1,36),(197,200,2,36),(198,400,3,36),(199,800,4,36),(200,1600,5,36),(201,3200,6,36),(202,6400,7,36),(203,12800,8,36),(204,25600,9,36),(205,51200,10,36),(206,102400,11,36),(207,204800,12,36),(208,409600,13,36),(209,819200,14,36),(210,1638400,15,36),(211,3276800,16,36),(212,6553600,17,36),(213,13107200,18,36),(214,26214400,19,36),(215,52428800,20,36),(216,26214400,21,36),(217,13107200,22,36),(218,6553600,23,36),(219,3276800,24,36),(220,1638400,25,36),(221,819200,26,36),(222,409600,27,36),(223,204800,28,36),(224,102400,29,36),(225,51200,30,36),(226,25600,31,36),(227,12800,32,36),(228,6400,33,36),(229,3200,34,36),(230,1600,35,36),(231,800,36,36),(232,400,37,36),(233,200,38,36),(234,100,39,36),(235,50,40,36),(236,45,41,36),(237,40,42,36),(238,35,43,36),(239,30,44,36),(240,25,45,36),(241,20,46,36),(242,15,47,36),(243,10,48,36),(244,5,49,36),(245,4,50,36),(246,3,51,36),(247,2,52,36),(248,1,53,36),(249,1,54,36),(250,1,55,36),(251,1,56,36),(252,1,57,36),(253,1,58,36),(254,1,59,36),(255,1,60,36),(256,1,61,36),(257,1,62,36),(258,1,63,36),(259,1,64,36),(260,1,65,36),(261,1,66,36),(262,1,67,36),(263,1,68,36),(264,1,69,36),(265,1,70,36),(266,1,71,36),(267,1,72,36),(268,1,73,36),(269,1,74,36),(270,1,75,36),(271,1,76,36),(272,1,77,36),(273,1,78,36),(274,1,79,36),(275,1,80,36),(276,1,81,36),(277,1,82,36),(278,1,83,36),(279,1,84,36),(280,1,85,36),(281,1,86,36),(282,1,87,36),(283,1,88,36),(284,1,89,36),(285,1,90,36),(286,1,91,36),(287,1,92,36),(288,1,93,36),(289,1,94,36),(290,1,95,36),(291,1,96,36),(292,1,97,36),(293,1,98,36),(294,1,99,36),(295,1,100,36),(296,1,101,36),(297,1,102,36),(298,1,103,36),(299,1,104,36),(300,1,105,36),(301,1,106,36),(302,1,107,36),(303,1,108,36),(304,1,109,36),(305,1,110,36),(306,1,111,36),(307,1,112,36),(308,1,113,36),(309,1,114,36),(310,1,115,36),(311,1,116,36),(312,1,117,36),(313,1,118,36),(314,1,119,36),(315,1,120,36),(316,1,121,36),(317,1,122,36),(318,1,123,36),(319,1,124,36),(320,1,125,36),(321,1,126,36),(322,1,127,36),(323,1,128,36),(324,1,129,36),(325,1,130,36),(326,1,131,36),(327,1,132,36),(328,1,133,36),(329,1,134,36),(330,1,135,36),(331,1,136,36),(332,1,137,36),(333,1,138,36),(334,1,139,36),(335,1,140,36),(336,1,141,36),(337,1,142,36),(338,1,143,36),(339,1,144,36),(340,1,145,36),(341,1,146,36),(342,1,147,36),(343,1,148,36),(344,1,149,36),(345,1,150,36),(346,1,151,36),(347,1,152,36),(348,1,153,36),(349,1,154,36),(350,1,155,36),(351,1,156,36),(352,1,157,36),(353,1,158,36),(354,1,159,36),(355,1,160,36),(356,1,161,36),(357,1,162,36),(358,1,163,36),(359,1,164,36),(360,1,165,36),(361,1,166,36),(362,1,167,36),(363,1,168,36),(364,1000,1,39),(365,500,2,39),(366,2000,3,39),(367,100,4,39),(368,1500,5,39);
/*!40000 ALTER TABLE `stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `Correo` varchar(255) NOT NULL,
  `Nombre` varchar(100) DEFAULT NULL,
  `Contrasena` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('arnau.ricart@students.salle.url.edu','chuggy','chuggybot123'),('botbot@gmail.com','ARNAURICART','BOTBOTBOT'),('ducky@nodomain.com','Ducky','123'),('dummy@nodomain.com','Dummy','123'),('gerardmas@gmail.com','gerardmas','gerimas2005'),('hector@gmail.com','hector','hectorbot'),('peip@nodomain.com','Peip','123'),('prova@gmail.com','NOMPROVA','Provanom123'),('tassani@gmail.com','T','1'),('tassvid@gmail.com','tassini123','tassvidbot456'),('zuri@gmail.com','ZURIKETE','zuripaki');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-14  8:42:44
