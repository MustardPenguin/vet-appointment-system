
DROP SCHEMA IF EXISTS "appointment" CASCADE;

CREATE SCHEMA "appointment";

DROP TABLE IF EXISTS "appointment".appointments CASCADE;

CREATE TABLE "appointment".appointments(
    id uuid NOT NULL,
    owner_id uuid NOT NULL,
    pet_id uuid NOT NULL,
    description varchar NOT NULL,
    appointment_start_date_time timestamp WITHOUT TIME ZONE NOT NULL,
    appointment_end_date_time timestamp WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT appointment_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "appointment".accounts CASCADE;

CREATE TABLE "appointment".accounts(
    id uuid NOT NULL,
    email varchar NOT NULL,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    CONSTRAINT account_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "appointment".pets CASCADE;

CREATE TABLE "appointment".pets (
    id uuid NOT NULL,
    owner_id uuid NOT NULL,
    name varchar NOT NULL,
    species varchar NOT NULL,
    birth_date date NOT NULL,
    CONSTRAINT pet_pkey PRIMARY KEY (id)
);