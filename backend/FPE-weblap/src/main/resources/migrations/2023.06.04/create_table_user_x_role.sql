--liquibase formatted sql
--changeset Bokros Olivér:create_table_user_x_role

CREATE TABLE user_x_role(

    user_id BIGINT,
    role_id BIGINT,

    CONSTRAINT fk_user_in_user_x_role
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE CASCADE,

    CONSTRAINT fk_role_in_user_x_role
    FOREIGN KEY (role_id)
    REFERENCES role (id)
    ON DELETE CASCADE
)