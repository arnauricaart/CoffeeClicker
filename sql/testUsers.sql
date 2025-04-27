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

/*acabades*/
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (17, 'Amapola', 30, 'tassani@gmail.com', true, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (18, 'rei', 12, 'tassani@gmail.com', true, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (19, 'fdfd', 330, 'tassani@gmail.com', true, NOW());
insert into partida (IdPartida, Nombre, Cafes, Correo, Terminada, UltimoAcceso) values (20, 'iaa', 3450, 'tassani@gmail.com', true, NOW());



/* Sample stats for different partidas showing progression over time */
-- Game 3 (Hola) - Progressive growth
insert into stats (Cafes, Minuto, IdPartida) values (10, 1, 3);
insert into stats (Cafes, Minuto, IdPartida) values (20, 2, 3);
insert into stats (Cafes, Minuto, IdPartida) values (50, 3, 3);
insert into stats (Cafes, Minuto, IdPartida) values (100, 4, 3);
insert into stats (Cafes, Minuto, IdPartida) values (200, 5, 3);

-- Game 17 (Amapola) - Slower progression with 15 data points
insert into stats (Cafes, Minuto, IdPartida) values (5, 1, 17);
insert into stats (Cafes, Minuto, IdPartida) values (8, 2, 17);
insert into stats (Cafes, Minuto, IdPartida) values (12, 3, 17);
insert into stats (Cafes, Minuto, IdPartida) values (15, 4, 17);
insert into stats (Cafes, Minuto, IdPartida) values (18, 5, 17);
insert into stats (Cafes, Minuto, IdPartida) values (22, 6, 17);
insert into stats (Cafes, Minuto, IdPartida) values (25, 7, 17);
insert into stats (Cafes, Minuto, IdPartida) values (30, 8, 17);
insert into stats (Cafes, Minuto, IdPartida) values (35, 9, 17);
insert into stats (Cafes, Minuto, IdPartida) values (40, 10, 17);
insert into stats (Cafes, Minuto, IdPartida) values (50, 11, 17);
insert into stats (Cafes, Minuto, IdPartida) values (70, 12, 17);
insert into stats (Cafes, Minuto, IdPartida) values (100, 13, 17);
insert into stats (Cafes, Minuto, IdPartida) values (130, 14, 17);
insert into stats (Cafes, Minuto, IdPartida) values (150, 15, 17);

-- Game 5 (maricon) - Fluctuating progression
insert into stats (Cafes, Minuto, IdPartida) values (500, 1, 5);
insert into stats (Cafes, Minuto, IdPartida) values (15, 2, 5);
insert into stats (Cafes, Minuto, IdPartida) values (100, 3, 5);
insert into stats (Cafes, Minuto, IdPartida) values (70, 4, 5);
insert into stats (Cafes, Minuto, IdPartida) values (150, 5, 5);

-- Game 6 (gluyer) - Rapid growth
insert into stats (Cafes, Minuto, IdPartida) values (1000, 1, 6);
insert into stats (Cafes, Minuto, IdPartida) values (105, 2, 6);
insert into stats (Cafes, Minuto, IdPartida) values (370, 3, 6);
insert into stats (Cafes, Minuto, IdPartida) values (70, 4, 6);
insert into stats (Cafes, Minuto, IdPartida) values (500, 5, 6);

-- Game 7 (bot) - Starting slow then growing
insert into stats (Cafes, Minuto, IdPartida) values (0, 1, 7);
insert into stats (Cafes, Minuto, IdPartida) values (100, 2, 7);
insert into stats (Cafes, Minuto, IdPartida) values (200, 3, 7);
insert into stats (Cafes, Minuto, IdPartida) values (300, 4, 7);
insert into stats (Cafes, Minuto, IdPartida) values (134, 5, 7);

-- Game 9 (nigga) - Moderate progression
insert into stats (Cafes, Minuto, IdPartida) values (10, 1, 9);
insert into stats (Cafes, Minuto, IdPartida) values (25, 2, 9);
insert into stats (Cafes, Minuto, IdPartida) values (45, 3, 9);
insert into stats (Cafes, Minuto, IdPartida) values (65, 4, 9);
insert into stats (Cafes, Minuto, IdPartida) values (78, 5, 9);

-- Game 8 (nini) - No progress
insert into stats (Cafes, Minuto, IdPartida) values (0, 1, 8);
insert into stats (Cafes, Minuto, IdPartida) values (0, 2, 8);
insert into stats (Cafes, Minuto, IdPartida) values (0, 3, 8);
insert into stats (Cafes, Minuto, IdPartida) values (0, 4, 8);
insert into stats (Cafes, Minuto, IdPartida) values (0, 5, 8);

-- Game 10 (hombre) - Slow steady growth
insert into stats (Cafes, Minuto, IdPartida) values (2, 1, 10);
insert into stats (Cafes, Minuto, IdPartida) values (5, 2, 10);
insert into stats (Cafes, Minuto, IdPartida) values (8, 3, 10);
insert into stats (Cafes, Minuto, IdPartida) values (10, 4, 10);
insert into stats (Cafes, Minuto, IdPartida) values (11, 5, 10);

-- Game 11 (trabolo) - Minimal progress
insert into stats (Cafes, Minuto, IdPartida) values (0, 1, 11);
insert into stats (Cafes, Minuto, IdPartida) values (0, 2, 11);
insert into stats (Cafes, Minuto, IdPartida) values (0, 3, 11);
insert into stats (Cafes, Minuto, IdPartida) values (1, 4, 11);
insert into stats (Cafes, Minuto, IdPartida) values (1, 5, 11);

-- Game 12 (pre) - Steady growth
insert into stats (Cafes, Minuto, IdPartida) values (50, 1, 12);
insert into stats (Cafes, Minuto, IdPartida) values (100, 2, 12);
insert into stats (Cafes, Minuto, IdPartida) values (150, 3, 12);
insert into stats (Cafes, Minuto, IdPartida) values (200, 4, 12);
insert into stats (Cafes, Minuto, IdPartida) values (230, 5, 12);

-- Game 13 (tigre) - Moderate growth
insert into stats (Cafes, Minuto, IdPartida) values (20, 1, 13);
insert into stats (Cafes, Minuto, IdPartida) values (40, 2, 13);
insert into stats (Cafes, Minuto, IdPartida) values (60, 3, 13);
insert into stats (Cafes, Minuto, IdPartida) values (80, 4, 13);
insert into stats (Cafes, Minuto, IdPartida) values (90, 5, 13);

-- Game 14 (dks) - Rapid early growth
insert into stats (Cafes, Minuto, IdPartida) values (100, 1, 117);
insert into stats (Cafes, Minuto, IdPartida) values (200, 2, 117);
insert into stats (Cafes, Minuto, IdPartida) values (300, 3, 117);
insert into stats (Cafes, Minuto, IdPartida) values (350, 4, 117);
insert into stats (Cafes, Minuto, IdPartida) values (380, 5, 117);

-- Game 15 (ffdf) - Exponential growth
insert into stats (Cafes, Minuto, IdPartida) values (100, 1, 15);
insert into stats (Cafes, Minuto, IdPartida) values (300, 2, 15);
insert into stats (Cafes, Minuto, IdPartida) values (700, 3, 15);
insert into stats (Cafes, Minuto, IdPartida) values (1000, 4, 15);
insert into stats (Cafes, Minuto, IdPartida) values (1230, 5, 15);

-- Game 16 (fdfdf) - Steady growth with plateau
insert into stats (Cafes, Minuto, IdPartida) values (200, 1, 16);
insert into stats (Cafes, Minuto, IdPartida) values (400, 2, 16);
insert into stats (Cafes, Minuto, IdPartida) values (600, 3, 16);
insert into stats (Cafes, Minuto, IdPartida) values (800, 4, 16);
insert into stats (Cafes, Minuto, IdPartida) values (870, 5, 16);

/*
CREATE USER 'root'@'%' IDENTIFIED BY 'hola';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
*/