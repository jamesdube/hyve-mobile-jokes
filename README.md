# Hyve Jokes App

Downloads a number of jokes from [chuck norris jokes](https://api.chucknorris.io/jokes/random) and saves them to a database

## Prerequisites

What things you need to install the software and how to install them

- Maven
- JDK 1.8

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Clone repository:

```
git clone git@github.com:jamesdube/hyve-mobile-jokes.git
```

### Build the project:

```
cd hyve-mobile-jokes && mvn clean install
```

## Environment Setup

To get started you need the following services running,
- mysql

### 1. Mysql

```shell script
docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql:8.0
```