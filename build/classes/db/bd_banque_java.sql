-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 27 avr. 2020 à 13:37
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bd_banque_java`
--

-- --------------------------------------------------------

--
-- Structure de la table `affectation_agence`
--

DROP TABLE IF EXISTS `affectation_agence`;
CREATE TABLE IF NOT EXISTS `affectation_agence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agence_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `dateDebut` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateFin` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `affectation_agence`
--

INSERT INTO `affectation_agence` (`id`, `agence_id`, `user_id`, `dateDebut`, `dateFin`) VALUES
(1, 1, 1, '2020-03-23 00:25:06', NULL),
(2, 3, 3, '2020-03-23 00:25:11', NULL),
(3, 1, 1, '2020-03-24 13:41:27', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `affectation_guichet`
--

DROP TABLE IF EXISTS `affectation_guichet`;
CREATE TABLE IF NOT EXISTS `affectation_guichet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agence_id` int(11) NOT NULL,
  `guichet_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `dateDebut` datetime DEFAULT CURRENT_TIMESTAMP,
  `dateFin` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `affectation_guichet`
--

INSERT INTO `affectation_guichet` (`id`, `agence_id`, `guichet_id`, `user_id`, `dateDebut`, `dateFin`) VALUES
(1, 0, 1, 1, '2020-03-07 23:19:52', NULL),
(2, 0, 1, 1, '2020-03-07 23:24:24', NULL),
(3, 3, 3, 3, '2020-03-23 01:11:18', NULL),
(4, 3, 2, 1, '2020-03-23 01:12:48', NULL),
(5, 1, 2, 3, '2020-03-24 13:26:36', NULL),
(6, 1, 2, 3, '2020-03-24 13:29:01', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

DROP TABLE IF EXISTS `agence`;
CREATE TABLE IF NOT EXISTS `agence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(200) NOT NULL,
  `telephone` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `agence`
--

INSERT INTO `agence` (`id`, `libelle`, `telephone`, `email`, `adresse`, `createdAt`) VALUES
(1, 'Ponty', '3344446446', 'einsarr@gmail.com', 'Dakar centre', '2020-03-07 16:23:27'),
(2, 'Ponty', '3344446446', 'einsarr@gmail.com', 'Dakar centre', '2020-03-07 16:57:03'),
(3, 'Agence marché keur massar', '7774355355', 'canfndeye@gmail.com', 'Keur massar', '2020-03-22 23:19:24');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  `cni` varchar(200) NOT NULL,
  `telephone` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`idClient`, `user_id`, `adresse`, `cni`, `telephone`, `email`) VALUES
(1, 1, 'ffggfgg', 'gffhhhjghgj', 'bhhggh', 'rjjgg@ggghg'),
(2, 2, 'Keur masse', '15902003002837', '7774374440', 'einsarr@gmail.com'),
(3, 1, 'ffggfgg', 'gffhhhjghgj', 'bhhggh', 'rjjgg@ggghg'),
(4, 2, 'ffggfgg', 'gffhhhjghgj', 'bhhggh', 'rjjgg@ggghg'),
(5, 2, 'ffggfgg', 'gffhhhjghgj', 'bhhggh', 'rjjgg@ggghg');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `numero` varchar(200) NOT NULL,
  `solde` float NOT NULL DEFAULT '0',
  `etat` varchar(10) NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id`, `client_id`, `numero`, `solde`, `etat`, `createdAt`) VALUES
(1, 1, 'NDCghgj2', -4200, 'Inactif', '2020-03-07 22:25:29'),
(2, 1, 'NDCghgj8', 609200, 'Inactif', '2020-03-07 22:29:59'),
(3, 1, 'NDCghgj4', 2000, 'actif', '2020-03-07 22:30:46'),
(6, 2, 'NDC28377', 0, '', '2020-03-07 22:42:00'),
(5, 1, 'NDCghgj3', 0, '', '2020-03-07 22:38:29');

-- --------------------------------------------------------

--
-- Structure de la table `comptecheck`
--

DROP TABLE IF EXISTS `comptecheck`;
CREATE TABLE IF NOT EXISTS `comptecheck` (
  `id_cpt_ch` int(11) NOT NULL AUTO_INCREMENT,
  `compte_id` int(11) NOT NULL,
  `frais` int(11) NOT NULL,
  PRIMARY KEY (`id_cpt_ch`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `comptecheck`
--

INSERT INTO `comptecheck` (`id_cpt_ch`, `compte_id`, `frais`) VALUES
(1, 5, 6464665),
(2, 6, 6464665),
(3, 0, 20000),
(4, 0, 21000),
(5, 0, 21000);

-- --------------------------------------------------------

--
-- Structure de la table `compteepargne`
--

DROP TABLE IF EXISTS `compteepargne`;
CREATE TABLE IF NOT EXISTS `compteepargne` (
  `id_cpt_ep` int(11) NOT NULL AUTO_INCREMENT,
  `compte_id` int(11) NOT NULL,
  `taux` float NOT NULL,
  `dateDebut` datetime DEFAULT NULL,
  `dateFin` date DEFAULT NULL,
  PRIMARY KEY (`id_cpt_ep`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `compteepargne`
--

INSERT INTO `compteepargne` (`id_cpt_ep`, `compte_id`, `taux`, `dateDebut`, `dateFin`) VALUES
(1, 1, 3.3, NULL, NULL),
(2, 2, 3.3, NULL, NULL),
(3, 3, 3.3, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `guichet`
--

DROP TABLE IF EXISTS `guichet`;
CREATE TABLE IF NOT EXISTS `guichet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agence_id` int(11) DEFAULT NULL,
  `numero` int(11) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `guichet`
--

INSERT INTO `guichet` (`id`, `agence_id`, `numero`, `description`) VALUES
(1, 1, 12, 'Caisse normal'),
(2, 2, 12, 'GROS VERSEMENTS'),
(3, 3, 11, 'CAISSE');

-- --------------------------------------------------------

--
-- Structure de la table `profil`
--

DROP TABLE IF EXISTS `profil`;
CREATE TABLE IF NOT EXISTS `profil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `profil`
--

INSERT INTO `profil` (`id`, `libelle`, `description`) VALUES
(1, 'Super User', 'Concepteur'),
(2, 'Admin Système', 'Administrateur du système'),
(4, 'Caissier', 'Chargé de caisse'),
(3, 'Responsable d\'agence', 'Directeur de l\'agence'),
(5, 'Client', 'Client de la banque'),
(33, 'Admin', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE IF NOT EXISTS `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guichet_id` int(11) NOT NULL,
  `compte_id` int(11) NOT NULL,
  `montant` int(11) NOT NULL,
  `type` varchar(200) NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `transaction`
--

INSERT INTO `transaction` (`id`, `guichet_id`, `compte_id`, `montant`, `type`, `createdAt`) VALUES
(1, 1, 1, 20000, 'Depot', '2020-04-24 16:10:51'),
(2, 1, 2, 10000, 'Retrait', '2020-04-24 16:11:10'),
(3, 1, 1, 10000, 'Virement', '2020-04-24 16:11:34'),
(4, 1, 2, 10000, 'Virement', '2020-04-24 16:11:34'),
(5, 1, 1, 35000, 'virement-', '2020-04-24 16:14:40'),
(6, 1, 2, 35000, 'virement+', '2020-04-24 16:14:40');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agence_id` int(11) NOT NULL,
  `profil_id` int(11) NOT NULL,
  `guichet_id` int(11) DEFAULT NULL,
  `nom` varchar(200) NOT NULL,
  `prenom` varchar(200) NOT NULL,
  `login` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `etat` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `agence_id`, `profil_id`, `guichet_id`, `nom`, `prenom`, `login`, `password`, `etat`) VALUES
(1, 1, 1, 1, 'CANFANY', 'NDEYE', 'canfndeye', '67b602ef143753bc15af26cd0d8e0bfed70e5d33', 'actif'),
(2, 3, 3, 1, 'SARR', 'Moussa', 'sarr', 'ded24c291b86cfeb2417a04a5253289244808935', 'Actif');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
