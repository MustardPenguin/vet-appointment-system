package com.vet.appointment.system.pet.service.messaging.mapper;

import com.vet.appointment.system.kafka.avro.model.CreatePetEventAvroModel;
import com.vet.appointment.system.messaging.event.PetAppointmentEventPayload;
import org.springframework.stereotype.Component;

@Component
public class PetMessagingDataMapper {

    public CreatePetEventAvroModel eventPayloadToCreatePetAvroModel(PetAppointmentEventPayload petAppointmentEventPayload) {
        return CreatePetEventAvroModel.newBuilder()
                .setId(petAppointmentEventPayload.getId())
                .setOwnerId(petAppointmentEventPayload.getOwnerId())
                .setName(petAppointmentEventPayload.getName())
                .setSpecies(petAppointmentEventPayload.getSpecies())
                .setBirthDate(petAppointmentEventPayload.getBirthDate().toString())
                .build();
    }
}
