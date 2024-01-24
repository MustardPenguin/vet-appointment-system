
DROP SCHEMA IF EXISTS "account" CASCADE;

CREATE SCHEMA account;

DROP TABLE IF EXISTS "account".accounts CASCADE;

CREATE TABLE "account".accounts (
    id uuid NOT NULL,
    email varchar NOT NULL,
    password varchar NOT NULL,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    CONSTRAINT accounts_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "account".appointment_outbox CASCADE;

DROP TYPE IF EXISTS outbox_status;
CREATE TYPE outbox_status AS ENUM('STARTED', 'COMPLETED', 'FAILED');

CREATE TABLE "account".appointment_outbox (
    id uuid NOT NULL,
    created_at TIMESTAMP NOT NULL,
    processed_at TIMESTAMP,
    payload jsonb NOT NULL,
    outbox_status outbox_status NOT NULL,
    version integer NOT NULL,
    CONSTRAINT appointment_outbox_pkey PRIMARY KEY (id)
);