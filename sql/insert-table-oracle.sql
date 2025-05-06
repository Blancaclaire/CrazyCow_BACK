-- Tabla CUSTOMERS
INSERT INTO CUSTOMERS (customer_id, name, surname, email, phone_number, user_name, password, address)
VALUES
  (13000, 'Alice', 'Smith', 'alice@example.com', '123456789', 'alice_s', 'password123', '123 Main St');

INSERT INTO CUSTOMERS (customer_id, name, surname, email, phone_number, user_name, password, address)
VALUES
  (13001, 'Bob', 'Johnson', 'bob@example.com', '987654321', 'bobby_j', 'pass456', '456 Elm St');

INSERT INTO CUSTOMERS (customer_id, name, surname, email, phone_number, user_name, password, address)
VALUES
  (13002, 'Carol', 'Williams', 'carol@example.com', '555123456', 'carol_w', 'secret789', '789 Oak St');

-- Tabla CATEGORIES
INSERT INTO CATEGORIES (category_id, category_name)
VALUES
  (1000, 'Burgers');

INSERT INTO CATEGORIES (category_id, category_name)
VALUES
  (1001, 'For Bitting');

INSERT INTO CATEGORIES (category_id, category_name)
VALUES
  (1002, 'Drinks');

INSERT INTO CATEGORIES (category_id, category_name)
VALUES
  (1003, 'Desserts');

-- Tabla JOBS
INSERT INTO JOBS (job_id, department_name, job_title)
VALUES
  (6000, 'Management', 'Restaurant Manager');

INSERT INTO JOBS (job_id, department_name, job_title)
VALUES
  (6001, 'Kitchen', 'Chef');

INSERT INTO JOBS (job_id, department_name, job_title)
VALUES
  (6002, 'Service', 'Waiter');

-- Tabla EMPLOYEES
INSERT INTO EMPLOYEES (employee_id, manager_id, job_id, restaurant_id, salary, name, surname, email, phone_number, user_name, password, address, start_date)
VALUES
  (9000, NULL, 6000, 10000, 5000.00, 'John', 'Doe', 'john.doe@restaurant.com', '2125551111', 'johnd', 'pass123', '100 Burger Lane', TO_DATE('2024-01-01', 'YYYY-MM-DD'));

INSERT INTO EMPLOYEES (employee_id, manager_id, job_id, restaurant_id, salary, name, surname, email, phone_number, user_name, password, address, start_date)
VALUES
  (9001, 9000, 6001, 10000, 3000.00, 'Emily', 'Smith', 'emily.smith@restaurant.com', '2125552222', 'emilys', 'chefpass', '100 Burger Lane', TO_DATE('2024-02-01', 'YYYY-MM-DD'));

INSERT INTO EMPLOYEES (employee_id, manager_id, job_id, restaurant_id, salary, name, surname, email, phone_number, user_name, password, address, start_date)
VALUES
  (9002, 9000, 6002, 10000, 2500.00, 'Michael', 'Brown', 'michael.brown@restaurant.com', '2125553333', 'mikeb', 'waiterpass', '100 Burger Lane', TO_DATE('2024-03-01', 'YYYY-MM-DD'));

INSERT INTO EMPLOYEES (employee_id, manager_id, job_id, restaurant_id, salary, name, surname, email, phone_number, user_name, password, address, start_date)
VALUES
  (9003, NULL, 6000, 10001, 4800.00, 'Laura', 'Williams', 'laura.williams@restaurant.com', '3125554444', 'lauraw', 'pass321', '200 Fries Ave', TO_DATE('2024-01-15', 'YYYY-MM-DD'));

-- Tabla RESTAURANTS
INSERT INTO RESTAURANTS (restaurant_id, manager_id, adress, city, phone_number, capacity, opening_hours, closing_hours)
VALUES
  (10000, 9000, '100 Burger Lane', 'Los Angeles', '2125551234', 50, 10, 22);

INSERT INTO RESTAURANTS (restaurant_id, manager_id, adress, city, phone_number, capacity, opening_hours, closing_hours)
VALUES
  (10001, 9003, '200 Fries Ave', 'New York', '3125554321', 40, 9, 21);

-- Tabla ORDERS
INSERT INTO ORDERS (order_id, customer_id, restaurant_id, order_date, order_hour, order_status, total, location)
VALUES
  (11000, 13000, 10000, TO_DATE('2025-01-01', 'YYYY-MM-DD'), 12, 'Delivered', 15.50, '123 Main St');

INSERT INTO ORDERS (
  order_id, customer_id, restaurant_id, order_date, order_hour, order_status, total, location
)
VALUES (
  11000, 13000, 10000,
  TO_DATE('2025-01-01', 'YYYY-MM-DD'),
  TO_DATE('12:00', 'HH24:MI'),
  'Delivered', 15.50, '123 Main St'
);

INSERT INTO ORDERS (order_id, customer_id, restaurant_id, order_date, order_hour, order_status, total, location)
VALUES
  (11001, 13001, 10001, TO_DATE('2025-01-02', 'YYYY-MM-DD'), 13, 'In Preparation', 22.00, '456 Elm St');

-- Tabla PAYMENTS
INSERT INTO PAYMENTS (payment_id, order_id, holder_name, holder_number, cvv, card_type, price)
VALUES
  (12000, 11000, 'Alice Smith', '4111111111111111', '123', 'Visa', 15.50);

INSERT INTO PAYMENTS (payment_id, order_id, holder_name, holder_number, cvv, card_type, price)
VALUES
  (12001, 11001, 'Bob Johnson', '5500000000000004', '456', 'MasterCard', 22.00);

-- Tabla PRODUCTS
INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8000, 1000, 'Cowabunga Burger', 'Juicy beef patty, crispy lettuce, tomato, melted cheese and our signature special sauce.', 9.99, 'burger1.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8001, 1000, 'Moo Moo Madness', 'Double beef patties with spicy BBQ sauce, cheddar cheese, crispy onion rings and pickles.', 12.99, 'burger2.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8002, 1000, 'Rancher Rumble', 'Beef patty topped with ranch dressing, crispy bacon, fresh lettuce, tomato and a sprinkle of fried onions.', 10.99, 'burger3.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8003, 1000, 'Funky Fries Burger', 'Beef patty with melted cheese, crispy potato rounds, special sauce and a kick of jalapeños.', 11.49, 'burger4.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8004, 1000, 'Happy Hooves', 'Triple beef patty, melted cheese, crunchy bacon, fresh lettuce and our legendary special sauce.', 13.99, 'burger5.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8005, 1000, 'Mad Cow Melt', 'Beef patty with melted Swiss cheese, grilled onions and a dash of spicy mustard.', 10.49, 'burger6.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8006, 1000, 'Bovine Bonanza', 'Double beef patty with sharp cheddar cheese, tangy pickles and an extra dose of special sauce.', 11.49, 'burger7.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8007, 1000, 'Lettuce Lunacy', 'Beef patty, fresh lettuce, tomato, creamy avocado and a zesty lime dressing.', 10.99, 'burger8.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8008, 1000, 'The Cow-ntdown', 'Mini burger featuring a beef patty, melted cheese and a blend of signature sauces for a quick bite.', 7.99, 'burger9.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8009, 1000, 'Pasture Party', 'Angus beef patty with Pepper Jack cheese, roasted jalapeños and a fresh pico de gallo topping.', 12.49, 'burger10.jpg');

-- For Bitting (1001)
INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8010, 1001, 'Crazy Chicken Chunks', 'Crispy chicken nuggets tossed in a zesty spice blend, served with a tangy dip.', 5.49, 'chicken_chunks.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8011, 1001, 'Raging Ring Rings', 'Golden battered onion rings, fried to perfection and served with a smoky dipping sauce.', 4.99, 'onion_rings.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8012, 1001, 'Cheesy Twisters', 'Breaded mozzarella sticks with a delightfully melty center, paired with our homemade marinara sauce.', 6.49, 'mozzarella_sticks.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8013, 1001, 'Tot-tally Crazy Tots', 'Crispy tater tots loaded with CrazyCow seasoning and served with a spicy dipping sauce.', 4.99, 'tater_tots.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8014, 1001, 'Buffalo Bonanza Bites', 'Spicy buffalo-style chicken bites drizzled with cool ranch dressing for an explosion of flavor.', 5.99, 'buffalo_bites.jpg');

-- Drinks (1002)
INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8015, 1002, 'Coca-Cola', 'Refreshing cola drink', 1.99, 'coke.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8016, 1002, 'Coca-Cola Zero', 'Zero sugar cola drink', 1.99, 'coke_zero.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8017, 1002, 'Fanta Orange', 'Orange flavored soda', 2.49, 'fanta.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8018, 1002, 'Aquarius Lemon', 'Lemon sports drink', 2.90, 'aquarius.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8019, 1002, 'Fuze Tea', 'Refreshing tea drink', 2.29, 'fuze_tea.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8020, 1002, 'Red Bull', 'Energy drink', 3.50, 'red_bull.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8021, 1002, 'Mineral Water', 'Natural mineral water', 1.99, 'water.jpg');

-- Desserts (1003)
INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8022, 1003, 'Ice Cream', 'Vanilla ice cream topped with chocolate syrup', 3.99, 'ice_cream.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8023, 1003, 'CheeseCake', 'Creamy cheesecake with a buttery graham cracker crust and strawberry compote', 4.49, 'cheesecake.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8024, 1003, 'Apple Pie', 'Homestyle apple pie with a flaky crust', 3.99, 'apple_pie.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8025, 1003, 'Cowabunga Sundae', 'Vanilla ice cream drenched in rich chocolate fudge, crowned with whipped cream and a cherry—a sundae with a splash of CrazyCow madness.', 4.99, 'sundae.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8026, 1003, 'Moo Moo Mousse', 'Decadent chocolate mousse with a hint of espresso, topped with a silky caramel drizzle for a smooth, indulgent finish.', 4.49, 'mousse.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8027, 1003, 'CrazyCow Brownie', 'A fudgy, decadent brownie loaded with walnuts and a swirl of creamy ganache – a perfect bite of rebellion.', 3.99, 'brownie.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8028, 1003, 'Bovine Banana Split', 'A classic banana split featuring three scoops of ice cream, drizzled with hot fudge and sprinkled with colorful toppings for extra flair.', 5.49, 'banana_split.jpg');

INSERT INTO PRODUCTS (product_id, category_id, product_name, description, price, image)
VALUES
  (8029, 1003, 'Funky Fudge Cake', 'Moist and rich fudge cake layered with velvety chocolate frosting and a dash of CrazyCow secret spice for an unforgettable treat.', 4.99, 'fudge_cake.jpg');

-- Tabla INGREDIENTS
INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2000, 'Beef Patty');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2001, 'Lettuce');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2002, 'Tomato');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2003, 'Cheese');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2004, 'Special Sauce');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2005, 'Potatoes');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2006, 'Chicken');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2007, 'Onion');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2008, 'Pickles');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2009, 'Ketchup');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2010, 'BBQ Sauce');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2011, 'Dough');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2012, 'Vanilla');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2013, 'Chocolate');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2014, 'Apple');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2015, 'Bacon');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2016, 'Jalapeños');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2017, 'Avocado');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2018, 'Ranch Dressing');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2019, 'Mustard');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2020, 'Pico de Gallo');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2021, 'Pepper Jack Cheese');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2022, 'Swiss Cheese');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2023, 'Lime Dressing');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2024, 'Spice Blend');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2025, 'Tangy Dip');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2026, 'Marinara Sauce');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2027, 'CrazyCow Seasoning');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2028, 'Spicy Dipping Sauce');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2029, 'Buffalo Sauce');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2030, 'Cream Cheese');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2031, 'Graham Cracker');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2032, 'Strawberry Compote');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2033, 'Whipped Cream');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2034, 'Cherry');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2035, 'CrazyCow Madness');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2036, 'Espresso');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2037, 'Caramel Drizzle');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2038, 'Walnuts');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2039, 'Ganache');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2040, 'Banana');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2041, 'Toppings');

INSERT INTO INGREDIENTS (ingredient_id, ingredient_name)
VALUES
  (2042, 'CrazyCow Secret Spice');

-- Tabla ALLERGENS
INSERT INTO ALLERGENS (allergen_id, allergen_name)
VALUES
  (4000, 'Gluten');

INSERT INTO ALLERGENS (allergen_id, allergen_name)
VALUES
  (4001, 'Dairy');

INSERT INTO ALLERGENS (allergen_id, allergen_name)
VALUES
  (4002, 'Soy');

INSERT INTO ALLERGENS (allergen_id, allergen_name)
VALUES
  (4003, 'Nuts');

-- Tabla APPLICANTS
INSERT INTO APPLICANTS (applicant_id, job_id, name, surname, email, phone_number, address, resume, aplication_date)
VALUES
  (7000, 6002, 'Sarah', 'Connor', 'sarah.connor@example.com', '2125554444', '300 Future St', '/resumes/sarah_connor.pdf', TO_DATE('2025-01-10', 'YYYY-MM-DD'));

INSERT INTO APPLICANTS (applicant_id, job_id, name, surname, email, phone_number, address, resume, aplication_date)
VALUES
  (7001, 6001, 'Kyle', 'Reese', 'kyle.reese@example.com', '3125555555', '400 Resistance Ave', '/resumes/kyle_reese.pdf', TO_DATE('2025-01-12', 'YYYY-MM-DD'));

-- Tabla PRODUCTS_ORDER
INSERT INTO PRODUCTS_ORDER (order_id, product_id, quantity)
VALUES
  (11000, 8000, 2);

INSERT INTO PRODUCTS_ORDER (order_id, product_id, quantity)
VALUES
  (11000, 8002, 1);



-- Tabla INGREDIENTS_PRODUCTS
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8000, 2000);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8000, 2001);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8000, 2002);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8000, 2003);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8000, 2004);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8001, 2000);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8001, 2010);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8001, 2003);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8001, 2007);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8001, 2008);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8002, 2000);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8002, 2018);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8002, 2015);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8002, 2001);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8002, 2002);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8002, 2007);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8003, 2000);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8003, 2003);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8003, 2004);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8003, 2005);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8003, 2016);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8004, 2000);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8004, 2003);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8004, 2015);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8004, 2001);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8004, 2004);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8005, 2000);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8005, 2022);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8005, 2007);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8005, 2019);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8006, 2000);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8006, 2003);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8006, 2008);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8006, 2004);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8007, 2000);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8007, 2001);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8007, 2002);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8007, 2017);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8007, 2023);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8008, 2000);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8008, 2003);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8008, 2004);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8009, 2000);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8009, 2021);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8009, 2016);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8009, 2020);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8010, 2006);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8010, 2024);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8010, 2025);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8011, 2007);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8012, 2003);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8012, 2026);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8013, 2005);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8013, 2027);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8013, 2028);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8014, 2006);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8014, 2018);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8022, 2012);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8022, 2013);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8023, 2030);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8023, 2031);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8023, 2032);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8024, 2014);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8024, 2011);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8025, 2012);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8025, 2013);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8025, 2033);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8025, 2034);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8025, 2035);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8026, 2013);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8026, 2036);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8026, 2037);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8027, 2013);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8027, 2038);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8027, 2039);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8028, 2040);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8028, 2012);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8028, 2013);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8028, 2041);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8029, 2013);
INSERT INTO INGREDIENTS_PRODUCTS (product_id, ingredient_id)
VALUES
  (8029, 2042);

-- Tabla ALLERGEN_INGREDIENTS
INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id)
VALUES
  (4001, 2003);

INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id)
VALUES
  (4000, 2004);

INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id)
VALUES
  (4002, 2010);

INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id)
VALUES
  (4000, 2011);

INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id)
VALUES
  (4001, 2018);

INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id)
VALUES
  (4001, 2021);

INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id)
VALUES
  (4001, 2022);

INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id)
VALUES
  (4001, 2029);

INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id)
VALUES
  (4001, 2030);

INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id)
VALUES
  (4000, 2031);

INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id)
VALUES
  (4001, 2033);

INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id)
VALUES
  (4001, 2037);

INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id)
VALUES
  (4003, 2038);

INSERT INTO ALLERGEN_INGREDIENTS (allergen_id, ingredient_id)
VALUES
  (4001, 2039);
  

