/**********************************************************************/
/* Create the master key */
CREATE MASTER KEY ENCRYPTION BY   
PASSWORD = 'masterKeyPassword'; 

/* Drop the master key */
DROP MASTER KEY;

/* Show symmetric key - master key */
select * from sys.symmetric_keys

/**********************************************************************/
/* Create a certificate encrypted by master key */
CREATE CERTIFICATE PersonCert  
WITH SUBJECT = 'Person certificate';  
GO  

/* Drop the master key */
DROP CERTIFICATE PersonCert

/* Show certificates */
select * from sys.certificates

/**********************************************************************/
/* Create symmetric key encryption key using the the created certificate*/
CREATE SYMMETRIC KEY Person_SymmetricKey  
WITH ALGORITHM = AES_256  
ENCRYPTION BY CERTIFICATE PersonCert;  
GO

/* Drop the master key */
DROP SYMMETRIC KEY Person_SymmetricKey;

/* Show symmetric key */
select * from sys.symmetric_keys

/**********************************************************************/
/* Create table person */
CREATE TABLE Person
(
    PersonID int,
    FirstName varchar(50),
    LastName varchar(50),
	Password_Encrypted varbinary(256), -- equals to varchar(256)
    SSN_Encrypted varbinary(256) -- equals to varchar(256)
);

/* Delete rows */
delete from Person;

/* drop table */
drop table Person;

/**********************************************************************/
/* The following is not correct */
INSERT INTO Person (PersonID, FirstName, LastName, Password_Encrypted, SSN_Encrypted)
VALUES (1, 'John', 'Doe',  'MyPassword', '123-45-6789');

INSERT INTO Person (PersonID, FirstName, LastName, Password_Encrypted, SSN_Encrypted)
VALUES (1, 'John', 'Doe', ENCRYPTBYKEY(KEY_GUID('Person_SymmetricKey'), 'MyPassword'),
ENCRYPTBYKEY(KEY_GUID('Person_SymmetricKey'), '123-45-6789'));

/**********************************************************************/
/* Open symmetric key for encryption */
OPEN SYMMETRIC KEY Person_SymmetricKey
DECRYPTION BY CERTIFICATE PersonCert;

INSERT INTO Person (PersonID, FirstName, LastName, Password_Encrypted, SSN_Encrypted)
VALUES (1, 'Suriya', 'Prakhash', 
ENCRYPTBYKEY(KEY_GUID('Person_SymmetricKey'), 'Suriya'), 
ENCRYPTBYKEY(KEY_GUID('Person_SymmetricKey'), '123-45-1234'));

INSERT INTO Person (PersonID, FirstName, LastName, Password_Encrypted, SSN_Encrypted)
VALUES (1, 'Renu', 'Prakhash', 
ENCRYPTBYKEY(KEY_GUID('Person_SymmetricKey'), 'Renu'), 
ENCRYPTBYKEY(KEY_GUID('Person_SymmetricKey'), '234-45-4355'));

INSERT INTO Person (PersonID, FirstName, LastName, Password_Encrypted, SSN_Encrypted)
VALUES (1, 'Arjun', 'Raj', 
ENCRYPTBYKEY(KEY_GUID('Person_SymmetricKey'), 'Arjun'), 
ENCRYPTBYKEY(KEY_GUID('Person_SymmetricKey'), '123-45-3464'));

CLOSE SYMMETRIC KEY Person_SymmetricKey;

/**********************************************************************/
/* the following will show NULL values for the encrypted data column */
SELECT TOP (1000) [PersonID]
    ,[FirstName]
    ,[LastName]
    ,[Password_Encrypted]
    ,[SSN_Encrypted]
FROM [mytest].[dbo].[Person]

SELECT PersonID, FirstName, LastName,
CONVERT(varchar, DecryptByKey(Password_Encrypted)) as Password,
CONVERT(varchar, DecryptByKey(SSN_Encrypted)) as SSN 
FROM Person;

/**********************************************************************/
/* open symetric key for decryption */
OPEN SYMMETRIC KEY Person_SymmetricKey
DECRYPTION BY CERTIFICATE PersonCert;

SELECT PersonID, FirstName, LastName,
CONVERT(varchar, DecryptByKey(Password_Encrypted)) as Password ,
CONVERT(varchar, DecryptByKey(SSN_Encrypted)) as SSN
FROM Person;

CLOSE SYMMETRIC KEY Person_SymmetricKey;

/**********************************************************************/
/* open symetric key for decryption - where condition */
OPEN SYMMETRIC KEY Person_SymmetricKey
DECRYPTION BY CERTIFICATE PersonCert;

SELECT PersonID, FirstName, LastName,
CONVERT(varchar, DecryptByKey(Password_Encrypted)) as Password ,
CONVERT(varchar, DecryptByKey(SSN_Encrypted)) as SSN
FROM Person where CONVERT(varchar, DecryptByKey(SSN_Encrypted)) like '%123%'

CLOSE SYMMETRIC KEY Person_SymmetricKey;

/**********************************************************************/
/* BACKUP */
/**********************************************************************/
/* https://learn.microsoft.com/en-us/sql/t-sql/statements/backup-master-key-transact-sql?view=sql-server-ver16 */

BACKUP MASTER KEY TO FILE = 'C:\Suriya\ws\learning\sql\encryption\master.dmk' ENCRYPTION BY PASSWORD = 'masterKeyPassword'

