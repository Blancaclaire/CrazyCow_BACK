-- Primero, establecer los valores iniciales para AUTO_INCREMENT
ALTER TABLE CATEGORIES AUTO_INCREMENT = 1000;
ALTER TABLE JOBS AUTO_INCREMENT = 6000;
ALTER TABLE RESTAURANTS AUTO_INCREMENT = 10000;
ALTER TABLE EMPLOYEES AUTO_INCREMENT = 9000;
ALTER TABLE CUSTOMERS AUTO_INCREMENT = 13000;
ALTER TABLE PRODUCTS AUTO_INCREMENT = 8000;
ALTER TABLE INGREDIENTS AUTO_INCREMENT = 2000;
ALTER TABLE ALLERGENS AUTO_INCREMENT = 4000;
ALTER TABLE ORDERS AUTO_INCREMENT = 11000;
ALTER TABLE PAYMENTS AUTO_INCREMENT = 12000;
ALTER TABLE APPLICANTS AUTO_INCREMENT = 7000;

-- CATEGORIES (sin especificar category_id)
INSERT INTO CATEGORIES (category_name) VALUES
('Burgers'),
('For Bitting'),
('Drinks'),
('Desserts');

-- JOBS (sin especificar job_id)
INSERT INTO JOBS (department_name, job_title) VALUES
('Management', 'Restaurant Manager'),
('Kitchen', 'Chef'),
('Service', 'Waiter'),
('IT', 'System Administrator'),
('RRHH', 'Resource Manager'),
('Finance', 'Accountant'),
('Marketing', 'Marketing Specialist'),
('Sales', 'Sales Representative');

-- RESTAURANTS (sin especificar restaurant_id)
INSERT INTO RESTAURANTS (adress, city, phone_number, capacity, opening_hours, closing_hours) VALUES
('100 Burger Lane', 'Los Angeles', '2125551234', 50, '10:00:00', '22:00:00'),
('200 Fries Ave', 'New York', '3125554321', 40, '09:00:00', '21:00:00');

-- EMPLOYEES (sin especificar employee_id)
-- Nota: Para el primer empleado que es manager de sí mismo, necesitaremos una inserción especial
INSERT INTO EMPLOYEES (manager_id, job_id, restaurant_id, salary, name, surname, email, phone_number, user_name, password, address, start_date) VALUES
(NULL, 6000, 10000, 5000.00, 'John', 'Doe', 'john.doe@restaurant.com', '2125551111', 'johnd', 'pass123', '100 Burger Lane', '2024-01-15');

-- Ahora actualizamos al manager para que sea manager de sí mismo
UPDATE EMPLOYEES SET manager_id = 9000 WHERE employee_id = 9000;

-- Continuamos con el resto de empleados
INSERT INTO EMPLOYEES (manager_id, job_id, restaurant_id, salary, name, surname, email, phone_number, user_name, password, address, start_date) VALUES
(9000, 6001, 10000, 3000.00, 'Emily', 'Smith', 'emily.smith@restaurant.com', '2125552222', 'emilys', 'chefpass', '100 Burger Lane', '2024-01-15'),
(9000, 6002, 10000, 2500.00, 'Michael', 'Brown', 'michael.brown@restaurant.com', '2125553333', 'mikeb', 'waiterpass', '100 Burger Lane', '2024-01-15'),
(9000, 6000, 10001, 4800.00, 'Laura', 'Williams', 'laura.williams@restaurant.com', '3125554444', 'lauraw', 'pass321', '200 Fries Ave', '2024-01-15');

-- CUSTOMERS (sin especificar customer_id)
INSERT INTO CUSTOMERS (name, surname, email, phone_number, user_name, password, address) VALUES
('Alice', 'Smith', 'alice@example.com', '123456789', 'alice_s', 'password123', '123 Main St'),
('Bob', 'Johnson', 'bob@example.com', '987654321', 'bobby_j', 'pass456', '456 Elm St'),
('Carol', 'Williams', 'carol@example.com', '555123456', 'carol_w', 'secret789', '789 Oak St');

-- PRODUCTS (sin especificar product_id)
-- Burgers
INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image) VALUES
(8000, 1000, 'Cowabunga Burger', 'Juicy beef patty, crispy lettuce, tomato, melted cheese and our signature special sauce.', 9.99, 'burguer2.png'),
(8001, 1000, 'Moo Moo Madness', 'Double beef patties with spicy BBQ sauce, cheddar cheese, crispy onion rings and pickles.', 12.99, 'burguer4.png'),
(8002, 1000, 'Rancher Rumble', 'Beef patty topped with ranch dressing, crispy bacon, fresh lettuce, tomato and a sprinkle of fried onions.', 10.99, 'burguer1.png'),
(8003, 1000, 'Funky Fries Burger', 'Beef patty with melted cheese, crispy potato rounds, special sauce and a kick of jalapeños.', 11.49, 'burguer3.png'),
(8004, 1000, 'Happy Hooves', 'Triple beef patty, melted cheese, crunchy bacon, fresh lettuce and our legendary special sauce.', 13.99, 'burguer5.png'),
(8005, 1000, 'Mad Cow Melt', 'Beef patty with melted Swiss cheese, grilled onions and a dash of spicy mustard.', 10.49, 'burguer7.png'),
(8006, 1000, 'Bovine Bonanza', 'Double beef patty with sharp cheddar cheese, tangy pickles and an extra dose of special sauce.', 11.49, 'burguer7.png'),
(8007, 1000, 'Lettuce Lunacy', 'Beef patty, fresh lettuce, tomato, creamy avocado and a zesty lime dressing.', 10.99, 'burguer6.png'),
(8008, 1000, 'The Cow-ntdown', 'Mini burger featuring a beef patty, melted cheese and a blend of signature sauces for a quick bite.', 7.99, 'burguer16.png'),
(8009, 1000, 'Pasture Party', 'Angus beef patty with Pepper Jack cheese, roasted jalapeños and a fresh pico de gallo topping.', 12.49, 'burger10.png');

-- For Bitting - Asegurando IDs desde 8100
INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image) VALUES
(8100, 1001, 'Cheese Fries', 'Crispy fries topped with melted cheese sauce.', 5.49, 'picoteo16.png'),
(8101, 1001, 'Spring Rolls', 'Golden fried spring rolls with vegetable filling.', 4.99, 'picoteo15.png'),
(8102, 1001, 'Mozzarella Sticks', 'Breaded mozzarella sticks with a delightfully melty center, paired with our homemade marinara sauce.', 6.49, 'picoteo14.png'),
(8103, 1001, 'Classic French Fries', 'Crispy golden french fries with sea salt.', 4.99, 'picoteo12.png'),
(8104, 1001, 'Shoestring Fries', 'Thin-cut fries with a crispy texture.', 5.99, 'picoteo7.png');

-- Drinks - Asegurando IDs desde 8200
INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image) VALUES
(8200, 1002, 'Draft Beer', 'Refreshing golden draft beer served in a chilled mug.', 4.99, 'bebida7.png'),
(8201, 1002, 'Cola Bottle', 'Classic cola in an iconic glass bottle.', 2.99, 'bebida4.png'),
(8202, 1002, 'Cranberry Spritzer', 'Refreshing cranberry juice cocktail with a splash of soda.', 3.99, 'bebida6.png'),
(8203, 1002, 'Sunset Iced Tea', 'Vibrant layered iced tea with citrus and grenadine', 4.49, 'bebida2.png'),
(8204, 1002, 'Classic Cola', 'Traditional cola in a glass bottle', 2.99, 'bebida3.png'),
(8205, 1002, 'Amber Ale', 'Rich amber-colored ale with a smooth finish.', 5.99, 'bebida1.png');

-- Desserts - Asegurando IDs desde 8300
INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image) VALUES
(8300, 1003, 'Bacon Sundae', 'Vanilla ice cream with bacon bits and maple syrup.', 3.99, 'postre11.png'),
(8301, 1003, 'Cookie Delight', 'Warm cookie with ice cream and chocolate sauce.', 4.49, 'postre10.png'),
(8302, 1003, 'Berry Cheesecake', 'Classic cheesecake with mixed berry topping.', 3.99, 'postre9.png'),
(8303, 1003, 'Matcha Cheesecake', 'Japanese-style green tea cheesecake.', 4.99, 'postre7.png'),
(8304, 1003, 'Mango Cheesecake', 'Creamy cheesecake with mango coulis.', 4.49, 'postre8.png'),
(8305, 1003, 'Berry Pancakes', 'Fluffy pancakes with fresh berries and syrup.', 3.99, 'postre5.png'),
(8306, 1003, 'Blueberry Cheesecake', 'New York style cheesecake with blueberry topping.', 5.49, 'postre6.png'),
(8307, 1003, 'Strawberry Milkshake', 'Creamy milkshake with fresh strawberries.', 4.99, 'postre4.png');
-- INGREDIENTS (sin especificar ingredient_id)
INSERT INTO INGREDIENTS (ingredient_name) VALUES
('Beef Patty'),
('Lettuce'),
('Tomato'),
('Cheese'),
('Special Sauce'),
('Potatoes'),
('Chicken'),
('Onion'),
('Pickles'),
('Ketchup'),
('BBQ Sauce');

-- ALLERGENS (sin especificar allergen_id)
INSERT INTO ALLERGENS (allergen_name) VALUES
('Gluten'),
('Dairy'),
('Soy'),
('Nuts');

-- ORDERS (sin especificar order_id)
INSERT INTO ORDERS (customer_id, restaurant_id, order_date, order_status, total, location) VALUES
(13000, 10000, '2025-01-01', 'Delivered', 15.50, '123 Main St'),
(13001, 10001, '2025-01-02', 'In Preparation', 22.00, '456 Elm St');

-- PAYMENTS (sin especificar payment_id)
INSERT INTO PAYMENTS (order_id, holder_name, holder_number, cvv, card_type, price) VALUES
(11000, 'Alice Smith', '4111111111111111', '123', 'Visa', 15.50),
(11001, 'Bob Johnson', '5500000000000004', '456', 'MasterCard', 22.00);

-- PRODUCTS_ORDER
-- Aquí necesitamos mantener los IDs específicos ya que son FOREIGN KEYs
INSERT INTO PRODUCTS_ORDER (order_id, product_id, quantity) VALUES
(11000, 8000, 2),  -- Cowabunga Burger
(11000, 8103, 1),  -- Classic French Fries
(11001, 8201, 3),  -- Cola Bottle
(11001, 8302, 1);  -- Berry Cheesecake

-- INGREDIENTS_PRODUCTS
-- Aquí necesitamos mantener los IDs específicos ya que son FOREIGN KEYs
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id) VALUES
(8000, 2000),  -- Cowabunga Burger -> Beef Patty
(8000, 2001),  -- Cowabunga Burger -> Lettuce
(8000, 2002),  -- Cowabunga Burger -> Tomato
(8000, 2003),  -- Cowabunga Burger -> Cheese
(8000, 2004),  -- Cowabunga Burger -> Special Sauce
(8001, 2000),  -- Moo Moo Madness -> Beef Patty
(8001, 2010),  -- Moo Moo Madness -> BBQ Sauce
(8001, 2003),  -- Moo Moo Madness -> Cheese
(8102, 2003),  -- Mozzarella Sticks -> Cheese
(8102, 2005),  -- Mozzarella Sticks -> Potatoes
(8103, 2005),  -- Classic French Fries -> Potatoes
(8302, 2001),  -- Berry Cheesecake -> Lettuce (decoración)
(8302, 2003);  -- Berry Cheesecake -> Cheese

-- ALLERGEN_INGREDIENTS
-- Aquí necesitamos mantener los IDs específicos ya que son FOREIGN KEYs
INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id) VALUES
(4001, 2003),  -- Dairy -> Cheese
(4000, 2004),  -- Gluten -> Special Sauce
(4002, 2010),  -- Soy -> BBQ Sauce
(4000, 2000);  -- Gluten -> Beef Patty

-- APPLICANTS (sin especificar applicant_id)
INSERT INTO APPLICANTS (job_id, name, surname, email, phone_number, address, resume, aplication_date) VALUES
(6002, 'Sarah', 'Connor', 'sarah.connor@example.com', '2125554444', '300 Future St', '\CrazyCow_FRONT\imagenes\sarah_connor.pdf', '2025-01-10'),
(6001, 'Kyle', 'Reese', 'kyle.reese@example.com', '3125555555', '400 Resistance Ave', '\CrazyCow_FRONT\imagenes\kyle_reese.pdf', '2025-01-12');

INSERT INTO JOB_OFFERS (job_id, title, description, location) VALUES
(6003, 'Full Stack Web Developer', 'We are looking for a web developer with experience in frontend and backend to improve our order platform and internal management system.', 'Los Angeles'),
(6003, 'Frontend Software Engineer', 'Responsible for the development and maintenance of the user interface of our website and mobile application.', 'New York');


INSERT INTO JOB_OFFERS_APPLICANTS (job_offer_id, applicant_id, application_date) VALUES
(1, 7004, '2025-05-14'), -- Postulante para Full Stack Developer
(2, 7005, '2025-05-15'); -- Postulante para Frontend Engineer
