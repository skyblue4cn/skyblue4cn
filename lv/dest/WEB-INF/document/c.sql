-- MySQL dump 10.10
--
-- Host: localhost    Database: dbinsurance
-- ------------------------------------------------------
-- Server version	5.0.16-nt

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
-- Dumping data for table `tbAccidentalFee`
--


/*!40000 ALTER TABLE `tbAccidentalFee` DISABLE KEYS */;
LOCK TABLES `tbAccidentalFee` WRITE;
INSERT INTO `tbAccidentalFee` VALUES (1,NULL,1,1.5,2,3,4,6,8,1,8,15,2,50,100);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbAccidentalFee` ENABLE KEYS */;

--
-- Dumping data for table `tbAccount`
--


/*!40000 ALTER TABLE `tbAccount` DISABLE KEYS */;
LOCK TABLES `tbAccount` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbAccount` ENABLE KEYS */;

--
-- Dumping data for table `tbAccountPayLog`
--


/*!40000 ALTER TABLE `tbAccountPayLog` DISABLE KEYS */;
LOCK TABLES `tbAccountPayLog` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbAccountPayLog` ENABLE KEYS */;

--
-- Dumping data for table `tbAdjustFee`
--


/*!40000 ALTER TABLE `tbAdjustFee` DISABLE KEYS */;
LOCK TABLES `tbAdjustFee` WRITE;
INSERT INTO `tbAdjustFee` VALUES (1,NULL,0.2,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbAdjustFee` ENABLE KEYS */;

--
-- Dumping data for table `tbBill`
--


/*!40000 ALTER TABLE `tbBill` DISABLE KEYS */;
LOCK TABLES `tbBill` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbBill` ENABLE KEYS */;

--
-- Dumping data for table `tbBillKind`
--


/*!40000 ALTER TABLE `tbBillKind` DISABLE KEYS */;
LOCK TABLES `tbBillKind` WRITE;
INSERT INTO `tbBillKind` VALUES (1,'责任险','旅行社责任保险人员调节确认表',NULL,NULL),(2,'意外险','境内、境外旅游团队人身伤害保险预约投保申请表',NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbBillKind` ENABLE KEYS */;

--
-- Dumping data for table `tbBillState`
--


/*!40000 ALTER TABLE `tbBillState` DISABLE KEYS */;
LOCK TABLES `tbBillState` WRITE;
INSERT INTO `tbBillState` VALUES (1,0,0,0,-1,'保单预提交，还没有确认信息正式提交'),(2,0,0,0,0,'保单已提交，需要旅行社进行审核'),(3,0,0,0,0,'保单已提交，需要保险公司进行审核'),(4,1,1,0,1,'保险公司已审核，保单已生效'),(5,0,0,0,0,'保单被旅行社总社退回'),(6,0,0,0,-4,'保单被保险公司退回');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbBillState` ENABLE KEYS */;

--
-- Dumping data for table `tbCancelBillInfo`
--


/*!40000 ALTER TABLE `tbCancelBillInfo` DISABLE KEYS */;
LOCK TABLES `tbCancelBillInfo` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbCancelBillInfo` ENABLE KEYS */;

--
-- Dumping data for table `tbFeeRate`
--


/*!40000 ALTER TABLE `tbFeeRate` DISABLE KEYS */;
LOCK TABLES `tbFeeRate` WRITE;
INSERT INTO `tbFeeRate` VALUES (1,NULL,100,'通用费率');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbFeeRate` ENABLE KEYS */;

--
-- Dumping data for table `tbLimitFee`
--


/*!40000 ALTER TABLE `tbLimitFee` DISABLE KEYS */;
LOCK TABLES `tbLimitFee` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbLimitFee` ENABLE KEYS */;

--
-- Dumping data for table `tbMessage`
--


/*!40000 ALTER TABLE `tbMessage` DISABLE KEYS */;
LOCK TABLES `tbMessage` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbMessage` ENABLE KEYS */;

--
-- Dumping data for table `tbMonthPayInfo`
--


/*!40000 ALTER TABLE `tbMonthPayInfo` DISABLE KEYS */;
LOCK TABLES `tbMonthPayInfo` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbMonthPayInfo` ENABLE KEYS */;

--
-- Dumping data for table `tbNote`
--


/*!40000 ALTER TABLE `tbNote` DISABLE KEYS */;
LOCK TABLES `tbNote` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbNote` ENABLE KEYS */;

--
-- Dumping data for table `tbPartment`
--


/*!40000 ALTER TABLE `tbPartment` DISABLE KEYS */;
LOCK TABLES `tbPartment` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbPartment` ENABLE KEYS */;

--
-- Dumping data for table `tbPayOutInfo`
--


/*!40000 ALTER TABLE `tbPayOutInfo` DISABLE KEYS */;
LOCK TABLES `tbPayOutInfo` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbPayOutInfo` ENABLE KEYS */;

--
-- Dumping data for table `tbRight`
--


/*!40000 ALTER TABLE `tbRight` DISABLE KEYS */;
LOCK TABLES `tbRight` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbRight` ENABLE KEYS */;

--
-- Dumping data for table `tbRole`
--


/*!40000 ALTER TABLE `tbRole` DISABLE KEYS */;
LOCK TABLES `tbRole` WRITE;
INSERT INTO `tbRole` VALUES (1,'黑名单',3,NULL),(2,'保险公司领导',1,NULL),(3,'保险公司业务员',1,NULL),(4,'旅行社总社领导',2,NULL),(5,'旅行社总社员工',2,NULL),(6,'旅行社部门领导',2,NULL),(7,'旅行社部门员工',2,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbRole` ENABLE KEYS */;

--
-- Dumping data for table `tbTravelerInfo`
--


/*!40000 ALTER TABLE `tbTravelerInfo` DISABLE KEYS */;
LOCK TABLES `tbTravelerInfo` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbTravelerInfo` ENABLE KEYS */;

--
-- Dumping data for table `tbUser`
--


/*!40000 ALTER TABLE `tbUser` DISABLE KEYS */;
LOCK TABLES `tbUser` WRITE;
INSERT INTO `tbUser` VALUES (1,'admin','D0D08EE4E807BB89',NULL,'业务员1',NULL,NULL,7,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbUser` ENABLE KEYS */;

--
-- Dumping data for table `tbUserType`
--


/*!40000 ALTER TABLE `tbUserType` DISABLE KEYS */;
LOCK TABLES `tbUserType` WRITE;
INSERT INTO `tbUserType` VALUES (1,'保险公司人员','保险公司人员'),(2,'旅行社人员','旅行社人员'),(3,'旅行社人员','旅行社人员');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbUserType` ENABLE KEYS */;

--
-- Dumping data for table `tbYearPayInfo`
--


/*!40000 ALTER TABLE `tbYearPayInfo` DISABLE KEYS */;
LOCK TABLES `tbYearPayInfo` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbYearPayInfo` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

