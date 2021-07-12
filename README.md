# Ecommerce-Demo API

10,000+ Unique Customers

20 Endpoints
```
(api/register):
# Register Customer
/customer

(/api/customers):
# Customer
/update
/delete/{email}
/list/customers
/{email}

# Cart
/{email}/cart/add/{productName}
/{email}/cart/remove/{productName}
/{email}/cart/empty
/{email}/cart/get

# Credit Card
/{email}/creditCard/add
/{email}/creditCard/remove/{fourDigits}
/{email}/creditCards/list
/{email}/orders/add
/{email}/orders/updateStatus/{uuid}/{status}
/{email}/orders/list

(api/inventory):
# Update item
/update/{productName}

# Add item
/add

# Get item
/get/{productName}

# Remove item
/remove/{productName}

# List item
/items/list
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
