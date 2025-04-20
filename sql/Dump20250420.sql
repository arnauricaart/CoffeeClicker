-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: cofeeclicker_schema
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
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
  `Cafes` int DEFAULT NULL,
  `Correo` varchar(255) DEFAULT NULL,
  `Terminada` tinyint DEFAULT NULL,
  `UltimoAcceso` datetime DEFAULT NULL,
  PRIMARY KEY (`IdPartida`),
  KEY `Correo` (`Correo`),
  CONSTRAINT `partida_ibfk_1` FOREIGN KEY (`Correo`) REFERENCES `users` (`Correo`),
  CONSTRAINT `partida_nombre_uk` UNIQUE KEY (`Nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partida`
--

LOCK TABLES `partida` WRITE;
/*!40000 ALTER TABLE `partida` DISABLE KEYS */;
INSERT INTO `partida` VALUES (3,'Hola',30,'tassani@gmail.com',0,'2025-04-20 13:13:30'),(4,'gay',12,'tassani@gmail.com',0,'2025-04-20 13:13:30'),(5,'maricon',330,'tassani@gmail.com',0,'2025-04-20 13:13:30'),(6,'gluyer',3450,'tassani@gmail.com',0,'2025-04-20 13:13:30'),(7,'bot',80,'tassani@gmail.com',0,'2025-04-20 13:13:30'),(8,'nini',0,'tassani@gmail.com',0,'2025-04-20 13:13:30'),(9,'nigga',78,'tassani@gmail.com',0,'2025-04-20 13:13:30'),(10,'hombre',11,'tassani@gmail.com',0,'2025-04-20 13:13:30'),(11,'trabolo',1,'tassani@gmail.com',0,'2025-04-20 13:13:30'),(12,'pre',230,'tassani@gmail.com',0,'2025-04-20 13:13:30'),(13,'tigre',90,'tassani@gmail.com',0,'2025-04-20 13:13:30'),(14,'dks',380,'tassani@gmail.com',0,'2025-04-20 13:13:30'),(15,'ffdf',1230,'tassani@gmail.com',0,'2025-04-20 13:13:30'),(16,'fdfdf',870,'tassani@gmail.com',0,'2025-04-20 13:13:30');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stats`
--

LOCK TABLES `stats` WRITE;
/*!40000 ALTER TABLE `stats` DISABLE KEYS */;
INSERT INTO `stats` VALUES (1,1,1,3);
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
INSERT INTO `users` VALUES ('arnau.ricart@students.salle.url.edu','chuggy','chuggybot123'),('botbot@gmail.com','ARNAURICART','BOTBOTBOT'),('gerardmas@gmail.com','gerardmas','gerimas2005'),('hector@gmail.com','hector','hectorbot'),('tassani@gmail.com','TASSANI','botbotbot123'),('tassvid@gmail.com','tassini123','tassvidbot456'),('zuri@gmail.com','ZURIKETE','zuri¬paki');
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

-- Dump completed on 2025-04-20 19:31:03
