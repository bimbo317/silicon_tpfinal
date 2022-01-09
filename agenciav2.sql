CREATE DATABASE  IF NOT EXISTS `agenciav2` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `agenciav2`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: agenciav2
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `ID` bigint NOT NULL,
  `ALTA` tinyint(1) DEFAULT '0',
  `cod_cliente` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `cod_cliente` (`cod_cliente`),
  CONSTRAINT `FK_CLIENTE_ID` FOREIGN KEY (`ID`) REFERENCES `persona` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (203,1,1),(601,1,2),(602,1,3);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_venta`
--

DROP TABLE IF EXISTS `cliente_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_venta` (
  `Cliente_ID` bigint NOT NULL,
  `listaventas_IDVENTA` bigint NOT NULL,
  PRIMARY KEY (`Cliente_ID`,`listaventas_IDVENTA`),
  KEY `FK_CLIENTE_VENTA_listaventas_IDVENTA` (`listaventas_IDVENTA`),
  CONSTRAINT `FK_CLIENTE_VENTA_Cliente_ID` FOREIGN KEY (`Cliente_ID`) REFERENCES `persona` (`ID`),
  CONSTRAINT `FK_CLIENTE_VENTA_listaventas_IDVENTA` FOREIGN KEY (`listaventas_IDVENTA`) REFERENCES `venta` (`IDVENTA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_venta`
--

LOCK TABLES `cliente_venta` WRITE;
/*!40000 ALTER TABLE `cliente_venta` DISABLE KEYS */;
INSERT INTO `cliente_venta` VALUES (203,651),(203,701),(203,751);
/*!40000 ALTER TABLE `cliente_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `ID` bigint NOT NULL,
  `CARGO` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cod_empleado` bigint NOT NULL AUTO_INCREMENT,
  `SUELDO` double DEFAULT NULL,
  `USUARIO_id_usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `cod_empleado` (`cod_empleado`),
  KEY `FK_EMPLEADO_USUARIO_id_usuario` (`USUARIO_id_usuario`),
  CONSTRAINT `FK_EMPLEADO_ID` FOREIGN KEY (`ID`) REFERENCES `persona` (`ID`),
  CONSTRAINT `FK_EMPLEADO_USUARIO_id_usuario` FOREIGN KEY (`USUARIO_id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (2,'Gerente',1,50000,1),(53,'Asesor',2,150000,52),(202,'empleado',5,50000,201);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paquete`
--

DROP TABLE IF EXISTS `paquete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paquete` (
  `ID` bigint NOT NULL,
  `cod_paquete` bigint NOT NULL AUTO_INCREMENT,
  `COSTO_PAQUETE` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `cod_paquete` (`cod_paquete`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paquete`
--

LOCK TABLES `paquete` WRITE;
/*!40000 ALTER TABLE `paquete` DISABLE KEYS */;
INSERT INTO `paquete` VALUES (401,1,22500),(451,2,5850);
/*!40000 ALTER TABLE `paquete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paquete_servicio`
--

DROP TABLE IF EXISTS `paquete_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paquete_servicio` (
  `Paquete_ID` bigint NOT NULL,
  `lista_servicios_incluidos_ID` bigint NOT NULL,
  PRIMARY KEY (`Paquete_ID`,`lista_servicios_incluidos_ID`),
  KEY `FK_PAQUETE_SERVICIO_lista_servicios_incluidos_ID` (`lista_servicios_incluidos_ID`),
  CONSTRAINT `FK_PAQUETE_SERVICIO_lista_servicios_incluidos_ID` FOREIGN KEY (`lista_servicios_incluidos_ID`) REFERENCES `servicio` (`ID`),
  CONSTRAINT `FK_PAQUETE_SERVICIO_Paquete_ID` FOREIGN KEY (`Paquete_ID`) REFERENCES `paquete` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paquete_servicio`
--

LOCK TABLES `paquete_servicio` WRITE;
/*!40000 ALTER TABLE `paquete_servicio` DISABLE KEYS */;
INSERT INTO `paquete_servicio` VALUES (401,251),(401,252),(451,252),(451,302);
/*!40000 ALTER TABLE `paquete_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paquete_venta`
--

DROP TABLE IF EXISTS `paquete_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paquete_venta` (
  `Paquete_ID` bigint NOT NULL,
  `listaVenta_IDVENTA` bigint NOT NULL,
  PRIMARY KEY (`Paquete_ID`,`listaVenta_IDVENTA`),
  KEY `FK_PAQUETE_VENTA_listaVenta_IDVENTA` (`listaVenta_IDVENTA`),
  CONSTRAINT `FK_PAQUETE_VENTA_listaVenta_IDVENTA` FOREIGN KEY (`listaVenta_IDVENTA`) REFERENCES `venta` (`IDVENTA`),
  CONSTRAINT `FK_PAQUETE_VENTA_Paquete_ID` FOREIGN KEY (`Paquete_ID`) REFERENCES `paquete` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paquete_venta`
--

LOCK TABLES `paquete_venta` WRITE;
/*!40000 ALTER TABLE `paquete_venta` DISABLE KEYS */;
INSERT INTO `paquete_venta` VALUES (401,701),(451,751);
/*!40000 ALTER TABLE `paquete_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `ID` bigint NOT NULL,
  `DTYPE` varchar(31) COLLATE utf8_spanish_ci DEFAULT NULL,
  `APELLIDO` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `CELULAR` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `DIRECCION` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `DNI` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `EMAIL` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `FECHANAC` date DEFAULT NULL,
  `NACIONALIDAD` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NOMBRE` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DNI` (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (2,'Empleado','Guerrero','+542616469555','Av Bandera de los Andes 2268, PA D4, Nueva Ciudad','36745373','bimbo.lt@gmail.com','1993-12-22','Argentina','Victor'),(53,'Empleado','Guerrero','+542616464646','Av Bandera de los Andes 2268, PA D4, Nueva Ciudad','39609119','guad@lupe.com','2021-12-04','Argentina','Guadalupe'),(202,'Empleado','Caro','261444','fleming 851','33040728','pascual_caro@hotmail.com','2021-11-29','Armenia','Pascual'),(203,'Cliente','Guzman','39017257','39017257','39017257','nancysol.ng@gmail.com','1989-04-28','Argentina','Nancy'),(601,'Cliente','Guzman','2614565566','Sarmiento 1265 Guaymallen Mendoza','28321123','maga@gmail.com','1991-10-06','','Magali'),(602,'Cliente','Guzman','41852123','2614851379','41852123','maga@gmail.com','1991-10-06','Aruba','Magali');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('SEQ_GEN',950);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio`
--

DROP TABLE IF EXISTS `servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio` (
  `ID` bigint NOT NULL,
  `cod_servicio` bigint NOT NULL AUTO_INCREMENT,
  `COSTO_SERVICIO` double DEFAULT NULL,
  `DESCRIPCION` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `DESTINO` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `FECHA_SERVICIO` date DEFAULT NULL,
  `NOMBRE` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `cod_servicio` (`cod_servicio`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` VALUES (251,1,20000,'Una noche de hotel, desayuno incluido','Madrid',NULL,'Hotel'),(252,2,5000,'Alquiler de auto sedan 4 puertas por dia','Madrid',NULL,'Auto'),(301,3,90000,'Pasaje turista','Mendoza Madrid',NULL,'Vuelo'),(302,4,1500,'Pasaje de tren','Madrid-Barcelona','2021-12-30','Tren'),(551,6,1000,'Pasaje colectivo','Madrid-Barcelona',NULL,'Colectivo'),(552,7,3000,'Visita guiada por el Palacio Real','Madrid',NULL,'Excursion'),(553,8,28000,'Aerosmith en Madrid (Wanda Metropolitano)','Madrid','2022-06-04','Evento');
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio_paquete`
--

DROP TABLE IF EXISTS `servicio_paquete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio_paquete` (
  `Servicio_ID` bigint NOT NULL,
  `lista_paquetes_ID` bigint NOT NULL,
  PRIMARY KEY (`Servicio_ID`,`lista_paquetes_ID`),
  KEY `FK_SERVICIO_PAQUETE_lista_paquetes_ID` (`lista_paquetes_ID`),
  CONSTRAINT `FK_SERVICIO_PAQUETE_lista_paquetes_ID` FOREIGN KEY (`lista_paquetes_ID`) REFERENCES `paquete` (`ID`),
  CONSTRAINT `FK_SERVICIO_PAQUETE_Servicio_ID` FOREIGN KEY (`Servicio_ID`) REFERENCES `servicio` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio_paquete`
--

LOCK TABLES `servicio_paquete` WRITE;
/*!40000 ALTER TABLE `servicio_paquete` DISABLE KEYS */;
INSERT INTO `servicio_paquete` VALUES (251,401),(252,401),(252,451),(302,451);
/*!40000 ALTER TABLE `servicio_paquete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio_venta`
--

DROP TABLE IF EXISTS `servicio_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio_venta` (
  `Servicio_ID` bigint NOT NULL,
  `listaVenta_IDVENTA` bigint NOT NULL,
  PRIMARY KEY (`Servicio_ID`,`listaVenta_IDVENTA`),
  KEY `FK_SERVICIO_VENTA_listaVenta_IDVENTA` (`listaVenta_IDVENTA`),
  CONSTRAINT `FK_SERVICIO_VENTA_listaVenta_IDVENTA` FOREIGN KEY (`listaVenta_IDVENTA`) REFERENCES `venta` (`IDVENTA`),
  CONSTRAINT `FK_SERVICIO_VENTA_Servicio_ID` FOREIGN KEY (`Servicio_ID`) REFERENCES `servicio` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio_venta`
--

LOCK TABLES `servicio_venta` WRITE;
/*!40000 ALTER TABLE `servicio_venta` DISABLE KEYS */;
INSERT INTO `servicio_venta` VALUES (252,651);
/*!40000 ALTER TABLE `servicio_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` bigint NOT NULL,
  `ALTA` tinyint(1) DEFAULT '0',
  `CONTRASENIA` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NOMBREUSUARIO` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `NOMBREUSUARIO` (`NOMBREUSUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,1,'123123','bimbo'),(52,1,'123123','guada'),(201,1,'123123','pascual');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_venta`
--

DROP TABLE IF EXISTS `usuario_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_venta` (
  `Usuario_id_usuario` bigint NOT NULL,
  `listaVentas_IDVENTA` bigint NOT NULL,
  PRIMARY KEY (`Usuario_id_usuario`,`listaVentas_IDVENTA`),
  KEY `FK_USUARIO_VENTA_listaVentas_IDVENTA` (`listaVentas_IDVENTA`),
  CONSTRAINT `FK_USUARIO_VENTA_listaVentas_IDVENTA` FOREIGN KEY (`listaVentas_IDVENTA`) REFERENCES `venta` (`IDVENTA`),
  CONSTRAINT `FK_USUARIO_VENTA_Usuario_id_usuario` FOREIGN KEY (`Usuario_id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_venta`
--

LOCK TABLES `usuario_venta` WRITE;
/*!40000 ALTER TABLE `usuario_venta` DISABLE KEYS */;
INSERT INTO `usuario_venta` VALUES (1,651),(1,701),(1,751);
/*!40000 ALTER TABLE `usuario_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `IDVENTA` bigint NOT NULL,
  `FECHA_VENTA` date DEFAULT NULL,
  `MEDIO_PAGO` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `num_venta` bigint NOT NULL AUTO_INCREMENT,
  `CLIENTE_ID` bigint DEFAULT NULL,
  `PAQUETE_ID` bigint DEFAULT NULL,
  `SERVICIO_ID` bigint DEFAULT NULL,
  `USUARIO_id_usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`IDVENTA`),
  UNIQUE KEY `num_venta` (`num_venta`),
  KEY `FK_VENTA_CLIENTE_ID` (`CLIENTE_ID`),
  KEY `FK_VENTA_PAQUETE_ID` (`PAQUETE_ID`),
  KEY `FK_VENTA_USUARIO_id_usuario` (`USUARIO_id_usuario`),
  KEY `FK_VENTA_SERVICIO_ID` (`SERVICIO_ID`),
  CONSTRAINT `FK_VENTA_CLIENTE_ID` FOREIGN KEY (`CLIENTE_ID`) REFERENCES `persona` (`ID`),
  CONSTRAINT `FK_VENTA_PAQUETE_ID` FOREIGN KEY (`PAQUETE_ID`) REFERENCES `paquete` (`ID`),
  CONSTRAINT `FK_VENTA_SERVICIO_ID` FOREIGN KEY (`SERVICIO_ID`) REFERENCES `servicio` (`ID`),
  CONSTRAINT `FK_VENTA_USUARIO_id_usuario` FOREIGN KEY (`USUARIO_id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (651,'2021-12-20','credito',1,203,NULL,252,1),(701,'2021-12-20','efectivo',2,203,401,NULL,1),(751,'2021-12-20','efectivo',3,203,451,NULL,1);
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-20 23:32:09
