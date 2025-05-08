-- CATEGORIES 
INSERT INTO CATEGORIES (category_id, category_name) VALUES
(1000, 'Burgers'),
(1001, 'For Bitting'),
(1002, 'Drinks'),
(1003, 'Desserts');

-- JOBS 
INSERT INTO JOBS (job_id, department_name, job_title) VALUES
(6000, 'Management', 'Restaurant Manager'),
(6001, 'Kitchen', 'Chef'),
(6002, 'Service', 'Waiter'),
(6003, 'IT', 'System Administrator'),
(6004, 'RRHH', 'Resource Manager'),
(6005, 'Finance', 'Accountant'),
(6006, 'Marketing', 'Marketing Specialist'),
(6007, 'Sales', 'Sales Representative');

-- RESTAURANTS 
INSERT INTO RESTAURANTS (restaurant_id, adress, city, phone_number, capacity, opening_hours, closing_hours) VALUES
(10000, '100 Burger Lane', 'Los Angeles', '2125551234', 50, 10, 22),
(10001, '200 Fries Ave', 'New York', '3125554321', 40, 9, 21);

-- EMPLOYEES 
INSERT INTO EMPLOYEES (employee_id, manager_id, job_id, restaurant_id, salary, name, surname, email, phone_number, user_name, password, address, start_date) VALUES
(9000, 9000, 6000, 10000, 5000.00, 'John', 'Doe', 'john.doe@restaurant.com', '2125551111', 'johnd', 'pass123', '100 Burger Lane', '2024-01-15'),
(9001, 9000, 6001, 10000, 3000.00, 'Emily', 'Smith', 'emily.smith@restaurant.com', '2125552222', 'emilys', 'chefpass', '100 Burger Lane', '2024-01-15'),
(9002, 9000, 6002, 10000, 2500.00, 'Michael', 'Brown', 'michael.brown@restaurant.com', '2125553333', 'mikeb', 'waiterpass', '100 Burger Lane', '2024-01-15'),
(9003, 9000, 6000, 10001, 4800.00, 'Laura', 'Williams', 'laura.williams@restaurant.com', '3125554444', 'lauraw', 'pass321', '200 Fries Ave', '2024-01-15');

-- CUSTOMERS 
INSERT INTO CUSTOMERS (customer_id, name, surname, email, phone_number, user_name, password, address) VALUES
(13000, 'Alice', 'Smith', 'alice@example.com', '123456789', 'alice_s', 'password123', '123 Main St'),
(13001, 'Bob', 'Johnson', 'bob@example.com', '987654321', 'bobby_j', 'pass456', '456 Elm St'),
(13002, 'Carol', 'Williams', 'carol@example.com', '555123456', 'carol_w', 'secret789', '789 Oak St');

-- PRODUCTOS (transformado a IDs numéricos)
INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image) VALUES
-- Burgers (1000)
(8000, 1000, 'Cowabunga Burger', 'Juicy beef patty, crispy lettuce, tomato, melted cheese and our signature special sauce.', 9.99, 'burger1.jpg'),
(8001, 1000, 'Moo Moo Madness', 'Double beef patties with spicy BBQ sauce, cheddar cheese, crispy onion rings and pickles.', 12.99, 'burger2.jpg'),
(8002, 1000, 'Rancher Rumble', 'Beef patty topped with ranch dressing, crispy bacon, fresh lettuce, tomato and a sprinkle of fried onions.', 10.99, 'burger3.jpg'),
(8003, 1000, 'Funky Fries Burger', 'Beef patty with melted cheese, crispy potato rounds, special sauce and a kick of jalapeños.', 11.49, 'burger4.jpg'),
(8004, 1000, 'Happy Hooves', 'Triple beef patty, melted cheese, crunchy bacon, fresh lettuce and our legendary special sauce.', 13.99, 'burger5.jpg'),
(8005, 1000, 'Mad Cow Melt', 'Beef patty with melted Swiss cheese, grilled onions and a dash of spicy mustard.', 10.49, 'burger6.jpg'),
(8006, 1000, 'Bovine Bonanza', 'Double beef patty with sharp cheddar cheese, tangy pickles and an extra dose of special sauce.', 11.49, 'burger7.jpg'),
(8007, 1000, 'Lettuce Lunacy', 'Beef patty, fresh lettuce, tomato, creamy avocado and a zesty lime dressing.', 10.99, 'burger8.jpg'),
(8008, 1000, 'The Cow-ntdown', 'Mini burger featuring a beef patty, melted cheese and a blend of signature sauces for a quick bite.', 7.99, 'burger9.jpg'),
(8009, 1000, 'Pasture Party', 'Angus beef patty with Pepper Jack cheese, roasted jalapeños and a fresh pico de gallo topping.', 12.49, 'burger10.jpg'),

-- For Bitting (1001)
(8010, 1001, 'Crazy Chicken Chunks', 'Crispy chicken nuggets tossed in a zesty spice blend, served with a tangy dip.', 5.49, 'chicken1.jpg'),
(8011, 1001, 'Raging Ring Rings', 'Golden battered onion rings, fried to perfection and served with a smoky dipping sauce.', 4.99, 'onion_rings.jpg'),
(8012, 1001, 'Cheesy Twisters', 'Breaded mozzarella sticks with a delightfully melty center, paired with our homemade marinara sauce.', 6.49, 'mozzarella.jpg'),
(8013, 1001, 'Tot-tally Crazy Tots', 'Crispy tater tots loaded with CrazyCow seasoning and served with a spicy dipping sauce.', 4.99, 'tater_tots.jpg'),
(8014, 1001, 'Buffalo Bonanza Bites', 'Spicy buffalo-style chicken bites drizzled with cool ranch dressing for an explosion of flavor.', 5.99, 'buffalo_bites.jpg'),

-- Drinks (1002)
(8015, 1002, 'Coca-Cola', 'Refreshing cola drink', 1.99, 'coke.jpg'),
(8016, 1002, 'Coca-Cola Zero', 'Zero sugar cola drink', 1.99, 'coke_zero.jpg'),
(8017, 1002, 'Fanta Orange', 'Sparkling orange drink', 2.49, 'fanta.jpg'),
(8018, 1002, 'Aquarius Lemon', 'Sports drink lemon flavor', 2.90, 'aquarius.jpg'),
(8019, 1002, 'Fuze Tea', 'Iced tea with fruit flavors', 2.29, 'fuze_tea.jpg'),
(8020, 1002, 'Red Bull', 'Energy drink', 3.50, 'red_bull.jpg'),
(8021, 1002, 'Mineral Water', 'Natural mineral water', 1.99, 'water.jpg'),

-- Desserts (1003)
(8022, 1003, 'Ice Cream', 'Vanilla ice cream topped with chocolate syrup', 3.99, 'ice_cream.jpg'),
(8023, 1003, 'CheeseCake', 'Creamy cheesecake with a buttery graham cracker crust and strawberry compote', 4.49, 'cheesecake.jpg'),
(8024, 1003, 'Apple Pie', 'Homestyle apple pie with a flaky crust', 3.99, 'apple_pie.jpg'),
(8025, 1003, 'Cowabunga Sundae', 'Vanilla ice cream drenched in rich chocolate fudge, crowned with whipped cream and a cherry', 4.99, 'sundae.jpg'),
(8026, 1003, 'Moo Moo Mousse', 'Decadent chocolate mousse with a hint of espresso, topped with a silky caramel drizzle', 4.49, 'mousse.jpg'),
(8027, 1003, 'CrazyCow Brownie', 'A fudgy, decadent brownie loaded with walnuts and a swirl of creamy ganache', 3.99, 'brownie.jpg'),
(8028, 1003, 'Bovine Banana Split', 'A classic banana split featuring three scoops of ice cream, drizzled with hot fudge', 5.49, 'banana_split.jpg'),
(8029, 1003, 'Funky Fudge Cake', 'Moist and rich fudge cake layered with velvety chocolate frosting', 4.99, 'fudge_cake.jpg');

-- INGREDIENTS 
INSERT INTO INGREDIENTS (ingredient_id, ingredient_name) VALUES
(2000, 'Beef Patty'),
(2001, 'Lettuce'),
(2002, 'Tomato'),
(2003, 'Cheese'),
(2004, 'Special Sauce'),
(2005, 'Potatoes'),
(2006, 'Chicken'),
(2007, 'Onion'),
(2008, 'Pickles'),
(2009, 'Ketchup'),
(2010, 'BBQ Sauce');

-- ALLERGENS 
INSERT INTO ALLERGENS (allergen_id, allergen_name) VALUES
(4000, 'Gluten'),
(4001, 'Dairy'),
(4002, 'Soy'),
(4003, 'Nuts');

-- ORDERS 
INSERT INTO ORDERS (order_id, customer_id, restaurant_id, order_date, order_hour, order_status, total, location) VALUES
(11000, 13000, 10000, '2025-01-01', '12:00:00', 'Delivered', 15.50, '123 Main St'),
(11001, 13001, 10001, '2025-01-02', '13:00:00', 'In Preparation', 22.00, '456 Elm St');

-- PAYMENTS 
INSERT INTO PAYMENTS (payment_id, order_id, holder_name, holder_number, cvv, card_type, price) VALUES
(12000, 11000, 'Alice Smith', '4111111111111111', '123', 'Visa', 15.50),
(12001, 11001, 'Bob Johnson', '5500000000000004', '456', 'MasterCard', 22.00);

-- PRODUCTS_ORDER 
INSERT INTO PRODUCTS_ORDER (order_id, product_id, quantity) VALUES
(11000, 8000, 2),  -- Cowabunga Burger
(11000, 8002, 1);  -- Rancher Rumble

-- INGREDIENTS_PRODUCTS 
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id) VALUES
(8000, 2000),  -- Cowabunga Burger -> Beef Patty
(8000, 2001),  -- Cowabunga Burger -> Lettuce
(8000, 2002),  -- Cowabunga Burger -> Tomato
(8000, 2003),  -- Cowabunga Burger -> Cheese
(8000, 2004),  -- Cowabunga Burger -> Special Sauce
(8001, 2000),  -- Moo Moo Madness -> Beef Patty
(8001, 2010),  -- Moo Moo Madness -> BBQ Sauce
(8001, 2003);  -- Moo Moo Madness -> Cheese

-- ALLERGEN_INGREDIENTS 
INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id) VALUES
(4001, 2003),  -- Dairy -> Cheese
(4000, 2004),  -- Gluten -> Special Sauce
(4002, 2010),  -- Soy -> BBQ Sauce
(4000, 2000);  -- Gluten -> Beef Patty

-- APPLICANTS 
INSERT INTO APPLICANTS (applicant_id, job_id, name, surname, email, phone_number, address, resume, aplication_date) VALUES
(7000, 6002, 'Sarah', 'Connor', 'sarah.connor@example.com', '2125554444', '300 Future St', '/resumes/sarah_connor.pdf', '2025-01-10'),
(7001, 6001, 'Kyle', 'Reese', 'kyle.reese@example.com', '3125555555', '400 Resistance Ave', '/resumes/kyle_reese.pdf', '2025-01-12');