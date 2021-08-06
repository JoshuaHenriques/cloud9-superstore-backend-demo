<p align="center">
  <h3 align="center">Ecommerce API Demo</h3>
  <p align="center">
    Ecommerce backend API demo made with Springboot 
  </p>
</p>

<!-- ABOUT THE PROJECT -->
## About The Project
Springboot RESTful API bootstrap for a more complete project from spring initializer.

[Entity-Relationship Diagram](https://github.com/JoshuaHenriques/cloud9-superstore-demo/blob/master/Cloud9-ERD.png)

Endpoints:

    (api/register):
    # Register Customer
    /customer         POST

    (/api/customers):
    # Customer        
    /update           PUT
    /delete/{email}   DELETE
    /list/customers   GET
    /{email}          GET

    # Cart
    /{email}/cart/add/{productName}     POST
    /{email}/cart/remove/{productName}  DELETE
    /{email}/cart/empty                 PATCH
    /{email}/cart/get                   GET

    # Credit Card
    /{email}/creditCard/add                   POST
    /{email}/creditCard/remove/{fourDigits}   DELETE
    /{email}/creditCards/list                 GET

    # Order
    /{email}/orderDetails/add                           POST
    /{email}/orderDetails/updateStatus/{uuid}/{status}  PUT
    /{email}/orderDetails/list                          GET

    (api/inventory):
    # Update item
    /update/{productName}   PUT
    /add                    POST
    /get/{productName}      GET
    /remove/{productName}   DELETE
    /items/list             GET
    
    135/135 Passed Tests, 5 Tests Ignored

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

Project Link: [https://github.com/joshuahenriques/ecommerce-backend](https://github.com/joshuahenriques/ecommerce-backend)
