USE `crowdsource_db`;

CREATE TABLE `categories` (
	`identity` INTEGER NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`imagePath` VARCHAR(255),
	PRIMARY KEY (`identity`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `users` (
    `identity` INTEGER NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(255) NOT NULL UNIQUE,
    `name` VARCHAR(64),
    `level` INTEGER,
    `password` CHAR(32) NOT NULL,
    `photoPath` VARCHAR(255),
    PRIMARY KEY (`identity`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `images` (
    `identity` INTEGER NOT NULL AUTO_INCREMENT,
	`path` VARCHAR(255),
	`category` INTEGER,
	PRIMARY KEY (`identity`),
	FOREIGN KEY (`category`)
    REFERENCES `categories` (`identity`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `favorites` (
	`identity` INTEGER NOT NULL AUTO_INCREMENT,
	`user_identity` INTEGER NOT NULL,
	`category_identity` INTEGER NOT NULL,
	PRIMARY KEY (`identity`),
	FOREIGN KEY (`user_identity`)
	REFERENCES `users` (`identity`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,
	FOREIGN KEY (`category_identity`)
	REFERENCES `categories` (`identity`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;




