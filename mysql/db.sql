CREATE DATABASE personnelDatabase;
USE personnelDatabase;
create table personnel(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(100) NOT NULL,
   surname VARCHAR(40) NOT NULL,
   registry VARCHAR(40) NOT NULL,
   PRIMARY KEY ( id )
);
INSERT INTO personnel (name, surname, registry) values ("Kaan Han", "Gunay", "1");