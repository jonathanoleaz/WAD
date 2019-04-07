drop database inventarios;
create DATABASE inventarios;
use inventarios;

create TABLE categoria(
idcategoria int not null auto_increment PRIMARY KEY,
descripcion VARCHAR(30) not null unique,
precio float not null,
existencia int default 50
);


create TABLE articulo(
claveart int not null auto_increment PRIMARY KEY,
descripcion VARCHAR(30) not null unique,
precio float not null,
existencia int default 50,
idcategoria INT DEFAULT NULL,
	FOREIGN KEY(idcategoria)
		REFERENCES categoria(idcategoria)
);


create TABLE movimientoarticulo(
folio int not null auto_increment PRIMARY KEY,
fecha VARCHAR(40) not null,
tipo VARCHAR(100) not null,
cantidad int,
claveart int not null,
 FOREIGN KEY(claveart)
	REFERENCES articulo(claveart)
);

