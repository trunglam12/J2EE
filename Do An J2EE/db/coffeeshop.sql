CREATE DATABASE  IF NOT EXISTS `coffeeshop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `coffeeshop`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: coffeeshop
-- ------------------------------------------------------
-- Server version	5.6.24-log

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
-- Table structure for table `detailreceipt`
--

DROP TABLE IF EXISTS `detailreceipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detailreceipt` (
  `ReceiptId` int(11) NOT NULL,
  `ProductId` int(11) NOT NULL,
  `Count` int(11) NOT NULL,
  PRIMARY KEY (`ReceiptId`,`ProductId`),
  KEY `FK_DetailReceipt_Product` (`ProductId`),
  CONSTRAINT `FK_DetailReceipt_Product` FOREIGN KEY (`ProductId`) REFERENCES `product` (`ProductId`),
  CONSTRAINT `FK_DetailReceipt_Receipt` FOREIGN KEY (`ReceiptId`) REFERENCES `receipt` (`ReceiptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detailreceipt`
--

LOCK TABLES `detailreceipt` WRITE;
/*!40000 ALTER TABLE `detailreceipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `detailreceipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detailreceiptnote`
--

DROP TABLE IF EXISTS `detailreceiptnote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detailreceiptnote` (
  `ReceiptNoteId` int(11) NOT NULL,
  `ProductId` int(11) NOT NULL,
  `Price` decimal(10,0) NOT NULL,
  `Count` int(11) NOT NULL,
  PRIMARY KEY (`ReceiptNoteId`,`ProductId`),
  KEY `FK_DetailReceiptNote_Product` (`ProductId`),
  CONSTRAINT `FK_DetailReceiptNote_Product` FOREIGN KEY (`ProductId`) REFERENCES `product` (`ProductId`),
  CONSTRAINT `FK_DetailReceiptNote_ReceiptNote` FOREIGN KEY (`ReceiptNoteId`) REFERENCES `receiptnote` (`ReceiptNoteId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detailreceiptnote`
--

LOCK TABLES `detailreceiptnote` WRITE;
/*!40000 ALTER TABLE `detailreceiptnote` DISABLE KEYS */;
INSERT INTO `coffeeshop`.`detailreceipt` (`ReceiptId`, `ProductId`, `Count`) VALUES ('1', '1', '3');
INSERT INTO `coffeeshop`.`detailreceipt` (`ReceiptId`, `ProductId`, `Count`) VALUES ('1', '3', '1');
INSERT INTO `coffeeshop`.`detailreceipt` (`ReceiptId`, `ProductId`, `Count`) VALUES ('2', '1', '4');
INSERT INTO `coffeeshop`.`detailreceipt` (`ReceiptId`, `ProductId`, `Count`) VALUES ('2', '2', '1');
INSERT INTO `coffeeshop`.`detailreceipt` (`ReceiptId`, `ProductId`, `Count`) VALUES ('3', '2', '3');
INSERT INTO `coffeeshop`.`detailreceipt` (`ReceiptId`, `ProductId`, `Count`) VALUES ('3', '3', '2');
/*!40000 ALTER TABLE `detailreceiptnote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `EmployeeId` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeName` varchar(100) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `Phone` varchar(15) NOT NULL,
  `Role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`EmployeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Nguyễn Hải Đăng','Q9, HCM','123456789',NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupproduct`
--

DROP TABLE IF EXISTS `groupproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groupproduct` (
  `GroupId` int(11) NOT NULL AUTO_INCREMENT,
  `GroupName` varchar(100) NOT NULL,
  PRIMARY KEY (`GroupId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupproduct`
--

LOCK TABLES `groupproduct` WRITE;
/*!40000 ALTER TABLE `groupproduct` DISABLE KEYS */;
INSERT INTO `groupproduct` VALUES (1,'Thức uống'),(2,'thuc an');
/*!40000 ALTER TABLE `groupproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `ProductId` int(11) NOT NULL AUTO_INCREMENT,
  `GroupId` int(11) NOT NULL,
  `UnitId` int(11) NOT NULL,
  `ProductName` varchar(100) NOT NULL,
  `Price` decimal(10,0) NOT NULL,
  `Count` int(11) NOT NULL,
  `Status` bit(1) NOT NULL,
  `Image` varchar(100) DEFAULT NULL,
  `OgrinalPrice` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ProductId`),
  KEY `FK_Product_Group` (`GroupId`),
  KEY `FK_Product_Unit` (`UnitId`),
  CONSTRAINT `FK_Product_Group` FOREIGN KEY (`GroupId`) REFERENCES `groupproduct` (`GroupId`),
  CONSTRAINT `FK_Product_Unit` FOREIGN KEY (`UnitId`) REFERENCES `unit` (`UnitId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,1,1,'Aquafina',10000,100,'','/Product/aquafina.png',NULL);
INSERT INTO `product` VALUES (2,1,1,'Sting',15000,100,'','/Product/aquafina.png',NULL);
INSERT INTO `product` VALUES (3,3,1,'Khoai tây chiên',20000,100,'','/Product/aquafina.png',NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provider` (
  `ProviderId` int(11) NOT NULL AUTO_INCREMENT,
  `ProviderName` varchar(100) NOT NULL,
  `Address` varchar(200) NOT NULL,
  PRIMARY KEY (`ProviderId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
INSERT INTO `provider` VALUES (2,'Cà phê Trung Nguyên','Q9, TPHCM'),(3,'Vinamilk','Q1, HCM'),(4,'Cocacola','Q9, HCM');
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt` (
  `ReceiptId` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeId` int(11) NOT NULL,
  `Date` date NOT NULL,
  `CustomerName` varchar(100) NOT NULL,
  `TotalPrice` decimal(10,0) NOT NULL,
  `Status` bit(1) NOT NULL,
  PRIMARY KEY (`ReceiptId`),
  KEY `FK_Receipt_Employee` (`EmployeeId`),
  CONSTRAINT `FK_Receipt_Employee` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`EmployeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `coffeeshop`.`receipt` (`EmployeeId`, `Date`, `CustomerName`, `TotalPrice`, `Status`, `TableId`) VALUES ('1', '2016/06/13', 'customer','50000', 1, '1')
INSERT INTO `coffeeshop`.`receipt` (`EmployeeId`, `Date`, `CustomerName`, `TotalPrice`, `Status`, `TableId`) VALUES ('1', '2016/06/13', 'customer','55000', 1, '2')
INSERT INTO `coffeeshop`.`receipt` (`EmployeeId`, `Date`, `CustomerName`, `TotalPrice`, `Status`, `TableId`) VALUES ('1', '2016/06/14', 'customer','85000', 1, '2')
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receiptnote`
--

DROP TABLE IF EXISTS `receiptnote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receiptnote` (
  `ReceiptNoteId` int(11) NOT NULL AUTO_INCREMENT,
  `ProviderId` int(11) NOT NULL,
  `Date` date NOT NULL,
  `TotalCount` int(11) NOT NULL,
  PRIMARY KEY (`ReceiptNoteId`),
  KEY `FK_ReceiptNote_Provider` (`ProviderId`),
  CONSTRAINT `FK_ReceiptNote_Provider` FOREIGN KEY (`ProviderId`) REFERENCES `provider` (`ProviderId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receiptnote`
--

LOCK TABLES `receiptnote` WRITE;
/*!40000 ALTER TABLE `receiptnote` DISABLE KEYS */;
INSERT INTO `receiptnote` VALUES (2,4,'2016-06-23',0),(7,2,'2016-06-30',0);
/*!40000 ALTER TABLE `receiptnote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tablecoffee`
--

DROP TABLE IF EXISTS `tablecoffee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tablecoffee` (
  `TableId` int(11) NOT NULL AUTO_INCREMENT,
  `TableName` varchar(100) NOT NULL,
  `Status` bit(1) NOT NULL,
  `ReceiptId` int(11) DEFAULT NULL,
  PRIMARY KEY (`TableId`),
  KEY `FK_Table_Receipt_idx` (`ReceiptId`),
  CONSTRAINT `FK_Table_Receipt` FOREIGN KEY (`ReceiptId`) REFERENCES `receipt` (`ReceiptId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tablecoffee`
--

LOCK TABLES `tablecoffee` WRITE;
/*!40000 ALTER TABLE `tablecoffee` DISABLE KEYS */;
INSERT INTO `tablecoffee` VALUES (1,'Bàn 1','\0',NULL),(2,'Bàn 2','',NULL);
/*!40000 ALTER TABLE `tablecoffee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit` (
  `UnitId` int(11) NOT NULL AUTO_INCREMENT,
  `UnitName` varchar(50) NOT NULL,
  PRIMARY KEY (`UnitId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'chai'),(2,'thùng');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `UserAccount` varchar(30) NOT NULL,
  `Password` varchar(64) NOT NULL,
  `EmployeeId` int(11) NOT NULL,
  PRIMARY KEY (`UserId`),
  KEY `FK_User_Employee` (`EmployeeId`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`EmployeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','81dc9bdb52d04dc20036dbd8313ed055',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'coffeeshop'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-13 10:05:39
