--- Drop the table if it exists
 DROP TABLE IF EXISTS account;

 -- Create the table
 CREATE TABLE account (
     id VARCHAR(255) PRIMARY KEY,
     user_name VARCHAR(255),
     food_balance DECIMAL(19, 2),
     meal_balance DECIMAL(19, 2),
     cash_balance DECIMAL(19, 2)
 );