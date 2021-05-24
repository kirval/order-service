INSERT INTO warehouses(id, name, coordinateX, coordinateY)
VALUES (1, 'WH_1', 1, 1),
       (2, 'WH_2', 2, 2);

INSERT INTO products(id, name)
VALUES (1, 'Product_1'),
       (2, 'Product_2'),
       (3, 'Product_3');

INSERT INTO warehouse_product(warehouse_id, product_id)
VALUES (1, 1),
       (2, 2),
       (1, 3),
       (2, 3);