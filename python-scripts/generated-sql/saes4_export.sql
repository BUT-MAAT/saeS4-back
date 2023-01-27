-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : mysql
-- Généré le : ven. 27 jan. 2023 à 14:39
-- Version du serveur : 10.4.13-MariaDB-1:10.4.13+maria~focal
-- Version de PHP : 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `saes4`
--

-- --------------------------------------------------------

--
-- Structure de la table `CATEGORIE`
--

CREATE TABLE `CATEGORIE` (
  `IdCategorie` int(11) NOT NULL,
  `NomCategorie` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `CATEGORIE`
--

INSERT INTO `CATEGORIE` (`IdCategorie`, `NomCategorie`) VALUES
(1, 'entr?es et plats compos?s'),
(2, 'fruits, l?gumes, l?gumineuses '),
(3, 'p?tes diverses, produits c?r?a'),
(4, 'viandes, ?ufs, poissons et ass'),
(5, 'produits laitiers et assimil?s'),
(6, 'eaux et autres boissons'),
(7, 'produits sucr?s'),
(8, 'glaces et sorbets'),
(9, 'mati?res grasses'),
(10, 'aides culinaires et ingr?dient'),
(11, 'aliments infantiles');

-- --------------------------------------------------------

--
-- Structure de la table `SOUSCATEGORIE`
--

CREATE TABLE `SOUSCATEGORIE` (
  `IdSousCategorie` int(11) NOT NULL,
  `IdCategorie` int(11) DEFAULT NULL,
  `NomSousCategorie` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `SOUSCATEGORIE`
--

INSERT INTO `SOUSCATEGORIE` (`IdSousCategorie`, `IdCategorie`, `NomSousCategorie`) VALUES
(101, 1, 'salades compos?es et crudit?s'),
(102, 1, 'soupes'),
(103, 1, 'plats compos?s'),
(104, 1, 'pizzas, tartes et cr?pes sal?e'),
(105, 1, 'sandwichs'),
(106, 1, 'feuillet?es et autres entr?es'),
(201, 2, 'l?gumes'),
(202, 2, 'pommes de terre et autres tube'),
(203, 2, 'l?gumineuses'),
(204, 2, 'fruits'),
(205, 2, 'fruits ? coque et graines ol?a'),
(301, 3, 'p?tes, riz et c?r?ales'),
(302, 3, 'pains et assimil?s'),
(303, 3, 'biscuits ap?ritifs'),
(305, 3, 'pates et farines'),
(401, 4, 'viandes cuites'),
(402, 4, 'viandes crues'),
(403, 4, 'charcuteries et assimil?s'),
(404, 4, 'autres produits ? base de vian'),
(405, 4, 'poissons cuits'),
(406, 4, 'poissons crus'),
(407, 4, 'mollusques et crustac?s cuits'),
(408, 4, 'mollusques et crustac?s crus'),
(409, 4, 'produits ? base de poissons et'),
(410, 4, '?ufs'),
(411, 4, 'substitus de produits carn?s'),
(501, 5, 'laits'),
(502, 5, 'produits laitiers frais et ass'),
(503, 5, 'fromages et assimil?s'),
(504, 5, 'cr?mes et sp?cialit?s ? base d'),
(601, 6, 'eaux'),
(602, 6, 'boissons sans alcool'),
(603, 6, 'boisson alcoolis?es'),
(701, 7, 'sucres, miels et assimil?s'),
(702, 7, 'chocolats et produits ? base d'),
(703, 7, 'confiseries non chocolat?es'),
(704, 7, 'confitures et assimil?s'),
(705, 7, 'viennoiseries'),
(706, 7, 'biscuits sucr?s'),
(707, 7, 'c?r?ales de petit-d?jeuner'),
(708, 7, 'barres c?r?ali?res'),
(709, 7, 'g?teaux et p?tisseries'),
(801, 8, 'glaces'),
(802, 8, 'sorbets'),
(803, 8, 'desserts glac?s'),
(901, 9, 'beurres'),
(902, 9, 'huiles et graisses v?g?tales'),
(903, 9, 'margarines'),
(904, 9, 'huiles de poissons'),
(905, 9, 'autres mati?res grasses'),
(1001, 10, 'sauces'),
(1002, 10, 'condiments'),
(1003, 10, 'aides culinaires'),
(1004, 10, 'sels'),
(1005, 10, '?pices'),
(1006, 10, 'herbes'),
(1007, 10, 'algues'),
(1008, 10, 'denr?es destin?es ? une alimen'),
(1010, 10, 'ingr?dients divers'),
(1101, 11, 'laits et boissons infantiles'),
(1102, 11, 'petits pots sal?s et plats inf'),
(1103, 11, 'desserts infantiles'),
(1104, 11, 'c?r?ales et biscuits infantile');

-- --------------------------------------------------------

--
-- Structure de la table `SOUSSOUSCATEGORIE`
--

CREATE TABLE `SOUSSOUSCATEGORIE` (
  `IdSousSousCategorie` int(11) NOT NULL,
  `IdSousCategorie` int(11) DEFAULT NULL,
  `NomSousSousCategorie` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `SOUSSOUSCATEGORIE`
--

INSERT INTO `SOUSSOUSCATEGORIE` (`IdSousSousCategorie`, `IdSousCategorie`, `NomSousSousCategorie`) VALUES
(10301, 103, 'plats de viande sans garniture'),
(10302, 103, 'plats de viande et f?culents'),
(10303, 103, 'plats de viande et l?gumes/l?g'),
(10304, 103, 'plats de poisson sans garnitur'),
(10305, 103, 'plats de poisson et f?culents'),
(10306, 103, 'plats de l?gumes/l?gumineuses'),
(10307, 103, 'plats de c?r?ales/p?tes'),
(10308, 103, 'plats de fromage'),
(10309, 103, 'plats v?g?tariens'),
(10600, 106, 'Non sp?cialisable'),
(20101, 201, 'l?gumes crus'),
(20102, 201, 'l?gumes cuits'),
(20103, 201, 'l?gumes s?ch?s ou d?shydrat?s'),
(20104, 201, 'l?gumes et leurs produits de l'),
(20105, 201, 'l?gumes et leurs produits de l'),
(20301, 203, 'l?gumineuses cuites'),
(20302, 203, 'l?gumineuses fra?ches'),
(20303, 203, 'l?gumineuses s?ches'),
(20401, 204, 'fruits crus'),
(20402, 204, 'compotes et assimil?s'),
(20403, 204, 'fruits appertis?s'),
(20404, 204, 'fruits s?ch?s'),
(20405, 204, 'fruits et leurs produits de la'),
(20406, 204, 'fruits et leurs produits de la'),
(30101, 301, 'p?tes, riz et c?r?ales cuits'),
(30102, 301, 'p?tes, riz et c?r?ales crus'),
(30201, 302, 'pains'),
(30202, 302, 'biscottes et pains grill?s'),
(30501, 305, 'farines'),
(30502, 305, 'p?tes'),
(40101, 401, 'b?uf et veau halal'),
(40102, 401, 'porc'),
(40103, 402, 'poulet halal'),
(40104, 402, 'dinde casher'),
(40105, 401, 'agneau et mouton casher'),
(40106, 401, 'gibier casher'),
(40107, 402, 'autres viandes'),
(40108, 401, 'abats'),
(40201, 402, 'b?uf et veau'),
(40202, 402, 'porc cru'),
(40203, 402, 'poulet'),
(40204, 402, 'dinde'),
(40205, 402, 'agneau et mouton casher'),
(40206, 402, 'gibier'),
(40207, 402, 'autres viandes casher'),
(40208, 402, 'abats'),
(40301, 403, 'jambons cuits'),
(40302, 403, 'jambons secs et crus'),
(40303, 403, 'saucisson secs'),
(40304, 403, 'saucisses et assimil?s'),
(40305, 403, 'p?t?s et terrines'),
(40306, 403, 'rillettes'),
(40307, 403, 'quenelles'),
(40308, 403, 'autres sp?cialit?s charcuti?re'),
(40309, 403, 'substituts de charcuteries pou'),
(41001, 410, '?ufs cuits'),
(41002, 410, '?ufs crus'),
(41003, 410, 'omelettes et autres ovoproduit'),
(50101, 501, 'laits de vaches liquides (non '),
(50102, 501, 'laits autres que de vache'),
(50103, 501, 'laits de vache concentr?s ou e'),
(50201, 502, 'yaourts et sp?cialit?s laiti?r'),
(50202, 502, 'fromages blancs'),
(50203, 502, 'desserts lact?s'),
(50204, 502, 'autres desserts'),
(50205, 502, 'desserts v?g?taux'),
(50301, 503, 'fromages ? p?te molle'),
(50302, 503, 'fromages ? p?te persill?e'),
(50303, 503, 'fromages ? p?te press?e'),
(50304, 503, 'fromage fondus'),
(50305, 503, 'autres fromages et sp?cialit?s'),
(50306, 503, 'substituts de fromages pour v?'),
(60201, 602, 'jus'),
(60202, 602, 'nectars'),
(60203, 602, 'boissons rafra?chissantes sans'),
(60204, 602, 'boissons rafra?chissantes lact'),
(60205, 602, 'boissons v?g?tales'),
(60206, 602, 'caf?, th?, cacao etc. pr?ts ? '),
(60207, 602, 'boissons ? reconstituer'),
(60301, 603, 'vins'),
(60302, 603, 'bi?res et cidres'),
(60303, 603, 'liqueurs et alcools'),
(60304, 603, 'cocktails'),
(100101, 1001, 'sauces condimentaires'),
(100102, 1001, 'sauces chaudes'),
(100103, 1001, 'sauces sucr?es'),
(100601, 1006, 'herbes fra?ches'),
(100602, 1006, 'herbes s?ch?es');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `CATEGORIE`
--
ALTER TABLE `CATEGORIE`
  ADD PRIMARY KEY (`IdCategorie`);

--
-- Index pour la table `SOUSCATEGORIE`
--
ALTER TABLE `SOUSCATEGORIE`
  ADD PRIMARY KEY (`IdSousCategorie`),
  ADD KEY `FK_SousCategorie_Categorie` (`IdCategorie`);

--
-- Index pour la table `SOUSSOUSCATEGORIE`
--
ALTER TABLE `SOUSSOUSCATEGORIE`
  ADD PRIMARY KEY (`IdSousSousCategorie`),
  ADD KEY `FK_SousSousCategorie_SousCategorie` (`IdSousCategorie`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `SOUSCATEGORIE`
--
ALTER TABLE `SOUSCATEGORIE`
  ADD CONSTRAINT `FK_SousCategorie_Categorie` FOREIGN KEY (`IdCategorie`) REFERENCES `CATEGORIE` (`IdCategorie`);

--
-- Contraintes pour la table `SOUSSOUSCATEGORIE`
--
ALTER TABLE `SOUSSOUSCATEGORIE`
  ADD CONSTRAINT `FK_SousSousCategorie_SousCategorie` FOREIGN KEY (`IdSousCategorie`) REFERENCES `SOUSCATEGORIE` (`IdSousCategorie`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
