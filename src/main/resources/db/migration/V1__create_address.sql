CREATE TABLE IF NOT EXISTS `address` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(255),
    state VARCHAR(255),
    city VARCHAR(255),
    zipcode VARCHAR(255),
    street VARCHAR(255)
);
