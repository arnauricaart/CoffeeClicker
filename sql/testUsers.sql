use cofeeclicker_schema;
insert into users(Correo, Nombre, Contrasena) values ('tassani@gmail.com', 'TASSANI', 'botbotbot123');
insert into partida (IdPartida, Cafes, Correo, Terminada) values (2, 30, 'tassani@gmail.com', false);
insert into stats (SubStats, Cafes, Minuto, IdPartida) values (1, 1, 1, 2);

insert into generador(IdGenerador, Nombre, Coste, Produccion, Incr_Coste, Imagen) values (1, 'gen', 2.3, 4, 1.5, 'imagen');
insert into partida_generador(IdPartida_Generador, IdPartida, IdGenerador) values (2, 2, 1);
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (3, 'Hola', 30, 'tassani@gmail.com', false, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (4, 'gay', 12, 'tassani@gmail.com', false, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (5, 'maricon', 330, 'tassani@gmail.com', false, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (6, 'gluyer', 3450, 'tassani@gmail.com', false, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (7, 'bot',80, 'tassani@gmail.com', false, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (9, 'nigga', 78, 'tassani@gmail.com', false, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (8, 'nini', 0, 'tassani@gmail.com', false, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (10, 'hombre', 11, 'tassani@gmail.com', false, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (11, 'trabolo' ,1, 'tassani@gmail.com', false, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (12, 'pre',230, 'tassani@gmail.com', false, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (13, 'tigre', 90, 'tassani@gmail.com', false, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (14, 'dks' ,380, 'tassani@gmail.com', false, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (15, 'ffdf' ,1230, 'tassani@gmail.com', false, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (16, 'fdfdf' ,870, 'tassani@gmail.com', false, NOW());

/*
CREATE USER 'root'@'%' IDENTIFIED BY 'hola';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
*/