# Shell script for sending POST request to Debezium connector to creating connectors for postgres outbox tables

# Gets IP address of Debezium Postgres container
DATABASE_HOSTNAME=$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' postgres_cdc)

# Json config
account_appointment_json=$(jq -n \
  --arg dbh "$DATABASE_HOSTNAME" \
  '{
    "name": "account-appointment-connector",
    "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "tasks.max": "1",
      "database.hostname": $dbh,
      "database.user": "user",
      "database.password": "admin",
      "database.dbname": "postgres",
      "table.include.list": "account.appointment_outbox",
      "topic.prefix": "debezium",
      "tombstones.on.delete" : "false",
      "slot.name": "account_appointment_outbox_slot",
      "plugin.name": "pgoutput"
    }
  }')

pet_appointment_json=$(jq -n \
  --arg dbh "$DATABASE_HOSTNAME" \
  '{
    "name": "pet-appointment-connector",
    "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "tasks.max": "1",
      "database.hostname": $dbh,
      "database.user": "user",
      "database.password": "admin",
      "database.dbname": "postgres",
      "table.include.list": "pet.appointment_outbox",
      "topic.prefix": "debezium",
      "tombstones.on.delete" : "false",
      "slot.name": "pet_appointment_outbox_slot",
      "plugin.name": "pgoutput"
    }
  }')

# Curl POST request to Debezium connector
curl -X POST -H "Content-Type: application/json" --data "$account_appointment_json" http://localhost:8083/connectors
curl -X POST -H "Content-Type: application/json" --data "$pet_appointment_json" http://localhost:8083/connectors
