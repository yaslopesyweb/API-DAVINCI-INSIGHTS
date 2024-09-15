CREATE TABLE CLIENTE (
    cliente_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    login VARCHAR(255),
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE OPINIAO (
    opiniao_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    comentario VARCHAR(255),
    id_cliente BIGINT,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(cliente_id)
);

CREATE TABLE PRODUTO (
    produto_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    valor DECIMAL(19, 2),
    id_cliente BIGINT,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(cliente_id)
);
