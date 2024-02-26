
DROP SCHEMA IF EXISTS "pet" CASCADE;

CREATE SCHEMA "pet";

DROP TABLE IF EXISTS "pet".pets CASCADE;

CREATE TABLE "pet".pets (
    id uuid NOT NULL,
    owner_id uuid NOT NULL,
    name varchar NOT NULL,
    species varchar NOT NULL,
    birth_date date NOT NULL,
    CONSTRAINT pet_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "pet".appointment_outbox CASCADE;

CREATE TABLE "pet".appointment_outbox (
    id uuid NOT NULL,
    created_at TIMESTAMP NOT NULL,
    payload jsonb NOT NULL,
    version integer NOT NULL,
    CONSTRAINT appointment_outbox_pkey PRIMARY KEY (id)
);