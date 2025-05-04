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

-- Partida 20 (22 values) - Exponential growth
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(70, 10, 1, 20), (71, 20, 2, 20), (72, 40, 3, 20), (73, 80, 4, 20), (74, 160, 5, 20),
(75, 320, 6, 20), (76, 640, 7, 20), (77, 1280, 8, 20), (78, 2560, 9, 20), (79, 5120, 10, 20),
(80, 10240, 11, 20), (81, 20480, 12, 20), (82, 40960, 13, 20), (83, 81920, 14, 20), (84, 163840, 15, 20),
(85, 327680, 16, 20), (86, 655360, 17, 20), (87, 1310720, 18, 20), (88, 2621440, 19, 20), (89, 5242880, 20, 20),
(90, 10485760, 21, 20), (91, 20971520, 22, 20);

-- Partida 32 (104 values) - Steady increase
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(92, 100, 1, 32), (93, 150, 2, 32), (94, 200, 3, 32), (95, 250, 4, 32), (96, 300, 5, 32),
(97, 350, 6, 32), (98, 400, 7, 32), (99, 450, 8, 32), (100, 500, 9, 32), (101, 550, 10, 32);
-- Continue with steady increase pattern for remaining values
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(102, 600, 11, 32), (103, 650, 12, 32), (104, 700, 13, 32), (105, 750, 14, 32), (106, 800, 15, 32),
(107, 850, 16, 32), (108, 900, 17, 32), (109, 950, 18, 32), (110, 1000, 19, 32), (111, 1050, 20, 32);
-- Continue pattern...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(112, 1100, 21, 32), (113, 1150, 22, 32), (114, 1200, 23, 32), (115, 1250, 24, 32), (116, 1300, 25, 32),
(117, 1350, 26, 32), (118, 1400, 27, 32), (119, 1450, 28, 32), (120, 1500, 29, 32), (121, 1550, 30, 32);
-- Continue pattern...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(122, 1600, 31, 32), (123, 1650, 32, 32), (124, 1700, 33, 32), (125, 1750, 34, 32), (126, 1800, 35, 32),
(127, 1850, 36, 32), (128, 1900, 37, 32), (129, 1950, 38, 32), (130, 2000, 39, 32), (131, 2050, 40, 32);
-- Continue pattern...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(132, 2100, 41, 32), (133, 2150, 42, 32), (134, 2200, 43, 32), (135, 2250, 44, 32), (136, 2300, 45, 32),
(137, 2350, 46, 32), (138, 2400, 47, 32), (139, 2450, 48, 32), (140, 2500, 49, 32), (141, 2550, 50, 32);
-- Continue pattern...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(142, 2600, 51, 32), (143, 2650, 52, 32), (144, 2700, 53, 32), (145, 2750, 54, 32), (146, 2800, 55, 32),
(147, 2850, 56, 32), (148, 2900, 57, 32), (149, 2950, 58, 32), (150, 3000, 59, 32), (151, 3050, 60, 32);
-- Continue pattern...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(152, 3100, 61, 32), (153, 3150, 62, 32), (154, 3200, 63, 32), (155, 3250, 64, 32), (156, 3300, 65, 32),
(157, 3350, 66, 32), (158, 3400, 67, 32), (159, 3450, 68, 32), (160, 3500, 69, 32), (161, 3550, 70, 32);
-- Continue pattern...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(162, 3600, 71, 32), (163, 3650, 72, 32), (164, 3700, 73, 32), (165, 3750, 74, 32), (166, 3800, 75, 32),
(167, 3850, 76, 32), (168, 3900, 77, 32), (169, 3950, 78, 32), (170, 4000, 79, 32), (171, 4050, 80, 32);
-- Continue pattern...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(172, 4100, 81, 32), (173, 4150, 82, 32), (174, 4200, 83, 32), (175, 4250, 84, 32), (176, 4300, 85, 32),
(177, 4350, 86, 32), (178, 4400, 87, 32), (179, 4450, 88, 32), (180, 4500, 89, 32), (181, 4550, 90, 32);
-- Continue pattern...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(182, 4600, 91, 32), (183, 4650, 92, 32), (184, 4700, 93, 32), (185, 4750, 94, 32), (186, 4800, 95, 32),
(187, 4850, 96, 32), (188, 4900, 97, 32), (189, 4950, 98, 32), (190, 5000, 99, 32), (191, 5050, 100, 32),
(192, 5100, 101, 32), (193, 5150, 102, 32), (194, 5200, 103, 32), (195, 5250, 104, 32);

-- Partida 36 (168 values) - Increase then decrease
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(196, 100, 1, 36), (197, 200, 2, 36), (198, 400, 3, 36), (199, 800, 4, 36), (200, 1600, 5, 36),
(201, 3200, 6, 36), (202, 6400, 7, 36), (203, 12800, 8, 36), (204, 25600, 9, 36), (205, 51200, 10, 36);
-- Continue with peak and decline pattern for remaining values
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(206, 102400, 11, 36), (207, 204800, 12, 36), (208, 409600, 13, 36), (209, 819200, 14, 36), (210, 1638400, 15, 36),
(211, 3276800, 16, 36), (212, 6553600, 17, 36), (213, 13107200, 18, 36), (214, 26214400, 19, 36), (215, 52428800, 20, 36);
-- Peak reached, start decline
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(216, 26214400, 21, 36), (217, 13107200, 22, 36), (218, 6553600, 23, 36), (219, 3276800, 24, 36), (220, 1638400, 25, 36),
(221, 819200, 26, 36), (222, 409600, 27, 36), (223, 204800, 28, 36), (224, 102400, 29, 36), (225, 51200, 30, 36);
-- Continue decline pattern...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(226, 25600, 31, 36), (227, 12800, 32, 36), (228, 6400, 33, 36), (229, 3200, 34, 36), (230, 1600, 35, 36),
(231, 800, 36, 36), (232, 400, 37, 36), (233, 200, 38, 36), (234, 100, 39, 36), (235, 50, 40, 36);
-- Continue pattern with slower decline...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(236, 45, 41, 36), (237, 40, 42, 36), (238, 35, 43, 36), (239, 30, 44, 36), (240, 25, 45, 36),
(241, 20, 46, 36), (242, 15, 47, 36), (243, 10, 48, 36), (244, 5, 49, 36), (245, 4, 50, 36);
-- Continue with minimal values...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(246, 3, 51, 36), (247, 2, 52, 36), (248, 1, 53, 36), (249, 1, 54, 36), (250, 1, 55, 36),
(251, 1, 56, 36), (252, 1, 57, 36), (253, 1, 58, 36), (254, 1, 59, 36), (255, 1, 60, 36);
-- Continue pattern for remaining values...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(256, 1, 61, 36), (257, 1, 62, 36), (258, 1, 63, 36), (259, 1, 64, 36), (260, 1, 65, 36),
(261, 1, 66, 36), (262, 1, 67, 36), (263, 1, 68, 36), (264, 1, 69, 36), (265, 1, 70, 36);
-- Continue pattern...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(266, 1, 71, 36), (267, 1, 72, 36), (268, 1, 73, 36), (269, 1, 74, 36), (270, 1, 75, 36),
(271, 1, 76, 36), (272, 1, 77, 36), (273, 1, 78, 36), (274, 1, 79, 36), (275, 1, 80, 36);
-- Continue pattern until 168 values...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(276, 1, 81, 36), (277, 1, 82, 36), (278, 1, 83, 36), (279, 1, 84, 36), (280, 1, 85, 36),
(281, 1, 86, 36), (282, 1, 87, 36), (283, 1, 88, 36), (284, 1, 89, 36), (285, 1, 90, 36);
-- Continue pattern...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(286, 1, 91, 36), (287, 1, 92, 36), (288, 1, 93, 36), (289, 1, 94, 36), (290, 1, 95, 36),
(291, 1, 96, 36), (292, 1, 97, 36), (293, 1, 98, 36), (294, 1, 99, 36), (295, 1, 100, 36);
-- Continue until 168...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(296, 1, 101, 36), (297, 1, 102, 36), (298, 1, 103, 36), (299, 1, 104, 36), (300, 1, 105, 36),
(301, 1, 106, 36), (302, 1, 107, 36), (303, 1, 108, 36), (304, 1, 109, 36), (305, 1, 110, 36);
-- Continue pattern...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(306, 1, 111, 36), (307, 1, 112, 36), (308, 1, 113, 36), (309, 1, 114, 36), (310, 1, 115, 36),
(311, 1, 116, 36), (312, 1, 117, 36), (313, 1, 118, 36), (314, 1, 119, 36), (315, 1, 120, 36);
-- Final values...
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(316, 1, 121, 36), (317, 1, 122, 36), (318, 1, 123, 36), (319, 1, 124, 36), (320, 1, 125, 36),
(321, 1, 126, 36), (322, 1, 127, 36), (323, 1, 128, 36), (324, 1, 129, 36), (325, 1, 130, 36),
(326, 1, 131, 36), (327, 1, 132, 36), (328, 1, 133, 36), (329, 1, 134, 36), (330, 1, 135, 36),
(331, 1, 136, 36), (332, 1, 137, 36), (333, 1, 138, 36), (334, 1, 139, 36), (335, 1, 140, 36),
(336, 1, 141, 36), (337, 1, 142, 36), (338, 1, 143, 36), (339, 1, 144, 36), (340, 1, 145, 36),
(341, 1, 146, 36), (342, 1, 147, 36), (343, 1, 148, 36), (344, 1, 149, 36), (345, 1, 150, 36),
(346, 1, 151, 36), (347, 1, 152, 36), (348, 1, 153, 36), (349, 1, 154, 36), (350, 1, 155, 36),
(351, 1, 156, 36), (352, 1, 157, 36), (353, 1, 158, 36), (354, 1, 159, 36), (355, 1, 160, 36),
(356, 1, 161, 36), (357, 1, 162, 36), (358, 1, 163, 36), (359, 1, 164, 36), (360, 1, 165, 36),
(361, 1, 166, 36), (362, 1, 167, 36), (363, 1, 168, 36);

-- Partida 39 (5 values) - Fluctuating pattern
INSERT INTO stats (SubStats, Cafes, Minuto, IdPartida) VALUES 
(364, 1000, 1, 39), (365, 500, 2, 39), (366, 2000, 3, 39), (367, 100, 4, 39), (368, 1500, 5, 39);