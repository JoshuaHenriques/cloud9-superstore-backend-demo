<p align="center">
  <h3 align="center">Cloud9 Superstore Demo</h3>
  <p align="center">
    RESTful API Application
  </p>
</p>

<!-- ABOUT THE PROJECT -->
## About The Project
Inspired by the Netflix show Cloud9 Superstore.

[Entity-Relationship Diagram](https://github.com/JoshuaHenriques/cloud9-superstore-demo/blob/master/Cloud9-ERD.png)

~55 Endpoints:

Address
api/address
  /update/{entity}/{uuid}
Cart:
  /api/item
    /add
    /update/{itemId}
    /delete/{itemId}
    /get/{itemId}
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
    
135/~200 Passed Tests, 5 Tests Ignored
  Tests Controller, service, webmvc, jpadatabase

### Built With

* [Springboot 2.5.3]
* [Java 11]
* [Gradle]

### Usage

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

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.

<!-- CONTACT -->
## Contact

Personal Website - [https://joshuahenriques.com](https://joshuahenriques.com)

Project Link: [https://github.com/joshuahenriques/cloud9-backend-demo](https://github.com/joshuahenriques/cloud9-backend-demo)
