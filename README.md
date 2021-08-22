<p align="center">
  <h3 align="center">Cloud9 Superstore Management System Demo</h3>
  
<p align="center">
    RESTful API Application

<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
    </li>
    <li><a href="#about-the-project">About</a></li>
    <li><a href="#endpoints">Endpoints</a></li>
    <li><a href="#layers-tested">Layers Tested</a></li>
    <li><a href="#models">Models</a></li>
    <li><a href="#dependencies">Dependencies</a></li>
    <li><a href="#built-with">Built With</a></li>
    <li><a href="#usage-for-cli">Usage for CLI</a></li>
    <li><a href="#usage-for-docker">Usage for Docker</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project
This app is a replica of a real world store.* The theme of the project was inspired by the Netflix show Cloud9 Superstore. Great to fork to expand for your use case

#### Endpoints:
```
Address:
api/address
  /update/{entity}/{uuid}

Cart:
  /api/item
    /{customerId}/cart/add/{type}/{itemId}
    /{customerId}/cart/empty
    /{customerId}/cart/delete/{itemId}
    {customerId}/cart/get
    /list

CreditCard:
  /api/customer/creditCard
    /{customerId}/creditCard/add
    /{customerId}/creditCard/delete/{cardId}
    /{customerId}/creditCard/list

Customer:
  /api/customer
    /add
    /update/{customerId}
    /delete/{customerId}
    /get/{customerId}
    /list

Orders:
  /api/orders
    /add
    /update/{customerId}
    /get/{ordersId}
    /list/{customerId}

OnlineInventory:
  /api/inventory/online
    /add/{itemId}
    /update/{inventoryId}
    /delete/{inventoryId}
    /get/{inventoryId}
    /list

StoreInventory:
  /api/inventory/store
    /add/{itemId}
    /update/{inventoryId}
    /delete/{inventoryId}
    /get/{inventoryId}
    /list

Item:
  /api/item
    /add
    /update/{itemId}
    /delete/{itemId}
    /get/{itemId}
    /list

Review:
  api/review
    /add/{itemId}/{reviewId}
    /update/{itemId}/{reviewId}
    /delete/{itemId}/{reviewId}
    /get/{itemId}/{reviewId}
    /list

Login:
  /api/login
    /add
    /update/{loginId}
    /delete/{loginId}
    /get/{loginId}
    /list

Employee:
  /api/employee
    /add
    /update/{employeeId}
    /delete/{employeeId}
    /get/{employeeId}
    /list
    
Store:
  /api/store
    /add
    /update/{storeId}
    /delete/{storeId}
    /get{storeId}
    /list
```
    
#### Layers Tested
```
(~200 Passing Tests)
Unit Tests:
  @Controller 
  @Service
Integration Tests:
  @WebMvcTest
  @JpaDataTest
  @SpringBootTest 
```

#### Models
<a href="#https://github.com/JoshuaHenriques/cloud9-superstore-backend-demo/blob/master/src/main/resources/db/migration/V0_0_1__create_cloud9_schema.sql">Schema</a> for a clearer model representation
```
Address

Customer
   Orders
   CreditCard
   Cart
   
Inventory
   OnlineInventory
   StoreInventory
   
Item
   Review
   
Login
   Attempts
   
Store
   Employee
```

#### Dependencies
```
Flyway Migration
Spring Boot DevTools
Lombok
Spring Web
Spring Data JPA
Spring Security
PostgreSQL Driver
Commons Validator
Hibernate
Auth0 Java JWT
```

#### Built With
* [Springboot 2.5.3]
* [Java 11]
* [Gradle]

#### Usage for CLI
1. Clone the repo
   ```sh
   git clone https://github.com/joshuahenriques/cloud9-superstore-demo.git
   ```
3. In root directory build the app
   ```sh
   ./gradlew build
   ```
4. Then run it
   ```sh
   ./gradlew bootRun
   ```

#### Usage for Docker
1. Clone the repo
   ```sh
   git clone https://github.com/joshuahenriques/cloud9-superstore-demo.git
   ```
3. In root directory build the app
   ```sh
   ./gradlew build
   ```
4. Then run it
   ```sh
   ./gradlew bootRun
   ```

### Contributing
Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### License
Distributed under the MIT License. See `LICENSE` for more information.

### Contact
[https://joshuahenriques.com](https://joshuahenriques.com)

[https://github.com/joshuahenriques/cloud9-backend-demo](https://github.com/joshuahenriques/cloud9-backend-demo)
