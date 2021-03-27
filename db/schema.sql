#Create database

CREATE DATABASE if NOT EXISTS quotedb;
GRANT ALL PRIVILEGES ON quotedb.* TO 'Radha'@'%';
FLUSH PRIVILEGES;

use quotedb;

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
);

INSERT INTO quotedb.hibernate_sequence(next_val) VALUES (1);

CREATE TABLE IF NOT EXISTS `login` (
  `id` bigint NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

insert into quotedb.login(id, user, password) values(1, 'Radha', 'Krishna');

CREATE TABLE IF NOT EXISTS `info` (
    `id` bigint PRIMARY KEY,
    `title` VARCHAR(255) NOT NULL,
    `info` VARCHAR(10000) NOT NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `info_link` (
    `id` bigint PRIMARY KEY,
    `link_id` bigint,
    `link` VARCHAR(255),
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(link_id) REFERENCES INFO(ID)
);

/*
CREATE TABLE quotedb.`event_detail` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `day` INTEGER NOT NULL,
    `month` INTEGER NOT NULL,
    `year` INTEGER NOT NULL,
    `title` VARCHAR(200) NOT NULL,
    `info` VARCHAR(1000) NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE quotedb.`event_detail_link` (
    `link_id` INTEGER,
    `link` VARCHAR(100),
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(link_id) REFERENCES event_detail(ID)
);
*/

