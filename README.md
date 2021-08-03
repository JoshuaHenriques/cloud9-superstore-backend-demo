<p align="center">
  <h3 align="center">Ecommerce API Demo</h3>
  <p align="center">
    Ecommerce backend API demo made with Springboot 
  </p>
</p>

<!-- ABOUT THE PROJECT -->
## About The Project
Springboot RESTful API bootstrap for a more complete project from spring initializer.


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
```
