--liquibase formatted sql
--changeset Bokros Olivér:add_title_to_gallery_images

ALTER TABLE gallery_images
ADD COLUMN title VARCHAR;