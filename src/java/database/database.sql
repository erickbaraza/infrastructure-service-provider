CREATE DATABASE IF NOT EXISTS `isp_db`;

-- USERS TABLE
CREATE TABLE IF NOT EXISTS `isp_db`.`users`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(15) NOT NULL,
    `last_name` VARCHAR(15) NOT NULL,
    `email` VARCHAR(100) UNIQUE NOT NULL,
    `role` VARCHAR(100) NOT NULL DEFAULT "Customer",
    `password` VARCHAR(64) NOT NULL,
    `phone_number` VARCHAR(13) NOT NULL,
    `verification_code` VARCHAR(64) NOT NULL,
    `is_verified` int default 0,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- ADDRESSES TABLE
CREATE TABLE IF NOT EXISTS `isp_db`.`addresses`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` INT,
    `type` ENUM ('address','billing', 'shipping') DEFAULT 'address',
    `country` VARCHAR(15),
    `city` VARCHAR(15),
    `street` VARCHAR(15),
    `house_number` INT,
    `postal_code` INT,
    `zip_code` INT
);

-- CATEGORIES TABLE
CREATE TABLE IF NOT EXISTS `isp_db`.`categories`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `slug` VARCHAR(100) UNIQUE NOT NULL,
    `description` VARCHAR(200)  ,
    `status` VARCHAR(100) NOT NULL DEFAULT "active",
    `image` LONGBLOB,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- COMPANIES TABLE
CREATE TABLE IF NOT EXISTS `isp_db`.`companies`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `reg_no` VARCHAR(100) UNIQUE NOT NULL,
    `email` VARCHAR(100) UNIQUE NOT NULL,
    `phone` VARCHAR(15) UNIQUE NOT NULL,
    `address` VARCHAR(100) NOT NULL,
    `description` VARCHAR(200)  ,
    `status` VARCHAR(15) NOT NULL DEFAULT "active",
    `image` LONGBLOB,
    `category_id` ,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- PRODUCTS TABLE
CREATE TABLE IF NOT EXISTS `isp_db`.`products`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `slug` VARCHAR(100) UNIQUE NOT NULL,
    `short_description` VARCHAR(400) NOT NULL,
    `description` TEXT  ,
    `price` float,
    `mrp_price` float,
    `quantity` int default 0,
    `status` VARCHAR(15) NOT NULL DEFAULT "instock", 
    `image` LONGBLOB,
    `category_id` int,
    `company_id` int ,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- SERVICES TABLE
CREATE TABLE IF NOT EXISTS `isp_db`.`services`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `slug` VARCHAR(100) UNIQUE NOT NULL,
    `short_description` VARCHAR(400) NOT NULL,
    `description` TEXT  ,
    `price` float,
    `mrp_price` float,
    `image` LONGBLOB,
    `category_id` int,
    `company_id` int ,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- CART TABLE
CREATE TABLE IF NOT EXISTS `isp_db`.`cart`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` int,
    `product_id` INT,
    `quantity` INT DEFAULT 1,
    `price` FLOAT,
    `mrp_price` float,
    `confirm` boolean default false,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP
); 


-- ORDERS
CREATE TABLE IF NOT EXISTS `isp_db`.`orders`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` int,
    `items` INT,
    `amount` FLOAT,
    `mrp_amount` float,
    `status` VARCHAR(15) NOT NULL DEFAULT "pending", 
    `payment_id` INT,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- ORDER ITEMS TABLE
CREATE TABLE IF NOT EXISTS `isp_db`.`order_items`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `order_id` int,
    `product_id` INT,
    `quantity` INT DEFAULT 1,
    `price` FLOAT,
    `mrp_price` float,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP
); 

CREATE TABLE `isp_db`.`transactions`(
    id int  primary key auto_increment,
    user_id int not null,
    order_id int not null,
    mpesa_ref varchar(15) not null,
    mpesa_number int not null,
    amount float not null ,
    created_at datetime default current_timestamp,
    updated_at datetime default current_timestamp on update current_timestamp
);

