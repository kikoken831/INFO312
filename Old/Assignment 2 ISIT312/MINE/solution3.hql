!record solution3.rpt

-- Student Name: Lee Yu Xian
-- Student Number: 7233164

-- Create table for student numbers and evaluation of subjects
create table studeva(
stdno		decimal(7),
evaluation	array<map<string, int>> );

desc studeva;

-- Inserting sample data into table
insert into studeva
	select '1111', array(map('CSIT111',01),map('CSIT121',23),map('CSIT101',50),map('CSIT235',99),map('ISIT312',02));
insert into studeva
	select '1112', array(map('CSIT101',56),map('CSIT111',78),map('CSIT115',10),map('ISIT312',05));
insert into studeva
	select '1113', array(map('CSIT121',76),map('CSIT235',87),map('ISIT312',49));
insert into studeva
	select '1114', array(map('CSIT111',50),map('ISIT312',45));
insert into studeva
	select '1115', array(map('ISIT115',67),map('CSCI235',45),map('CSIT127',56));

-- List contents of the table
select * from studeva;

-- Spring cleaning
drop table studeva;

!record
