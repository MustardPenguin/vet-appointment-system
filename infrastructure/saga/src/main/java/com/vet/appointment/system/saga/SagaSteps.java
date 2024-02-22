package com.vet.appointment.system.saga;

public interface SagaSteps<T> {

    void process(T data);
    void rollback(T data);
}
