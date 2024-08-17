ALTER TABLE user_permission
    ADD CONSTRAINT uniqe_user_permission_per_user UNIQUE (user_id, permission_id, group_id);
