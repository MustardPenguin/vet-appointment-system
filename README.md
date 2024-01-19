<h2>Veterinarian appointment system</h2>

<h3>Services</h3>
To start the services, run the service application found in service container.
Ensure the API gateway found in infrastructure is started as well.

<h3>SQL Database</h3>
To run a Postgres database, execute the command

```bash
docker run -d -p 5432:5432 --name postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=admin postgres
```

<h3>Kafka deployment</h3>
To run kafka deployment, run the following command in "infrastructure/docker-compose" directory:

```bash
docker-compose -f kafka-deployment.yml up
```