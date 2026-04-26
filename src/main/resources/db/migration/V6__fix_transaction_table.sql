ALTER TABLE `transaction` DROP FOREIGN KEY fk_transaction_sender;
ALTER TABLE `transaction` DROP FOREIGN KEY fk_transaction_receiver;

ALTER TABLE `transaction`
    ADD CONSTRAINT fk_transaction_sender_acc FOREIGN KEY (sender_id) REFERENCES `account`(id),
    ADD CONSTRAINT fk_transaction_receiver_acc FOREIGN KEY (receiver_id) REFERENCES `account`(id);

ALTER TABLE `transaction`
    ADD COLUMN amount DECIMAL(19, 2) NOT NULL,
    ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
