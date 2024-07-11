--- Drop the table if it exists
DROP TABLE IF EXISTS merchant;
CREATE TABLE IF NOT EXISTS merchant (
    id UUID PRIMARY KEY,
    merchant_name VARCHAR(255) NOT NULL,
    mcc VARCHAR(4) NOT NULL
);




DROP TABLE IF EXISTS account;

 -- Create the table
 CREATE TABLE account (
     id VARCHAR(255) PRIMARY KEY,
     user_name VARCHAR(255),
     food_balance DECIMAL(19, 2),
     meal_balance DECIMAL(19, 2),
     cash_balance DECIMAL(19, 2)
 );