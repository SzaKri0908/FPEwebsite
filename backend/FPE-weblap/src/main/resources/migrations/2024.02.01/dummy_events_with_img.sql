--liquibase formatted sql
--changeset Bokros Olivér:dummy_events_with_image

delete from events;

INSERT INTO events (title, text, image_url, event_date, deleted, facebook_event_link)
SELECT
        'Dummy Title ' || row_number() OVER (PARTITION BY month)::text AS title,
        'Dummy Text ' || row_number() OVER (PARTITION BY month)::text AS text,
        'https://upload.wikimedia.org/wikipedia/commons/1/14/The_Event_2010_Intertitle.svg' AS image_url,
        ('2024-' || LPAD(month::TEXT, 2, '0') || '-' || LPAD(row_number() OVER (PARTITION BY month)::TEXT, 2, '0'))::date AS event_date,
        0 AS deleted,
        'https://www.facebook.com/dummy_event' || row_number() OVER (PARTITION BY month)::text AS facebook_event_link
FROM
    generate_series(1, 12) AS month
        JOIN
    generate_series(1, 10) AS event_number ON true;


delete from news_page;

INSERT INTO news_page (title, text, image_url, created_on, deleted, facebook_link)
SELECT
        'Dummy Title ' || row_number() OVER (PARTITION BY month)::text AS title,
        'Dummy Text ' || row_number() OVER (PARTITION BY month)::text AS text,
        'https://www.shutterstock.com/shutterstock/photos/1928997539/display_1500/stock-vector-breaking-news-template-with-d-red-and-blue-badge-breaking-news-text-on-dark-blue-with-earth-and-1928997539.jpg' AS image_url,
        ('2024-' || LPAD(month::TEXT, 2, '0') || '-' || LPAD(row_number() OVER (PARTITION BY month)::TEXT, 2, '0'))::date AS created_on,
        0 AS deleted,
        'https://www.facebook.com/dummy_news' || row_number() OVER (PARTITION BY month)::text AS facebook_link
FROM
    generate_series(1, 12) AS month
        JOIN
    generate_series(1, 10) AS event_number ON true;