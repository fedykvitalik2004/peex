CREATE TABLE Categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE Foods (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category_id INT,
    price NUMERIC(10, 2),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES Categories(id)
);

CREATE TABLE Ingredients (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    quantity VARCHAR(50),
    food_id INT,
    CONSTRAINT fk_food FOREIGN KEY (food_id) REFERENCES Foods(id)
);