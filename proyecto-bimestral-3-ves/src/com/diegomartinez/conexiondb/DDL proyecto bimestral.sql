create database if not exists proyecto_b3_2026166_in4av;
use proyecto_b3_2026166_in4av;

Create table usuario (
	id_usuario INT auto_increment primary key,
    nombre_completo VARCHAR(100) not null,
    usuario varchar(50) not null UNIQUE,
    correo varchar(100) not null unique,
    clave varchar(255) not null
);

delimiter $$
	create procedure sp_crear_usuario()
    
    begin
		insert into usuario()
			values(uuid());
    end $$
delimiter ;