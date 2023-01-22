/********* USE CASE *********/

/** Mode X & X Lock **/
/** Execute the each one after the other(1) - or - with a delay and as fast as possible **/
/** Transcation Session 2 **/
BEGIN TRAN two;
update Persons set FirstName='id2 second transcation' where ID = 2;
-- WAITFOR DELAY '00:00:10'
update Persons set FirstName='id1 second transcation' where ID = 1;
COMMIT TRAN two;


/** Mode S & X Lock **/
/** Execute the each one after the other(3) - or - with a delay and as fast as possible **/
/** Transcation Session 4 **/
BEGIN TRAN four;
update Persons set FirstName='id2 fourth transcation' where ID = 2;
-- WAITFOR DELAY '00:00:10'
select * from Persons where ID = 1;
COMMIT TRAN four;


/** Mode S & U Lock **/
/** Execute the whole quickly after the other(5) - as fast as possible, try multiple times in case if you don't get it **/
/** Transcation Session 6 **/
BEGIN TRAN six;
update Persons set FirstName='id1 sixth transcation'  where LastName='Renu';
WAITFOR DELAY '00:00:10'
update Persons set FirstName='id1 sixth transcation'  where LastName='Suriya';
COMMIT TRAN six;