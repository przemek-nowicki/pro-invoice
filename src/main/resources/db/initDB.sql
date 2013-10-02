DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_role` int(11) NOT NULL,
  `email` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `enabled` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `FK_ID_ROLE` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `roles` VALUES (1,'ADMIN_ROLE'),(5,'USER_ROLE');
INSERT INTO `users` VALUES (1,1,'admin@make-it-with.us','admin','90f1f859a97c0dfb367ee9a2fd119ab37b6daa666f8db4c4e4292aac8945e45b',1),(2,5,'user@make-it-with.us','user','8142bcc21e40c8bf8cc0bbc07c1263180ee26451e0825e4be135e9aa6e8130dd',1);