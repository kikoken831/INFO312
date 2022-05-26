--TASK 1
customers = load '/user/bigdata/Assn3/customer/customer.tbl' using PigStorage('|') as (
CUSTOMER_ID:int,
CUSTOMER_CODE:chararray,
COMPANY_NAME:chararray,
CONTACT_NAME:chararray,
CONTACT_TITLE:chararray,
CITY:chararray,
REGION:chararray,
POSTAL_CODE:chararray,
COUNTRY:chararray,
PHONE:chararray,
FAX:chararray);

salespersons = load '/user/bigdata/Assn3/salesperson/salesperson.tbl' using PigStorage('|') as (
EMPLOYEE_ID:int,
LAST_NAME:chararray,
FIRST_NAME:chararray,
TITLE:chararray,
BIRTH_DATE:chararray,
HIRE_DATE:chararray,
NOTES:chararray);

orders = load '/user/bigdata/Assn3/order/order.tbl' using PigStorage('|') as (
ORDER_ID:int,
ORDER_DATE:chararray,
SHIP_VIA:chararray,
SHIP_CITY:chararray,
SHIP_REGION:chararray,
SHIP_POSTAL_CODE:chararray,
SHIP_COUNTRY:chararray);

products = load '/user/bigdata/Assn3/product/product.tbl' using PigStorage('|') as (
PRODUCT_ID:int,
PRODUCT_NAME:chararray,
UNIT_PRICE:double,
UNIT_IN_STOCK:int,
UNIT_ON_STOCK:int,
DISCONTINUED:chararray);

order_details = load '/user/bigdata/Assn3/order_detail/order_detail.tbl' using PigStorage('|') as (
ORDER_ID:int,
PRODUCT_ID:int,
CUSTOMER_ID:int,
SALESPERSON_ID:int,
UNIT_PRICE:double,
QUANTITY:int,
DISCOUNT:double);


--TASK 2
consolidated = filter customers by COMPANY_NAME == 'Consolidated Holdings';
consolidated_details = join consolidated by CUSTOMER_ID, order_details by CUSTOMER_ID;
consolidated_details_sales = join consolidated_details by SALESPERSON_ID, salespersons by EMPLOYEE_ID;
results = foreach consolidated_details_sales generate FIRST_NAME, LAST_NAME;
dump results;

--TASK 3
--get all orders in year 1996
orders_1996 = filter orders by ENDSWITH(ORDER_DATE,'1996');

--join with order details
order_details_1996 = join orders_1996 by ORDER_ID, order_details by ORDER_ID;

--find all products in orders within 1996
group_orders = group order_details_1996 by order_details::PRODUCT_ID;

--get all the id's of products sold in 1996
product_id_only = foreach group_orders generate group;

--left outer join with current product list
product_full = join products by PRODUCT_ID left outer, product_id_only by group;

--if last col is null, product was not sold in 1996
filtered_1996 = filter product_full by product_id_only::group is null;

--get count of filtered
c = group filtered_1996 all;
count = foreach c generate COUNT(filtered_1996);
dump count;

--TASK 4

--group products by ID
grouped_products = group order_details by PRODUCT_ID;

--for each product, get the sum of qty sold
summarize_products = foreach grouped_products generate group,SUM(order_details.QUANTITY);
dump summarize_products;

--TASK 5

--Prod id of ikura
ikura = filter products by PRODUCT_NAME == 'Ikura';

--Prod id of Tofu
tofu = filter products by  PRODUCT_NAME == 'Tofu';

--get orders where ikura is present
ikura_order = join ikura by PRODUCT_ID, order_details by PRODUCT_ID;

--get orders where tofu is present
tofu_order = join tofu by PRODUCT_ID, order_details by PRODUCT_ID;

--get the orderid where ikura is present
ikura_order_id = foreach ikura_order generate order_details::ORDER_ID;

--get the orderid where tofu is present
tofu_order_id = foreach tofu_order generate order_details::ORDER_ID;

--join both tables together
both_orders_id = join ikura_order_id by order_details::ORDER_ID, tofu_order_id by order_details::ORDER_ID;

--view table
dump both_orders_id;


