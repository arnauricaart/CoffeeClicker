use cofeeclicker_schema;
insert into users(Correo, Nombre, Contrasena) values ('tassani@gmail.com', 'TASSANI', 'botbotbot123');
insert into partida (IdPartida, Cafes, Correo, Terminada) values (2, 30, 'tassani@gmail.com', false);
insert into stats (SubStats, Cafes, Minubo, IdPartida) values (1, 1, 1, 2);
insert into generador(IdGenerador, Nombre, Coste, Produccion, Incr_Coste, Imagen) values (1, 'gen', 2.3, 4, 1.5, 'imagen');
insert into partida_generador(IdPartida_Generador, IdPartida, IdGenerador) values (2, 2, 1);

insert into partida (IdPartida, Cafes, Correo, Terminada) values (3, 30, 'tassani@gmail.com', false);
insert into partida (IdPartida, Cafes, Correo, Terminada) values (4, 12, 'tassani@gmail.com', false);
insert into partida (IdPartida, Cafes, Correo, Terminada) values (5, 330, 'tassani@gmail.com', false);
insert into partida (IdPartida, Cafes, Correo, Terminada) values (6, 3450, 'tassani@gmail.com', false);
insert into partida (IdPartida, Cafes, Correo, Terminada) values (7, 80, 'tassani@gmail.com', false);
insert into partida (IdPartida, Cafes, Correo, Terminada) values (9, 78, 'tassani@gmail.com', false);
insert into partida (IdPartida, Cafes, Correo, Terminada) values (8, 0, 'tassani@gmail.com', false);
insert into partida (IdPartida, Cafes, Correo, Terminada) values (10, 11, 'tassani@gmail.com', false);
insert into partida (IdPartida, Cafes, Correo, Terminada) values (11, 1, 'tassani@gmail.com', false);
insert into partida (IdPartida, Cafes, Correo, Terminada) values (12, 230, 'tassani@gmail.com', false);
insert into partida (IdPartida, Cafes, Correo, Terminada) values (13, 90, 'tassani@gmail.com', false);
insert into partida (IdPartida, Cafes, Correo, Terminada) values (14, 380, 'tassani@gmail.com', false);
insert into partida (IdPartida, Cafes, Correo, Terminada) values (15, 1230, 'tassani@gmail.com', false);
insert into partida (IdPartida, Cafes, Correo, Terminada) values (16, 870, 'tassani@gmail.com', false);

/*
CREATE USER 'root'@'%' IDENTIFIED BY 'hola';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
*/