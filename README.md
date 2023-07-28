# Invento Backend API - TEST

This document explains the backend API which implements a Reactive Messaging 
flow. 

Receives a message from an API client, publishes it to a queue in RabbitMQ, and 
has a consumer which reads off the messages and prints (logs) them on the console
(and to a log file).

# Basics
### Java Version
The application has been developed using Java version
```
11
```

### Spring Version
The application has been developed on spring version 
```
'org.springframework.boot' version '2.7.14'
```

When the application is run locally, it launches the following
- server port: ```7000```
- [localhost](http://localhost:7000)
- [Swagger UI](http://localhost:7000/swagger-ui/index.html) 


# RabbitMQ
### Local instance

To install a local RabbitMQ instance on ```windows```, one needs to do the following:
- download and install [Erlang](https://erlang.org/download/otp_versions_tree.html)
- download and install [RabbitMQ](https://github.com/rabbitmq/rabbitmq-server/releases/download/v3.12.2/rabbitmq-server-3.12.2.exe)
- run the following command in the RabbitMQ Command Prompt (sbin dir) to enable the management portal
```shell
rabbitmq-plugins enable rabbitmq_management
```

Once installed successfully, you are able to access the RabbitMQ Management portal with the following credentials.
- portal [rabbitMQ](http://localhost:15672/)
- username: ```guest```
- password: ```guest```

### Docker
Alternatively, one could use docker to run a RabbitMQ docker image if they don't wish to have it installed locally
There is a docker file (docker-compose.yml) which is in the project directory. You need to run the following command 
while in the project root directory.

To download docker, please use the following [docker download](https://www.docker.com/products/docker-desktop/)

```shell
docker-compose up
```



Then visit the following link [rabbitmq](http://localhost:15672) to access the management portal, and the credentials remain
the same ```guest``` / ```guest```








