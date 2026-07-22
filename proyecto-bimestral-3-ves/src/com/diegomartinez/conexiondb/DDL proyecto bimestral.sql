drop database if exists proyecto_b3_2026166_in4av;
create database proyecto_b3_2026166_in4av;
use proyecto_b3_2026166_in4av;

create table usuario (
	id_usuario INT auto_increment primary key,
    nombre_completo VARCHAR(100) not null,
    usuario varchar(50) not null UNIQUE,
    correo varchar(100) not null unique,
    clave varchar(255) not null
);
 
delimiter $$
	create procedure sp_crear_usuario(
			IN p_nombre_completo VARCHAR(100),
			IN p_usuario VARCHAR(50),
			IN p_correo VARCHAR(100),
			IN p_clave VARCHAR(255))
	begin
		insert into usuario (nombre_completo, usuario, correo, clave)
			values (p_nombre_completo, p_usuario, p_correo, p_clave);
	end $$
delimiter ;

delimiter $$
	create procedure sp_leer_usuario()
    begin
		select * from usuario;
    end $$
delimiter ;