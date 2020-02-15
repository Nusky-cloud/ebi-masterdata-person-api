# REST API for person management

This repository contains the REST API for person management. You can create, update, fetch and delete person data using this REST API. Also there is a web front-end and a command line client which uses this REST API.

1) Web frontend - <repository link>
2) Command line client - <repository link>

# How to run?

## Requirements

For building and running the REST API you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.5](https://archive.apache.org/dist/maven/maven-3/3.5.0/binaries/)

## Running

First, clone this repository on your computer.
```shell
git clone <repository link>
```

After that, change directory to project root and execute below command. This will build and run the project.

```shell
mvn spring-boot:run
```

After the service is up & running, send a GET request to the URL via a REST API client (i.e. [Postman] (https://www.postman.com/downloads/)) :- 

```shell
GET 
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
	
	Username is sa and there is no password.

# API Information

Please make sure to provide authorization headers given above when accessing the APIs.

| API| Description |
|----|-------------|
|POST http://localhost:8080/person/create|This is used to create a new person. Request payload should be ```shell {
	"firstName": "Larry",
	"lastName": "Elison",
	"age": "25",
	"favouriteColor": "red",
	"hobby": ["cycling", "swimming"]
}```|
