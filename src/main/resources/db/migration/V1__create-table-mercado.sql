USE mercado; -- Use the mercado database

CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    login VARCHAR(6) NOT NULL UNIQUE,
    senha VARCHAR(8) NOT NULL,
    acesso VARCHAR(13) NOT NULL,
    PRIMARY KEY (id)
);
