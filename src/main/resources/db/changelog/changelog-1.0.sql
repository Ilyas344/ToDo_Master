-- liquibase formatted sql
-- changeset Pro100:1
-- 2024-04-10--create-table-document
-- author: Pro100
-- comment: Create tables for measurements and methods
CREATE TABLE IF NOT EXISTS tasks
(
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    title        VARCHAR(255) NOT NULL,
    description  VARCHAR(255) NOT NULL,
    due_date     TIMESTAMP NOT NULL,
    is_completed BOOLEAN
);
