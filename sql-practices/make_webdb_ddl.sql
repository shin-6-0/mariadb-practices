-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema webdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema webdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `webdb` DEFAULT CHARACTER SET utf8 ;
USE `webdb` ;

-- -----------------------------------------------------
-- Table `webdb`.`dept`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webdb`.`dept` ;

CREATE TABLE IF NOT EXISTS `webdb`.`dept` (
  `no` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webdb`.`emp`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webdb`.`emp` ;

CREATE TABLE IF NOT EXISTS `webdb`.`emp` (
  `no` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `dept_no` INT NOT NULL,
  PRIMARY KEY (`no`),
  INDEX `fk_emp_dept_idx` (`dept_no` ASC) VISIBLE,
  CONSTRAINT `fk_emp_dept`
    FOREIGN KEY (`dept_no`)
    REFERENCES `webdb`.`dept` (`no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
