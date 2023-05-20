--------------------------------------------------------
--  DDL for schema payment_system_db
--------------------------------------------------------
DROP SCHEMA IF EXISTS payment_system_db;
CREATE SCHEMA IF NOT EXISTS payment_system_db;

--------------------------------------------------------
--  DDL for Table payment_system_db
--------------------------------------------------------
DROP TABLE IF EXISTS payment_system_db.merchant;
CREATE TABLE IF NOT EXISTS payment_system_db.merchant (
                                                    id VARCHAR(60),
                                                    nameMerchant VARCHAR(15),
                                                    createdAt Timestamp);
DROP TABLE IF EXISTS payment_system_db.bank_accounts;
CREATE TABLE IF NOT EXISTS payment_system_db.bank_accounts (
                                                    id VARCHAR(60),
                                                    merchantId VARCHAR(60),
                                                    status VARCHAR(10),
                                                    accountNumber VARCHAR(60),
                                                    createdAt Timestamp);
--------------------------------------------------------
--  DML for Table payment_system_db
--------------------------------------------------------
INSERT INTO payment_system_db.merchant(id, nameMerchant, createdAt) VALUES(?, ?, ?);
INSERT INTO payment_system_db.bank_accounts(id, merchantId, status, accountNumber, createdAt) VALUES(?, ?, ?, ?, ?);