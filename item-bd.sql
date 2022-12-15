-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.31 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para items_management
CREATE DATABASE IF NOT EXISTS `items_management` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `items_management`;

-- Volcando estructura para procedimiento items_management.enabled_item
DELIMITER //
CREATE PROCEDURE `enabled_item`(IN id int)
BEGIN

UPDATE item SET item_status = 'A' WHERE item_id = id ;

           COMMIT;
           END//
DELIMITER ;

-- Volcando estructura para procedimiento items_management.find_item
DELIMITER //
CREATE PROCEDURE `find_item`(IN id int)
BEGIN

SELECT * FROM item WHERE item_id = id ;

           COMMIT;
           END//
DELIMITER ;

-- Volcando estructura para tabla items_management.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para procedimiento items_management.insert_item
DELIMITER //
CREATE PROCEDURE `insert_item`(in item_name VARCHAR(64), in item_description VARCHAR(200))
BEGIN
    declare maximo VARCHAR(07);
    declare num int;
    declare codigo VARCHAR(07);

    SET maximo = (SELECT MAX(item_code) FROM item);
    SET num = (SELECT LTRIM(RIGHT(maximo,6)));

    IF num >= 1 and num <= 8 then
        set num=num+1;
        set codigo=(select CONCAT('000000',cast(num as char)));
    ELSEIF num>= 9 and num <= 98 then
        set num=num+1;
        set codigo=(select CONCAT('00000',cast(num as char)));
    ELSEIF num>= 99 and num <= 998 then
        set num=num+1;
        set codigo=(select CONCAT('0000',cast(num AS char )));
    ELSEIF num>= 999 and num <= 9998 then
        set num=num+1;
        set codigo=(select CONCAT('000',cast(num as char)));
    ELSE
        set codigo=(SELECT '0000001');
    END IF;

INSERT INTO item(item_name,item_description,item_code,item_date_creation, item_date_modification) VALUES (item_name, item_description,codigo, NOW(),NOW());

           COMMIT;
           END//
DELIMITER ;

-- Volcando estructura para tabla items_management.item
CREATE TABLE IF NOT EXISTS `item` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `item_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `item_description` varchar(200) NOT NULL,
  `item_code` varchar(16) NOT NULL,
  `item_date_creation` datetime DEFAULT NULL,
  `item_date_modification` datetime DEFAULT NULL,
  `item_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'A',
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `item_code` (`item_code`),
  UNIQUE KEY `item_name` (`item_name`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para procedimiento items_management.remove_item
DELIMITER //
CREATE PROCEDURE `remove_item`(IN id int)
BEGIN

UPDATE item SET item_status = 'B' WHERE item_id = id ;

           COMMIT;
           END//
DELIMITER ;

-- Volcando estructura para procedimiento items_management.update_item
DELIMITER //
CREATE PROCEDURE `update_item`(IN id int,in name_item VARCHAR(64), in description_item VARCHAR(200),IN code_item VARCHAR(17) )
BEGIN

UPDATE item SET item_name = name_item , item_description = description_item, item_code = code_item , item_date_modification = NOW() WHERE item_id = id ;
           COMMIT;
           END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
