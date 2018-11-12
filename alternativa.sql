--Database: Cella

--DROP DATABASE "cella";
--create database "cella";
drop schema if exists cella cascade;
create schema cella;

begin;

drop table if exists cella.material cascade;
create table cella.material(
    id serial primary key,
    nombreMaterial text not null unique,
    descripcion text not null,
    foto bytea
);

drop table if exists cella.alumno cascade;
create table cella.alumno(
	id serial primary key, 
	nombreUsuario text not null unique, 
	correo text not null unique,
	contrasena text not null, 
	nombre text not null, 
	apellidoP text not null,
	apellidoM text not null, 
	foto bytea,
	edoCuenta boolean not null
 );

drop table if exists cella.profesor cascade;
create table cella.profesor(
	id serial primary key,
	nombreUsuario text not null, 
	correo text not null unique,
	contrasena text not null, 
	nombre text not null, 
	apellidoP text not null,
	apellidoM text not null, 
	foto bytea,
	edoCuenta boolean not null,
	rfc varchar(13) not null,
	noTrabajador varchar not null
);

drop table if exists cella.administrador cascade;
create table cella.administrador(
	id serial primary key,
	nombreUsuario text not null, 
	correo text not null unique,
	contrasena text not null, 
	nombre text not null, 
	apellidoP text not null,
	apellidoM text not null, 
	foto bytea,
	rfc varchar(13) not null
);

drop table if exists cella.categoria cascade;
create table cella.categoria(
	id serial primary key,    
	nombreCategoria text not null unique,
	descripcion text not null
);
-- 
drop table if exists cella.kit cascade;

create table cella.kit(
	id serial primary key,
	nombreKit text not null,
	materia text not null,
	id_profesor int references cella.profesor(id)on delete cascade
);

drop table if exists cella.unidad_material cascade;

create table cella.unidad_material(
	id serial primary key,
	nombreMaterial text not null unique,
	estado text not null check (estado in ('disponible','no disponible','en mantenimiento')),
        id_material int not null references cella.material(id),
	foto bytea
);
						
drop table if exists cella.solicitar_prestamo_profesor cascade;

create table cella.solicitar_prestamo_profesor(
	id serial primary key,	
	id_unidad_material int references cella.unidad_material(id) on delete cascade,
	fechainicio date not null
);

drop table if exists cella.solicitar_prestamo_alumno cascade;

create table cella.solicitar_prestamo_alumno(
	id serial primary key,	
	id_unidad_material int not null references cella.unidad_material(id) on delete cascade,    
	fechainicio date not null
);

drop table if exists cella.contener_kit_material cascade;

create table cella.contener_kit_material(
	id serial primary key,
	nombreMaterial text,
	nombreKit text,
	noUnidades int not null,
        id_material int not null references cella.material(id)
);
						
						
drop table if exists cella.pertenecer_material_categoria cascade;
		
create table cella.pertenecer_material_categoria(
	id serial primary key,    
	nombreMaterial text,
	nombreCategoria text,
        id_material int not null references cella.material(id),
        id_categoria int not null references cella.categoria(id)
);

drop table if exists cella.subcategorias cascade;
	
create table cella.subcategorias(
        id serial primary key,
	nombreCategoria text,
	nombreSubcategoria text,
        id_categoria int not null references cella.categoria(id)
);
commit;					
