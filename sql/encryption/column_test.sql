/****** Script for SelectTopNRows command from SSMS  ******/
SELECT TOP (1000) [ID]
      ,[LastName]
      ,[FirstName]
      ,[Age]
  FROM [mytest].[dbo].[Person]


  /* Create account table */
  create table [dbo].[Account] (
	Id INT Primary Key,
	AccountNumber varbinary(128),
	AccountType VARCHAR(20)
  );

  /* Create the master key */
  CREATE MASTER KEY ENCRYPTION BY   
  PASSWORD = 'masterKeyPassword'; 
  
  DROP MASTER KEY;

   /* Create a certificate encrypted by master key */
   CREATE CERTIFICATE AccountNumberCert  
   WITH SUBJECT = 'Employee Account Number';  
   GO  

   DROP CERTIFICATE AccountNumberCert

   /* Create symmetric key encryption key using the the created certificate*/
   CREATE SYMMETRIC KEY AccountNumber_SymmetricKey  
   WITH ALGORITHM = AES_256  
   ENCRYPTION BY CERTIFICATE AccountNumberCert;  
   GO  


  select * from sys.symmetric_keys
  select * from sys.asymmetric_keys
  select * from sys.certificates
  select * from sys.column_master_keys

  EncryptByKey(Key_GUID('AccountNumber_SymmetricKey'), AccountNumber)

  Insert into Account (Id, AccountNumber , AccountType) values (2, 12345, 'Savings');

    Insert into Account (Id, AccountNumber , AccountType) values (5,  ENCRYPTBYKEY(KEY_GUID('AccountNumber_SymmetricKey'), Id), 'Savings');

	update  Account set AccountNumber = ENCRYPTBYKEY(KEY_GUID('AccountNumber_SymmetricKey'), '54321') where Id =1

  select id, CONVERT(int, AccountNumber), AccountType from Account where AccountNumber = 12345

  drop table Account














    select * from sys.symmetric_keys