select * from sth;
select part, sum(amount) from orders group by part;
select oyear, sum(amount), count(*) from orders group by oyear;
select part, oyear, customer, sum(amount), count(*)
    from orders group by part, oyear, customer;

select part, oyear, customer, sum(amount), count(*)
    from orders group by part, oyear, customer
    order by parat, oyear, customer;


select part, oyear, customer, sum(amount), count(*)
from orders 
group by part, oyear, customer
union
select part, oyear,NULL, sum(amount), count(*)
from ordersgroup by part,oyear
union
select part, NULL,NULL,sum(amount), count(*)
from ordersgroup by part
order by part, oyear,customer;



select part, oyear, customer, sum(amount), count(*)
from orders 
group by part, oyear, customer with rollup
order by part, oyear, customer;

select part, oyear, customer, sum(amount), count(*)
from ordersgroup by part, oyear, customer with cube
order by part, oyear, customer;

select part, oyear, customer, sum(amount), count(*)
from ordersgroup by part, oyear, customer
grouping set ((part),(oyear,customer),(oyear))

select part, customer, amount, max(amount)
over (partition by part order by amount),
sum(amount) over (partition by part order by amount)
from orders;



select part, amount, sum(amount)
over (partition by part order by amount, oyear, omonth, oday),
avg(amount) over (partition by part order by amount, oyyear, omonth, oday)
from orders;

select part, amount, 
sum(amount) over (partition by part order by oyear, omonth, oday rows 1 preceding),
avg(amount) over (partition by part order by oyear, omonth, oday rows 1 preceding)
from orders;

select part, amount, 
sum(amount) over (partition by part order by oyear, omonth, oday rows unbounded preceding),
avg(amount) over (partition by part order by oyear, omonth, oday rows unbounded preceding)
from orders;




select part, amount, row_number() over
(partition by part order by amount) from 
orders;