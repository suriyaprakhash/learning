# Deadlock analysis

 - [Base](#base)
 - [Monitoring](#monitoring)
 - [Understanding concepts](#understanding-concepts)
 - [Types](#types)

## Base

### Create

```
CREATE TABLE Persons (
    ID int NOT NULL PRIMARY KEY,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Age int
);
```

### Select
```
/** select **/
select * from Persons;
```

### Insert
```
/** insert **/
insert into Persons (ID, LastName, FirstName, Age) values (1, 'Suriya', 'Prakhash', 31);
insert into Persons (ID, LastName, FirstName, Age) values (2, 'Renu', 'Prakhash', 26);
```

### Delete
```
/** delete **/
delete from Persons;
```

### Check for uncommited transcations

```
/** Check if any uncommited transactions **/
SELECT 
er.session_id
,er.open_transaction_count
FROM sys.dm_exec_requests er
```

## Monitoring

Mangement -> System_health, right click Watch live data

![alt](./images/monitor/management_system_health_watch_live_data.jpg)

or 

package).event.file -> right click -> View Targeted Data.. (A little bit slower approach)

## Understanding Concepts

https://learn.microsoft.com/en-us/sql/relational-databases/pages-and-extents-architecture-guide?view=sql-server-ver16

https://learn.microsoft.com/en-us/sql/relational-databases/sql-server-transaction-locking-and-row-versioning-guide?view=sql-server-ver16

### Page

Like a [book page](https://learn.microsoft.com/en-us/sql/relational-databases/pages-and-extents-architecture-guide?view=sql-server-ver16#pages) where it contains rows of size 8kb within it. Overflown data will be saved seapartely and are refrenced.

### Extents

8 pages - so 64kb

### Row
They are holds the metadata (like indexes - in Index Page) and data (actual rows), overflowing data above 8kb 


### Indexes
The indexs are stored in Page in a *B+ tree* structure and the leaf node is the pointer to the data in the page or someother page, gets resolved starting form the root page.
https://learn.microsoft.com/en-us/sql/relational-databases/indexes/indexes?view=sql-server-ver16#available-index-types

### Non-Indexes

The non indexed row values are stored in the form of Heap.

## Types

Primarily [locks](https://learn.microsoft.com/en-us/sql/relational-databases/sql-server-transaction-locking-and-row-versioning-guide?view=sql-server-ver16#lock_modes) are applied on [resources](https://learn.microsoft.com/en-us/sql/relational-databases/sql-server-transaction-locking-and-row-versioning-guide?view=sql-server-ver16#lock-granularity-and-hierarchies) to complete the transcation. 

When a transaction holds the resource that the other transaction need, which in turn holds the resource the first one needs results in a condtion called [deadlock](https://learn.microsoft.com/en-us/sql/relational-databases/sql-server-transaction-locking-and-row-versioning-guide?view=sql-server-ver16#deadlocks).

Here are the resources that can cause deadlock
https://learn.microsoft.com/en-us/sql/relational-databases/sql-server-transaction-locking-and-row-versioning-guide?view=sql-server-ver16#deadlock_resources

https://learn.microsoft.com/en-us/sql/relational-databases/performance-monitor/sql-server-locks-object?view=sql-server-ver16#example

Here are the modes
https://learn.microsoft.com/en-us/sql/relational-databases/event-classes/lock-deadlock-event-class?view=sql-server-ver16#lockdeadlock-event-class-data-columns


### KeyLock - Replication method

#### Same table - different row order change - Exclusive & Exclusive in same table

Execute the following line by line, one line from session 1 and other from session 2 then session 1 and so on..

Session 1 - in a new tab
```
/** Transcation Session 1 **/
BEGIN TRAN one;
update Persons set FirstName='one id1 first transcation' where ID = 1;
update Persons set FirstName='one id2 first transcation' where ID = 2;
ROLLBACK TRAN one;
COMMIT TRAN one;
```

Session 2 - in a new tab
```
/** Transcation Session 2 **/
BEGIN TRAN two;
update Persons set FirstName='one id2 second transcation' where ID = 2;
update Persons set FirstName='one id1 second transcation' where ID = 1;
ROLLBACK TRAN two;
COMMIT TRAN two;
```

This results in a [deadlock](./deadlock-xml/same%20table%20-%20different%20row%20order%20change%20-%20exclusive%20%26%20exclusive.xml) mode -> X & X


In the where condition ID is cluseterd index primary key

#### Same table - different row order change - Exclusive & Select in same table

Session 3 - in a new tab
```
/** Transcation Session 3 **/
BEGIN TRAN three;
update Persons set FirstName='one id1 third transcation' where ID = 1;
select * from Persons where ID = 2;
ROLLBACK TRAN three;
COMMIT TRAN three;
```

Session 4 - in a new tab
```
/** Transcation Session 4 **/
BEGIN TRAN four;
update Persons set FirstName='one id2 fourth transcation' where ID = 2;
select * from Persons where ID = 1;
ROLLBACK TRAN four;
COMMIT TRAN four;
```

This results in a [deadlock](./deadlock-xml/same%20table%20-%20different%20row%20order%20change%20-%20exclusive%20%26%20select.xml) mode -> X & S

In the where condition ID is cluseterd index primary key

#### Same table - different row order change - Exclusive & Update in same table

Execute the whole following quickly one after the other

If you dont *wait for delay* the session would inturn wait and we wont be able to replicate the issue. So we need to have the delay
and execute the queries as quickly as possible

```
/** Transcation Session 5 **/
BEGIN TRAN five;
update Persons set FirstName='id1 fifth transcation' where LastName='Suriya';
WAITFOR DELAY '00:00:10'
update Persons set FirstName='id1 fifth transcation' where LastName='Renu';
COMMIT TRAN five;
```

```
BEGIN TRAN six;
update Persons set FirstName='id1 sixth transcation'  where LastName='Renu';
WAITFOR DELAY '00:00:10'
update Persons set FirstName='id1 sixth transcation'  where LastName='Suriya';
COMMIT TRAN six;
```

here we are using a non-indexed column, so the it takes **update lock** instead of **exclusive lock** unlike the [first scenario with ID](#same-table---different-row-order-change---exclusive--exclusive-in-same-table) reason being it need to scan the whole table for all matching records to update.

### RowIDLock - Replication method

Drop the person table and recreate it without a PRIMARY_KEY and repeat the [KeyLock step](#same-table---different-row-order-change---exclusive--update-in-same-table) and now you will see it as [ROW ID LOCK](./deadlock-xml/same%20table%20-%20different%20row%20order%20change%20RID%20Lock%20-%20exclusive%20%26%20update.xml)