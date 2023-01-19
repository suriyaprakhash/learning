/** lists databases **/
SELECT name, database_id, create_date  
FROM sys.databases;  
GO  

/**table with **/
CREATE TABLE Persons (
    ID int NOT NULL PRIMARY KEY,
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
select * from Persons;

/** delete **/
delete from Persons;

/** insert **/
insert into Persons (ID, LastName, FirstName, Age) values (1, 'Suriya', 'Prakhash', 31);
insert into Persons (ID, LastName, FirstName, Age) values (2, 'Renu', 'Prakhash', 26);


/** Transcation 1 **/
BEGIN TRAN one;
update Persons set FirstName='one first' where ID = 1;
update Persons set LastName='one last' where ID = 1;
ROLLBACK TRAN one;
COMMIT TRAN one;


/** Transcation 2 **/
BEGIN TRAN two;
update Persons set FirstName='two first' where ID = 1;
update Persons set LastName='two last' where ID = 1;
ROLLBACK TRAN two;
COMMIT TRAN two;


/** Transcation Session 1 **/
BEGIN TRAN one;
update Persons set FirstName='one id1 first transcation' where ID = 1;
update Persons set FirstName='one id2 first transcation' where ID = 2;
ROLLBACK TRAN one;
COMMIT TRAN one;


/** Check if any uncommited transactions **/
SELECT 
er.session_id
,er.open_transaction_count
FROM sys.dm_exec_requests er
