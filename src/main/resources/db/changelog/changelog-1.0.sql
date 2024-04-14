-- liquibase formatted sql
-- changeset ilyas:1
-- 2024-04-10--create-table-document
-- author: ilyas
-- comment: Create tables for task
CREATE TABLE IF NOT EXISTS t_tasks
(
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    title        VARCHAR(255) NOT NULL,
    description  VARCHAR(255) NOT NULL,
    due_date     TIMESTAMP NOT NULL,
    is_completed BOOLEAN
);
