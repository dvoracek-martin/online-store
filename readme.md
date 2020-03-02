# Online-store coding exercise

The Online-store-app is a simple coding exercise. It's written in Java 8 and Maven 3.6.1 was used to build the app.
To build the app, navigate to the main folder and run `mvn clean install`. If you don't have either Java 8 or Maven installed on your computer, please follow these guides:

* https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
* https://maven.apache.org/install.html

Once you have the application built, navigate to the `./online-store-app/target` folder and run the program with a command `java -jar online-store-app-0.0.1-SNAPSHOT.jar`.

To start the application, you'll also need PostgreSQL 9.6.5 database running on the port 5434. You can either run it on your machine or, if you have Docker installed, you can use ./docker/online-store/docker-compose.yml and start the database in a container. 
The database schema will be regenerated automatically during every start of the application.

Application listens on the port 8080 and has exposed following REST endpoints:

## UserRestController:

* Http.POST ("/api/users"), RequestBody: { "email": "String"}, ResponseBody: { "id": Long, "email": "String"}, HttpStatus.CREATED
* Http.GET ("/api/users/getAllUsers"), RequestBody:, ResponseBody: [{ "id": Long,  "email": "String"}...], HttpStatus.OK

## ProductRestController:
* Http.POST ("/api/products), RequestBody: { "productName": "String","productPrice": "BigDecimal"}, ResponseBody:{ "id":Long, "productName": "String","productPrice": BigDecimal}, HttpStatus.CREATED
* Http.PUT ("/api/products/{id}"), RequestParam: "id":Long, RequestBody: { "productName": "String","productPrice": BigDecimal}, ResponseBody:{ "id":Long, "productName": String,"productPrice": BigDecimal}, HttpStatus.OK
* Http.GET ("/api/products/getAllProducts"), RequestBody:, ResponseBody: [{ "id":Long, "productName": "String","productPrice": BigDecimal}...], HttpStatus.OK

## ShoppingOrderRestController:
* Http.POST ("/api/shoppingOrders), RequestBody: {"userId":Long, "productIds":Long[]}, ResponseBody: { "id":Long, "userEMail": String,"purchasedAt": LocalDateTime, "products" : [{ "id":Long, "productName": String,"productPrice": BigDecimal}..],"priceTotal":BigDecimal}, HttpStatus.CREATED
* Http.GET ("/api/shoppingOrders"), RequestBody:, RequestParam: "dateFrom":String, RequestParam: "dateTo":String, ResponseBody: [{ "id":Long, "userEMail": String,"purchasedAt": LocalDateTime, "products" : [{ "id":Long, "productName": String,"productPrice": BigDecimal}..],"priceTotal":BigDecimal}...], HttpStatus.OK



## Authentication
Authentication could be handled by OAuth2, if we'd have a suitable authorization server. If not, plain Spring Security would also do the job.

## Redundancy
There are more ways, how to make the service redundant. One is to dockerize it and deploy it on some Kubernetes-based solution. With proper setting, Kubernetes will take care of autoscaling and distribution of the load. You can read more about the possibilities of the setting in Kubernets in one of my older pet-projects https://github.com/dvoracek-martin/Cheatsheet.
Another option would be to have a load balancer like Zuul and the applications physically running on several servers at once. 


In case of any questions, feel free to contact me on Dworza@gmail.com  
