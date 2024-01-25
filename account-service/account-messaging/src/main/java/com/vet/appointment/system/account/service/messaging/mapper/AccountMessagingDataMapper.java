package com.vet.appointment.system.account.service.messaging.mapper;

import com.vet.appointment.system.account.service.domain.outbox.model.AccountAppointmentEventPayload;
import com.vet.appointment.system.kafka.avro.model.CreateAccountEventAvroModel;
import org.springframework.stereotype.Component;

@Component
public class AccountMessagingDataMapper {

    public CreateAccountEventAvroModel eventPayloadToAccountCreatedEventAvroModel(
            AccountAppointmentEventPayload accountAppointmentEventPayload) {
        return CreateAccountEventAvroModel.newBuilder()
                .setId(accountAppointmentEventPayload.getId().toString())
                .setEmail(accountAppointmentEventPayload.getEmail())
                .setFirstName(accountAppointmentEventPayload.getFirstName())
                .setLastName(accountAppointmentEventPayload.getLastName())
                .build();
    }
}
