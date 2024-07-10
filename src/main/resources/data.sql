-- Drop the table if it exists
DROP TABLE IF EXISTS account;

-- Create the table
CREATE TABLE account (
    id VARCHAR(255) PRIMARY KEY,
    user_name VARCHAR(255),
    food_balance DECIMAL(19, 2),
    meal_balance DECIMAL(19, 2),
    cash_balance DECIMAL(19, 2)
);

-- Insert data
INSERT INTO account (id, food_balance, meal_balance, cash_balance) VALUES ('123', 100.00, 200.00, 300.00);
INSERT INTO account (id, food_balance, meal_balance, cash_balance) VALUES ('456', 150.00, 250.00, 350.00);
INSERT INTO account (id, food_balance, meal_balance, cash_balance) VALUES ('789', 200.00, 300.00, 400.00);
