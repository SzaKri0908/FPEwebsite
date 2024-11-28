--liquibase formatted sql
--changeset Bokros Olivér:create_events_table

CREATE TABLE events(
  id SERIAL PRIMARY KEY,
  title VARCHAR,
  text VARCHAR,
  image_url VARCHAR,
  event_date TIMESTAMP,
  deleted INTEGER NOT NULL DEFAULT 0,
  created_by VARCHAR,
  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR,
  updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);