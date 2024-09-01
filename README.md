[//]: # ()
[//]: # (    docker run --name springboot-r2dbc-crud-db -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=r2dbc -e MYSQL_USER=username -e MYSQL_PASSWORD=password -p 3306:3306 -d mysql:8.0)

[//]: # (   )
[//]: # (    docker exec -i springboot-r2dbc-crud-db mysql -uroot -ppassword -e "GRANT ALL PRIVILEGES ON *.* TO 'username'@'%' WITH GRANT OPTION;")

[//]: # (    docker exec -i springboot-r2dbc-crud-db mysql -uusername -ppassword -e "CREATE DATABASE r2dbc;")

[//]: # (    docker exec -i springboot-r2dbc-crud-db mysql -uusername -ppassword -e "use r2dbc; CREATE TABLE IF NOT EXISTS game &#40;id INT NOT NULL AUTO_INCREMENT, title VARCHAR&#40;255&#41;, description VARCHAR&#40;255&#41;, published BOOLEAN, PRIMARY KEY &#40;id&#41;&#41;;")

[//]: # (    )
[//]: # (    )
[//]: # (    docker build --no-cache -t springboot-r2dbc-crud-app .)

[//]: # (    docker build -t springboot-r2dbc-crud-app .)

[//]: # (    docker rm -f springboot-r2dbc-crud-app || true)

[//]: # (    docker run -p 9898:9899 --name springboot-r2dbc-crud-app --link springboot-r2dbc-crud-db:mysql -e DATABASE_URL=r2dbc:mysql://springboot-r2dbc-crud-db:3306/r2dbc -d springboot-r2dbc-crud-app)


# Spring Boot R2DBC CRUD Application

This is a simple CRUD application built with **Java 17** and **Spring Boot 3.0.1**. It demonstrates the use of reactive programming with **R2DBC (Reactive Relational Database Connectivity)** and **MySQL**. The application provides RESTful endpoints to manage a collection of games, allowing you to create, read, update, and delete records.

By leveraging the non-blocking nature of R2DBC, this application is optimized for high-performance scenarios where scalability is key. The combination of Spring Boot and Java 17 ensures a modern, robust framework to build reactive web applications.

## Features

- **Reactive Programming**: Utilizing Spring's reactive stack with R2DBC for non-blocking database access.
- **MySQL Database**: Integration with MySQL as the relational database.
- **CRUD Operations**: Basic Create, Read, Update, and Delete operations through RESTful endpoints.
- **Java 17**: Built with the latest LTS version of Java.
- **Spring Boot 3.0.1**: Leverages the power and simplicity of Spring Boot for application development.


## How to run app:
1. Open the terminal.
2. Run the following command:

   ```bash
   docker-compose up

## Endpoints:
### 1. Find all
```text 
  curl --location 'localhost:9898/api/games'
```

### 2. Find by id
```text
  GET: curl --location 'localhost:9898/api/games/1'
```

### 3. Create
```text
   POST: curl --location 'localhost:9898/api/games' \
      --header 'Content-Type: application/json' \
      --data '{
      "title": "crom",
      "description": "crom des",
      "published": true
      }'
```

### 4. Update
 ```text
   PUT: curl --location --request PUT 'localhost:9898/api/games/1' \
   --header 'Content-Type: application/json' \
   --data '{
   "title": "crom",
   "description": "crom des",
   "published": false
   }'
```

### 5. Delete by id
```text
 DELETE: curl --location --request DELETE 'localhost:9898/api/games/1'
```
