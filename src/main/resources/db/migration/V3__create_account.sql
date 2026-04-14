CREATE TABLE IF NOT EXISTS `account` (
    id UUID PRIMARY KEY DEFAULT (UUID()),
    account_type VARCHAR(255),
    user_id UUID,
    CONSTRAINT fk_account_user FOREIGN KEY (user_id) REFERENCES `user`(id) ON DELETE CASCADE
);
