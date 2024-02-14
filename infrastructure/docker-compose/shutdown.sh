

curl -X DELETE http://localhost:8083/connectors/account-appointment-connector
curl -X DELETE http://localhost:8083/connectors/pet-appointment-connector
curl -X DELETE http://localhost:8083/connectors/appointment-availability-connector

echo "Deleted connectors"
curl http://localhost:8083/connectors