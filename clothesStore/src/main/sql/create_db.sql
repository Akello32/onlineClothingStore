CREATE database 'clothingDB' DEFAULT CHARACTER SET utf8;

GRANT SELECT,INSERT,UPDATE,DELETE
ON `clothingDB`.*
TO clothing_user@localhost
IDENTIFIED BY 'clothing_password';

GRANT SELECT,INSERT,UPDATE,DELETE
ON `clothingDB`.*
TO clothing_user@'%'
IDENTIFIED BY 'clothing_password';