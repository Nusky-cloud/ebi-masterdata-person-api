# REST API for EBI person management

This repository contains the REST API for EBI person management. You can create, update, fetch and delete person data using this REST API. Also there is a web front-end and a command line client which uses this REST API.

1) Web frontend        - https://github.com/Nusky-cloud/ebi-person-management-ui.git
2) Command line client - https://github.com/Nusky-cloud/ebi-person-management-cli.git

# How to run?

## Requirements

For building and running the REST API you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.5](https://archive.apache.org/dist/maven/maven-3/3.5.0/binaries/)

**NOTE :** After extracting Apache Maven to a location, add the **C:\Program Files\apache-maven-3.5.0\bin** path to the **Path** variable in **environment variables**. Also add a user variable **JAVA_HOME** and set the value to JDK path **C:\Program Files\Java\jdk1.8.0_144**. Open a console and execute **mvn -version** and you should see below output. 
```shell
Apache Maven 3.5.0 (ff8f5e7444045639af65f6095c62210b5713f426; 2017-04-04T01:09:06+05:30)
Maven home: C:\Installations\apache-maven-3.5.0-bin\apache-maven-3.5.0\bin\..
Java version: 1.8.0_144, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk1.8.0_144\jre
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

## Running

- First, clone this repository on your computer or download as a zip file.
```shell
git clone https://github.com/Nusky-cloud/ebi-masterdata-person-api.git
```

- After that, open a console and change directory to project root and execute below command. This will build and run the project.

```shell
mvn spring-boot:run
```

- After the service is up & running, send a **GET** request to the URL via a REST API client (i.e. [Postman](https://www.postman.com/downloads/)) :- 

```shell
http://localhost:8080/person/getAll
```

All the APIs are secured. Therefore you have to provide authorization headers:
```shell
username : admin
password : password
```
	
You will receive two persons data as response which is inserted to the database when the service starts running. H2 database is used as the in-memory database. You can access h2 database from below URL:-

```shell 
http://localhost:8080/person/h2
```
	
**NOTE :** It will prompt for authorization. Please provide **admin** for username and **password** for password. After that you will see the login UI of h2 database. Please provide below details for the login. Password should be **empty**.

```shell
JDBC URL  : jdbc:h2:mem:testdb
User Name : sa
Password  : 
```

# API Information

Please make sure to provide authorization headers given above when accessing the APIs.


- **POST** http://localhost:8080/person/create <br>
This is used to create a new person. Request payload should be 
```shell 
{
	"firstName": "Larry",
	"lastName": "Elison",
	"age": "25",
	"favouriteColor": "red",
	"hobby": ["cycling", "swimming"]
}
```

- **PUT** http://localhost:8080/person/update <br>
This is used to update a person. Request payload should be 
```shell 
{
	"personId": "3",
	"firstName": "LarryUpdated",
	"lastName": "Elison",
	"age": "25",
	"favouriteColor": "red",
	"hobby": ["cycling", "swimming"]
}
```
- **GET** http://localhost:8080/person/3 <br> 
This is used to fetch one person. You need to provide the **patient id** as URL path parameter.

- **GET** http://localhost:8080/person/getAll <br>
This is used to fetch all persons.

- **DELETE** http://localhost:8080/person/remove/3 <br> 
This is used to remove one person. You need to provide the **patient id** as URL path parameter.

Additionally, you can test this REST API via the web front-end or command line client. The repository links are given above.
