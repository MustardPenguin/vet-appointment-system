package com.vet.appointment.system.account.service.messaging.publisher.kafka;

import com.vet.appointment.system.account.service.domain.outbox.model.AccountAppointmentEventPayload;
import com.vet.appointment.system.account.service.domain.outbox.model.AccountAppointmentOutboxMessage;
import com.vet.appointment.system.account.service.domain.ports.output.message.publisher.AccountCreatedMessagePublisher;
import com.vet.appointment.system.account.service.messaging.mapper.AccountMessagingDataMapper;
import com.vet.appointment.system.kafka.avro.model.CreateAccountEventAvroModel;
import com.vet.appointment.system.kafka.producer.KafkaMessageHelper;
import com.vet.appointment.system.outbox.OutboxStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class AccountCreatedEventKafkaPublisher implements AccountCreatedMessagePublisher {

    private final KafkaMessageHelper kafkaMessageHelper;
    private final AccountMessagingDataMapper accountMessagingDataMapper;

    public AccountCreatedEventKafkaPublisher(KafkaMessageHelper kafkaMessageHelper,
                                             AccountMessagingDataMapper accountMessagingDataMapper) {
        this.kafkaMessageHelper = kafkaMessageHelper;
        this.accountMessagingDataMapper = accountMessagingDataMapper;
    }

    @Override
    public void publish(AccountAppointmentOutboxMessage orderPaymentOutboxMessage,
                        BiConsumer<AccountAppointmentOutboxMessage, OutboxStatus> outboxCallback) {
        log.info("Publishing events to kafka broker");

        AccountAppointmentEventPayload accountAppointmentEventPayload =
                kafkaMessageHelper.getEventPayload(
                        orderPaymentOutboxMessage.getPayload(), AccountAppointmentEventPayload.class);

        CreateAccountEventAvroModel createAccountEventAvroModel =
                accountMessagingDataMapper.eventPayloadToAccountCreatedEventAvroModel(accountAppointmentEventPayload);


    }
}
