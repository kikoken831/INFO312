!record solution4.rpt

--Name: Kendrick Kee
--UOW ID: 73668314

create external table part(
	p_partkey decimal(10),
	p_name varchar(50),
	p_mfgr varchar(30),
	p_brand varchar(30),
	p_type varchar(30),
	p_size varchar(30),
	p_container varchar(30),
	p_retailprice decimal(10,2),
	p_comment varchar(30)
)
row format delimited fields terminated by '|'
stored as textfile location '/user/bigdata/Asn2/part';

create external table supplier(

	s_suppkey decimal(10),
	s_name varchar(50),
	s_address varchar(30),
	s_val decimal(10),
	s_phone varchar (10),
	s_acctbal decimal(10,2),
	s_comment varchar(50)

)
row format delimited fields terminated by '|'
stored as textfile location '/user/bigdata/Asn2/supplier';

create external table partsupp(
	ps_partkey decimal(10),
	ps_suppkey decimal(10),
	ps_availqty decimal(10),
	ps_supplycost decimal(10,2),
	ps_comment varchar(50)
)
row format delimited fields terminated by '|'
stored as textfile location '/user/bigdata/Asn2/partsupp';


--select from tables
select * from part limit 5;
select * from supplier limit 5;
select * from partsupp limit 5;

-- Drop Tables
drop table partsupp;
drop table part;
drop table supplier;


!record


