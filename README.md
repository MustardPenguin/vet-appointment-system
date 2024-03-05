<h2>Veterinarian appointment system</h2>

This project is a basic appointment system for account, pet, and appointment creation and management. 

It is built following an event-driven microservice architecture using typical patterns for resilient and scalable microservices, such as DDD, clean and hexagonal architecture, CQRS pattern, Saga pattern, outbox pattern, and CDC.

The purpose of this project is purely for educational purposes to learn how to develop distributed microservices and maintaining consistency between them using popular technologies and design principles.

<h2>To run locally</h2>

<u><h3>Kafka deployment</h3></u>

<h4>Debezium dependencies</h4>
As Debezium containers does not have Confluent Schema Registry support, jar files are required in the Connect plugin directory.

https://debezium.io/documentation/reference/stable/configuration/avro.html#confluent-schema-registry

Therefore, before running the kafka deployment, ensure the jar file in debezium-connect found under docker-compose is populated with JAR files, as this will be copied over to the plugin directory in the Debezium connect container.

If not, run mvn clean install in debezium-dependencies found in docker-compose.
```angular2html
mvn clean install
```
This should install the necessary JAR files in the jar directory for debezium connect.

To change dependencies version, update the pom.xml file, clear jar file of .jar files, and run mvn clean install again.

<h4>Docker compose</h4>
To run kafka deployment, run the following command in "infrastructure/docker-compose" directory:

```bash
docker-compose -f kafka-deployment.yml up
```

This should start the following containers:
<ul>
    <li>Kafka</li>
    <li>Zookeeper</li>
    <li>Schema Registry</li>
    <li>Kafka UI</li>
    <li>Postgres</li>
    <li>Debezium Connect</li>
</ul>

Kafka UI should then be located at port 9000 to view topics and schemas.

To remove the containers, run the following command:
```bash
docker-compose -f kafka-deployment.yml down
```

<h4>Initialize connectors</h4>

And start bash script for Debezium connectors, this may take a couple of minutes for connectors to start working.
```bash
./init-connectors.sh
```

Run this script to delete Debezium connectors
```bash
./delete-connectors.sh
```

<u><h3>Services</h3></u>
In the infrastructure folder, run the Eureka server first then the API gateway.
Afterward, to start the services, run the service application found in service container.

<h4>Ports</h4>
The services should run on the following ports:
<ul>
    <li>Account: 8181</li>
    <li>Pet: 8182</li>
    <li>Appointment: 8183</li>
    <li>Availability: 8184</li>
    <li>Payment: 8185</li>
    <li>API Gateway: 8080</li>
    <li>Eureka: 8761</li>
</ul>

<h2>Documentation</h2>

<u><h3>API</h3></u>

<h4>Account</h4>
Create account: POST http://localhost:8080/api/account

Body:

```json
{
  "email": "test@gmail.com",
  "password": "password",
  "firstName": "John",
  "lastName": "Jim"
}
```

Authenticate: POST http://localhost:8080/api/authenticate

Body:

```json
{
  "email": "test@gmail.com",
  "password": "password"
}
```

<h4>Pet</h4>

Create pet: POST http://localhost:8080/api/pet

Headers: 

<ul>
    <li>Authorization: Bearer token</li>
</ul>

Body:

```json
{
  "name": "Buddy",
  "species": "Dog",
  "birthDate": "2021-01-01"
}
```

<h4>Appointment</h4>

Create appointment: POST http://localhost:8080/api/appointment

Headers:
<ul>
    <li>Authorization: Bearer token</li>
</ul>

Body:

```json
{
  "petId": "3ddf2488-72c4-4fe3-9412-b17732777090",
  "description": "update vaccinations",
  "appointmentStartDateTime": "2024-04-24T15:50",
  "appointmentEndDateTime": "2024-04-24T16:55"
}
```

Get appointments: GET http://localhost:8080/api/appointment/{appointmentId}

Headers:
<ul>
    <li>Authorization: Bearer token</li>
</ul>
