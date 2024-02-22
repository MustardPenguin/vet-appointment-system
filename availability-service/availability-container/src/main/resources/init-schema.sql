
DROP SCHEMA IF EXISTS "availability" CASCADE;

CREATE SCHEMA "availability";

DROP TYPE IF EXISTS event_type;
CREATE TYPE event_type AS ENUM('APPOINTMENT', 'HOLIDAY', 'OTHER');

DROP TABLE IF EXISTS "availability"."availabilities" CASCADE;

CREATE TABLE "availability".availabilities (
    id UUID PRIMARY KEY,
    event_id UUID NOT NULL,
    event_type event_type NOT NULL,
    start_date_time timestamp WITHOUT TIME ZONE NOT NULL,
    end_date_time timestamp WITHOUT TIME ZONE NOT NULL,
    reason varchar(255)
);

DROP TABLE IF EXISTS "availability".appointment_outbox CASCADE;

CREATE TABLE "availability".appointment_outbox (
    id uuid NOT NULL,
    saga_id uuid NOT NULL,
    created_at TIMESTAMP NOT NULL,
    payload jsonb NOT NULL,
    version integer NOT NULL,
    CONSTRAINT appointment_outbox_pkey PRIMARY KEY (id)
);