--liquibase formatted sql
--changeset Bokros Olivér:alter_news_page_model

ALTER TABLE news_page
ADD COLUMN image_url VARCHAR;