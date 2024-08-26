USE master if exists (SELECT * FROM SYS.databases WHERE name = 'pizzariadb')

DROP DATABASE pizzariadb
GO

CREATE DATABASE pizzariadb
GO

USE pizzariadb
GO

CREATE TABLE categorias (
id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
nome VARCHAR(45) NOT NULL,
descricao VARCHAR(100) NULL,
cod_status BIT NOT NULL,
)

CREATE TABLE produtos (
id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
tipo VARCHAR(45) NULL,
descricao VARCHAR(250) NULL,
preco_compra DECIMAL(5,2) NULL,
preco_venda DECIMAL(5,2) NULL,
quantidade_estoque int NULL,
cod_status BIT NOT NULL,
categoria_id BIGINT NOT NULL,
CONSTRAINT fk_produtos_categoria_id FOREIGN KEY (categoria_id) REFERENCES categorias(id)
)

CREATE TABLE usuarios (
id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
nome VARCHAR(100) NULL,
cpf VARCHAR(20) NULL,
username VARCHAR(45) NOT NULL UNIQUE,
password VARCHAR(250) NOT NULL,
tipo_usuario VARCHAR(45) NOT NULL,
logradouro VARCHAR(100) NULL,
cep VARCHAR(15) NULL,
bairro VARCHAR(100) NULL,
uf VARCHAR(2) NULL,
cod_status BIT NOT NULL
)

CREATE TABLE telefones (
id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
numero VARCHAR(15) NOT NULL,
cod_status BIT NOT NULL,
usuario_id BIGINT NOT NULL,
CONSTRAINT fk_telefones_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
)

CREATE TABLE pedidos (
id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
numero_pedido VARCHAR(10) NOT NULL,
data_hora_compra DATETIME NULL,
data_hora_entrega DATETIME NULL,
valor_total DECIMAL(5,2) NOT NULL,
status VARCHAR(20) NOT NULL,
cod_status BIT NOT NULL,
usuario_id BIGINT NOT NULL,
CONSTRAINT fk_pedidos_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
)

CREATE TABLE itens_pedido (
id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
quantidade_item INT NOT NULL,
preco_unitario DECIMAL(5,2) NOT NULL,
cod_status BIT NOT NULL,
pedido_id BIGINT NOT NULL,
produto_id BIGINT NOT NULL,
CONSTRAINT fk_itens_pedido_pedido_id FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
CONSTRAINT fk_itens_pedido_produto_id FOREIGN KEY (produto_id) REFERENCES produtos(id)
)





