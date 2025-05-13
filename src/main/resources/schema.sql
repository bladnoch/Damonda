-- 기존 테이블 삭제
# DROP TABLE IF EXISTS payment;
# DROP TABLE IF EXISTS order_item;
# DROP TABLE IF EXISTS orders;
# DROP TABLE IF EXISTS product;
# DROP TABLE IF EXISTS users;
-- 사용자 테이블
CREATE TABLE users (
                       id BINARY(16) PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       name VARCHAR(100),
                       phone VARCHAR(100)
);

-- 상품 테이블
CREATE TABLE product (
                         id BINARY(16) PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         price DECIMAL(19,2) NOT NULL,
                         stock_quantity INT NOT NULL
);

-- 주문 테이블
CREATE TABLE orders (
                        id BINARY(16) PRIMARY KEY,
                        order_time DATETIME NOT NULL,
                        status VARCHAR(20) NOT NULL
);

-- 주문 상세 (주문상품) 테이블
CREATE TABLE order_item (
                            id BINARY(16) PRIMARY KEY,
                            order_id BINARY(16) NOT NULL,
                            product_id BINARY(16) NOT NULL,
                            quantity INT NOT NULL,
                            price DECIMAL(19,2) NOT NULL,
                            CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(id),
                            CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id)
);
