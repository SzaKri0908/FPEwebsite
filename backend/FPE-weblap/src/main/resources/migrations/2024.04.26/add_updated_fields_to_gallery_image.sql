--liquibase formatted sql
--changeset Bokros Olivér:add_updated_fields_to_gallery_image

ALTER TABLE gallery_images
ADD COLUMN created_on TIMESTAMP;

ALTER TABLE gallery_images
ADD COLUMN created_by VARCHAR;

ALTER TABLE gallery_images
ADD COLUMN updated_on TIMESTAMP;

ALTER TABLE gallery_images
ADD COLUMN updated_by VARCHAR;
