alter table cofeeclicker_schema.partida
drop constraint partida_nombre_uk;


alter table cofeeclicker_schema.partida
    add constraint partida_nombre_uk
        unique (Correo, Nombre);

