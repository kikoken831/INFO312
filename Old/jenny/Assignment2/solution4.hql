!record solution4.rpt

--Student Name: Liang Leran Jenny
--Student ID: 7063167

--CREATE APPLICANT TABLE
create external table applicant(
applicant_number VARCHAR(6),
first_name VARCHAR(30),
last_name VARCHAR(30),
date_of_birth DATE,
city VARCHAR(30),
state VARCHAR(30),
phone STRING,
fax STRING,
email VARCHAR(30)
)
row format delimited fields terminated by ',' stored as textfile location '/user/bigdata/a2/applicant';

--CREATE APPLICATION TABLE
create external table application(
application_number VARCHAR(3),
applicant_number VARCHAR(6),
position_number VARCHAR(8),
ename VARCHAR(30),
date DATE,
total_skills DECIMAL,
total_courses_passed DECIMAL,
total_past_employers DECIMAL,
total_years_experience DECIMAL,
education_level VARCHAR(30)
)
row format delimited fields terminated by ',' stored as textfile location '/user/bigdata/a2/application';

--CREATE EMPLOYER TABLE
create external table employer(
ename STRING,
city VARCHAR(30),
state VARCHAR(30),
phone STRING,
fax STRING,
email VARCHAR(30),
web VARCHAR(30)
)
row format delimited fields terminated by ',' stored as textfile location '/user/bigdata/a2/employer';

--CREATE POSITION TABLE
create external table position(
position_number VARCHAR(8),
title VARCHAR(30),
salary DECIMAL(10,2),
extras varchar(30),
bonus DECIMAL(10,2),
specification VARCHAR(30),
ename STRING
)
row format delimited fields terminated by ',' stored as textfile location '/user/bigdata/a2/position';

--SELECT STATEMENTS FROM APPLICANT TABLE
SELECT * FROM applicant LIMIT 3;
SELECT COUNT(applicant_number) AS num_rows_applicant FROM applicant;

--SELECT STATEMENTS FROM APPLICATION TABLE
SELECT * FROM application LIMIT 3;
SELECT COUNT(application_number) AS num_rows_application FROM application;

--SELECT STATEMENTS FROM EMPLOYER TABLE
SELECT * FROM employer LIMIT 3;
SELECT COUNT(ename) AS num_rows_employer FROM employer;

--SELECT STATEMENTS FROM POSITION TABLE
SELECT * FROM position LIMIT 3;
SELECT COUNT(position_number) AS num_rows_position FROM position;

!record