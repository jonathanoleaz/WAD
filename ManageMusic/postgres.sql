CREATE DATABASE musica;
\c musica;


CREATE TABLE customer
(customerid SERIAL PRIMARY KEY,
firstname 	VARCHAR,
lastname 	VARCHAR,
company 	VARCHAR,
address 	VARCHAR,
city 		VARCHAR,
state 		VARCHAR,
country 	VARCHAR,
postalcode 	VARCHAR,
phone 		VARCHAR,
fax 		VARCHAR);


CREATE TABLE invoice
(invoiceid SERIAL 	PRIMARY KEY,
customerid SERIAL 	REFERENCES customer(customerid),
invoicedate 		TIMESTAMP,
billingaddress 		VARCHAR,
billingcity 		VARCHAR,
billingstate 		VARCHAR,
billingcountry 		VARCHAR,
billingpostalcode 	VARCHAR,
total 				DECIMAL);

CREATE TABLE artist
(artistid SERIAL PRIMARY KEY,
name VARCHAR
);

CREATE TABLE album
(albumid SERIAL PRIMARY KEY,
artistid SERIAL REFERENCES artist(artistid),
name VARCHAR
);

CREATE TABLE genre
(genreid 	 SERIAL PRIMARY KEY,
name		 VARCHAR
);

CREATE TABLE mediatype
(mediatypeid 	SERIAL PRIMARY KEY,
name 			VARCHAR
);

CREATE TABLE track
(trackid 	SERIAL PRIMARY KEY,
albumid 	SERIAL REFERENCES album(albumid),
mediatypeid SERIAL REFERENCES mediatype(mediatypeid),
genreid 	SERIAL REFERENCES genre(genreid),
name 		VARCHAR,
composer 	VARCHAR,
miliseconds DECIMAL,
bytes 		INTEGER,
unitprice 	DECIMAL
);

CREATE TABLE playlist
(
playlistid 	SERIAL PRIMARY KEY,
name 		SERIAL REFERENCES mediatype(mediatypeid)
);


CREATE TABLE playlisttrack
(
playlisttrackid SERIAL PRIMARY KEY,
playlistid 	SERIAL REFERENCES album(albumid),
trackid 	SERIAL REFERENCES mediatype(mediatypeid)
);



CREATE TABLE invoiceline
(invoicelineid 	SERIAL PRIMARY KEY,
invoiceid 		SERIAL REFERENCES invoice(invoiceid),
trackid 		SERIAL REFERENCES track(trackid),
unitprice 		DECIMAL,
quantity 		SMALLINT);