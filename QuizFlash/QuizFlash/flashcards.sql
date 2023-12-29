-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 19, 2023 at 04:21 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

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
-- Table structure for table `lesson_1`
--

CREATE TABLE `lesson_1` (
  `id` int(11) NOT NULL,
  `front` varchar(50) NOT NULL,
  `back` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lesson_1`
--

INSERT INTO `lesson_1` (`id`, `front`, `back`) VALUES
(1, 'Object Oriented Programming', 'A programming model that follows the concept of objects.'),
(2, 'Procedural Programming', 'A programming model derived from structural programming. Follows the concept of calling procedure.'),
(3, 'Variable', 'Data containers that save the data values during Java program execution.'),
(4, 'Data Type', 'Determines the values a variable may contain.'),
(5, 'Attributes', 'Variables that belong to an object and is also called as \"fields\".'),
(6, 'Class', 'Is a template for objects.'),
(7, 'Object', 'Is an instance of a class.'),
(8, 'Constructor', 'A block of code that initializes a newly created object.'),
(9, 'Method', 'A collection of statements which returns a value upon its execution.'),
(10, 'Construtor Overloading', 'When a class has more than one constructors.'),
(11, 'Instance Variable', 'A class property that can be different for each object.'),
(12, 'Static Variable', 'A variable that belongs to the class itself rather than to any specific instance of the class.'),
(13, 'Access Modifiers', 'Used to set the accessibility of classes, constructors, methods, and other members of Java.'),
(14, 'Private', 'It can only be accessed within the same class and not from outside the class.'),
(15, 'Default', 'It can only be accessed within the same package and not from outside the package.'),
(16, 'Protected', 'It can be accessed within the same package and also from outside the package with the help of the child class.'),
(17, 'Public', 'It can be accessed from anywhere.'),
(18, 'Encapsulation', 'Prevents outer classes from accessing and changing the fields and methods of a class.'),
(19, 'Data Hiding', 'The process which hides the internal data from you.'),
(20, 'Data Abstraction', 'The process where certain internal implementation of particular set of services are hidden from you.'),
(21, 'Inheritance', 'Allows the child class to inherit the features of its parent class.'),
(22, 'Polymorphism', 'Allows us to define one interface with multiple implementations.'),
(23, 'Many', 'Poly means ___.'),
(24, 'Form or Shapes', 'Morphism means ____.'),
(25, 'C', 'The following are OOP languages except for one:\nJava, JavaScript, C, C#, C++');

-- --------------------------------------------------------

--
-- Table structure for table `wiseone`
--

CREATE TABLE `wiseone` (
  `id` int(11) NOT NULL,
  `front` varchar(200) NOT NULL,
  `back` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Indexes for table `lesson_1`
--
ALTER TABLE `lesson_1`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `wiseone`
--
ALTER TABLE `wiseone`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `lesson_1`
--
ALTER TABLE `lesson_1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `wiseone`
--
ALTER TABLE `wiseone`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
