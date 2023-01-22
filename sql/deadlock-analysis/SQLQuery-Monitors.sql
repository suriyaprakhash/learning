
/** select lock type on a object **/
exec sp_lock
/** select lock user based on processId **/
exec sp_who 78

/** Check if any uncommited transactions in this window session **/
SELECT 
er.session_id
,er.open_transaction_count
FROM sys.dm_exec_requests er order by er.session_id desc

/** You begin to explore the query performance counters in this object using this T-SQL query on the sys.dm_os_performance_counters dynamic management view **/
/** https://learn.microsoft.com/en-us/sql/relational-databases/performance-monitor/sql-server-locks-object?view=sql-server-ver16#example **/
SELECT * FROM sys.dm_os_performance_counters
WHERE object_name LIKE '%Locks%';


/** You can see the locks being taken with the following query **/
/** https://stackoverflow.com/questions/62852902/update-with-rowlock-in-mssql-server **/
select      tl.request_session_id,
            tl.resource_type,
            tl.request_mode,
            tl.resource_description,
            tl.request_status
from        sys.dm_tran_locks   tl
join        sys.partitions      pt  on  pt.hobt_id = tl.resource_associated_entity_id
join        sys.objects         ob  on  ob.object_id = pt.object_id
where       tl.resource_database_id = db_id()
order by    tl.request_session_id