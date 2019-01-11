-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mobileservice
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `curplan`
--

DROP TABLE IF EXISTS `curplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `curplan` (
  `userID` char(11) NOT NULL,
  `planID` int(11) NOT NULL,
  `callMinsLeft` int(11) NOT NULL,
  `mesCntLeft` int(11) NOT NULL,
  `localDataLeft` double NOT NULL,
  `domDataLeft` double NOT NULL,
  `calNextMonth` tinyint(4) NOT NULL,
  PRIMARY KEY (`userID`,`planID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curplan`
--

LOCK TABLES `curplan` WRITE;
/*!40000 ALTER TABLE `curplan` DISABLE KEYS */;
/*!40000 ALTER TABLE `curplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deductionrecord`
--

DROP TABLE IF EXISTS `deductionrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `deductionrecord` (
  `deID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` char(11) NOT NULL,
  `srvType` varchar(9) NOT NULL,
  `amount` double NOT NULL,
  `cost` double NOT NULL,
  `deTime` date NOT NULL,
  PRIMARY KEY (`deID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deductionrecord`
--

LOCK TABLES `deductionrecord` WRITE;
/*!40000 ALTER TABLE `deductionrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `deductionrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hstusage`
--

DROP TABLE IF EXISTS `hstusage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hstusage` (
  `userID` char(11) NOT NULL,
  `plans` varchar(12) NOT NULL,
  `totalCall` int(11) NOT NULL,
  `totalMes` int(11) NOT NULL,
  `totalLocalData` double NOT NULL,
  `totalDomData` double NOT NULL,
  `begDate` date NOT NULL,
  `endDate` date NOT NULL,
  `pay` double NOT NULL,
  PRIMARY KEY (`userID`,`begDate`,`endDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hstusage`
--

LOCK TABLES `hstusage` WRITE;
/*!40000 ALTER TABLE `hstusage` DISABLE KEYS */;
/*!40000 ALTER TABLE `hstusage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `plan` (
  `planID` int(11) NOT NULL,
  `planName` varchar(23) NOT NULL,
  `callMins` int(11) NOT NULL DEFAULT '0',
  `mesCnt` int(11) NOT NULL DEFAULT '0',
  `localData` double NOT NULL DEFAULT '0',
  `domData` double NOT NULL DEFAULT '0',
  `planPrice` double NOT NULL,
  PRIMARY KEY (`planID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1,'callMins',100,0,0,0,20),(2,'mesCnt',0,200,0,0,10),(3,'localDataUsg',0,0,2048,0,20),(4,'domDataUsg',0,0,0,2048,30),(5,'combinedPlan',50,100,1024,1024,40);
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plansublog`
--

DROP TABLE IF EXISTS `plansublog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `plansublog` (
  `userID` char(11) NOT NULL,
  `planID` int(11) NOT NULL,
  `subDate` date NOT NULL,
  `cost` double NOT NULL,
  `validity` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`userID`,`planID`,`subDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plansublog`
--

LOCK TABLES `plansublog` WRITE;
/*!40000 ALTER TABLE `plansublog` DISABLE KEYS */;
/*!40000 ALTER TABLE `plansublog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `mobileNum` char(11) NOT NULL,
  `validDate` date NOT NULL,
  `balance` double NOT NULL DEFAULT '0',
  `curPlans` varchar(12) NOT NULL,
  `curCall` int(11) NOT NULL,
  `curMes` int(11) NOT NULL,
  `curLocalData` double NOT NULL,
  `curDomData` double NOT NULL,
  `curPay` double NOT NULL,
  PRIMARY KEY (`mobileNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-30 15:38:35
