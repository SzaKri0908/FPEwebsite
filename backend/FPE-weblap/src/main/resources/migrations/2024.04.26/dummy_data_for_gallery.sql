--liquibase formatted sql
--changeset Bokros Olivér:dummy_data_for_gallery

INSERT INTO public.gallery_images(
    facebook_link, imgurl, deleted, created_on, created_by, updated_on, updated_by)
VALUES ('asdasdasdasd','..\docker\gallery\digital_camera_photo.jpg', 0, '2024-04-27 14:01:22.918', 'FPEAdministrator', '2024-04-27 14:01:22.918', 'FPEAdministrator');
INSERT INTO public.gallery_images(
    facebook_link, imgurl, deleted, created_on, created_by, updated_on, updated_by)
VALUES ('asdasdasdasd', '..\docker\gallery\free-images.jpg', 0, '2024-04-27 14:01:22.919', 'FPEAdministrator', '2024-04-27 14:01:22.919', 'FPEAdministrator');
INSERT INTO public.gallery_images(
    facebook_link, imgurl, deleted, created_on, created_by, updated_on, updated_by)
VALUES ('asdasdasdasd', '..\docker\gallery\tree-736885_1280.jpg', 0, '2024-04-27 14:02:12.871', 'FPEAdministrator', '2024-04-27 14:02:12.871', 'FPEAdministrator');
