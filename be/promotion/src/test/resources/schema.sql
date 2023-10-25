SET foreign_key_checks = 0;

DROP DATABASE IF EXISTS test;
CREATE DATABASE test;

USE test;

DROP TABLE IF EXISTS member;
CREATE TABLE member
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    nickname   VARCHAR(45)  NOT NULL UNIQUE,
    email      VARCHAR(45)  NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    password   VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS coupon;
CREATE TABLE coupon
(
    id               BIGINT      NOT NULL AUTO_INCREMENT,
    title            VARCHAR(50) NOT NULL,
    discount         INT         NOT NULL,
    type             VARCHAR(50) NOT NULL,
    initial_quantity INT         NOT NULL,
    remain_quantity  INT         NOT NULL,
    coupon_group_id  BIGINT      NOT NULL,
    created_at       TIMESTAMP   NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS promotion_history;
CREATE TABLE promotion_history
(
    id              BIGINT    NOT NULL AUTO_INCREMENT,
    participated_at TIMESTAMP NOT NULL,
    member_id       BIGINT    NOT NULL,
    promotion_id    BIGINT    NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    id         BIGINT    NOT NULL AUTO_INCREMENT,
    created_at TIMESTAMP NOT NULL,
    member_id  BIGINT    NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS promotion_option;
CREATE TABLE promotion_option
(
    id                BIGINT      NOT NULL AUTO_INCREMENT,
    last_order_at     TIMESTAMP   NULL,
    last_order_before TINYINT     NULL,
    member_type       VARCHAR(50) NOT NULL,
    promotion_id      BIGINT      NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS coupon_group;
CREATE TABLE coupon_group
(
    id             BIGINT      NOT NULL AUTO_INCREMENT,
    title          VARCHAR(50) NOT NULL,
    started_at     TIMESTAMP   NOT NULL,
    finished_at    TIMESTAMP   NOT NULL,
    type           VARCHAR(20) NOT NULL,
    admin_nickname VARCHAR(45) NOT NULL,
    is_random      TINYINT     NOT NULL,
    promotion_id   BIGINT      NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS promotion_option_coupon_group;
CREATE TABLE promotion_option_coupon_group
(
    id                  BIGINT NOT NULL AUTO_INCREMENT,
    promotion_option_id BIGINT NOT NULL,
    coupon_group_id     BIGINT NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS promotion;
CREATE TABLE promotion
(
    id                 BIGINT        NOT NULL AUTO_INCREMENT,
    title              VARCHAR(50)   NOT NULL,
    content            VARCHAR(512)  NULL,
    banner_url         VARCHAR(1024) NOT NULL,
    started_at         TIMESTAMP     NOT NULL,
    finished_at        TIMESTAMP     NOT NULL,
    progress_status    VARCHAR(50)   NOT NULL,
    promotion_page_url VARCHAR(1024) NOT NULL,
    is_display         TINYINT       NOT NULL,
    created_at         TIMESTAMP     NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS admin;
CREATE TABLE admin
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    nickname   VARCHAR(45)  NOT NULL,
    email      VARCHAR(45)  NOT NULL,
    password   VARCHAR(500) NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS member_coupon;
CREATE TABLE member_coupon
(
    id        BIGINT    NOT NULL AUTO_INCREMENT,
    issued_at TIMESTAMP NOT NULL,
    member_id BIGINT    NOT NULL,
    coupon_id BIGINT    NOT NULL,
    PRIMARY KEY (id)
);

SET foreign_key_checks = 1;
