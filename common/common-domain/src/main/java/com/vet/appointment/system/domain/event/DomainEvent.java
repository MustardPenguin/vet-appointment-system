package com.vet.appointment.system.domain.event;

import java.time.ZonedDateTime;

public class DomainEvent<Entity> {

    private final Entity entity;
    private final ZonedDateTime createdAt;

    public DomainEvent(Entity entity, ZonedDateTime createdAt) {
        this.entity = entity;
        this.createdAt = createdAt;
    }

    public Entity getEntity() {
        return entity;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
