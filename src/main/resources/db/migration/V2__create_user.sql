CREATE TABLE IF NOT EXISTS `user` (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    full_name VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL UNIQUE,
    login VARCHAR(255),
    status_user VARCHAR(255) NOT NULL,
    role VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    birth_date DATE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    address_id BIGINT,
    CONSTRAINT fk_user_address FOREIGN KEY (address_id) REFERENCES `address`(id) ON DELETE SET NULL
);
