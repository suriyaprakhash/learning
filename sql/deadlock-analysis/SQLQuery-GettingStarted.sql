/** lists databases **/
SELECT name, database_id, create_date  
FROM sys.databases;  
GO  

/**table with **/
CREATE TABLE Person (
    ID int NOT NULL PRIMARY KEY,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Age int
);

/** table without index **/
CREATE TABLE Person (
    ID int NOT NULL,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Age int
);

/** list all tables **/
SELECT * FROM SYSOBJECTS 
WHERE
  xtype = 'U';
GO

SELECT
  *
FROM
  INFORMATION_SCHEMA.TABLES;
GO

/** select **/
select * from Person;

/** delete **/
delete from Person;

/** insert **/
INSERT INTO person (ID, FirstName, LastName, age) VALUES (1, 'John', 'Smith', 35);
INSERT INTO person (ID, FirstName, LastName, age) VALUES (2, 'Jane', 'Doe', 28);
-- Refer SQLQuery-InsertPerson.sql file for more details