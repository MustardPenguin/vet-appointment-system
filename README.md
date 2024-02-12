<h2>Veterinarian appointment system</h2>

<h3>Services</h3>
In the infrastructure folder, run the Eureka server first then the API gateway.
Afterward, to start the services, run the service application found in service container.

<h3>Ports</h3>
The services should run on the following ports:
<ul>
    <li>Account: 8181</li>
    <li>Pet: 8182</li>
    <li>Appointment: 8183</li>
    <li>API Gateway: 8080</li>
    <li>Eureka: 8761</li>
</ul>

<h3>SQL Database</h3>
To run a Postgres database, execute the command:

```bash
docker run -d -p 5432:5432 --name postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=admin postgres
```

<h3>Kafka deployment</h3>
Before running the kafka deployment, ensure the jar file in debezium-connect found under docker-compose is populated with JAR files.

If not, run mvn clean install in debezium-dependencies found in docker-compose.
```angular2html
mvn clean install
```
This should install the necessary JAR files in the jar directory for debezium connect.

To run kafka deployment, run the following command in "infrastructure/docker-compose" directory:

```bash
docker-compose -f kafka-deployment.yml up
```

This should run the necessary containers, including kafka broker, zookeeper, schema registry, kafka ui, and debezium connect.
Kafka UI should then be located at port 9000 to view topics and schemas.

To create topics for kafka:
```bash
docker-compose -f init-kafka.yml up
```

