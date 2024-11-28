--liquibase formatted sql
--changeset Bokros Olivér:create_gallery_view

CREATE VIEW gallery_view AS
SELECT
            row_number() OVER() as id,
            title,
            jsonb_agg(jsonb_build_object('imgUrl', imgUrl, 'facebook_link', facebook_link)) AS images
FROM
    gallery_images
GROUP BY
    title;

