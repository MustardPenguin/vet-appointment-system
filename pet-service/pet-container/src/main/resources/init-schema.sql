
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