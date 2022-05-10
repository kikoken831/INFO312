!record solution5.rpt
--Name:Kendrick Kee
--UOW ID: 7366814
--Qn5.1
--
select ps_partkey,ps_availqty,min(ps_availqty)
over (partition by ps_partkey) as smallest
,avg(ps_availqty) over (partition by ps_partkey) as average
from partsupp
where ps_partkey in (5,15);

--Qn5.2
select ps_partkey,ps_availqty,sum(ps_availqty) over (partition by ps_partkey) as sum
from partsupp
where ps_partkey in (5,10,15,25)
group by ps_partkey,ps_availqty with cube
order by sum asc;

--Qn5.3
select ps_partkey,ps_availqty,sum(ps_availqty) over (partition by ps_partkey
order by ps_supplycost) as sum
from partsupp
where ps_partkey in (5,10,15,25)
order by ps_partkey,sum asc;

--Qn5.4
select ps_partkey,ps_availqty, ROW_NUMBER() over 
(partition by ps_partkey order by ps_availqty desc)
from partsupp
where ps_partkey in (10,20);

--Qn5.5
select ps_partkey,ps_availqty, avg(ps_availqty)
 over (partition by ps_partkey order by ps_availqty rows unbounded preceding)
from partsupp
where ps_partkey in (15,25);

!record
