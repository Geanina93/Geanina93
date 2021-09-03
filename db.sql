CREATE DATABASE IF NOT EXISTS `banking_demo` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `banking_demo`;


-- Dumping structure for table banking_demo.test_accounts
CREATE TABLE IF NOT EXISTS `test_accounts` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `sum` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
