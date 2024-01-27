package com.vet.appointment.system.account.service.messaging.publisher.kafka;

import com.vet.appointment.system.account.service.domain.outbox.model.AccountAppointmentEventPayload;
import com.vet.appointment.system.account.service.domain.outbox.model.AccountAppointmentOutboxMessage;
import com.vet.appointment.system.account.service.domain.ports.output.message.publisher.AccountCreatedMessagePublisher;
import com.vet.appointment.system.account.service.messaging.mapper.AccountMessagingDataMapper;
import com.vet.appointment.system.kafka.avro.model.CreateAccountEventAvroModel;
import com.vet.appointment.system.kafka.producer.KafkaMessageHelper;
import com.vet.appointment.system.kafka.producer.KafkaProducer;
import com.vet.appointment.system.kafka.producer.exception.KafkaProducerException;
import com.vet.appointment.system.outbox.OutboxStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

import static com.vet.appointment.system.account.service.domain.config.AccountServiceDataConfig.AccountCreatedEventTopicName;

@Slf4j
@Component
public class AccountCreatedEventKafkaPublisher implements AccountCreatedMessagePublisher {

    private final KafkaMessageHelper kafkaMessageHelper;
    private final AccountMessagingDataMapper accountMessagingDataMapper;
    private final KafkaProducer<String, CreateAccountEventAvroModel> kafkaProducer;


    public AccountCreatedEventKafkaPublisher(KafkaMessageHelper kafkaMessageHelper,
                                             AccountMessagingDataMapper accountMessagingDataMapper,
                                             KafkaProducer<String, CreateAccountEventAvroModel> kafkaProducer) {
        this.kafkaMessageHelper = kafkaMessageHelper;
        this.accountMessagingDataMapper = accountMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void publish(AccountAppointmentOutboxMessage accountAppointmentOutboxMessage,
                        BiConsumer<AccountAppointmentOutboxMessage, OutboxStatus> outboxCallback) {
        log.info("Publishing events to kafka broker");

        AccountAppointmentEventPayload accountAppointmentEventPayload =
                kafkaMessageHelper.getEventPayload(
                        accountAppointmentOutboxMessage.getPayload(), AccountAppointmentEventPayload.class);

        try {
            CreateAccountEventAvroModel createAccountEventAvroModel =
                    accountMessagingDataMapper.eventPayloadToAccountCreatedEventAvroModel(accountAppointmentEventPayload);

            BiConsumer<SendResult<String, CreateAccountEventAvroModel>, Throwable> callback =
                    kafkaMessageHelper.getKafkaCallback(
                            AccountCreatedEventTopicName,
                            createAccountEventAvroModel,
                            accountAppointmentOutboxMessage,
                            outboxCallback);
            kafkaProducer.send(
                    AccountCreatedEventTopicName,
                    "1234",
                    createAccountEventAvroModel,
                    callback);
        } catch (Exception e) {
            log.error("Error while sending AccountAppointmentEventPayload to kafka with id: {}" +
                    " and error: {}", accountAppointmentEventPayload.getId(), e.getMessage());
            throw new KafkaProducerException("Error while sending AccountAppointmentEventPayload to kafka with id: " +
                    accountAppointmentEventPayload.getId() + ", Error: " + e);
        }
    }
}
