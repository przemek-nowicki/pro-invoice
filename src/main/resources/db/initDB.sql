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


CREATE TABLE `pages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `metaDescription` varchar(255) DEFAULT NULL,
  `metaKeywords` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `body` text,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `url_UNIQUE` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `countries` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `iso2` char(2) DEFAULT NULL,
  `short_name` varchar(80) NOT NULL DEFAULT '',
  `long_name` varchar(80) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=251 DEFAULT CHARSET=utf8;


CREATE TABLE `invoices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) NOT NULL,
  `no` varchar(255) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `currency_symbol` varchar(25) DEFAULT NULL,
  `currency_code` varchar(5) DEFAULT NULL,
  `payment` text,
  `note` text,
  PRIMARY KEY (`id`),
  KEY `fk_client_id_idx` (`id_client`),
  CONSTRAINT `fk_client_id` FOREIGN KEY (`id_client`) REFERENCES `clients` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text,
  `amount` decimal(10,0) DEFAULT '0',
  `tax` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;


CREATE TABLE `invoice_item` (
  `id_invoice` int(11) NOT NULL,
  `id_item` int(11) NOT NULL,
  `position` int(11) DEFAULT '0',
  KEY `fk_invoice_id_idx` (`id_invoice`),
  KEY `fk_item_id_idx` (`id_item`),
  CONSTRAINT `fk_invoice_id` FOREIGN KEY (`id_invoice`) REFERENCES `invoices` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_item_id` FOREIGN KEY (`id_item`) REFERENCES `items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `roles` VALUES (1,'ADMIN_ROLE'),(5,'USER_ROLE');
INSERT INTO `users` VALUES (1,1,'admin@make-it-with.us','admin','90f1f859a97c0dfb367ee9a2fd119ab37b6daa666f8db4c4e4292aac8945e45b',1),(2,5,'user@make-it-with.us','user','8142bcc21e40c8bf8cc0bbc07c1263180ee26451e0825e4be135e9aa6e8130dd',1);
INSERT INTO `pages` VALUES (1,'Welcome in description section','keyword 1, keyword2, keyword 3','Page Welcome','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus vitae velit accumsan, dapibus eros non, ultricies magna. Phasellus convallis est ac fringilla tincidunt. Vivamus et diam vitae sem tempus pretium. Vivamus venenatis adipiscing lorem ut pharetra. Vestibulum eleifend ultricies leo, a dignissim justo lacinia eu. ','page-welcome'),(4,'about','about','About','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus vitae velit accumsan, dapibus eros non, ultricies magna. Phasellus convallis est ac fringilla tincidunt. Vivamus et diam vitae sem tempus pretium. Vivamus venenatis adipiscing lorem ut pharetra. Vestibulum eleifend ultricies leo, a dignissim justo lacinia eu. ','about');
INSERT INTO `countries` VALUES (1,'AF','Afghanistan','Islamic Republic of Afghanistan'),(2,'AX','Aland Islands','&Aring;land Islands'),(3,'AL','Albania','Republic of Albania'),(4,'DZ','Algeria','People\'s Democratic Republic of Algeria'),(5,'AS','American Samoa','American Samoa'),(6,'AD','Andorra','Principality of Andorra'),(7,'AO','Angola','Republic of Angola'),(8,'AI','Anguilla','Anguilla'),(9,'AQ','Antarctica','Antarctica'),(10,'AG','Antigua and Barbuda','Antigua and Barbuda'),(11,'AR','Argentina','Argentine Republic'),(12,'AM','Armenia','Republic of Armenia'),(13,'AW','Aruba','Aruba'),(14,'AU','Australia','Commonwealth of Australia'),(15,'AT','Austria','Republic of Austria'),(16,'AZ','Azerbaijan','Republic of Azerbaijan'),(17,'BS','Bahamas','Commonwealth of The Bahamas'),(18,'BH','Bahrain','Kingdom of Bahrain'),(19,'BD','Bangladesh','People\'s Republic of Bangladesh'),(20,'BB','Barbados','Barbados'),(21,'BY','Belarus','Republic of Belarus'),(22,'BE','Belgium','Kingdom of Belgium'),(23,'BZ','Belize','Belize'),(24,'BJ','Benin','Republic of Benin'),(25,'BM','Bermuda','Bermuda Islands'),(26,'BT','Bhutan','Kingdom of Bhutan'),(27,'BO','Bolivia','Plurinational State of Bolivia'),(28,'BQ','Bonaire, Sint Eustatius and Saba','Bonaire, Sint Eustatius and Saba'),(29,'BA','Bosnia and Herzegovina','Bosnia and Herzegovina'),(30,'BW','Botswana','Republic of Botswana'),(31,'BV','Bouvet Island','Bouvet Island'),(32,'BR','Brazil','Federative Republic of Brazil'),(33,'IO','British Indian Ocean Territory','British Indian Ocean Territory'),(34,'BN','Brunei','Brunei Darussalam'),(35,'BG','Bulgaria','Republic of Bulgaria'),(36,'BF','Burkina Faso','Burkina Faso'),(37,'BI','Burundi','Republic of Burundi'),(38,'KH','Cambodia','Kingdom of Cambodia'),(39,'CM','Cameroon','Republic of Cameroon'),(40,'CA','Canada','Canada'),(41,'CV','Cape Verde','Republic of Cape Verde'),(42,'KY','Cayman Islands','The Cayman Islands'),(43,'CF','Central African Republic','Central African Republic'),(44,'TD','Chad','Republic of Chad'),(45,'CL','Chile','Republic of Chile'),(46,'CN','China','People\'s Republic of China'),(47,'CX','Christmas Island','Christmas Island'),(48,'CC','Cocos (Keeling) Islands','Cocos (Keeling) Islands'),(49,'CO','Colombia','Republic of Colombia'),(50,'KM','Comoros','Union of the Comoros'),(51,'CG','Congo','Republic of the Congo'),(52,'CK','Cook Islands','Cook Islands'),(53,'CR','Costa Rica','Republic of Costa Rica'),(54,'CI','Cote d\'ivoire (Ivory Coast)','Republic of C&ocirc;te D\'Ivoire (Ivory Coast)'),(55,'HR','Croatia','Republic of Croatia'),(56,'CU','Cuba','Republic of Cuba'),(57,'CW','Curacao','Cura&ccedil;ao'),(58,'CY','Cyprus','Republic of Cyprus'),(59,'CZ','Czech Republic','Czech Republic'),(60,'CD','Democratic Republic of the Congo','Democratic Republic of the Congo'),(61,'DK','Denmark','Kingdom of Denmark'),(62,'DJ','Djibouti','Republic of Djibouti'),(63,'DM','Dominica','Commonwealth of Dominica'),(64,'DO','Dominican Republic','Dominican Republic'),(65,'EC','Ecuador','Republic of Ecuador'),(66,'EG','Egypt','Arab Republic of Egypt'),(67,'SV','El Salvador','Republic of El Salvador'),(68,'GQ','Equatorial Guinea','Republic of Equatorial Guinea'),(69,'ER','Eritrea','State of Eritrea'),(70,'EE','Estonia','Republic of Estonia'),(71,'ET','Ethiopia','Federal Democratic Republic of Ethiopia'),(72,'FK','Falkland Islands (Malvinas)','The Falkland Islands (Malvinas)'),(73,'FO','Faroe Islands','The Faroe Islands'),(74,'FJ','Fiji','Republic of Fiji'),(75,'FI','Finland','Republic of Finland'),(76,'FR','France','French Republic'),(77,'GF','French Guiana','French Guiana'),(78,'PF','French Polynesia','French Polynesia'),(79,'TF','French Southern Territories','French Southern Territories'),(80,'GA','Gabon','Gabonese Republic'),(81,'GM','Gambia','Republic of The Gambia'),(82,'GE','Georgia','Georgia'),(83,'DE','Germany','Federal Republic of Germany'),(84,'GH','Ghana','Republic of Ghana'),(85,'GI','Gibraltar','Gibraltar'),(86,'GR','Greece','Hellenic Republic'),(87,'GL','Greenland','Greenland'),(88,'GD','Grenada','Grenada'),(89,'GP','Guadaloupe','Guadeloupe'),(90,'GU','Guam','Guam'),(91,'GT','Guatemala','Republic of Guatemala'),(92,'GG','Guernsey','Guernsey'),(93,'GN','Guinea','Republic of Guinea'),(94,'GW','Guinea-Bissau','Republic of Guinea-Bissau'),(95,'GY','Guyana','Co-operative Republic of Guyana'),(96,'HT','Haiti','Republic of Haiti'),(97,'HM','Heard Island and McDonald Islands','Heard Island and McDonald Islands'),(98,'HN','Honduras','Republic of Honduras'),(99,'HK','Hong Kong','Hong Kong'),(100,'HU','Hungary','Hungary'),(101,'IS','Iceland','Republic of Iceland'),(102,'IN','India','Republic of India'),(103,'ID','Indonesia','Republic of Indonesia'),(104,'IR','Iran','Islamic Republic of Iran'),(105,'IQ','Iraq','Republic of Iraq'),(106,'IE','Ireland','Ireland'),(107,'IM','Isle of Man','Isle of Man'),(108,'IL','Israel','State of Israel'),(109,'IT','Italy','Italian Republic'),(110,'JM','Jamaica','Jamaica'),(111,'JP','Japan','Japan'),(112,'JE','Jersey','The Bailiwick of Jersey'),(113,'JO','Jordan','Hashemite Kingdom of Jordan'),(114,'KZ','Kazakhstan','Republic of Kazakhstan'),(115,'KE','Kenya','Republic of Kenya'),(116,'KI','Kiribati','Republic of Kiribati'),(117,'XK','Kosovo','Republic of Kosovo'),(118,'KW','Kuwait','State of Kuwait'),(119,'KG','Kyrgyzstan','Kyrgyz Republic'),(120,'LA','Laos','Lao People\'s Democratic Republic'),(121,'LV','Latvia','Republic of Latvia'),(122,'LB','Lebanon','Republic of Lebanon'),(123,'LS','Lesotho','Kingdom of Lesotho'),(124,'LR','Liberia','Republic of Liberia'),(125,'LY','Libya','Libya'),(126,'LI','Liechtenstein','Principality of Liechtenstein'),(127,'LT','Lithuania','Republic of Lithuania'),(128,'LU','Luxembourg','Grand Duchy of Luxembourg'),(129,'MO','Macao','The Macao Special Administrative Region'),(130,'MK','Macedonia','The Former Yugoslav Republic of Macedonia'),(131,'MG','Madagascar','Republic of Madagascar'),(132,'MW','Malawi','Republic of Malawi'),(133,'MY','Malaysia','Malaysia'),(134,'MV','Maldives','Republic of Maldives'),(135,'ML','Mali','Republic of Mali'),(136,'MT','Malta','Republic of Malta'),(137,'MH','Marshall Islands','Republic of the Marshall Islands'),(138,'MQ','Martinique','Martinique'),(139,'MR','Mauritania','Islamic Republic of Mauritania'),(140,'MU','Mauritius','Republic of Mauritius'),(141,'YT','Mayotte','Mayotte'),(142,'MX','Mexico','United Mexican States'),(143,'FM','Micronesia','Federated States of Micronesia'),(144,'MD','Moldava','Republic of Moldova'),(145,'MC','Monaco','Principality of Monaco'),(146,'MN','Mongolia','Mongolia'),(147,'ME','Montenegro','Montenegro'),(148,'MS','Montserrat','Montserrat'),(149,'MA','Morocco','Kingdom of Morocco'),(150,'MZ','Mozambique','Republic of Mozambique'),(151,'MM','Myanmar (Burma)','Republic of the Union of Myanmar'),(152,'NA','Namibia','Republic of Namibia'),(153,'NR','Nauru','Republic of Nauru'),(154,'NP','Nepal','Federal Democratic Republic of Nepal'),(155,'NL','Netherlands','Kingdom of the Netherlands'),(156,'NC','New Caledonia','New Caledonia'),(157,'NZ','New Zealand','New Zealand'),(158,'NI','Nicaragua','Republic of Nicaragua'),(159,'NE','Niger','Republic of Niger'),(160,'NG','Nigeria','Federal Republic of Nigeria'),(161,'NU','Niue','Niue'),(162,'NF','Norfolk Island','Norfolk Island'),(163,'KP','North Korea','Democratic People\'s Republic of Korea'),(164,'MP','Northern Mariana Islands','Northern Mariana Islands'),(165,'NO','Norway','Kingdom of Norway'),(166,'OM','Oman','Sultanate of Oman'),(167,'PK','Pakistan','Islamic Republic of Pakistan'),(168,'PW','Palau','Republic of Palau'),(169,'PS','Palestine','State of Palestine (or Occupied Palestinian Territory)'),(170,'PA','Panama','Republic of Panama'),(171,'PG','Papua New Guinea','Independent State of Papua New Guinea'),(172,'PY','Paraguay','Republic of Paraguay'),(173,'PE','Peru','Republic of Peru'),(174,'PH','Phillipines','Republic of the Philippines'),(175,'PN','Pitcairn','Pitcairn'),(176,'PL','Poland','Republic of Poland'),(177,'PT','Portugal','Portuguese Republic'),(178,'PR','Puerto Rico','Commonwealth of Puerto Rico'),(179,'QA','Qatar','State of Qatar'),(180,'RE','Reunion','R&eacute;union'),(181,'RO','Romania','Romania'),(182,'RU','Russia','Russian Federation'),(183,'RW','Rwanda','Republic of Rwanda'),(184,'BL','Saint Barthelemy','Saint Barth&eacute;lemy'),(185,'SH','Saint Helena','Saint Helena, Ascension and Tristan da Cunha'),(186,'KN','Saint Kitts and Nevis','Federation of Saint Christopher and Nevis'),(187,'LC','Saint Lucia','Saint Lucia'),(188,'MF','Saint Martin','Saint Martin'),(189,'PM','Saint Pierre and Miquelon','Saint Pierre and Miquelon'),(190,'VC','Saint Vincent and the Grenadines','Saint Vincent and the Grenadines'),(191,'WS','Samoa','Independent State of Samoa'),(192,'SM','San Marino','Republic of San Marino'),(193,'ST','Sao Tome and Principe','Democratic Republic of S&atilde;o Tom&eacute; and Pr&iacute;ncipe'),(194,'SA','Saudi Arabia','Kingdom of Saudi Arabia'),(195,'SN','Senegal','Republic of Senegal'),(196,'RS','Serbia','Republic of Serbia'),(197,'SC','Seychelles','Republic of Seychelles'),(198,'SL','Sierra Leone','Republic of Sierra Leone'),(199,'SG','Singapore','Republic of Singapore'),(200,'SX','Sint Maarten','Sint Maarten'),(201,'SK','Slovakia','Slovak Republic'),(202,'SI','Slovenia','Republic of Slovenia'),(203,'SB','Solomon Islands','Solomon Islands'),(204,'SO','Somalia','Somali Republic'),(205,'ZA','South Africa','Republic of South Africa'),(206,'GS','South Georgia and the South Sandwich Islands','South Georgia and the South Sandwich Islands'),(207,'KR','South Korea','Republic of Korea'),(208,'SS','South Sudan','Republic of South Sudan'),(209,'ES','Spain','Kingdom of Spain'),(210,'LK','Sri Lanka','Democratic Socialist Republic of Sri Lanka'),(211,'SD','Sudan','Republic of the Sudan'),(212,'SR','Suriname','Republic of Suriname'),(213,'SJ','Svalbard and Jan Mayen','Svalbard and Jan Mayen'),(214,'SZ','Swaziland','Kingdom of Swaziland'),(215,'SE','Sweden','Kingdom of Sweden'),(216,'CH','Switzerland','Swiss Confederation'),(217,'SY','Syria','Syrian Arab Republic'),(218,'TW','Taiwan','Republic of China (Taiwan)'),(219,'TJ','Tajikistan','Republic of Tajikistan'),(220,'TZ','Tanzania','United Republic of Tanzania'),(221,'TH','Thailand','Kingdom of Thailand'),(222,'TL','Timor-Leste (East Timor)','Democratic Republic of Timor-Leste'),(223,'TG','Togo','Togolese Republic'),(224,'TK','Tokelau','Tokelau'),(225,'TO','Tonga','Kingdom of Tonga'),(226,'TT','Trinidad and Tobago','Republic of Trinidad and Tobago'),(227,'TN','Tunisia','Republic of Tunisia'),(228,'TR','Turkey','Republic of Turkey'),(229,'TM','Turkmenistan','Turkmenistan'),(230,'TC','Turks and Caicos Islands','Turks and Caicos Islands'),(231,'TV','Tuvalu','Tuvalu'),(232,'UG','Uganda','Republic of Uganda'),(233,'UA','Ukraine','Ukraine'),(234,'AE','United Arab Emirates','United Arab Emirates'),(235,'GB','United Kingdom','United Kingdom of Great Britain and Nothern Ireland'),(236,'US','United States','United States of America'),(237,'UM','United States Minor Outlying Islands','United States Minor Outlying Islands'),(238,'UY','Uruguay','Eastern Republic of Uruguay'),(239,'UZ','Uzbekistan','Republic of Uzbekistan'),(240,'VU','Vanuatu','Republic of Vanuatu'),(241,'VA','Vatican City','State of the Vatican City'),(242,'VE','Venezuela','Bolivarian Republic of Venezuela'),(243,'VN','Vietnam','Socialist Republic of Vietnam'),(244,'VG','Virgin Islands, British','British Virgin Islands'),(245,'VI','Virgin Islands, US','Virgin Islands of the United States'),(246,'WF','Wallis and Futuna','Wallis and Futuna'),(247,'EH','Western Sahara','Western Sahara'),(248,'YE','Yemen','Republic of Yemen'),(249,'ZM','Zambia','Republic of Zambia'),(250,'ZW','Zimbabwe','Republic of Zimbabwe');