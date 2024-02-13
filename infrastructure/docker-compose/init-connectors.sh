


#{
#  "name": "account-appointment-connector",
#  "config": {
#    "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
#    "tasks.max": "1",
#    "database.hostname": "172.29.0.5",
#    "database.user": "user",
#    "database.password": "admin",
#    "database.dbname": "postgres",
#    "table.include.list": "account.appointment_outbox",
#    "topic.prefix": "debezium",
#    "tombstones.on.delete" : "false",
#    "slot.name": "account_appointment_outbox_slot",
#    "plugin.name": "pgoutput"
#  }
#}




#{
#  "name": "account-appointment-connector",
#  "config": {
#    "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
#    "tasks.max": "1",
#    "database.hostname": "172.29.0.5",
#    "database.user": "user",
#    "database.password": "admin",
#    "database.dbname": "postgres",
#    "table.include.list": "pet.appointment_outbox",
#    "topic.prefix": "debezium",
#    "tombstones.on.delete" : "false",
#    "slot.name": "pet_appointment_outbox_slot",
#    "plugin.name": "pgoutput"
#  }
#}