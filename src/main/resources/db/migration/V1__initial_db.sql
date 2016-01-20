/*
	Set the initial state: remove existing
	
	from the CLI:
	flyway -baselineOnMigrate=true -url=jdbc:mysql://localhost/ -schemas=sampledb -user=newuser -password=Passw0rd migrate
	
	extra possible argument: -locations=filesystem:/path/to/scrips/
	
DROP SCHEMA IF EXISTS `sampledb` ;
CREATE SCHEMA IF NOT EXISTS `sampledb` COLLATE utf8_general_ci ;
*/


/* 
	Create the contacts table  
*/
CREATE TABLE contacts (
  id bigint auto_increment NOT NULL, 
  name varchar(128) NOT NULL,
  PRIMARY KEY(id)
);