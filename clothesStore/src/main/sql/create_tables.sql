USE `clothingDB`;

CREATE TABLE `clothes_type` (
	`identity` INTEGER NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`identity`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `brands` (
	`identity` INTEGER NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`identity`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `users` (
	`identity` INTEGER NOT NULL AUTO_INCREMENT,
	`login` VARCHAR(35) NOT NULL UNIQUE,
	`password` VARCHAR(50) NOT NULL,
	/*
	 * 0 - admin (Role.ADMINISTRATOR)
	 * 1 - manager (Role.menager)
	 * 2 - buyer (Role.BUYER)
	 */
	`role` TINYINT NOT NULL CHECK (`role` IN (0, 1, 2)),
	PRIMARY KEY (`identity`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `discounts` (
	`identity` INTEGER NOT NULL AUTO_INCREMENT,
	`amount` INTEGER NOT NULL,
	`startDate` DATE NOT NULL,
	`endDate` DATE NOT NULL,
	PRIMARY KEY (`identity`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `clothes` (
	`identity` INTEGER NOT NULL AUTO_INCREMENT,
	`price` DECIMAL NOT NULL,
	`numbers` INTEGER NOT NULL, 
	`image` VARCHAR(8000) NOT NULL, 
	`size` VARCHAR(10) NOT NULL,
	`color` VARCHAR(70) NOT NULL,
	`typeID` INTEGER NOT NULL,
	`brandID` INTEGER NOT NULL,	
	PRIMARY KEY (`identity`),
	FOREIGN KEY (`typeID`)
	REFERENCES `clothes_type` (`identity`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,
	FOREIGN KEY (`brandID`)	
	REFERENCES `brands` (`identity`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `orders`(
	`identity` INTEGER NOT NULL AUTO_INCREMENT,
	`status` BOOLEAN NOT NULL,
	`user_id` INTEGER NOT NULL,
	`price` DECIMAL NOT NULL,
	PRIMARY KEY (`identity`),
	FOREIGN KEY (`user_id`)
	REFERENCES `users` (`identity`)
	ON UPDATE CASCADE
	ON DELETE NO ACTION
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `ordered_clothes` (
	`identity` INTEGER NOT NULL AUTO_INCREMENT,
	`thing_id` INTEGER NOT NULL,
	`order_id` INTEGER NOT NULL,
	PRIMARY KEY (`identity`),
	FOREIGN KEY (`thing_id`)
	REFERENCES `clothes` (`identity`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
	FOREIGN KEY (`order_id`)
	REFERENCES `orders` (`identity`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `used_discounts` (
	`identity` INTEGER NOT NULL AUTO_INCREMENT,
	`discount_id` INTEGER NOT NULL,
	`clothes_id` INTEGER NOT NULL,
	PRIMARY KEY (`identity`),
	FOREIGN KEY (`clothes_id`)
	REFERENCES `clothes` (`identity`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,
	FOREIGN KEY (`discount_id`)	
	REFERENCES `discounts` (`identity`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `reviews` (
	`identity` INTEGER NOT NULL AUTO_INCREMENT,
	`clothes_id` INTEGER NOT NULL,
	PRIMARY KEY (`identity`),
	FOREIGN KEY (`clothes_id`)
	REFERENCES `clothes` (`identity`)
	ON UPDATE CASCADE
	ON DELETE NO ACTION
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

