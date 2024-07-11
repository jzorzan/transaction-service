-- Example Merchants
INSERT INTO merchant (name, mcc) VALUES ('UBER TRIP                   SAO PAULO BR', '5999');
INSERT INTO merchant (name, mcc) VALUES ('UBER EATS                   SAO PAULO BR', '5812');
INSERT INTO merchant (name, mcc) VALUES ('PAG*JoseDaSilva          RIO DE JANEI BR', '5999');
INSERT INTO merchant (name, mcc) VALUES ('PICPAY*BILHETEUNICO           GOIANIA BR', '5999');

-- Example Accounts with initial balances
INSERT INTO account (account_id, food_balance, meal_balance, cash_balance) VALUES ('123', 100.00, 100.00, 100.00);
INSERT INTO account (account_id, food_balance, meal_balance, cash_balance) VALUES ('124', 50.00, 50.00, 50.00);

