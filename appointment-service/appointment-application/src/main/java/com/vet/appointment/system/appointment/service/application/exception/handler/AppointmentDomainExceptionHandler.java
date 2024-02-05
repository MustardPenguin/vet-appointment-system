package com.vet.appointment.system.appointment.service.application.exception.handler;

import com.vet.appointment.system.appointment.service.domain.exception.AppointmentDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppointmentDomainExceptionHandler {


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AppointmentDomainException.class)
    public String handleAppointmentDomainException(AppointmentDomainException e) {
        return e.getMessage();
    }
}
