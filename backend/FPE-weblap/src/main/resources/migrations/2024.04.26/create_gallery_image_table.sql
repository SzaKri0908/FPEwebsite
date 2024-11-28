--liquibase formatted sql
--changeset Bokros Olivér:create_gallery_image_table

CREATE TABLE gallery_images(
    id SERIAL PRIMARY KEY,
    facebook_link VARCHAR,
    imgUrl VARCHAR NOT NULL,
    deleted INTEGER NOT NULL DEFAULT 0
)