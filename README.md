# Ecommerce-Demo API
#### 172.105.3.51:8080
#### Test Cases: 131 ** explain tests
#### Explain models, controllers, services, repositories
#### Tech/Features used
#### Overall use
#### 20 Endpoints
```
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

## Installation

Build API with gradle.

```bash
# Build API
./gradlew build
```

## Usage

```python
# Run API
./gradlew run
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
