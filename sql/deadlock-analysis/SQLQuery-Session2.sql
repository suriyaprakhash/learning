/** select **/
select * from Persons;

/** Transcation 2 **/
BEGIN TRAN two;
update Persons set FirstName='two first' where ID = 1;
update Persons set LastName='two last' where ID = 1;
ROLLBACK TRAN two;
COMMIT TRAN two;


/** Transcation Session 2 **/
BEGIN TRAN two;
update Persons set FirstName='one id2 second transcation' where ID = 2;
update Persons set FirstName='one id1 second transcation' where ID = 1;
ROLLBACK TRAN two;
COMMIT TRAN two;



