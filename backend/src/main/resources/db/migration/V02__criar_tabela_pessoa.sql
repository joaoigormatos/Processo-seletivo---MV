CREATE TABLE profissional (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	endereco VARCHAR(30) NOT NULL,
	celular VARCHAR (20) NOT NULL,
	residencial VARCHAR (20) ,
	funcao VARCHAR (30)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO profissional (nome, endereco, celular, residencial, funcao) values ('João Silva', 'Rua do Abacaxi', '8592222020', null, 'Desenvolvedor');
INSERT INTO profissional (nome, endereco, celular, residencial, funcao) values ('Maria Rita', 'Rua do Sabiá', '8592222020', '33336652', 'Engenheira');
INSERT INTO profissional (nome, endereco, celular, residencial, funcao) values ('Pedro Santos', 'Rua da Bateria', '8592222020', null, 'Desenvolvedor');
INSERT INTO profissional (nome, endereco, celular, residencial, funcao) values ('Ricardo Pereira', 'Rua do Motorista', '8592222020', null,'backend');
INSERT INTO profissional (nome, endereco, celular, residencial, funcao) values ('Josué Mariano', 'Av Rio Branco', '8592222020', null, 'frontend');
INSERT INTO profissional (nome, endereco, celular, residencial, funcao) values ('Pedro Barbosa', 'Av Brasil', '8592222020', null, 'fullstack');
INSERT INTO profissional (nome, endereco, celular, residencial, funcao) values ('Henrique Medeiros', 'Rua do Sapo', '8592222020', '33336652', 'fullstack');
INSERT INTO profissional (nome, endereco, celular, residencial, funcao) values ('Carlos Santana', 'Rua da Manga', '8597222020', null, 'Devops');
INSERT INTO profissional (nome, endereco, celular, residencial, funcao) values ('Leonardo Oliveira', 'Rua do Músico', '8594222020', null, 'Devops');
INSERT INTO profissional (nome, endereco, celular, residencial, funcao) values ('Isabela Martins', 'Rua da Terra', '8592229020', '33336652', 'fullstack');
