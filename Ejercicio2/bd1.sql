create DATABASE instituto;
use instituto;

create TABLE carrera(
idcarrera int not null auto_increment PRIMARY KEY,
nombrecarrera VARCHAR(30) not null,
descripcion VARCHAR(30) not null,
duracion int
);

create table alumno(
noboleta int not null auto_increment PRIMARY KEY,
nombre VARCHAR(30) not null,
materno VARCHAR(30) not null,
paterno VARCHAR(30) not null,
domicilio VARCHAR(150) not null,
email VARCHAR(30) NOT NULL,
idcarrera int not null,
FOREIGN KEY(idcarrera) 
	REFERENCES carrera(idcarrera)
		on update cascade
		on delete cascade
);

load data local infile 'C:/Users/jonat/Dropbox/WAD/Ejercicio2/carrera.csv' into table carrera
 fields terminated by ','
 enclosed by '"'
 lines terminated by '\n'
 ignore 1 lines
 (nombrecarrera, descripcion, duracion);
 
 
 load data local infile 'C:/Users/jonat/Dropbox/WAD/Ejercicio2/alumno.csv' into table alumno
 fields terminated by ','
 enclosed by '"'
 lines terminated by '\n'
 ignore 1 lines
 (nombre, materno, paterno, domicilio, email, idcarrera);

INSERT INTO carrera(nombrecarrera, descripcion, duracion) VALUES("DD", "DD", 5);

DELETE FROM carrera WHERE idcarrera=1;


UPDATE carrera SET nombrecarrera='', descripcion='', duracion='' WHERE idcarrera=4;

