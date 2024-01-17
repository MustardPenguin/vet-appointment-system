
DROP SCHEMA IF EXISTS "appointment" CASCADE;

CREATE SCHEMA "appointment";

DROP TABLE IF EXISTS "appointment".appointments CASCADE;

CREATE TABLE "appointment".appointments(
    id uuid NOT NULL,
    ownerId uuid NOT NULL,
    pet uuid NOT NULL,
    description varchar NOT NULL,
    appointment_start_date_time date NOT NULL,
    appointment_end_date_time date NOT NULL,
    CONSTRAINT appointment_pkey primary key (id)
);