
# Project Title

Online shopping microservices project built using spring boot

## Technologies Used
1. Spring boot
2. Spring cloud api gateway
3. Spring cloud config server
4. Spring eureka server
5. Spring eureka client
6. Spring data jpa
7. Hibernate validation
8. Lombok
9. MySQL

## About

### Item-Service
	1. Get items - Return all items
		▪ Get url: /items
	2. Get a item detail if item name is sent as a parameter
		▪ Get url: /items/{itemname}
	3. add item
		▪ Post url: /addItem
        ▪ Input: Item request body
	4. Table: id, name, description, price

### Customer-Service
	1. Get customer - Return all customers details in the table
		▪ Get url: /customers
	2. Create a customer by sending in customer details in the request body
		▪ Get url: /customer
	3. Table: id, email, first_name, last_name

### Sales-Order-Service
	1. Get order - create an order and return an order id
		▪ Post url: /orders
		▪ Input : order description, order date, customer id, list of item names
		▪ Output: order id, order description, order line items
		▪ Validate customer by verifying the table "customer_sos" with cust_id.
		▪ Validate items by calling item-service with item name
		▪ Create order by inserting the order details in order table and items for the order in the order_line_item table
	2. Get order details by customer id
		▪ Get url: /orders?customerId={customerId}
		▪ Input: customer id
		▪ Output: order id, order description, order line items
		▪ Validate customer by verifying the table customer_sos with cust_id
		▪ Retrieve summary of all orders of a customer
	3. Get order details by order id
		▪ Get url: /orders/{orderId}
		▪ Input: order id
		▪ Output: order id, order description, order line items
	4. Update order details
		▪ Put url: /order/{orderId}
		▪ Input: order id, salesOrderDto request body
		▪ Output: salesOrderDto if update successful
	5. Delete order details by order id
		▪ Delete url: /order/{orderId}
		▪ Input: orderId
		▪ Output: send message for if order is successfully deleted or not
	6. Table:
		▪ Sales_order: id, order_date, cust_id, order_desc, total_price
        ▪ Order_line_item: id, item_name, item_quantity, order_id

### Order-Lookup-Service
	1. Get order details of a customer 
        ▪ Invokes APIs on sales-order-service to retrieve order details of a customer

## ER Diagram
![Alt text](/ER-Diagram.png)

## Authors
- [@AbhaySingh15](https://github.com/AbhaySingh15)



