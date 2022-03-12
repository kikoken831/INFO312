case class CUSTOMER(
customer_id: Int, 
code: String, 
company_name: String, 
contact_name: String, 
contact_title: String, 
city: String, 
region: String, 
postal_code: String, 
country: String, 
phone: String, 
fax: String)

val DF_CUSTOMER = spark.sparkContext.textFile("./customer.tbl").map(_.split(",")).map(attributes=>CUSTOMER(attributes(0).trim.toInt, attributes(1).trim(), attributes(2).trim(), attributes(3).trim(), attributes(4).trim(), attributes(5).trim(), attributes(6).trim(), attributes(7).trim(), attributes(8).trim(), attributes(9).trim(), attributes(10).trim())).toDF()

CustomerDF.show()


case class ORDERDETAIL(
order_id: Int, 
product_id: Int, 
customer_id: Int, 
supplier_id: Int, 
unit_price: Float, 
quantity: Int, 
discount: Float)

val DF_ORDERDETAIL = spark.sparkContext.textFile("./order_detail.tbl").map(_.split(",")).map(attributes=>ORDERDETAIL(attributes(0).trim.toInt, attributes(1).trim.toInt, attributes(2).trim.toInt, attributes(3).trim.toInt, attributes(4).trim.toFloat, attributes(5).trim.toInt, attributes(6).trim.toFloat)).toDF()

OrderDetailDF.show()


case class ORDER(
order_id: Int, 
order_date: String, 
ship_via: String, 
city: String, 
region: String, 
postal_code: 
String, country: String)

val DF_ORDER = spark.sparkContext.textFile("./order.tbl").map(_.split(",")).map(attributes=>ORDER(attributes(0).trim.toInt, attributes(1).trim(), attributes(2).trim(), attributes(3).trim(), attributes(4).trim(), attributes(5).trim(), attributes(6).trim())).toDF()

DF_ORDER.show()

case class PRODUCT(product_id: String, name: String, unit_price: String, unit_in_stock: String, unit_on_order: String, discontinued: String)

val DF_PRODUCT = spark.sparkContext.textFile("./product.tbl").map(_.split(",")).map(attributes=>PRODUCT(attributes(0), attributes(1), attributes(2), attributes(3), attributes(4), attributes(5))).toDF() 

DF_PRODUCT.show()

case class SALESPERSON(
employee_id:Int, 
last_name:String, 
first_name:String, 
title:String, 
dob:String, 
hire_date:String, 
notes:String)

val DF_SALESPERSON = spark.sparkContext.textFile("./salesperson.tbl").map(_.split(",")).map(attributes=>SALESPERSON(attributes(0).trim.toInt,attributes(1).trim(),attributes(2).trim(), attributes(3).trim(), attributes(4).trim(), attributes(5).trim(), attributes(6).trim())).toDF()

DF_SALESPERSON.show()

DF_ORDERDETAIL.filter(col("quantity") > 50).show()

DF_ORDER.filter(col("country").like("Germany")).count()

DF_ORDER.groupBy(col("country")).count().show()

DF_PRODUCT.sort(col("unit_price").desc).show(20)