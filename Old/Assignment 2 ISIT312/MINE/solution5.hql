!record solution5.rpt

-- Student Name: Lee Yu Xian
-- Student Number: 7233164

-- Implement group by clause with cube operator
select o_clerk, o_custkey, l_suppkey, sum(l_quantity) as tot_num_of_orders
from orders inner join lineitem on o_orderkey = l_orderkey
where o_clerk in ('Clerk#000000522', 'Clerk#000000154')
group by o_clerk, o_custkey, l_suppkey with cube;

-- Implement group by clause with roll up
select l_partkey, l_suppkey, max(l_discount) as largest_discount
from lineitem
where l_partkey in (7, 8, 9)
group by l_partkey, l_suppkey with rollup;

-- Implement group by clause with grouping sets
select year(o_orderdate) as years, o_clerk, min(l_extendedprice) as smallest_price
from orders inner join lineitem on o_orderkey = l_orderkey
group by year(o_orderdate), o_clerk 
grouping sets ((year(o_orderdate), o_clerk));

-- Implement select using window partitioning technique
-- (4)
select ps_partkey, ps_availqty, min(ps_availqty) 
over (partition by ps_partkey) as smallest, avg(ps_availqty) 
over (partition by ps_partkey) as average
from partsupp
where ps_partkey in (5, 15);

-- (5)
select ps_partkey, ps_availqty, row_number() 
over (partition by ps_partkey order by ps_availqty desc)
from partsupp
where ps_partkey in (10, 20);

-- (6)
select ps_partkey, ps_availqty, avg(ps_availqty) 
over (partition by ps_partkey order by ps_availqty rows unbounded preceding)
from partsupp
where ps_partkey in (15, 25);

!record
