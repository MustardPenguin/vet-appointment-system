<h2>Kubernetes deployment using Kind</h2>

Prerequisites:
 - Docker
 - Kubectl
 - Kind

First, the image of all the microservices are needed, run at the root of the project(vet-appointment-system).
```bash
mvn clean install
```

Additionally, since there is no official support for Confluent Schema Registry in Debezium, certain jar files are required in the Connect plugin directory.
https://debezium.io/documentation/reference/stable/configuration/avro.html#confluent-schema-registry


Build the debezium-connect-avro.dockerfile at the deployment file(vet-appointment-system/deployment)
```bash
docker build -t com.vet.appointment.system/debezium-connect:1.0-SNAPSHOT -f debezium-connect-avro.dockerfile .
```

Once that is done, these images should exist on your docker image list with this command, they will all need to be loaded into the Kind cluster.
```bash
docker image ls | grep com.vet
```

Images that should be shown:

```bash
com.vet.appointment.system/appointment-service:1.0-SNAPSHOT
com.vet.appointment.system/account-service:1.0-SNAPSHOT
com.vet.appointment.system/pet-service:1.0-SNAPSHOT
com.vet.appointment.system/availability-service:1.0-SNAPSHOT
com.vet.appointment.system/payment-service:1.0-SNAPSHOT
com.vet.appointment.system/eureka-service:1.0-SNAPSHOT
com.vet.appointment.system/api-gateway:1.0-SNAPSHOT
com.vet.appointment.system/debezium-connect:1.0-SNAPSHOT
```

For each image, load them into the Kind cluster
```bash
kind load docker-image com.vet.appointment.system/appointment-service:1.0-SNAPSHOT
```
```bash
kind load docker-image com.vet.appointment.system/account-service:1.0-SNAPSHOT
```
```bash
kind load docker-image com.vet.appointment.system/pet-service:1.0-SNAPSHOT
```
```bash
kind load docker-image com.vet.appointment.system/availability-service:1.0-SNAPSHOT
```
```bash
kind load docker-image com.vet.appointment.system/payment-service:1.0-SNAPSHOT
```
```bash
kind load docker-image com.vet.appointment.system/eureka-server:1.0-SNAPSHOT
```
```bash
kind load docker-image com.vet.appointment.system/api-gateway:1.0-SNAPSHOT
```
```bash
kind load docker-image com.vet.appointment.system/debezium-connect:1.0-SNAPSHOT
```

Finally, the deployment files can be run with these commands in this order:
```bash
kubectl apply -f database-deployment.yml
kubectl apply -f infrastructure-deployment.yml
kubectl apply -f microservices-deployment.yml
```