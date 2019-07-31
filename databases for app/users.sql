-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jul 10, 2019 at 05:30 PM
-- Server version: 5.7.25-log
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `users`
--

-- --------------------------------------------------------

--
-- Table structure for table `internet`
--

DROP TABLE IF EXISTS `internet`;
CREATE TABLE IF NOT EXISTS `internet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(40) DEFAULT NULL,
  `prezime` varchar(40) DEFAULT NULL,
  `adresa` varchar(50) DEFAULT NULL,
  `brzina` varchar(30) DEFAULT NULL,
  `protok` varchar(30) DEFAULT NULL,
  `ugovor` int(11) DEFAULT NULL,
  `identifikacioni_broj` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `internet`
--

INSERT INTO `internet` (`id`, `ime`, `prezime`, `adresa`, `brzina`, `protok`, `ugovor`, `identifikacioni_broj`) VALUES
(16, 'petar', 'scekic', 'perts skecka123', '50 MB', '5 GB', 2, 2582),
(17, 'Ana', 'Projac', 'adrisa scekica 3', '50 MB', '10 GB', 2, 3827);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
