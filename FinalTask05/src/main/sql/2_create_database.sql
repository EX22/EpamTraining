CREATE DATABASE IF NOT EXISTS `crowdsource_db` DEFAULT CHARACTER SET utf8;

CREATE USER 'crowdsource_user'@'%' IDENTIFIED BY 'crowdsource_password';

CREATE USER 'crowdsource_user'@'localhost' IDENTIFIED BY 'crowdsource_password';

GRANT ALL ON crowdsource_db.* TO 'crowdsource_user'@'localhost';

GRANT ALL ON crowdsource_db.* TO 'crowdsource_user'@'%';