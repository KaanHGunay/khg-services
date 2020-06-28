CREATE DATABASE user_database;
USE user_database;
create table users(
                          id INT NOT NULL AUTO_INCREMENT,
                          FIRSTNAME VARCHAR(100) NOT NULL,
                          LASTNAME VARCHAR(40) NOT NULL,
                          EMAIL VARCHAR(40) UNIQUE NOT NULL,
                          USERID VARCHAR(50) NOT NULL,
                          ENCRYPTEDPASSWORD         VARCHAR(500) not null ,
                          PRIMARY KEY ( id )
);
