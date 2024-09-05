# OnMa - REST API

The **OnMa RestAPI** is a REST API built using Spring Boot, backed by a MySQL database. It provides CRUD (Create, Read, Update, Delete) functionality for managing users, products, and orders. The API is structured with models, services, repositories, and controllers to ensure separation of concerns and scalability.

## Features
- User, Product, and Order management
- Full CRUD functionality via HTTP requests
- DTOs (Data Transfer Objects) for managing request and response data
- MySQL as the database for persistent data storage
- RESTful API endpoints for easy integration

## Technologies
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **MySQL**
- **Maven** for build management

---

## Table of Contents
- [Models](#models)
- [Repositories](#repositories)
- [Services](#services)
- [Controllers & Endpoints](#controllers--endpoints)
- [Setup Instructions](#setup-instructions)

---

## Models

The **OnMa RestAPI** API provides three key models: **User**, **Product**, and **Order**. These models define the entities stored in the MySQL database and their relationships. The models are barebone and may be expanded on in the future.

### User Model
```java
public class User {

    private Long id;
    private String name;
    private String password;
}
```

### Product Model
```java
public class Product {

    private Long id;
    private ProductCategory category;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private long sellerId;
}
```

### ProductCategory Model
```java
public enum ProductCategory {

    Books,
    Electronics,
    Entertainment,
    Fashion,
    Grocery,
    Home,
}

```

### Order Model
```java
private long id;
private long customerId;
private LocalDateTime orderDate;
private long productId;
private int quantity;
private OrderStatus status;
private double totalAmount;
```

### OrderStatus Model
```java
public enum OrderStatus {

    Pending,
    Delivered,
    Cancelled,
}
```
## Repositories

The Spring Data JPA's *CrudRepository* interface is leveraged to perform database operations on the entities. Each model has its own repository:
- **UserRepository**
- **ProductRepository**
- **OrderRepository**

## Services

The services layer abstracts the business logic and interacts with the repositories. By using DTOs (Data Transfer Objects) for requests and responses,
the API is able to keep the internal data model separate from the external facing API model. This allows for:

- **Data Security**: Sensitive fields or those only related to business logic can be hidden from the API response.
- **Decoupling**: Internal models can change without affecting API consumers.
- **Input validation**: DTOs can ensure proper data validation before interacting with the database.

### Example User Service
```java
public class UserService {
  public void createUser(UserRequest userRequest) {...}
  public List<UserResponse> getAllUsers() {...}
  public UserResponse getUserById(Long id) {...}
  public UserResponse loginUser(UserRequest userRequest) {...}
  public void updateUser(Long id, UserRequest userRequest) {...}


  private UserResponse mapUserToUserResponse(User user) {
    return UserResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .password(user.getPassword())
            .build();
  }
```

### Example DTOs

UserRequest DTO
```java
public class UserRequest {

    private String name;
    private String password;
}
```

UserResponse DTO
```java
public class UserResponse {

    private Long id;
    private String name;
    private String password;
}
```

## Controllers & Endpoints

Each model has a dedicated REST controller that provides mappings for CRUD operations. Below are the mappings and endpoints for each entity.

### User API

| HTTP Method | Endpoint          | Description                |
|-------------|-------------------|----------------------------|
| `POST`      | `/user/register`  | Create a new user          |
| `POST`      | `/user/login`     | Login a user              |
| `GET`       | `/user`           | Get all users              |
| `GET`       | `/user/{id}`     | Get a user by ID            |
| `PUT`       | `/user/{id}`     | Update user information     |
| `DELETE`    | `/user/{id}`     | Delete a user               |

### Product API

| HTTP Method | Endpoint           | Description                |
|-------------|--------------------|----------------------------|
| `POST`      | `/product`        | Create a new product        |
| `GET`       | `/product/{id}`   | Get a product by ID         |
| `PUT`       | `/product/{id}`   | Update product information  |
| `DELETE`    | `/product/{id}`   | Delete a product            |

### Order API

| HTTP Method | Endpoint           | Description                 |
|-------------|--------------------|-----------------------------|
| `POST`      | `/order`          | Create a new order           |
| `GET`       | `/order/{id}`     | Get an order by ID           |
| `PUT`       | `/order/{id}`     | Update an order              |
| `DELETE`    | `/order/{id}`     | Delete an order              |


## Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven
- MySQL server

### Installation

1. **Clone the repository:**
```bash
git clone https://github.com/DomRuff/OnMa-RestAPI.git
cd OnMa-RestAPI
```

2. **Configure MySql:** Set up a MySQL database and update the `application.properties` file with your MySQL configuration:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/olla_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. **Build the project:** Run Maven to install dependencies and build the project:
```bash
mvn clean install
```

4. **Run the application:**
```bash
mvn spring-boot:run
```
### API Testing
With in `api` folder, three different HTTP files can be found which depict test API calls. You may test the API or manually add instances of the modesl through them.
