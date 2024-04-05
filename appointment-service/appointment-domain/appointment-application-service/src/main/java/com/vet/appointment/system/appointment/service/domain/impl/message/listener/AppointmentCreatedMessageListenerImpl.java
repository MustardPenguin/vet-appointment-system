package com.vet.appointment.system.appointment.service.domain.impl.message.listener;

import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;
import com.vet.appointment.system.appointment.service.domain.ports.input.message.listener.AppointmentCreatedMessageListener;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.AppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class AppointmentCreatedMessageListenerImpl implements AppointmentCreatedMessageListener {

    private final AppointmentRepository appointmentRepository;

    public AppointmentCreatedMessageListenerImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void appointmentCreated(Appointment appointment) {
        log.info("Received appointment created event of id: {}", appointment.getId().getValue());
        Optional<Appointment> response = appointmentRepository.findById(appointment.getId().getValue());
        if(response.isPresent()) {
            log.info("Appointment of id: {} already exists in the database, either this instance is the source of message or it received a duplicate message!", appointment.getId().getValue());
            return;
        }
        Appointment savedAppointment = appointmentRepository.save(appointment);
        if(savedAppointment == null) {
            log.error("Could not save appointment with id: {} for event payload!", appointment.getId().getValue());
            throw new AppointmentDomainException("Could not save appointment with id: " + appointment.getId().getValue()
                    + " for event payload!");
        }
        log.info("Successfully saved appointment with id: {} for event payload!", savedAppointment.getId().getValue());
    }
}
