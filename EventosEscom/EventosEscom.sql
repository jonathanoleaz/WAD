create database EventosEscom;
use EventosEscom;

create table Evento(
	idEvento int not null auto_increment
		primary key,
	nombreEvento varchar(50) not null,
	inicio datetime not null,
	fin datetime not null,
	observaciones text );
	
	
create table Asistente(
	idAsistente int not null auto_increment
		primary key,
	nombreAsistente varchar(50) not null,
	paternoAsistente varchar(50) not null,
	maternoAsistente varchar(50) not null,
	idEvento int not null ,
	emailAsistente varchar(50) not null,
	semestre int not null,
	genero char(1) not null,
	activo boolean not null,
	observaciones text,
    foreign key (idEvento) references Evento(idEvento)	);
	
	

	
	