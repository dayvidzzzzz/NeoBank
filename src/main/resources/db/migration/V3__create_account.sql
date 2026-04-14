CREATE TABLE IF NOT EXISTS `account` (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    account_type VARCHAR(255),
    user_id CHAR(36),
    CONSTRAINT fk_account_user FOREIGN KEY (user_id) REFERENCES `user`(id) ON DELETE CASCADE
);
