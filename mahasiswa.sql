-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 24, 2024 at 05:38 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_mahasiswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` int(11) NOT NULL,
  `nim` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jenis_kelamin` varchar(255) NOT NULL,
  `fakultas` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `nim`, `nama`, `jenis_kelamin`, `fakultas`) VALUES
(1, '2203999', 'Amelia Zalfa Julianti', 'Perempuan', 'FPOK'),
(2, '2202292', 'Muhammad Iqbal Fadhilah', 'Laki-laki', 'FIP'),
(3, '2202346', 'Muhammad Rifky Afandi', 'Laki-laki', 'FPTK'),
(4, '2210239', 'Muhammad Hanif Abdillah', 'Laki-laki', 'FPBS'),
(5, '2202046', 'Nurainun', 'Perempuan', 'FPEB'),
(6, '2205101', 'Kelvin Julian Putra', 'Laki-laki', 'FPSD'),
(7, '2200163', 'Rifanny Lysara Annastasya', 'Perempuan', 'FPMIPA'),
(8, '2202869', 'Revana Faliha Salma', 'Perempuan', 'FPIPS'),
(9, '2209489', 'Rakha Dhifiargo Hariadi', 'Laki-laki', 'FPOK'),
(10, '2203142', 'Roshan Syalwan Nurilham', 'Laki-laki', 'FIP'),
(11, '2200311', 'Raden Rahman Ismail', 'Laki-laki', 'FPTK'),
(12, '2200978', 'Ratu Syahirah Khairunnisa', 'Perempuan', 'FPBS'),
(13, '2204509', 'Muhammad Fahreza Fauzan', 'Laki-laki', 'FPEB'),
(14, '2205027', 'Muhammad Rizki Revandi', 'Laki-laki', 'FPEB'),
(15, '2203484', 'Arya Aydin Margono', 'Laki-laki', 'FPTK'),
(16, '2200481', 'Marvel Ravindra Dioputra', 'Laki-laki', 'FPMIPA'),
(17, '2209889', 'Muhammad Fadlul Hafiizh', 'Laki-laki', 'FPIPS'),
(18, '2206697', 'Rifa Sania', 'Perempuan', 'FIP'),
(30, '2207260', 'Imam Chalish Rafidhul Haque', 'Laki-laki', 'FIP'),
(36, '2207308', 'Syifa Azzahra', 'Perempuan', 'FPMIPA');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
