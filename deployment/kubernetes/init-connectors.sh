

{
    "name": "account-appointment-connector",
    "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "tasks.max": "1",
      "database.hostname": "account-postgres-service",
      "database.user": "user",
      "database.password": "admin",
      "database.dbname": "postgres",
      "table.include.list": "account.appointment_outbox",
      "topic.prefix": "account_created",
      "tombstones.on.delete" : "false",
      "slot.name": "account_appointment_outbox_slot",
      "plugin.name": "pgoutput"
    }
}

{
    "name": "pet-appointment-connector",
    "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "tasks.max": "1",
      "database.hostname": "pet-postgres-service",
      "database.user": "user",
      "database.password": "admin",
      "database.dbname": "postgres",
      "table.include.list": "pet.appointment_outbox",
      "topic.prefix": "pet_created",
      "tombstones.on.delete" : "false",
      "slot.name": "pet_appointment_outbox_slot",
      "plugin.name": "pgoutput"
    }
}