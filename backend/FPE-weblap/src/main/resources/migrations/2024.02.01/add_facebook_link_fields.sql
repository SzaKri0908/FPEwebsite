--liquibase formatted sql
--changeset Bokros Olivér:add_facebook_link_fields

ALTER TABLE news_page
ADD COLUMN facebook_link VARCHAR;

ALTER TABLE events
ADD COLUMN facebook_event_link VARCHAR;