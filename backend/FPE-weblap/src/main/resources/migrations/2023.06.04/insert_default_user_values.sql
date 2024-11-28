--liquibase formatted sql
--changeset Bokros Olivér:insert_default_user_values

INSERT INTO role(name) VALUES('ROLE_ADMIN');
INSERT INTO users(username,password,enabled,deleted,created_by,updated_by) VALUES('FPEAdministrator','$2a$12$xXtXZ7/9g2vlpE1SmKl/uO06U9Oy0CLCoTt9gvsSBec5d4IftHnfC',1,0,'FPEAdministrator','FPEAdministrator');
INSERT INTO user_x_role(user_id,role_id) VALUES(1,1);
INSERT INTO news_page(title, text, deleted, created_by, updated_by) VALUES('Teszt poszt', 'Lorem ipsum dolor sit amet, consect et eiusmod tempor incididunt ut labore et', 0, 'FPEAdministrator', 'FPEAdministrator');