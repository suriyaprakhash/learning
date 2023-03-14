/** the following creates partitions on PRIMARY filegroup **/
CREATE PARTITION FUNCTION ageDemographicFunction (int)
AS RANGE LEFT 
FOR VALUES (30, 60, 80, 150);

CREATE PARTITION SCHEME ageDemographicScheme
AS PARTITION ageDemographicFunction
ALL TO ([PRIMARY]); -- represents file group, when specifiying actaul file group remove ALL

CREATE TABLE PersonPart (
    ID int NOT NULL,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Age int
) ON ageDemographicScheme(age);


select * from sys.partitions where object_id = OBJECT_ID('PersonPart')