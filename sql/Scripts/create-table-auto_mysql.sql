CREATE TABLE CUSTOMERS (
    customer_id INT PRIMARY KEY AUTO_INCREMENT, -- IDS13000 al 14000
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    user_name VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE CATEGORIES (
    category_id INT PRIMARY KEY AUTO_INCREMENT, -- IDs del 1000 al 1050
    category_name VARCHAR(255) NOT NULL
);

CREATE TABLE INGREDIENTS (
    ingredient_id INT PRIMARY KEY AUTO_INCREMENT, -- IDs del 2000 al 3000
    ingredient_name VARCHAR(255) NOT NULL
);

CREATE TABLE ALLERGENS (
    allergen_id INT PRIMARY KEY AUTO_INCREMENT, -- IDs del 4000 al 5000
    allergen_name VARCHAR(255) NOT NULL
);

CREATE TABLE JOBS (
    job_id INT PRIMARY KEY AUTO_INCREMENT, -- IDs del 6000 al 7000
    department_name VARCHAR(255) NOT NULL,
    job_title VARCHAR(255) NOT NULL
);

CREATE TABLE PRODUCTS (
    product_id INT PRIMARY KEY AUTO_INCREMENT, -- IDs del 8000 al 9000
    category_id INT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    image VARCHAR(255) NOT NULL, 
    FOREIGN KEY (category_id) REFERENCES CATEGORIES(category_id)
);

CREATE TABLE APPLICANTS (
    applicant_id INT PRIMARY KEY AUTO_INCREMENT, -- IDs del 7000 al 8000
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    resume VARCHAR(255) NOT NULL, 
    FOREIGN KEY (job_id) REFERENCES JOBS(job_id)
);

CREATE TABLE EMPLOYEES (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    manager_id INT,
    job_id INT,
    restaurant_id INT,
    salary DECIMAL(10, 2) NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL
);

CREATE TABLE RESTAURANTS (
    restaurant_id INT PRIMARY KEY AUTO_INCREMENT,
    adress VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    capacity INT NOT NULL,
    opening_hours TIME NOT NULL,
    closing_hours TIME NOT NULL
);

ALTER TABLE EMPLOYEES
ADD CONSTRAINT fk_employee_restaurant
FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS(restaurant_id);

CREATE TABLE ORDERS (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    restaurant_id INT NOT NULL,  
    order_date DATE NOT NULL,
    order_status VARCHAR(50) NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    location VARCHAR(255) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES CUSTOMERS(customer_id),
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS(restaurant_id)
);

CREATE TABLE PAYMENTS (
    payment_id INT PRIMARY KEY AUTO_INCREMENT, -- IDs del 12000 al 13000
    order_id INT NOT NULL UNIQUE,
    holder_name VARCHAR(255) NOT NULL,
    holder_number VARCHAR(20) NOT NULL,
    cvv VARCHAR(4) NOT NULL,
    card_type VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES ORDERS(order_id)
);


CREATE TABLE JOB_OFFERS(
    job_offer_id INT PRIMARY KEY AUTO_INCREMENT, -- IDs del 11000 al 12000
    job_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    location VARCHAR(255) NOT NULL,
    FOREIGN KEY (job_id) REFERENCES JOBS(job_id)
);

CREATE TABLE JOB_OFFERS_APPLICANTS(
    job_offer_id INT NOT NULL,
    applicant_id INT NOT NULL,
    application_date DATE NOT NULL,
    PRIMARY KEY (job_offer_id, applicant_id),
    FOREIGN KEY (job_offer_id) REFERENCES JOB_OFFERS(job_offer_id),
    FOREIGN KEY (applicant_id) REFERENCES APPLICANTS(applicant_id)
);

CREATE TABLE PRODUCTS_ORDER (
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES ORDERS(order_id),
    FOREIGN KEY (product_id) REFERENCES PRODUCTS(product_id)
); 

CREATE TABLE INGREDIENTS_PRODUCTS (
    product_id INT NOT NULL,
    ingredient_id INT NOT NULL,
    PRIMARY KEY (product_id, ingredient_id),
    FOREIGN KEY (product_id) REFERENCES PRODUCTS(product_id),
    FOREIGN KEY (ingredient_id) REFERENCES INGREDIENTS(ingredient_id)
);

CREATE TABLE ALLERGEN_INGREDIENTS (
    allergen_id INT NOT NULL,
    ingredient_id INT NOT NULL,
    PRIMARY KEY (allergen_id, ingredient_id),
    FOREIGN KEY (allergen_id) REFERENCES ALLERGENS(allergen_id),
    FOREIGN KEY (ingredient_id) REFERENCES INGREDIENTS(ingredient_id)
);
