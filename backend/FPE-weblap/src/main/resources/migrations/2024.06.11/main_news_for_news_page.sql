--liquibase formatted sql
--changeset Bokros Olivér:main_news_for_news_page

ALTER TABLE news_page
ADD COLUMN is_main_news BOOLEAN NOT NULL DEFAULT FALSE;