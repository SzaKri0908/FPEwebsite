--liquibase formatted sql
--changeset Bokros Olivér:create_table_users

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    username VARCHAR UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
    enabled INTEGER NOT NULL DEFAULT 1,
    deleted INTEGER NOT NULL DEFAULT 0,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR
)