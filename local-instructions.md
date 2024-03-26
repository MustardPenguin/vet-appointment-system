<h2>To run for local development</h2>

<u><h3>Prerequisites</h3></u>
- Java 17+
- Docker-compose
- Docker
- Maven
- Jq

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

Start this bash script in order to initialize Debezium connectors. This script requires jq command to be installed in your system.
https://jqlang.github.io/jq/

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