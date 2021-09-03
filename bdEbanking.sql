-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Gazda: localhost
-- Timp de generare: 20 Apr 2015 la 16:47
-- Versiune server: 5.1.41
-- Versiune PHP: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza de date: `ebanking`
--

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `clienti`
--

CREATE TABLE IF NOT EXISTS `clienti` (
  `Id` int(11) NOT NULL,
  `Nume` varchar(50) DEFAULT '0',
  `Prenume` varchar(50) DEFAULT '0',
  `UserName` varchar(50) DEFAULT '0',
  `Parola` varchar(50) DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `clienti`
--

INSERT INTO `clienti` (`Id`, `Nume`, `Prenume`, `UserName`, `Parola`) VALUES
(0, 'Bobariu', 'Geanina', 'geany93', 'pass'),
(1, 'Barz', 'Mihai', 'mihai89', 'passBarz'),
(2, 'Hlihor', 'Diana', 'dianah', 'pass'),
(3, 'Buiga', 'Diana', 'dianb', 'pass'),
(4, 'Barz', 'Nicolae', 'nicob', 'pass');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `conturi`
--

CREATE TABLE IF NOT EXISTS `conturi` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NrCont` int(30) unsigned NOT NULL DEFAULT '0',
  `Banca` varchar(50) NOT NULL DEFAULT '0',
  `UserID` int(11) NOT NULL DEFAULT '0',
  `Suma` double NOT NULL DEFAULT '0',
  `Stare` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Salvarea datelor din tabel `conturi`
--

INSERT INTO `conturi` (`ID`, `NrCont`, `Banca`, `UserID`, `Suma`, `Stare`) VALUES
(1, 1234567890, 'Banca Transilvania', 0, 200, 1),
(2, 987654321, 'Banca Comerciala Romana', 0, 215, 1),
(3, 123457, 'Banca Transilvania', 1, 500, 1);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `tranzactii`
--

CREATE TABLE IF NOT EXISTS `tranzactii` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `DataOperatiei` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `IdContSursa` int(30) NOT NULL DEFAULT '0',
  `SoldfinalContSursa` double NOT NULL DEFAULT '0',
  `IdContDestinatie` int(30) NOT NULL DEFAULT '0',
  `SoldFinalContDestinatie` double NOT NULL DEFAULT '0',
  `SumaTranzactie` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Salvarea datelor din tabel `tranzactii`
--

INSERT INTO `tranzactii` (`Id`, `DataOperatiei`, `IdContSursa`, `SoldfinalContSursa`, `IdContDestinatie`, `SoldFinalContDestinatie`, `SumaTranzactie`) VALUES
(1, '2015-03-17 00:00:00', 0, 0, 0, 0, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
