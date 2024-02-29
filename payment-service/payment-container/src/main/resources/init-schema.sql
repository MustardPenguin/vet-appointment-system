
DROP SCHEMA IF EXISTS "payment" CASCADE;
CREATE SCHEMA "payment";




DROP TABLE IF EXISTS "payment".appointment_outbox CASCADE;

CREATE TABLE "payment".appointment_outbox (
    id uuid NOT NULL,
    saga_id uuid NOT NULL,
    saga_type varchar NOT NULL,
    created_at TIMESTAMP NOT NULL,
    payload jsonb NOT NULL,
    version integer NOT NULL,
    CONSTRAINT appointment_outbox_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "payment".accounts CASCADE;

CREATE TABLE "payment".accounts(
    id uuid NOT NULL,
    email varchar NOT NULL,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    CONSTRAINT account_pkey PRIMARY KEY (id)
);
