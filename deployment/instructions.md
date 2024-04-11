<h2>Kubernetes deployment using Kind</h2>

Prerequisites:
 - Docker
 - Kubectl
 - Kind
 - Maven
 - Helm charts

First, the image of all the microservices are needed, run at the root of the project(vet-appointment-system).
```bash
mvn clean install
```

Then, create a Kind cluster:
```bash
kind create cluster
```

Additionally, since there is no official support for Confluent Schema Registry in Debezium, certain jar files are required in the Connect plugin directory.
https://debezium.io/documentation/reference/stable/configuration/avro.html#confluent-schema-registry


Build the debezium-connect-avro.dockerfile at the deployment file(vet-appointment-system/deployment)
```bash
docker build -t com.vet.appointment.system/debezium-connect:1.0-SNAPSHOT -f debezium-connect-avro.dockerfile ../
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

The images for the infrastructure services are also needed, so load them as well:
```bash
kind load docker-image confluentinc/cp-zookeeper:latest
```
```bash
kind load docker-image confluentinc/cp-schema-registry:latest
```
```bash
kind load docker-image confluentinc/cp-zookeeper:latest
```
```bash
kind load docker-image provectuslabs/kafka-ui:latest
```


Afterwards, at the directory of vet-appointment-system-chart, run the infrastructure chart, then all the services in any order:

```bash
helm install <name> ./charts/infrastructure
helm install <name> ./charts/account-service -f ./values.yml
helm install <name> ./charts/pet-service -f ./values.yml
helm install <name> ./charts/appointment-service -f ./values.yml
helm install <name> ./charts/availability-service -f ./values.yml
helm install <name> ./charts/payment-service -f ./values.yml
```

To uninstall the chart, run:
```bash
helm uninstall <chart-name>
```

<h3>Accessing the Kubernetes dashboard</h3>

Documentation for reference: https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/

Run this command to start a dashboard in 'kubernetes-dashboard' namespace

```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.7.0/aio/deploy/recommended.yaml
```

Then, an authentication token will be needed to access the dashboard. Run this command to create a service account in the kubernetes-dashboard namespace

```bash
kubectl apply -f dashboard-service-account.yml
```

Then, run this command to get the token

```bash
kubectl -n kubernetes-dashboard create token admin-user
```

Finally, run this command to enable access to the dashboard

```bash
kubectl proxy
```

The dashboard can be accessed at this URL: http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/