-- MySQL Script generated by MySQL Workbench
-- Mon Mar 28 13:22:44 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema museum
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema museum
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `museum` DEFAULT CHARACTER SET utf8 ;
USE `museum` ;

-- -----------------------------------------------------
-- Table `museum`.`admin_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `museum`.`admin_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `token` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `admin_user_id_uindex` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `museum`.`bank_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `museum`.`bank_account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `holder_name` VARCHAR(45) NOT NULL,
  `number` VARCHAR(16) NOT NULL,
  `type` ENUM('VISA', 'MASTERCARD', 'AMERICAN_EXPRESS') NOT NULL,
  `valid_thru` DATE NOT NULL,
  `pin` VARCHAR(3) NOT NULL,
  `balance` DECIMAL(11,2) NOT NULL,
  `blocked` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `bank_account_number_uindex` (`number` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `museum`.`logs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `museum`.`logs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `label` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `logs_id_uindex` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 147
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `museum`.`museum`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `museum`.`museum` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `latitude` DECIMAL(10,6) NOT NULL,
  `longitude` DECIMAL(10,6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 52
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `museum`.`media`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `museum`.`media` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `path` VARCHAR(255) NULL DEFAULT NULL,
  `museum_id` INT NOT NULL,
  `is_video` BIT(1) NOT NULL,
  `url` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idmedia_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_media_museum_idx` (`museum_id` ASC) VISIBLE,
  CONSTRAINT `fk_media_museum`
    FOREIGN KEY (`museum_id`)
    REFERENCES `museum`.`museum` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 126
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `museum`.`session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `museum`.`session` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `session_key` VARCHAR(255) NOT NULL,
  `valid_until` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `session_key_uindex` (`session_key` ASC) VISIBLE,
  UNIQUE INDEX `session_username_uindex` (`username` ASC) VISIBLE,
  UNIQUE INDEX `session_id_uindex` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 40
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `museum`.`tour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `museum`.`tour` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `museum_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `duration` FLOAT(2,1) NOT NULL,
  `price` FLOAT(5,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `iddate_of_visit_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_tour_museum_idx` (`museum_id` ASC) VISIBLE,
  CONSTRAINT `fk_tour_museum`
    FOREIGN KEY (`museum_id`)
    REFERENCES `museum`.`museum` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `museum`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `museum`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `e_mail` VARCHAR(45) NOT NULL,
  `is_approved` BIT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `user_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `e_mail_UNIQUE` (`e_mail` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `museum`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `museum`.`ticket` (
  `id` VARCHAR(10) NOT NULL,
  `tour_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `ticket_id_uindex` (`id` ASC) VISIBLE,
  INDEX `tour_id` (`tour_id` ASC) VISIBLE,
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `tour_id`
    FOREIGN KEY (`tour_id`)
    REFERENCES `museum`.`tour` (`id`),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `museum`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `museum`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `museum`.`transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bank_account_id` INT NOT NULL,
  `amount` DECIMAL(11,2) NOT NULL,
  `transaction_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `bank_account__id` (`bank_account_id` ASC) VISIBLE,
  CONSTRAINT `bank_account__id`
    FOREIGN KEY (`bank_account_id`)
    REFERENCES `museum`.`bank_account` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 49
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `museum`.`user_statistic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `museum`.`user_statistic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time_active` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_statistic_id_uindex` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;