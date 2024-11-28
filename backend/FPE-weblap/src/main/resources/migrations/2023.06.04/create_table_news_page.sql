--liquibase formatted sql
--changeset Bokros Olivér:create_table_news_page

CREATE TABLE news_page(
    id SERIAL PRIMARY KEY,
    title VARCHAR,
    text VARCHAR,
    deleted INTEGER NOT NULL DEFAULT 0,
    created_by VARCHAR,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);