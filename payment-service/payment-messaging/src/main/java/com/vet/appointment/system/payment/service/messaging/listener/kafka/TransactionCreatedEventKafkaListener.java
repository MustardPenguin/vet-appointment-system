package com.vet.appointment.system.payment.service.messaging.listener.kafka;

import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import com.vet.appointment.system.kafka.producer.KafkaMessageHelper;
import com.vet.appointment.system.messaging.DebeziumOp;
import com.vet.appointment.system.payment.service.domain.dto.message.TransactionCreatedEventPayload;
import com.vet.appointment.system.payment.service.domain.ports.input.message.listener.TransactionCreatedMessageListener;
import com.vet.appointment.system.payment.service.messaging.mapper.PaymentMessagingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import transaction_created.payment.payment_outbox.Envelope;
import transaction_created.payment.payment_outbox.Value;

import java.util.List;

@Slf4j
@Component
public class TransactionCreatedEventKafkaListener implements KafkaConsumer<Envelope> {

    private final TransactionCreatedMessageListener transactionCreatedMessageListener;
    private final PaymentMessagingMapper paymentMessagingMapper;
    private final KafkaMessageHelper kafkaMessageHelper;

    public TransactionCreatedEventKafkaListener(TransactionCreatedMessageListener transactionCreatedMessageListener,
                                                PaymentMessagingMapper paymentMessagingMapper,
                                                KafkaMessageHelper kafkaMessageHelper) {
        this.transactionCreatedMessageListener = transactionCreatedMessageListener;
        this.paymentMessagingMapper = paymentMessagingMapper;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }


    @Override
    @KafkaListener(topics = "${kafka-consumer-topic-name.transaction-created-event}",
            groupId = "${applicationDeploymentName}-transaction-created-listener")
    public void receive(@Payload List<Envelope> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("Received transaction propagation event!");
        messages.forEach(message -> {
            if(message.getBefore() == null && message.getOp().equals(DebeziumOp.CREATE.getValue())) {
                Value avroModel = message.getAfter();
                TransactionCreatedEventPayload transactionCreatedEventPayload =
                        kafkaMessageHelper.getEventPayload(avroModel.getPayload(), TransactionCreatedEventPayload.class);
                log.info("Transaction: {}, reason: {}", transactionCreatedEventPayload.getId(), transactionCreatedEventPayload.getReason());
                transactionCreatedMessageListener.transactionCreated(
                        paymentMessagingMapper.transactionCreatedEventPayloadToTransactionModel(transactionCreatedEventPayload));
            }
        });
    }
}
