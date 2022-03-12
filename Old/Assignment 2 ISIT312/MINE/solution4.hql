!record solution4.rpt

-- Student Name: Lee Yu Xian
-- Student Number: 7233164

-- Create author table
create external table author(
A_ID 		DECIMAL(10), 
A_FNAME 	VARCHAR(20), 
A_MNAME		VARCHAR(20), 
A_LNAME 	VARCHAR(20), 
A_DOB		DATE, 
A_BIO		VARCHAR(500)
)
row format delimited fields terminated by '|'
stored as textfile location '/user/bigdata/author';

-- Create item table
create external table item(
I_ID	 	DECIMAL(10),
I_TITLE	 	VARCHAR(60),
I_A_ID	 	DECIMAL(10),
I_PUB_DATE	DATE,
I_PUBLISHER 	VARCHAR(60),
I_SUBJECT 	VARCHAR(60),
I_DESC 		VARCHAR(500),
I_RELATED1	DECIMAL(10),
I_RELATED2      DECIMAL(10),
I_RELATED3      DECIMAL(10),
I_RELATED4      DECIMAL(10),
I_RELATED5      DECIMAL(10),
I_THUMBNAIL 	VARCHAR(10),
I_IMAGE		VARCHAR(10),
I_SRP 		DECIMAL(15,2),
I_COST 		DECIMAL(15,2),
I_AVAIL 	DATE,
I_STOCK		DECIMAL(4),
I_ISBN 		CHAR(13),
I_PAGE 		DECIMAL(4),
I_BACKING 	VARCHAR(15),
I_DIMENSIONS 	VARCHAR(25)
)
row format delimited fields terminated by '|'
stored as textfile location '/user/bigdata/item';

-- Total number of rows in the author table
select count(*) from author;

-- Total number of rows in the item table
select count(*) from item;

-- Total number of rows in both tables
with totrows as
(SELECT COUNT(*) as rn
FROM author
UNION ALL
SELECT COUNT(*)
FROM item)
select sum(rn) from totrows;

-- First three rows from author table
select * from author limit 3;

-- First three rows from item table
select * from item limit 3;

-- Spring cleaning
drop table author;
drop table item;

!record
