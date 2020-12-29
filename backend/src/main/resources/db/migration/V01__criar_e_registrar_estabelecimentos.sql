CREATE TABLE estabelecimento (
                              codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
                              nome VARCHAR(50) NOT NULL,
                              endereco VARCHAR(30) NOT NULL,
                              telefone VARCHAR (20) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO estabelecimento (nome, endereco, telefone) values ('Devnow', 'Rua do Abacaxi', '8593222020');
INSERT INTO estabelecimento (nome, endereco, telefone) values ('BritaJ', 'Rua do Sabiá', '8592222420');
INSERT INTO estabelecimento (nome, endereco, telefone) values ('DepositoZe', 'Rua da Bateria', '8592422020');
INSERT INTO estabelecimento (nome, endereco, telefone) values ('M.D.F', 'Rua do Motorista', '8592225020');
INSERT INTO estabelecimento (nome, endereco, telefone) values ('LagosPizzaria', 'Av Rio Branco', '8552222020');
INSERT INTO estabelecimento (nome, endereco, telefone) values ('PizzariaLC', 'Av Brasil', '85922220123');
INSERT INTO estabelecimento (nome, endereco, telefone) values ('PastelariaLC', 'Rua do Sapo', '85922220212');
INSERT INTO estabelecimento (nome, endereco, telefone) values ('JWT-Lanches', 'Rua da Manga', '8597222025');
INSERT INTO estabelecimento (nome, endereco, telefone) values ('OAuthSecurity', 'Rua do Músico', '8594222023');
INSERT INTO estabelecimento (nome, endereco, telefone) values ('lago-jrs', 'Rua da Terra', '8592229021');
