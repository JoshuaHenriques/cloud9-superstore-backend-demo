<p align="center">
  <h3 align="center">Store Manager</h3>

<p align="center">
    REST API Application Demonstration

<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
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
<!-- ## About The Project -->
<!-- This app is a replica of a real world store.* Great to fork to expand for your use case -->

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
    /{customerId}/cart/get
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
(230 Passing Tests)
Unit Tests:
  @Controller 
  @Service
Integration Tests *in development*:
  @WebMvcTest
  @JpaDataTest
```

#### Models
<a href="https://github.com/JoshuaHenriques/store-manager/blob/master/src/main/resources/db/migration/V0_0_1__schema.sql">Schema</a> for a clearer model representation

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
```

#### Built With
* [Springboot 2.5.3]
* [Java 11]
* [Gradle]

#### Usage for CLI
1. Clone the repo
   ```sh
   git clone https://github.com/joshuahenriques/store-manager.git
   ```
2. Install postgresql and create database
   ```sh
   sudo apt install postgresql -y
   su postgres
   psql
    *Within psql* "CREATE DATABASE store_manager_db;"
   ```
2. In root directory build the app
   ```sh
   ./gradlew build
   ```
3. Migrate database with Flyway
   ```sh
   ./gradlew flywayMigrate
   ```
4. Then run it
   ```sh
   ./gradlew bootRun
   ```

#### Usage for Docker
1. Clone the repo
   ```sh
   git clone https://github.com/joshuahenriques/store-manager.git
   ```
2. In root directory build the app
   ```sh
   docker-compose up

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

[https://github.com/joshuahenriques/store-manager](https://github.com/joshuahenriques/store-manager)
