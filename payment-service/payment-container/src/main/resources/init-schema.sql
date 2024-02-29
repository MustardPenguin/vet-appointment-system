
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

CREATE TABLE "payment".balances(
    id uuid NOT NULL,
    account_id uuid NOT NULL,
    email varchar NOT NULL,
    credit numeric(10, 2) NOT NULL,
    CONSTRAINT balance_pkey PRIMARY KEY (id)
);
