-- MySQL Forward Engineering 
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
-- -------------------------------------------------------------------------
-- Database bdproject 

CREATE DATABASE IF NOT EXISTS `bdproject` DEFAULT CHARACTER SET utf8;
USE `bdproject`;

-- -------------------------------------------------------------------------
-- Table tb_customer

CREATE TABLE IF NOT EXISTS tb_customer (
	id_customer			INTEGER 		NOT NULL AUTO_INCREMENT,
    nm_customer			VARCHAR(50) 	NOT NULL,
    cpf_cnpj			NUMERIC(11,0)	NOT NULL,
    PRIMARY KEY (id_customer),
    UNIQUE KEY  (cpf_cnpj)
);

-- -------------------------------------------------------------------------
-- Table tb_customer_account

CREATE TABLE IF NOT EXISTS tb_customer_account (
	id_customer			INTEGER 		NOT NULL AUTO_INCREMENT,
    nm_customer			VARCHAR(50) 	NOT NULL,
    cpf_cnpj			NUMERIC(11,0)	NOT NULL,
    is_active			
    PRIMARY KEY (id_customer),
    UNIQUE KEY  (cpf_cnpj)
);
-- -------------------------------------------------------------------------
-- Table dm_address_type

CREATE TABLE IF NOT EXISTS dm_address_type (
	cd_address_type		CHAR(1)			NOT NULL,
    ds_address_type		VARCHAR(150)	NOT NULL,
    PRIMARY KEY (cd_address_type)
);
-- -------------------------------------------------------------------------
-- Table tb_customer_adress
 
CREATE TABLE IF NOT EXISTS tb_customer_address (
	id_customer			INTEGER 		NOT NULL,
    cd_address_type		CHAR(1)			NOT NULL,
    street				VARCHAR(150)	NOT NULL,
    lot					INTEGER			NOT NULL,
    `references`		VARCHAR(20),
    zip_code			VARCHAR(10)		NOT NULL,
    PRIMARY KEY (id_customer, cd_address_type),
    FOREIGN KEY (id_customer) 
		REFERENCES tb_customer(id_customer),
	FOREIGN KEY (cd_address_type)
		REFERENCES dm_address_type(cd_address_type)
);
-- -------------------------------------------------------------------------
INSERT INTO dm_address_type VALUES 
('R', 'Residêncial'),
('C', 'Comercial'),
('O', 'Outros');

SELECT * FROM dm_address_type;

-- -------------------------------------------------------------------------
-- Respostas do Questionário do TesteSQL
-- -------------------------------------------------------------------------
-- 1- Identificação das Chaves Primárias de Cada tabela
SELECT id_customer 
FROM tb_customer;

SELECT cd_address_type
FROM dm_address_type;
-- -------------------------------------------------------------------------
-- 2- Inserção do Cliente Joãozinho Silva
-- -------------------------------------------------------------------------
INSERT INTO tb_customer (nm_customer, cpf_cnpj) VALUES
('Joãozinho Silva', 88877766655);

SELECT * FROM tb_customer;

INSERT INTO tb_customer_address (id_customer,cd_address_type, street, lot, `references`, zip_code) VALUES 
(1, 'R', 'Rua das Flores', 1, NULL, '01234-567'),
(1, 'C', 'Rua das Pedras', 100,'Conjunto 200', '01234-567');

SELECT * FROM tb_customer_address;
-- -------------------------------------------------------------------------
-- 3- Quantidade de Endereços cadastráveis por cliente
-- -------------------------------------------------------------------------
SELECT * FROM dm_address_type;
-- Podemos ver que existem 3 possibilidades de inserção de endereço: Residêncial, Comercial e Outros. 

-- -------------------------------------------------------------------------
-- -------------------------------------------------------------------------
-- 4- Remoção de um Cliente da base Dados
-- -------------------------------------------------------------------------

-- Para fazer a remoção do Cliente utilizando seu CPF, cria-se um SELECT da tabela WHERE cpf_cnpj corresponda ao CPF do cliente para achar seu ID

SELECT * FROM tb_customer
WHERE cpf_cnpj= '88877766655';

-- Sabedo que seu ID é o '1', agora podemos apagar todas as colunas com aquele ID

DELETE tb_customer, tb_customer_address
FROM tb_customer INNER JOIN tb_customer_address
WHERE tb_customer.id_customer = tb_customer_address.id_customer AND tb_customer.id_customer = '1';
-- -------------------------------------------------------------------------
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

