-- MySQL Script generated by MySQL Workbench
-- 03/30/18 00:46:32
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bibliotecajava
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bibliotecajava
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bibliotecajava` DEFAULT CHARACTER SET utf8 ;
USE `bibliotecajava` ;

-- -----------------------------------------------------
-- Table `bibliotecajava`.`autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`autor` (
  `id_autor` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(80) NULL DEFAULT NULL,
  PRIMARY KEY (`id_autor`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bibliotecajava`.`catalogo_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`catalogo_roles` (
  `id_catalogo_roles` INT(11) NOT NULL AUTO_INCREMENT,
  `rol` VARCHAR(80) NULL DEFAULT NULL,
  `descripcion` VARCHAR(80) NULL DEFAULT NULL,
  PRIMARY KEY (`id_catalogo_roles`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bibliotecajava`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`categoria` (
  `id_categoria` INT(11) NOT NULL AUTO_INCREMENT,
  `categoria` VARCHAR(80) NULL DEFAULT NULL,
  `descripcion` VARCHAR(80) NULL DEFAULT NULL,
  PRIMARY KEY (`id_categoria`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bibliotecajava`.`estante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`estante` (
  `id_estante` INT(11) NOT NULL AUTO_INCREMENT,
  `estante` VARCHAR(80) NULL DEFAULT NULL,
  `seccion` VARCHAR(85) NULL,
  `descripcion` VARCHAR(80) NULL DEFAULT NULL,
  PRIMARY KEY (`id_estante`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bibliotecajava`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`item` (
  `id_item` INT(11) NOT NULL AUTO_INCREMENT,
  `id_categoria` INT(11) NOT NULL,
  `id_estante` INT(11) NOT NULL,
  `nombre` VARCHAR(80) NULL,
  `descripcion` VARCHAR(80) NULL DEFAULT NULL,
  `unidades_para_prestar` INT(11) NOT NULL,
  PRIMARY KEY (`id_item`),
  INDEX `fk_catalogo_item_estante1_idx` (`id_estante` ASC),
  INDEX `fk_item_categoria1_idx` (`id_categoria` ASC),
  CONSTRAINT `fk_catalogo_item_estante1`
    FOREIGN KEY (`id_estante`)
    REFERENCES `bibliotecajava`.`estante` (`id_estante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_categoria1`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `bibliotecajava`.`categoria` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bibliotecajava`.`cd`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`cd` (
  `id_cd` INT(11) NOT NULL AUTO_INCREMENT,
  `id_item` INT(11) NOT NULL,
  `album` VARCHAR(45) NULL,
  `genero` VARCHAR(45) NULL,
  `fecha_lanzamiento` DATE NULL,
  `artista` VARCHAR(45) NULL,
  PRIMARY KEY (`id_cd`),
  INDEX `fk_cd_catalogo_item1_idx` (`id_item` ASC),
  CONSTRAINT `fk_cd_catalogo_item1`
    FOREIGN KEY (`id_item`)
    REFERENCES `bibliotecajava`.`item` (`id_item`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bibliotecajava`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`usuario` (
  `id_carne` VARCHAR(80) NOT NULL,
  `id_catalogo_roles` INT(11) NOT NULL,
  `password` VARCHAR(80) NULL DEFAULT NULL,
  `estado` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id_carne`),
  INDEX `fk_usuario_catalogo_roles1_idx` (`id_catalogo_roles` ASC),
  CONSTRAINT `fk_usuario_catalogo_roles1`
    FOREIGN KEY (`id_catalogo_roles`)
    REFERENCES `bibliotecajava`.`catalogo_roles` (`id_catalogo_roles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bibliotecajava`.`datos_personales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`datos_personales` (
  `id_datos_personales` INT(11) NOT NULL AUTO_INCREMENT,
  `id_carne` VARCHAR(80) NOT NULL,
  `nombre` VARCHAR(80) NULL DEFAULT NULL,
  `apellido` VARCHAR(80) NULL DEFAULT NULL,
  `genero` TINYINT(1) NULL DEFAULT NULL,
  `email` VARCHAR(80) NULL DEFAULT NULL,
  `telefono` VARCHAR(45) NULL,
  `direccion` VARCHAR(45) NULL,
  PRIMARY KEY (`id_datos_personales`),
  INDEX `fk_datos_personales_usuario1_idx` (`id_carne` ASC),
  CONSTRAINT `fk_datos_personales_usuario1`
    FOREIGN KEY (`id_carne`)
    REFERENCES `bibliotecajava`.`usuario` (`id_carne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bibliotecajava`.`prestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`prestamo` (
  `id_prestamo` INT(11) NOT NULL AUTO_INCREMENT,
  `id_item` INT(11) NOT NULL,
  `id_carne_prestamista` VARCHAR(80) NOT NULL,
  `id_carne_solicitante` VARCHAR(80) NOT NULL,
  `fecha_prestamo` DATE NULL DEFAULT NULL,
  `fecha_pactada` DATE NULL DEFAULT NULL,
  `descripcion` VARCHAR(80) NULL DEFAULT NULL,
  PRIMARY KEY (`id_prestamo`),
  INDEX `fk_prestamo_catalogo_item1_idx` (`id_item` ASC),
  INDEX `fk_prestamo_usuario1_idx` (`id_carne_prestamista` ASC),
  INDEX `fk_prestamo_usuario2_idx` (`id_carne_solicitante` ASC),
  CONSTRAINT `fk_prestamo_catalogo_item1`
    FOREIGN KEY (`id_item`)
    REFERENCES `bibliotecajava`.`item` (`id_item`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_prestamo_usuario1`
    FOREIGN KEY (`id_carne_prestamista`)
    REFERENCES `bibliotecajava`.`usuario` (`id_carne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_prestamo_usuario2`
    FOREIGN KEY (`id_carne_solicitante`)
    REFERENCES `bibliotecajava`.`usuario` (`id_carne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bibliotecajava`.`devolucion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`devolucion` (
  `id_devolucion` INT(11) NOT NULL AUTO_INCREMENT,
  `id_prestamo` INT(11) NOT NULL,
  `fecha_devolucion` DATE NULL DEFAULT NULL,
  `mora_cancelada` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id_devolucion`),
  INDEX `fk_devolucion_prestamo1_idx` (`id_prestamo` ASC),
  CONSTRAINT `fk_devolucion_prestamo1`
    FOREIGN KEY (`id_prestamo`)
    REFERENCES `bibliotecajava`.`prestamo` (`id_prestamo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bibliotecajava`.`libro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`libro` (
  `id_libro` INT(11) NOT NULL AUTO_INCREMENT,
  `id_item` INT(11) NOT NULL,
  `nota` VARCHAR(80) NULL DEFAULT NULL,
  `edicion` VARCHAR(85) NULL DEFAULT NULL,
  `fecha_publicacion` DATE NULL DEFAULT NULL,
  `lugar_publicacion` VARCHAR(80) NULL DEFAULT NULL,
  `isbn` VARCHAR(85) NULL,
  PRIMARY KEY (`id_libro`),
  INDEX `fk_libro_catalogo_item1_idx` (`id_item` ASC),
  CONSTRAINT `fk_libro_catalogo_item1`
    FOREIGN KEY (`id_item`)
    REFERENCES `bibliotecajava`.`item` (`id_item`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bibliotecajava`.`libro_autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`libro_autor` (
  `id_autor` INT(11) NOT NULL,
  `id_libro` INT(11) NOT NULL,
  INDEX `fk_table1_autor1_idx` (`id_autor` ASC),
  INDEX `fk_table1_libro1_idx` (`id_libro` ASC),
  CONSTRAINT `fk_table1_autor1`
    FOREIGN KEY (`id_autor`)
    REFERENCES `bibliotecajava`.`autor` (`id_autor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_libro1`
    FOREIGN KEY (`id_libro`)
    REFERENCES `bibliotecajava`.`libro` (`id_libro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bibliotecajava`.`parametros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`parametros` (
  `id_parametros` INT(11) NOT NULL AUTO_INCREMENT,
  `mora_por_dia` DECIMAL(15,2) NOT NULL,
  `dias_prestar_alumno` INT(11) NOT NULL,
  `dias_prestar_profesor` INT(11) NOT NULL,
  `cantidad_prestar_alumno` INT(11) NOT NULL,
  `cantidad_prestar_profesor` INT(11) NOT NULL,
  PRIMARY KEY (`id_parametros`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bibliotecajava`.`revista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`revista` (
  `id_revista` INT(11) NOT NULL AUTO_INCREMENT,
  `id_item` INT(11) NOT NULL,
  `edicion` VARCHAR(85) NULL,
  `editorial` VARCHAR(85) NULL,
  `lugar_lanzamiento` VARCHAR(85) NULL,
  `fecha_lanzamiento` DATE NULL,
  PRIMARY KEY (`id_revista`),
  INDEX `fk_revista_catalogo_item1_idx` (`id_item` ASC),
  CONSTRAINT `fk_revista_catalogo_item1`
    FOREIGN KEY (`id_item`)
    REFERENCES `bibliotecajava`.`item` (`id_item`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bibliotecajava`.`tesis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bibliotecajava`.`tesis` (
  `id_tesis` INT(11) NOT NULL AUTO_INCREMENT,
  `id_item` INT(11) NOT NULL,
  `fecha_publicacion` DATE NULL DEFAULT NULL,
  `lugar_desarrollo` VARCHAR(85) NULL,
  `autores` VARCHAR(85) NULL,
  PRIMARY KEY (`id_tesis`),
  INDEX `fk_tesis_catalogo_item1_idx` (`id_item` ASC),
  CONSTRAINT `fk_tesis_catalogo_item1`
    FOREIGN KEY (`id_item`)
    REFERENCES `bibliotecajava`.`item` (`id_item`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
