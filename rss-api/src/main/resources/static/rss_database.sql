-- CREATE DATABASE `rss_joseba` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE rss_joseba;

CREATE TABLE `feed` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(500) NOT NULL,
  `description` varchar(20000) NOT NULL,
  `uri` varchar(200) NOT NULL UNIQUE,
  `image` varchar(200),
  `published_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

INSERT INTO feed (title, description, uri, image, published_date) 
	VALUES ('Prueba', 'This is the description for prueba', 'www.google.com', 'https://nos.nl/data/image/2019/04/11/542976/1008x567.jpg', now());