
INSERT INTO address (city, country, state, zipcode) VALUES ('Fairfield', 'USA', 'Iowa', '52556');
INSERT INTO address (city, country, state, zipcode) VALUES ('Ottumwa', 'USA', 'Iowa', '64353');

INSERT INTO app_user (user_name, enable, password, type) VALUES ( 'admin', true, 'test', 'admin');
INSERT INTO app_user (user_name, enable, password, type) VALUES ( 'user', true, 'test', 'operator');

INSERT INTO customer ( user_name, address, phone) VALUES ('khadim', 'Fairfield, USA', '123456789');


INSERT INTO restaurant (name, address_id, operator_id) VALUES ('test-restaurant',1, 2);

INSERT INTO queue (name, size, restaurant_id) VALUES ('q-1-test-restaurant', 10,1);
INSERT INTO queue (name, size, restaurant_id) VALUES ('q-2-test-restaurant', 10,1);


INSERT INTO menu_item (description, price, name, restaurant_id) VALUES ('', 2.3, 'Pancakes', 1);
INSERT INTO menu_item (description, price, name, restaurant_id) VALUES ('', 1.5, 'Crumb Topped Banana', 1);
INSERT INTO menu_item (description, price, name, restaurant_id) VALUES ('', 3.4, 'Easy Breakfast Casserole', 1);
INSERT INTO menu_item (description, price, name, restaurant_id) VALUES ('', 4, 'The Bestest Belgian Waffles', 1);
INSERT INTO menu_item (description, price, name, restaurant_id) VALUES ('', 5.8, 'Spiced Butternut Squash Soup', 1);
INSERT INTO menu_item (description, price, name, restaurant_id) VALUES ('', 4.8, 'Tomato Bacon Grilled Cheese', 1);
INSERT INTO menu_item (description, price, name, restaurant_id) VALUES ('', 5.3, 'Philly Cheesteak Sandwich with Garlic Mayo', 1);
INSERT INTO menu_item (description, price, name, restaurant_id) VALUES ('', 8.1, 'Pork Marsala', 1);
INSERT INTO menu_item (description, price, name, restaurant_id) VALUES ('', 7.3, 'Pasta Pomodoro', 1);
INSERT INTO menu_item (description, price, name, restaurant_id) VALUES ('', 7.5, 'Fresh Tomato Shrimp Pasta', 1);
