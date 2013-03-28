DROP TABLE IF EXISTS products;
CREATE TABLE products (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name varchar(64) NOT NULL,
  price decimal(15,2) NOT NULL,
  description varchar(255),
  PRIMARY KEY (id),
  UNIQUE (name)
);