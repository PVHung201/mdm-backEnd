CREATE TABLE notifications
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    content    VARCHAR(255)          NULL,
    created_at datetime              NULL,
    CONSTRAINT pk_notifications PRIMARY KEY (id)
);

CREATE TABLE user_read_notifications
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    user_id         BIGINT                NOT NULL,
    notification_id BIGINT                NOT NULL,
    is_read         BIT(1)                NOT NULL,
    CONSTRAINT pk_user_read_notifications PRIMARY KEY (id)
);

ALTER TABLE provider_provides_services
    ADD PRIMARY KEY (provider_id, service_id);

ALTER TABLE user_read_notifications
    ADD CONSTRAINT FK_USER_READ_NOTIFICATIONS_ON_NOTIFICATION FOREIGN KEY (notification_id) REFERENCES notifications (id);

ALTER TABLE user_read_notifications
    ADD CONSTRAINT FK_USER_READ_NOTIFICATIONS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE provider_provides_services
    ADD CONSTRAINT fk_proproser_on_service FOREIGN KEY (service_id) REFERENCES services (id);

ALTER TABLE provider_provides_services
    ADD CONSTRAINT fk_proproser_on_supplier FOREIGN KEY (provider_id) REFERENCES suppliers (id);