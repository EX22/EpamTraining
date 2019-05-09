USE `crowdsource_db`;

CREATE TABLE IF NOT EXISTS `categories` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`image_path` VARCHAR(255) NOT NULL,
	`question` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `users` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(255) NOT NULL UNIQUE,
    `name` VARCHAR(64) NOT NULL DEFAULT '',
    `level` INTEGER NULL DEFAULT '0',
    `password` VARCHAR(255) NOT NULL,
    `photo_path` VARCHAR(255) NULL DEFAULT NULL,
    `role` TINYINT NOT NULL,
     CONSTRAINT CHK_role CHECK (`role` IN (0, 1)),
     PRIMARY KEY (`id`),
     INDEX login_pass (login, password)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `images` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `user_id` INTEGER NOT NULL,
	`path` VARCHAR(255) NOT NULL,
	`category_id` INTEGER NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `category` (`category_id`),
	CONSTRAINT `category_id`
	FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `favorites` (
	`user_id` INTEGER NOT NULL,
	`category_id` INTEGER NOT NULL,
	INDEX `user_id` (`user_id`),
	INDEX `category_id` (`category_id`),
	FOREIGN KEY (`user_id`)
	REFERENCES `users` (`id`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,
	FOREIGN KEY (`category_id`)
	REFERENCES `categories` (`id`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `settings` (
	`settings_name` VARCHAR(64) NOT NULL,
	`settings_value` VARCHAR(255) NOT NULL
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `blacklist` (
    `user_id` INTEGER NOT NULL,
	`login` VARCHAR(255) NOT NULL UNIQUE,
	INDEX `user_id` (`user_id`),
	CONSTRAINT `user_id`
	FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON UPDATE RESTRICT
    ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `recognizedimgs` (
	`image_id` INT(11) NOT NULL,
	`user_id` INT(11) NOT NULL,
	`answer` VARCHAR(32) NOT NULL,
	INDEX `image_id` (`image_id`),
	INDEX `user_id` (`user_id`),
	CONSTRAINT `im_id` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`),
	CONSTRAINT `us_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;
