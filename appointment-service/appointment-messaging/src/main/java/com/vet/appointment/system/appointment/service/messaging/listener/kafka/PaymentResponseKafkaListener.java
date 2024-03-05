package com.vet.appointment.system.appointment.service.messaging.listener.kafka;

import com.vet.appointment.system.appointment.service.domain.dto.message.PaymentResponse;
import com.vet.appointment.system.appointment.service.domain.ports.input.message.listener.PaymentResponseMessageListener;
import com.vet.appointment.system.appointment.service.messaging.mapper.AppointmentMessagingDataMapper;
import com.vet.appointment.system.domain.valueobject.PaymentStatus;
import com.vet.appointment.system.kafka.consumer.KafkaConsumer;
import com.vet.appointment.system.kafka.producer.KafkaMessageHelper;
import com.vet.appointment.system.messaging.DebeziumOp;
import com.vet.appointment.system.messaging.event.PaymentAppointmentEventPayload;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import payment_response.payment.appointment_outbox.Envelope;
import payment_response.payment.appointment_outbox.Value;

import java.util.List;
import java.util.UUID;

@Component
public class PaymentResponseKafkaListener implements KafkaConsumer<Envelope> {

    private final PaymentResponseMessageListener paymentResponseMessageListener;
    private final AppointmentMessagingDataMapper appointmentMessagingDataMapper;
    private final KafkaMessageHelper kafkaMessageHelper;

    public PaymentResponseKafkaListener(PaymentResponseMessageListener paymentResponseMessageListener,
                                        AppointmentMessagingDataMapper appointmentMessagingDataMapper,
                                        KafkaMessageHelper kafkaMessageHelper) {
        this.paymentResponseMessageListener = paymentResponseMessageListener;
        this.appointmentMessagingDataMapper = appointmentMessagingDataMapper;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    @KafkaListener(topics = "${kafka-consumer-topic-name.payment-response-event}",
            groupId = "${kafka-consumer-group-id.payment-group-id}")
    public void receive(@Payload List<Envelope> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        messages.forEach(avroModel -> {
            if(avroModel.getBefore() == null && avroModel.getOp().equals(DebeziumOp.CREATE.getValue())) {
                Value paymentResponseAvroModel = avroModel.getAfter();
                PaymentAppointmentEventPayload paymentAppointmentEventPayload = kafkaMessageHelper.getEventPayload(
                        paymentResponseAvroModel.getPayload(), PaymentAppointmentEventPayload.class);
                PaymentResponse paymentResponse = appointmentMessagingDataMapper.paymentAppointmentEventPayloadToPaymentResponse(
                        paymentAppointmentEventPayload, UUID.fromString(paymentResponseAvroModel.getSagaId()));
                if(paymentAppointmentEventPayload.getPaymentStatus() == PaymentStatus.PAID) {
                    paymentResponseMessageListener.paymentPaid(paymentResponse);
                } else {
                    paymentResponseMessageListener.paymentFailed(paymentResponse);
                }
            }
        });
    }
}
