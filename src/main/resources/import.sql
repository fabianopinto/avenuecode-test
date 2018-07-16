INSERT INTO product (name, description, parent_id) VALUES ('First Product', 'Simple product', NULL)
INSERT INTO product (name, description, parent_id) VALUES ('Second Product', 'Product with children', NULL)
INSERT INTO product (name, description, parent_id) VALUES ('Third Product', 'Product with a parent', 2)
INSERT INTO product (name, description, parent_id) VALUES ('Fourth Product', 'Another product with a parent', 2)

INSERT INTO image (type, product_id) VALUES ('Single image', 1)
INSERT INTO image (type, product_id) VALUES ('First image', 2)
INSERT INTO image (type, product_id) VALUES ('Second image', 2)
INSERT INTO image (type, product_id) VALUES ('Another image', 4)
