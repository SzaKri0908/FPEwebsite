--liquibase formatted sql
--changeset Bokros Olivér:set_new_password
UPDATE users
SET password = '$2a$12$uMlwWSvAxPv7tk2hhrT4Aun2wZdgokc6Zom3cTdNffO/53nGkdv.a'
WHERE username LIKE 'FPEAdministrator';