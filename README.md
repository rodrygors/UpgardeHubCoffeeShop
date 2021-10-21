# Bookstore SpringBoot API with MySQL- Backend Bootcamp Group 4





This document provides an example of a Coffee Shop REST API.





Main features:

- Manage your purchase of coffee.

- Add/Update/Delete coffees/customers/purchases by ID.

- Add new coffees/customers/purchases by ID.





# CRUD Methods





## coffee-controller





| HTTP METHOD | GET |

| ----------- | --------------- |

| CRUD OP | CREATE

| /api/coffees| Get all coffees

| /api/getCoffeeById{id}| Get coffee by ID






| HTTP METHOD | CREATE |

| ----------- | --------------- |

| CRUD OP | POST

| /api/coffee-create | Create a new coffee





| HTTP METHOD | UPDATE |

| ----------- | --------------- |

| CRUD OP | PUT

| /api/updateCoffee/{id} | Update coffee by ID





| HTTP METHOD | DELETE |

| ----------- | --------------- |

| CRUD OP | DELETE

| /api/deleteCofee/{id} | Delete coffee by ID






## customer-controller





| HTTP METHOD | GET |

| ----------- | --------------- |

| CRUD OP | CREATE

| /api/customers | Get all customeres

| /api/getCustomersById/{id} | Get customers by ID





| HTTP METHOD | CREATE |

| ----------- | --------------- |

| CRUD OP | POST

| /api/customer-create | Add a new customer





| HTTP METHOD | UPDATE |

| ----------- | --------------- |

| CRUD OP | PUT

| /api/updateCustomer/{id} | Update customer by ID





| HTTP METHOD | DELETE |

| ----------- | --------------- |

| CRUD OP | DELETE

| /api/deleteCustomer/{id} | Delete customer by ID





## purchase-controller





| HTTP METHOD | GET |

| ----------- | --------------- |

| CRUD OP | CREATE

| /api/purchases | Get all purchases

| /api/getPurchasesById/{id} | Get purchases by ID





| HTTP METHOD | CREATE |

| ----------- | --------------- |

| CRUD OP | POST

| /api/purchase-create | Add a new purchase





| HTTP METHOD | UPDATE |

| ----------- | --------------- |

| CRUD OP | PUT

| /api/updatePurchase/{id} | Update purchase by ID





| HTTP METHOD | DELETE |

| ----------- | --------------- |

| CRUD OP | DELETE

| /api/deletePurchase/{id} | Delete purchase by ID