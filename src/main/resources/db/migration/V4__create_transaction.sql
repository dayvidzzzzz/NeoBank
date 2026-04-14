CREATE TABLE IF NOT EXISTS `transaction` (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    transaction_type VARCHAR(255),
    transaction_state VARCHAR(255),
    sender_id CHAR(36),
    receiver_id CHAR(36),
    CONSTRAINT fk_transaction_sender FOREIGN KEY (sender_id) REFERENCES `user`(id) ON DELETE SET NULL,
    CONSTRAINT fk_transaction_receiver FOREIGN KEY (receiver_id) REFERENCES `user`(id) ON DELETE SET NULL
);
