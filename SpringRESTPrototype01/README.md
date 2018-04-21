## SpringRESTPrototype01
A simple Spring REST project. Began by copying Spring guide to creating a basic RESTful web service (https://spring.io/guides/gs/rest-service/) and made some changes.

## Features
* Uses Spring Boot, which includes embedded Tomcat and other features
* Apache Maven for build automation

## Run Instructions
To run, execute **SpringRESTPrototype01Application.java** (from Eclipse, right-click and select Run As -> Java Application)
If launch is successful, the URLs that become available are: 
* http://localhost:8080/greeting
* http://localhost:8080/greeting?name=Any Name
* http://localhost:8080/greeting?userid=1234
* http://localhost:8080/greeting?name=Any%20Name&userid=1234
