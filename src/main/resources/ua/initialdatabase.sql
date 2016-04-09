CREATE SCHEMA `userschema` ;

CREATE TABLE `userschema`.`user` (
  `login` VARCHAR(50) NOT NULL,
  `password` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`login`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC));

INSERT INTO `userschema`.`user` (`login`, `password`, `name`) VALUES ('ivan', 'a976f858703e499790e678858bbd3fd3', 'Иван');
INSERT INTO `userschema`.`user` (`login`, `password`, `name`) VALUES ('john', 'dc81f9bab329295e44cd4a573a9729f0', 'John');
