-- CREATE DATABASE `rss_joseba` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE rss_joseba;

CREATE TABLE `news` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(500) NOT NULL,
  `description` varchar(20000) NOT NULL,
  `uri` varchar(200) NOT NULL,
  `publication_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

INSERT INTO news (title, description, uri, publication_date) 
	VALUES ('Prueba', 'This is the description for prueba', 'www.google.com', now());