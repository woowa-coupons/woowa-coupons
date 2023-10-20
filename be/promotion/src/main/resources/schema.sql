CREATE TABLE IF NOT EXISTS member
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    nickname   VARCHAR(45)  NOT NULL UNIQUE,
    email      VARCHAR(45)  NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    password   VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS coupon
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

CREATE TABLE IF NOT EXISTS promotion_history
(
    id              BIGINT    NOT NULL AUTO_INCREMENT,
    participated_at TIMESTAMP NOT NULL,
    member_id       BIGINT    NOT NULL,
    promotion_id    BIGINT    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id         BIGINT    NOT NULL AUTO_INCREMENT,
    created_at TIMESTAMP NOT NULL,
    member_id  BIGINT    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS promotion_option
(
    id            BIGINT      NOT NULL AUTO_INCREMENT,
    last_order_at TIMESTAMP   NULL,
    member_type   VARCHAR(50) NOT NULL,
    is_random     TINYINT     NOT NULL,
    promotion_id  BIGINT      NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS coupon_group
(
    id             BIGINT       NOT NULL AUTO_INCREMENT,
    title          VARCHAR(50)  NOT NULL,
    started_at     TIMESTAMP    NOT NULL,
    finished_at    TIMESTAMP    NOT NULL,
    admin_nickname VARCHAR(45) NOT NULL,
    promotion_id   BIGINT       NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS promotion_option_coupon_group
(
    id                  BIGINT NOT NULL AUTO_INCREMENT,
    promotion_option_id BIGINT NOT NULL,
    coupon_group_id           BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS promotion
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

CREATE TABLE IF NOT EXISTS admin
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    nickname   VARCHAR(45)  NOT NULL,
    password   VARCHAR(500) NOT NULL,
    email      VARCHAR(45)  NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS member_coupon
(
    id        BIGINT    NOT NULL AUTO_INCREMENT,
    issued_at TIMESTAMP NOT NULL,
    member_id BIGINT    NOT NULL,
    coupon_id BIGINT    NOT NULL,
    PRIMARY KEY (id)
);
