# Builds a docker image with the necessary jar files to work with Confluent schema registry
FROM debezium/connect:2.5

RUN mkdir /kafka/connect/debezium-connector-schemaregistry
COPY ./debezium-dependencies/jar /kafka/connect/debezium-connector-schemaregistry