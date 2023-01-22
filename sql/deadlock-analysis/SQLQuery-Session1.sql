/********* USE CASE *********/

/** Mode X & X Lock **/
/** Execute the each one after the other(2) - or - with a delay and as fast as possible **/
/** Transcation Session 1 **/
BEGIN TRAN one;
update Persons set FirstName='id1 first transcation' where ID = 1;
-- WAITFOR DELAY '00:00:10'
update Persons set FirstName='id2 first transcation' where ID = 2;
COMMIT TRAN one;


/** Mode S & X Lock **/
/** Execute the each one after the other(4) - or - with a delay and as fast as possible **/
/** Transcation Session 3 **/
BEGIN TRAN three;
update Persons set FirstName='id1 third transcation' where ID = 1;
-- WAITFOR DELAY '00:00:10'
select * from Persons where ID = 2;
COMMIT TRAN three;


/** Mode S & U Lock **/
/** Execute the whole quickly after the other(6) -  as fast as possible, try multiple times in case if you don't get it **/
/** Transcation Session 5 **/
BEGIN TRAN five;
update Persons set FirstName='id1 fifth transcation' where LastName='Suriya';
WAITFOR DELAY '00:00:10'
update Persons set FirstName='id1 fifth transcation' where LastName='Renu';
COMMIT TRAN five;