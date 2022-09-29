# Grocery Store API

Grocery Store API built with Spring Boot   
>To do: Tests
### "Business" Requirements


- Create a basic Groceries Shop till which can `scan` fruits and vegetables of different types, producing a numeric result/bill in the end. Assume the currency is called `aws` and there is `100 cloud ('c' for short)` in 1 aws
- Apart from simply adding the value of each product, the till should contain logic for the following special deals:
  - `2 for 3` - for a given selection of items (customer buys 3 items but only pays for the value of 2 of them, the cheapest one is free). In case there are more than 3 items that are included in the `2 for 3` deal, the first 3 items are included.
    Example Deal ["banana", "orange", "tomato"], example items scanned ["banana", "orange", "orange", "tomato"] - the tomato is not included in the discount (the cheaper of `banana` or `orange` will be subtracted)
  - `buy 1 get 1 half price` - for a given selection of items (if the customer buys a given product under such offer, they receive a 50% reduction in the price of a second item of the same type)
- The till should be `programmable` so that whoever runs it can define 2 inputs:
  - The list of items supported by the till - each item with a given "price", "name"
  - Once a new item is added to the till, the administrator should be able to add it to any of the 2 special deals defined above
  - You should be able to scan a list of items and see the end price (any special deal discounts should be subtracted)



## Installation

No configuration needed. Using in-memory DB. Accesible UI at **[localhost]/h2-ui/** . 
```java
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=test
spring.datasource.password=
```


## Usage
Application is secured with in-memory authentication for the sake of the example via Spring Security. There are two users and two respective roles with functionality based on authority: 
```java
auth.inMemoryAuthentication()
                .withUser("user")
                .password("1234")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("1234")
                .roles("ADMIN");

```
The grocery store operates on a predefind list of supported items which is fully programable. For simplicity, the list is populated on startup in the ***GroceryStoreApplication.class***

## API reference:
Every request has to be previously authorised via Basic Auth and the appropriate credentials.

<img src="https://i.ibb.co/mhXfLg8/authorisation.png" alt="authorisation" border="0">

### Admin Functionality
The Admin role has access to every endpoint to this API.
Functionality is based on managing inventory and promotions:
#### Add new item to store inventory:
The store uses a pre-defined list of supported products. Every item has a type  defined in the supported list and upon adding to inventory is assigned automatically. This request adds supported items to the store's inventory: 

Ex.
```http
  POST http://localhost:8080/api/admin/inventory
```

```json
  {
	"itemName": "apple",
	"itemPrice": 0.50
}
```
Items that are not supported and have a price <= 0 cannot be added to inventory.

#### Add new item to supported list:
Adds new items to the supported items list. 

Ex.
```http
POST http://localhost:8080/api/admin/inventory/new
```

```json
{
	"itemName": "orange" ,
	"itemType" : "Fruit"
}
```
Items that are already supported cannot be added again.

#### Add a promotion:
Adds promotions to the store. There are two types of promotions as stated above:

```http
POST http://localhost:8080/api/admin/promotion
```
   - `2for3` 

```json
{
	"productNames":  ["apple", "banana", "tomato"],
	"promotionType" : "2for3"
}
```

  - `buy1get1` 
```json

{
	"productNames":   ["potato"],
	"promotionType" : "buy1get1"
}
```

The two promotions can be active together, but only one of specific type can be active at a time.

#### Delete existing promotion:
Deletes existing promotions by passing a string of their type: 

Ex.
```http
DELETE http://localhost:8080/api/admin/promotion
```

```String
	buy1get1
```

#### Get active promotions:
Retrieves list of currently active promotions

```http
GET http://localhost:8080/api/admin/promotion
```

ex.response:
```json
	[
    {
        "id": 2,
        "promotedCategory": "No Category",
        "promotionType": "2for3",
        "promotedItemNames": [
            "apple",
            "banana",
            "tomato"
        ]
    },
    {
        "id": 3,
        "promotedCategory": "No Category",
        "promotionType": "buy1get1",
        "promotedItemNames": [
            "Apple",
            "banana",
            "tomato"
        ]
    }
]
```

#### Get store inventory:
Retrieves a list of the store's inventory

```http
GET http://localhost:8080/api/products
```

ex.response:
```json
	 {
        "id": 1,
        "name": "apple",
        "type": "fruit",
        "itemPrice": 0.5
    },
    {
        "id": 2,
        "name": "banana",
        "type": "fruit",
        "itemPrice": 0.4
    },
```
#### Get by category:
Fetch items by their respective category passed as a path variable

Ex.
```http
  GET http://localhost:8080/api/products/fruit
```

```json
[
{
        "id": 1,
        "name": "apple",
        "type": "fruit",
        "itemPrice": 0.5
    },
    {
        "id": 2,
        "name": "banana",
        "type": "fruit",
        "itemPrice": 0.4
    }
]
```

### User functionality:
Users can see the store's inventory, add items to cart and buy them. Active promotions are taken in consideration when calculating the final price.

#### Add to cart:
Add item from inventory to cart via ID

Ex.
```http
  POST http://localhost:8080/api/cart
```

```json
	7
```
Only avaliable items can be added to cart


#### Get avaliable products:
Retrieves products currently for sale

Ex.
```http
  GET http://localhost:8080/api/products
```

```json
[
 {
        "id": 1,
        "name": "apple",
        "type": "fruit",
        "itemPrice": 0.5
    },
    {
        "id": 2,
        "name": "banana",
        "type": "fruit",
        "itemPrice": 0.4
    },
]
```

#### Get items from cart:
Retrieves items currently in cart

Ex.
```http
  GET http://localhost:8080/api/cart
```

```json
[
    {
        "id": 1,
        "name": "apple",
        "type": "fruit",
        "itemPrice": 0.5
    },
    {
        "id": 2,
        "name": "banana",
        "type": "fruit",
        "itemPrice": 0.4
    },
    {
        "id": 3,
        "name": "banana",
        "type": "fruit",
        "itemPrice": 0.4
    },
    {
        "id": 4,
        "name": "potato",
        "type": "vegetable",
        "itemPrice": 0.26
    },
    {
        "id": 5,
        "name": "tomato",
        "type": "vegetable",
        "itemPrice": 0.3
    },
    {
        "id": 6,
        "name": "banana",
        "type": "fruit",
        "itemPrice": 0.4
    },
    {
        "id": 7,
        "name": "potato",
        "type": "vegetable",
        "itemPrice": 0.26
    }
]
```
#### Get by category:
Fetch items by their respective category passed as a path variable

Ex.
```http
  GET http://localhost:8080/api/products/fruit
```

```json
[
{
        "id": 1,
        "name": "apple",
        "type": "fruit",
        "itemPrice": 0.5
    },
    {
        "id": 2,
        "name": "banana",
        "type": "fruit",
        "itemPrice": 0.4
    }
]
```


#### Checkout:
When checked-out, 'bought' products disappear from store's inventory. Final price is calculated upon consideration of active promotions:

Ex.
```http
  GET http://localhost:8080/api/checkout
```
With the items above + **2for1** and **buy1get1**, total price is:

```json
Total price of products: 1.99
```
