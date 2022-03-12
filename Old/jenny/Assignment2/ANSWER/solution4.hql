! record solution4.rpt

CREATE EXTERNAL TABLE APPLICATION(
application_number     decimal(3),
applicant_number       decimal(6),
position_number        decimal(8),
employer               string,
application_date       string,
total_skills           decimal(1),
total_past_employers   decomal(2),
total_years_experience decimal(2),
education_level        string )
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE LOCATION '/user/hive/application.tbl';

CREATE EXTERNAL TABLE APPLICANT(
applicant_number       decimal(6),
first_name             string,
last_name              string,
date_of_birth          string,
city                   string,
state                  string,
phone                  string,
fax                    string,
email                  string )
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE LOCATION '/user/hive/applicant.tbl';

CREATE EXTERNAL TABLE EMPLOYER(
ename                  string,
city                   string,
state                  string,
phone                  string,
fax                    string,
email                  string,
web                    string )
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE LOCATION '/user/hive/employer.tbl';

CREATE EXTERNAL TABLE POSITION(
position_number        decimal(8),
title                  string,
salary                 decimal(8,2),
extras                 string,
bonus                  decimal(8,2),
specification          string )
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE LOCATION '/user/hive/position.tbl';


SELECT * FROM APPLICATION LIMIT 3;
SELECT COUNT(*) FROM APPLICATION;

SELECT * FROM APPLICANT LIMIT 3;
SELECT COUNT(*) FROM APPLICANT;

SELECT * FROM EMPLOYER LIMIT 3;
SELECT COUNT(*) FROM EMPLOYER;

SELECT * FROM POSITION LIMIT 3;
SELECT COUNT(*) FROM POSITION;

! record