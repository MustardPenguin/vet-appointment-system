package com.vet.appointment.system.appointment.service.domain;

import com.vet.appointment.system.appointment.service.domain.dto.rest.get.GetAppointmentResponse;
import com.vet.appointment.system.appointment.service.domain.entity.Appointment;
import com.vet.appointment.system.appointment.service.domain.helper.AppointmentServiceHelper;
import com.vet.appointment.system.appointment.service.domain.mapper.AppointmentDataMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetAppointmentQueryHandler {

    private final AppointmentServiceHelper appointmentServiceHelper;
    private final AppointmentDataMapper appointmentDataMapper;

    public GetAppointmentQueryHandler(AppointmentServiceHelper appointmentServiceHelper,
                                      AppointmentDataMapper appointmentDataMapper) {
        this.appointmentServiceHelper = appointmentServiceHelper;
        this.appointmentDataMapper = appointmentDataMapper;
    }

    public GetAppointmentResponse getAppointmentFromQuery(UUID appointmentId) {
        Appointment appointment = appointmentServiceHelper.getAppointmentById(appointmentId);
        // TODO: Expand upon this, adding account + pet info to it
        return appointmentDataMapper.appointmentToGetAppointmentResponse(appointment);
    }
}
