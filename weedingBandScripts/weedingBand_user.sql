CREATE DATABASE  IF NOT EXISTS `weeding_band`;
USE `weeding_band`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `user`
--

INSERT INTO `user` VALUES 
	(1,'Leslie','Andrews'),
	(2,'Emma','Baumgarten'),
	(3,'Avani','Gupta'),
	(4,'Yuri','Petrov'),
	(5,'Juan','Vega');

