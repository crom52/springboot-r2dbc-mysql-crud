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

## How to run app:
1. Open the terminal
2. run `docker-compose up`
