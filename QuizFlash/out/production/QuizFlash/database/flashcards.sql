-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 17, 2023 at 08:25 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flashcards`
--

-- --------------------------------------------------------

--
-- Table structure for table `wiseone`
--

CREATE TABLE `wiseone` (
  `id` int(11) NOT NULL,
  `front` varchar(200) NOT NULL,
  `back` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `wiseone`
--

INSERT INTO `wiseone` (`id`, `front`, `back`) VALUES
(1, 'Heroes', 'they often met despair'),
(2, 'Excessive Fervor', 'It is the fire of self-immolation'),
(3, 'Teacher', 'It is often your strongest enemy'),
(4, 'Love', 'It has a price, it makes people willing to sacrifice'),
(5, 'Fluke Mentality', 'Human nature are complex, they have this thing called, what?'),
(6, 'School', 'This is same thing as a prison, theres a canteen, iron bars and jail called classes'),
(7, 'Sincerity', 'It is something people called because they fear deception, thus hoping people to follow their standards');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `wiseone`
--
ALTER TABLE `wiseone`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `wiseone`
--
ALTER TABLE `wiseone`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
