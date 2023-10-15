create database electronic_products;

\connect electronic_products;

CREATE TABLE electronic_product (
     id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
     name CHARACTER VARYING(255) NOT NULL,
     price INT NOT NULL,
     CONSTRAINT electronicProductUniq UNIQUE (name)
);

INSERT INTO electronic_product (name,price) VALUES ('Tv',700), ('Radio',400), ('Control',10);