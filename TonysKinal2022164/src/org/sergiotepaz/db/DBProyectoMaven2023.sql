Drop database if exists DBProyectoMaven2023;
create database DBProyectoMaven2023;

use DBProyectoMaven2023;

create table Persona(
	codigoPersona int not null,
    nombrePersona varchar(150) not null,
    apellidoPersona varchar(150) not null,
    primary key PK_codigoPersona (codigoPersona)
);

Select * from Persona;

Insert into Persona(codigoPersona, nombrePersona, apellidoPersona)
		values(1,'Axel','Xitumul');
Insert into Persona(codigoPersona, nombrePersona, apellidoPersona)
		values(2,'Jonathan','Jolón');