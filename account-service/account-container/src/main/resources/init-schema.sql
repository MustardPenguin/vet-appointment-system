
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