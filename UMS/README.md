# User details registration and fetching api's

## Description
	This project is a simple implementation of a user registration and user details fetch end points for a RESTful API using Spring Boot. 

## Table of Contents
- [Usage](#usage)
- [Configuration](#configuration)
- [Running the Tests](#running-the-tests)
- [Built With](#built-with)
- [Project Setup](#project-setup)

### Prerequisities
- Java 17 or higher
- Maven 3.6.0 or higher
- Git
- Postgresql database

##### Clone the repository
git clone https://github.com/BhaskarKumar7/ums-repo.git

##### Navigate to the project directory
cd {project-location}

##### Build the project
mvn clean install

##### Run the application
mvn spring-boot:run

## Usage
This project has got two Api's
- [POST] http://localhost:9787/api/user/register
	- This end point accepts a JSON request body containing the user details and validates it.
	- This end point also return appropriate responses to indicate success or failure of the registration process. 
- [GET] http://localhost:9787/api/user/fetch
	- This end point fetches the users information from the database based on username as request parameter.
	- This end point also returns appropriate responses to indicate success or failure of the user fetch process.
- [SWAGGER END POINT] http://localhost:9181/swagger-ui/index.html

## Configuration
##### Changes need to be done in application.properties file
- spring.datasource.username={your db username}
- spring.datasource.password={your db password}
- spring.datasource.url=jdbc:postgresql://localhost:{your db port no}/ums

## Running the Tests
- Test cases are implemented for controller and service class methods.
- run mvn test in cmd to execute the test cases

## Built With
- spring boot version (3.3.0)
- swagger ui version (2.5.0)
- postgresql version 15
- jakarta validation api
- spring tool suite IDE
- bucket4j library (for implementing rate limiting for the API)
- bcrypt library (to hash the password)