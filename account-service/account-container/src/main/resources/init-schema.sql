
DROP SCHEMA IF EXISTS "account" CASCADE;

CREATE SCHEMA "account";

DROP TABLE IF EXISTS "account".accounts CASCADE;

CREATE TABLE "account".accounts(
    id UUID NOT NULL,
    email STRING NOT NULL,
    password STRING NOT NULL,
    CONSTRAINT accounts_pkey PRIMARY KEY (id)
);

CREATE TABLE "account".account_outbox(
    id UUID NOT NULL,
    payload STRING NOT NULL,
    date DATE NOT NULL,
);