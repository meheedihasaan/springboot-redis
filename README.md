## Spring Boot - Redis

### Description
Boilerplate template for using Redis with Spring Boot Application.

### Redis
Redis, which stands for "REmote DIctionary Server", is an open-source, key-value database server that stores data in memory. It's known for being fast because it can read and write data from memory, which is much faster than accessing a disk or SSD. Redis is also durable because it persists data, but all reads are from a copy of the data in memory. Redis can be used as a database, cache, or message broker. It's often used for caching web pages to reduce the load on servers, and it can also be used to create responsive apps by storing frequently accessed data. Redis has some features that make it attractive for use as a database, such as support for transactions and publish/subscribe messaging. However, it doesn't have all the features of a traditional database like MySQL or MongoDB. 

Here are some other characteristics of Redis:
#### Data structures: Redis supports multiple data structures and native data types that can help solve a variety of problems, such as caching, queuing, and event processing.
#### Geospatial processing: Redis supports geospatial data storage and indexing, making it a good choice for location-based applications.
#### Replication: Redis has built-in replication capabilities that let you place data closer to the user for lower latency.
#### Persistence: To prevent data loss, Redis has a built-in persistence module that writes the in-memory state to dump files on disk.
#### Single-threaded: Redis is single-threaded, which means that every command or operation is atomic. While a command is executing, other clients' requests will not be served.

### Technology
* Language: Java 21
* Build Tool: Maven
* Spring Boot: 3.3.2
* Database: Redis

### Feature
- [x] CRUD operation with Redis
- [x] Redis as in-memory storage

### Clone Repository
Clone this repository from https://github.com/meheedihasaan/springboot-redis.git or use the below command for the GitHub CLI:

```
gh repo clone meheedihasaan/springboot-redis
```

### Build & Run
There are several ways to build and run a Spring Boot application. After cloning this repository, you can build it using the below command:

```
mvn clean install
```

Before giving this command, make sure that Maven is already installed on your PC. This command will clear the target folder and previously generated JAR files, build an artifact, and then create a new JAR file,  <span style = "color: cyan">springboot-redis-0.0.1-SNAPSHOT.jar</span> inside the target directory. Now, you can run this JAR file using the below command:

```
java -jar target\springboot-redis-0.0.1-SNAPSHOT.jar
```

Also, you can directly run this application without an explicit build using the below command:

```
mvn spring-boot:run
```

Or, you can run this application via the <span style = "color: cyan">Run</span> button of your preferred IDE.

### API Endpoints
1. CREATE http://localhost:8081/users/create
   This endpoint creates a User and returns a UUID of the user.

3. GET http://localhost:8081/users
   This endpoints returns all the users.

4. GET http://localhost:8081/users/{id}
   This endpoints returns a single user based on the id.

5. DELETE http://localhost:8081/users/{id}
   This endpoint delete a user based on his id.

6. GET http://localhost:8081/users/{id}/generate-token
   This endpoint generate a token of a user and store his email in Redis.

7. GET http://localhost:8081/users/email/get?token={id}
   This endpoint returns the previously stored email by the token.
