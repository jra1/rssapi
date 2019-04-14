CREATE DATABASE `rss_joseba` /*!40100 DEFAULT CHARACTER SET utf8 */;

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

CREATE  INDEX uriIdx ON feed(uri);
