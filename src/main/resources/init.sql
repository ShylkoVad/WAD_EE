--------------------------------------------------------
--  DDL for schema students_db
--------------------------------------------------------
DROP SCHEMA IF EXISTS students_db;
CREATE SCHEMA IF NOT EXISTS students_db;
--------------------------------------------------------
--  DDL for schema shop
--------------------------------------------------------
DROP SCHEMA IF EXISTS shop;
CREATE SCHEMA IF NOT EXISTS shop;

--------------------------------------------------------
--  DDL for Table students_db
--------------------------------------------------------
DROP TABLE IF EXISTS students_db.students;
CREATE TABLE IF NOT EXISTS students_db.students (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    course INT NOT NULL,
    PRIMARY KEY (id));
--------------------------------------------------------
--  DDL for Table shop
--------------------------------------------------------
DROP TABLE IF EXISTS shop.users;
CREATE TABLE IF NOT EXISTS shop.users (
    id INT NOT NULL AUTO_INCREMENT,
    login VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    name VARCHAR(45) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    balance INT NOT NULL,
    PRIMARY KEY (id));

DROP TABLE IF EXISTS shop.categories;
CREATE TABLE IF NOT EXISTS shop.categories (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    imagePath VARCHAR(100) NOT NULL,
    PRIMARY KEY (id));

--------------------------------------------------------
--  DML for Table students_db
--------------------------------------------------------
INSERT INTO students_db.students(name, surname, course) VALUES('Alex', 'Surkov', 1);
INSERT INTO students_db.students(name, surname, course) VALUES('Anna', 'Borodich', 2);
INSERT INTO students_db.students(name, surname, course) VALUES('Sergey', 'Kryvnitskiy', 3);
INSERT INTO students_db.students(name, surname, course) VALUES('Alina', 'Kedich', 4);
INSERT INTO students_db.students(name, surname, course) VALUES('Petr', 'Medvedzev', 5);
--------------------------------------------------------
--  DML for Table shop
--------------------------------------------------------
INSERT INTO shop.users(login, password, name, surname, balance) VALUES('shilko_vad@mail.ru', '1234', 'Вадим', 'Шилько', 0);
INSERT INTO shop.users(login, password, name, surname, balance) VALUES('login@mail.ru', '0000', 'Алексей', 'Иванов', 100);
INSERT INTO shop.users(login, password, name, surname, balance) VALUES('user1@mail.ru', '1111', 'Дмитрий', 'Козлов', 10);