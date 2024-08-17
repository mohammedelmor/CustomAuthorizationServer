CREATE SEQUENCE IF NOT EXISTS permission_id_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS user_group_id_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS user_id_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS user_permission_id_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE permission
(
    id   BIGINT       NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_permission PRIMARY KEY (id)
);

CREATE TABLE "user"
(
    id       BIGINT       NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_group
(
    id   BIGINT       NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user_group PRIMARY KEY (id)
);

CREATE TABLE user_permission
(
    id            BIGINT NOT NULL,
    user_id       BIGINT,
    permission_id BIGINT,
    group_id      BIGINT,
    CONSTRAINT pk_userpermission PRIMARY KEY (id)
);

ALTER TABLE permission
    ADD CONSTRAINT uc_permission_name UNIQUE (name);

ALTER TABLE user_group
    ADD CONSTRAINT uc_user_group_name UNIQUE (name);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_username UNIQUE (username);

ALTER TABLE user_permission
    ADD CONSTRAINT FK_USERPERMISSION_ON_GROUP FOREIGN KEY (group_id) REFERENCES user_group (id);

ALTER TABLE user_permission
    ADD CONSTRAINT FK_USERPERMISSION_ON_PERMISSION FOREIGN KEY (permission_id) REFERENCES permission (id);

ALTER TABLE user_permission
    ADD CONSTRAINT FK_USERPERMISSION_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);