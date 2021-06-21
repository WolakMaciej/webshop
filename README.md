# webshop

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [End Points](#end-points)
* [Clone](#clone)

## General info
Simple web shop with user registration and managemet options.
	
## Technologies
Project is created with:
* Spring Boot
* Gradle
* Lombok
* H2 SQL Database

## End Points
User-controller:
* GET	/details			"Get user details"
* GET 	/username		"Get username"
* GET 	/users/{id}		"Get user by id"
* GET 	/users			"Get all users"
* POST 	/api/users		"Create user by admin"
* POST 	/login			"Login user and admin"
* POST 	/logout			"Logout user and admin"
* POST 	/register		"Register user"
* DELETE 	/api/users/{id}		"Delete user by id"
* PUT 	/api/users/{id}		"Edit user by id"

Product-controller:
* GET	/products/{id}		"Get product by id"
* GET	/products		"Get all products"
* GET	/sumq			"Get sum quantity of all products"
* GET	/total			"Get sum price of all products"
* POST	/api/products		"Create product"
* DELETE	/api/products/{id}	"Delete product by id"
* PUT	/api/products/{id}	"Edit product by id"

Shop-order-controller:
* GET	/shopOrders/{id}	"Get shopOrder by id"
* GET	/shopOrders		"Get all shopOrders"
* POST	/shopOrders		"Create shopOrder"
* DELETE	/api/shopOrders/{id}	"Delete shopOrders by id"
* PUT	/shopOrders/{id}	"Edit shopOrders by id"
Item-cart-controller:
* GET	/itemCarts/{id}		"Get itemCart by id"
* GET	/itemCarts		"Get all itemCarts"
* POST	/ itemCarts		"Create itemCarts"
* DELETE	/api/itemCarts/{id}	"Delete itemCarts by id"
* PUT	/ itemCarts/{id}	"Edit itemCarts by id"

## Clone
To get started you can simply clone this repository using git:

```
git https://github.com/WolakMaciej/webshop

```

## Author

Copyright &copy; 2021, Maciej Wolak
