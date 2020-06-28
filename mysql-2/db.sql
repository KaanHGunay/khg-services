CREATE DATABASE details;
USE details;
create table personnel_details(
   id INT NOT NULL AUTO_INCREMENT,
   registry VARCHAR(40) UNIQUE NOT NULL,
   place_of_birth VARCHAR(40) NOT NULL,
   job VARCHAR(40) NOT NULL,
   PRIMARY KEY ( id )
);
INSERT INTO personnel_details (registry, place_of_birth, job) VALUES ("1", "Bursa", "Unemployment");