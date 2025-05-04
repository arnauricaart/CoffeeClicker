use cofeeclicker_schema;
insert into users(Correo, Nombre, Contrasena) values ('tassani@gmail.com', 'TASSANI', 'botbotbot123');
insert into users(Correo, Nombre, Contrasena) values ('dummy@nodomain.com', 'Dummy', '123');
insert into users(Correo, Nombre, Contrasena) values ('ducky@nodomain.com', 'Ducky', '123');
insert into users(Correo, Nombre, Contrasena) values ('peip@nodomain.com', 'Peip', '123');

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

/*acabades*/
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (17, 'Amapola', 30, 'tassani@gmail.com', true, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (18, 'rei', 12, 'tassani@gmail.com', true, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (19, 'fdfd', 330, 'tassani@gmail.com', true, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (20, 'iaa', 3450, 'tassani@gmail.com', true, NOW());

/* Games for Dummy */
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (21, 'Red', 450, 'dummy@nodomain.com', true, '2024-04-28 10:15:30');
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (22, 'Blue', 780, 'dummy@nodomain.com', true, '2024-04-29 15:45:20');
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (23, 'Green', 920, 'dummy@nodomain.com', true, '2024-04-30 09:30:45');
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (24, 'Yellow', 1200, 'dummy@nodomain.com', true, '2024-05-01 14:20:15');

/* Games for Ducky */
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (25, 'Purple', 550, 'ducky@nodomain.com', true, '2024-05-01 16:40:10');
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (26, 'Orange', 890, 'ducky@nodomain.com', true, '2024-05-02 11:25:40');
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (27, 'Pink', 670, 'ducky@nodomain.com', true, '2024-05-03 08:55:30');
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (28, 'Brown', 1500, 'ducky@nodomain.com', true, '2024-05-03 17:10:25');

/* Games for Peip */
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (29, 'Cyan', 820, 'peip@nodomain.com', true, '2024-05-02 13:35:50');
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (30, 'Magenta', 990, 'peip@nodomain.com', true, '2024-05-03 10:45:15');
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (31, 'Gold', 1100, 'peip@nodomain.com', true, '2024-05-04 09:15:30');
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (32, 'Silver', 750, 'peip@nodomain.com', true, '2024-05-04 15:50:20');

/*
CREATE USER 'root'@'%' IDENTIFIED BY 'hola';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
*/