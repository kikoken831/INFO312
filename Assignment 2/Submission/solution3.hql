!record solution3.rpt
--Name: Kendrick Kee
--UOW ID: 7366814
--CREATE PROJECT TABLE
create table project(
employee_id int,
employee_name varchar(50),
projects map<string,int>,
languages array<string>
)
row format delimited fields terminated by ',' stored as textfile;

--INSERT 5 ROWS
--2x employees with multiple projects and multiple languages
insert into project select 1,'Kendrick Kee',map('DB 3',30,'Oracle',25,'SQL-2022',100),array('Java','C','C++');
insert into project select 2,'Hendrick Lee',map('DB 2',40,'Oracle2',15,'SQL-2025',0),array('Python','JS','C++');
--1x employees with multiple projects but no languages
insert into project select 3,'Kenji Miyasaki',map('DB 420',30,'OracleX',35,'SeQL-2022',77),array();
--1x employees with no projects but multiple languages
insert into project select 4,'Henry Lee',map(NULL,cast(NULL as int)),array('Python','JS','Go');
--1x employees with no projects and no languages
insert into project select 5, 'Brainlet Boon',map(NULL,cast(NULL as int)),array();

--VIEW TABLE
select * from PROJECT;

--Clean up 
drop table PROJECT;

!record
