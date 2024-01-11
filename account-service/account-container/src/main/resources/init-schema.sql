
DROP SCHEMA IF EXISTS "account" CASCADE;

CREATE SCHEMA account;

DROP TABLE IF EXISTS "account".accounts CASCADE;
DROP TABLE IF EXISTS "account".account_outbox CASCADE;

CREATE TABLE "account".accounts (
    id uuid NOT NULL,
    email varchar NOT NULL,
    password varchar NOT NULL,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    CONSTRAINT accounts_pkey PRIMARY KEY (id)
);

CREATE TABLE "account".account_outbox(
    id uuid NOT NULL,
    payload varchar NOT NULL,
    date date NOT NULL
);