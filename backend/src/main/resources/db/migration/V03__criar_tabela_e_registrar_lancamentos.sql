CREATE TABLE vinculo (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    codigo_estabelecimento BIGINT(20) NOT NULL,
	codigo_profissional BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_estabelecimento) REFERENCES estabelecimento(codigo),
	FOREIGN KEY (codigo_profissional) REFERENCES profissional(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO vinculo (codigo_estabelecimento, codigo_profissional) values (1,1);
INSERT INTO vinculo ( codigo_estabelecimento, codigo_profissional) values ( 2, 2);
INSERT INTO vinculo ( codigo_estabelecimento, codigo_profissional) values ( 3, 3);
INSERT INTO vinculo ( codigo_estabelecimento, codigo_profissional) values (3, 4);
INSERT INTO vinculo ( codigo_estabelecimento, codigo_profissional) values ( 3, 5);
INSERT INTO vinculo ( codigo_estabelecimento, codigo_profissional) values ( 4, 6);
INSERT INTO vinculo ( codigo_estabelecimento, codigo_profissional) values ( 1, 7);
INSERT INTO vinculo ( codigo_estabelecimento,codigo_profissional) values ( 4, 8);
INSERT INTO vinculo ( codigo_estabelecimento, codigo_profissional) values ( 3, 9);
INSERT INTO vinculo ( codigo_estabelecimento, codigo_profissional) values (5, 10);
INSERT INTO vinculo ( codigo_estabelecimento, codigo_profissional) values ( 1, 5);
INSERT INTO vinculo ( codigo_estabelecimento, codigo_profissional) values ( 5, 4);
INSERT INTO vinculo ( codigo_estabelecimento, codigo_profissional) values ( 4, 3);
INSERT INTO vinculo ( codigo_estabelecimento, codigo_profissional) values ( 4, 2);
INSERT INTO vinculo ( codigo_estabelecimento, codigo_profissional) values ( 4, 1);
