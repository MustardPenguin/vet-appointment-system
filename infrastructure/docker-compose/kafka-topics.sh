
# Usage: ./kafka-topics.sh [KAFKA_HOST] [PARTITIONS]
BOOTSTRAP_SERVER=${1:-localhost:29092}
PARTITIONS=${2:-3}

kafka-topics --list --bootstrap-server $BOOTSTRAP_SERVER

kafka-topics --alter --topic account_created.account.appointment_outbox --partitions $PARTITIONS --bootstrap-server $BOOTSTRAP_SERVER
kafka-topics --alter --topic appointment_created.appointment.appointment_outbox --partitions $PARTITIONS --bootstrap-server $BOOTSTRAP_SERVER
kafka-topics --alter --topic pet_created.pet.appointment_outbox --partitions $PARTITIONS --bootstrap-server $BOOTSTRAP_SERVER
kafka-topics --alter --topic availability_request.appointment.availability_outbox --partitions $PARTITIONS --bootstrap-server $BOOTSTRAP_SERVER
kafka-topics --alter --topic availability_response.availability.appointment_outbox --partitions $PARTITIONS --bootstrap-server $BOOTSTRAP_SERVER
kafka-topics --alter --topic payment_request.appointment.payment_outbox --partitions $PARTITIONS --bootstrap-server $BOOTSTRAP_SERVER
kafka-topics --alter --topic payment_response.payment.appointment_outbox --partitions $PARTITIONS --bootstrap-server $BOOTSTRAP_SERVER
