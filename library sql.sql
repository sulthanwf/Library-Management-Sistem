-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 27, 2020 at 07:33 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `BID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `BNAME` varchar(50) NOT NULL,
  `GENRE` varchar(20) DEFAULT NULL,
  `AUTHOR` varchar(40) NOT NULL,
  `BORROW` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`BID`, `BNAME`, `GENRE`, `AUTHOR`, `BORROW`) VALUES
(1, 'Anne of Green Gables', 'Fiction', 'Lucy Maud Montgomery', 0);

-- --------------------------------------------------------

--
-- Table structure for table `issued`
--

CREATE TABLE `issued` (
  `IID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `UID` int(11) DEFAULT NULL,
  `BID` int(11) DEFAULT NULL,
  `ISSUED_DATE` varchar(20) DEFAULT NULL,
  `RETURN_DATE` varchar(20) DEFAULT NULL,
  `PERIOD` int(11) DEFAULT NULL,
  `FINE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `USERNAME` varchar(30) DEFAULT NULL,
  `PASSWORD` varchar(30) DEFAULT NULL,
  `ADDRESS` varchar(40) DEFAULT NULL,
  `PHONE` varchar(15) DEFAULT NULL,
  `ADMIN` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UID`, `USERNAME`, `PASSWORD`, `ADDRESS`, `PHONE`, `ADMIN`) VALUES
(1, 'admin', 'admin', NULL, '0243596659', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `issued`
--
ALTER TABLE `issued`
  ADD PRIMARY KEY (`IID`),
  ADD KEY `BID` (`BID`),
  ADD KEY `UID` (`UID`);
--
-- Constraints for table `issued`
--
ALTER TABLE `issued`
  ADD CONSTRAINT `issued_ibfk_1` FOREIGN KEY (`BID`) REFERENCES `books` (`BID`),
  ADD CONSTRAINT `issued_ibfk_2` FOREIGN KEY (`UID`) REFERENCES `users` (`UID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;