/********* USE CASE *********/

/** Mode X & X Lock **/
/** Execute the each one after the other(1) - or - with a delay and as fast as possible **/
/** Transcation Session 2 **/
BEGIN TRAN two;
update Person set FirstName='id2 second transcation' where ID = 2;
WAITFOR DELAY '00:00:10'
update Person set FirstName='id1 second transcation' where ID = 1;
COMMIT TRAN two;


/** Mode X & S Lock **/
/** Execute the each one after the other(3) - or - with a delay and as fast as possible **/
/** Transcation Session 4 **/
BEGIN TRAN four;
update Person set FirstName='id2 fourth transcation' where ID = 2;
-- WAITFOR DELAY '00:00:10'
select * from Person where ID = 1;
COMMIT TRAN four;


/** Mode X & U Lock **/
/** Execute the whole quickly after the other(5) - as fast as possible, try multiple times in case if you don't get it **/
/** Transcation Session 6 **/
BEGIN TRAN six;
update Person set FirstName='id1 sixth transcation'  where LastName='Doe';
WAITFOR DELAY '00:00:10'
update Person set FirstName='id1 sixth transcation'  where LastName='Smith';
COMMIT TRAN six;


BEGIN TRAN eight;
update Person with(PAGLOCK) set FirstName='id2 second transcation' where LastName='Doe';
WAITFOR DELAY '00:00:10'
update Person with(PAGLOCK) set FirstName='id1 second transcation' where LastName='Smith';
COMMIT TRAN eight;




BEGIN TRAN eight;
update Person with(PAGLOCK) set FirstName='id2 second transcation' where ID > 900;
WAITFOR DELAY '00:00:10'
update Person with(PAGLOCK) set FirstName='id1 second transcation' where ID < 100;
COMMIT TRAN eight;


