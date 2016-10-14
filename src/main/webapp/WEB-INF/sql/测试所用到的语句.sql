
DROP TABLE IF EXISTS `user_t`;  
  
CREATE TABLE `user_t` (  
  `id` INT(11) NOT NULL ,  
  `user_name` VARCHAR(40) NOT NULL,  
  `password` VARCHAR(255) NOT NULL,  
  `age` INT(4) NOT NULL,  
  PRIMARY KEY (`id`)  
);  
  
/*Data for the table `user_t` */  
  
INSERT  INTO `user_t`(`id`,`user_name`,`password`,`age`) VALUES (1,'测试','sfasgfaf',24); 

 

SELECT * FROM user_t;