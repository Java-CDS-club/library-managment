-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 01, 2022 at 12:07 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestion_bibliotheque`
--

-- --------------------------------------------------------

--
-- Table structure for table `bibliothecaire`
--

CREATE TABLE `bibliothecaire` (
  `biblio_id` int(8) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bibliothecaire`
--

INSERT INTO `bibliothecaire` (`biblio_id`, `nom`, `email`, `mot_de_passe`) VALUES
(1, 'Admin', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `livres`
--

CREATE TABLE `livres` (
  `livre_id` int(8) NOT NULL,
  `emprunteur_id` int(8) DEFAULT NULL,
  `titre` varchar(255) NOT NULL,
  `auteur` varchar(255) NOT NULL,
  `editeur` varchar(255) NOT NULL,
  `ISBN` varchar(255) NOT NULL,
  `emplacement` varchar(255) NOT NULL,
  `date_emprunt` date DEFAULT NULL,
  `date_retour` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `livres`
--

INSERT INTO `livres` (`livre_id`, `emprunteur_id`, `titre`, `auteur`, `editeur`, `ISBN`, `emplacement`, `date_emprunt`, `date_retour`) VALUES
(2, 2, 'React for DEV1', 'ISMAILI Ossama', 'ISMAILI Ossama', 'ASH12', '12-S', '2021-12-29', '2022-01-13'),
(3, 5, 'La Boîte à merveilles ', 'Ahmed Sefrioui', 'Ahmed Sefrioui', 'A12525', '12-A', '2021-12-29', '2022-01-13'),
(4, 5, 'Harry Potter and the Philosopher\'s Stone', 'J. K. Rowling', 'J. K. Rowling', 'A12425', 'S-13', '2021-12-29', '2022-01-13');

-- --------------------------------------------------------

--
-- Table structure for table `usagers`
--

CREATE TABLE `usagers` (
  `usager_id` int(8) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `categorie` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usagers`
--

INSERT INTO `usagers` (`usager_id`, `nom`, `adresse`, `categorie`) VALUES
(2, 'Ossama Ismaili', '133 HAY SALAM', 'Etudiant'),
(5, 'Meryam Arroubi', '152 Narjiss', 'Etudiant');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bibliothecaire`
--
ALTER TABLE `bibliothecaire`
  ADD PRIMARY KEY (`biblio_id`);

--
-- Indexes for table `livres`
--
ALTER TABLE `livres`
  ADD PRIMARY KEY (`livre_id`),
  ADD KEY `emprunteur_id` (`emprunteur_id`);

--
-- Indexes for table `usagers`
--
ALTER TABLE `usagers`
  ADD PRIMARY KEY (`usager_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bibliothecaire`
--
ALTER TABLE `bibliothecaire`
  MODIFY `biblio_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `livres`
--
ALTER TABLE `livres`
  MODIFY `livre_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `usagers`
--
ALTER TABLE `usagers`
  MODIFY `usager_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `livres`
--
ALTER TABLE `livres`
  ADD CONSTRAINT `livres_ibfk_1` FOREIGN KEY (`emprunteur_id`) REFERENCES `usagers` (`usager_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
