package com.vet.appointment.system.payment.service.messaging.listener.kafka;

import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import com.vet.appointment.system.kafka.producer.KafkaMessageHelper;
import com.vet.appointment.system.messaging.DebeziumOp;
import com.vet.appointment.system.messaging.event.AppointmentPaymentEventPayload;
import com.vet.appointment.system.payment.service.domain.dto.message.PaymentRequest;
import com.vet.appointment.system.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;
import com.vet.appointment.system.payment.service.messaging.mapper.PaymentMessagingMapper;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.K;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import payment_request.appointment.payment_outbox.Envelope;
import payment_request.appointment.payment_outbox.Value;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class PaymentRequestEventKafkaListener implements KafkaConsumer<Envelope> {

    private final PaymentRequestMessageListener paymentRequestMessageListener;
    private final PaymentMessagingMapper paymentMessagingMapper;
    private final KafkaMessageHelper kafkaMessageHelper;

    public PaymentRequestEventKafkaListener(PaymentRequestMessageListener paymentRequestMessageListener,
                                            PaymentMessagingMapper paymentMessagingMapper,
                                            KafkaMessageHelper kafkaMessageHelper) {
        this.paymentRequestMessageListener = paymentRequestMessageListener;
        this.paymentMessagingMapper = paymentMessagingMapper;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    @KafkaListener(topics = "${kafka-consumer-topic-name.payment-request-event}",
            groupId = "${kafka-consumer-group-id.payment-request-event}")
    public void receive(@Payload List<Envelope> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("Received kafka event message!");
        log.info("Messages: {}", messages);
        log.info("Key: {}", keys);
        log.info("Partitions: {}", partitions);
        log.info("Offsets: {}", offsets);

        messages.forEach(avroModel -> {
            if(avroModel.getBefore() == null && avroModel.getOp().equals(DebeziumOp.CREATE.getValue())) {
                Value paymentRequestEventAvroModel = avroModel.getAfter();
                AppointmentPaymentEventPayload appointmentPaymentEventPayload =
                        kafkaMessageHelper.getEventPayload(paymentRequestEventAvroModel.getPayload(), AppointmentPaymentEventPayload.class);

                paymentRequestMessageListener.paymentRequestReceived(
                        paymentMessagingMapper.paymentEventPayloadToPaymentRequest(
                                appointmentPaymentEventPayload, UUID.fromString(paymentRequestEventAvroModel.getSagaId())));
            }
        });
    }
}
