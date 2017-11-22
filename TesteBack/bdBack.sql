-- MySQL Forward Engineering 
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
-- -------------------------------------------------------------------------
-- Database bdBack 

CREATE DATABASE IF NOT EXISTS `bdback` DEFAULT CHARACTER SET utf8;
USE `bdback`;

-- -------------------------------------------------------------------------
-- Table tb_customer

CREATE TABLE IF NOT EXISTS tb_customer_account (
	id_customer			INTEGER 		NOT NULL,
    nm_customer			VARCHAR(50) 	NOT NULL,
    cpf_cnpj			NUMERIC(11,0)	NOT NULL,
    is_active			TINYINT(1)		NOT NULL,
    vl_total			FLOAT			NOT NULL,
    PRIMARY KEY (id_customer),
    UNIQUE KEY  (cpf_cnpj)
);

-- -------------------------------------------------------------------------
-- Chechking Client activity

SELECT  id_customer 				as	'ID',
		nm_customer					as	'Nome',
        cpf_cnpj					as	'CPF/CNPJ',
        vl_total					as  'Valor Total',
	CASE WHEN is_active='1' THEN 'Ativo' WHEN is_active='0' THEN 'Inativo' 
		end as 'Status do Cliente' 
FROM tb_customer_account;

-- Query de Média
SELECT AVG(vl_total)
FROM tb_customer_account
WHERE (vl_total > 560) AND (id_customer BETWEEN 1500 AND 2700);

-- Query dos Clientes usados para a Média
SELECT id_customer,nm_customer, cpf_cnpj, is_active vl_total 
FROM tb_customer_account
WHERE vl_total > 560 AND (id_customer BETWEEN 1500 AND 2700);