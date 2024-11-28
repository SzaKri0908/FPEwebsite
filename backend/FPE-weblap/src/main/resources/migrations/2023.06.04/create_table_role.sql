--liquibase formatted sql
--changeset Bokros Olivér:create_table_role

CREATE TABLE role(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
)