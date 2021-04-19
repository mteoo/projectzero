# Project Zero

### Demo project
Developed by Matheus Duarte Dias, see more on

* [GitHub](https://github.com/mteoo)
* [Linkedin](https://www.linkedin.com/in/matheusdiasdev)


### Developed with

- Spring Rest
- Spring Basic Authorization
- Swagger
- QueryDsl
- Api ExceptionHandlers
- H2 DB
- JPA Custom Validators
- Docker

### Running the demo locally

mvn clean install

spring-boot:run

* Start on localhost:8081

### Running docker

docker build -t projectzero:1.0 .

docker run -d -t -p 8081:8081 projectzero:1.0 .

* Start on localhost:8081


#### Auth default

* username: admin
* pass: projectzero

#### URLs

* [GitHub Source] http://localhost:8081/source
* [Swagger Doc] http://localhost:8081/swagger
* [H2 Console] http://localhost:8081/h2-ui




