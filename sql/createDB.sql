DROP DATABASE IF EXISTS salon;
CREATE DATABASE salon DEFAULT CHAR SET utf8;
USE salon;

CREATE TABLE IF NOT EXISTS roles (
    id INT AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    login VARCHAR(15) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    role_id INT DEFAULT(2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS order_statuses (
    id INT not null AUTO_INCREMENT,
    name VARCHAR(255) not null UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    status_id INT DEFAULT(1) NOT NULL,
    date DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (status_id) REFERENCES order_statuses (id)
);

CREATE TABLE IF NOT EXISTS receipt_statuses (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS receipts (
    id INT NOT NULL AUTO_INCREMENT,
    status_id INT DEFAULT(1) NOT NULL,
    sum INT NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (status_id) REFERENCES receipt_statuses (id)
);

CREATE TABLE IF NOT EXISTS service_types (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS services (
    id INT NOT NULL AUTO_INCREMENT,
    type_id INT NOT NULL,
    title_en text NOT NULL,
    title_ru text NOT NULL,
    title_by text NOT NULL,
	description_en text NOT NULL,
    description_ru text NOT NULL,
    description_by text NOT NULL,
    price INT NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (type_id) REFERENCES service_types (id)
);

CREATE TABLE IF NOT EXISTS ordered_services (
    id INT NOT NULL AUTO_INCREMENT,
    order_id INT NOT NULL,
    service_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (service_id) REFERENCES services (id)
);