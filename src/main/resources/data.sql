-- Insert data
INSERT INTO merchant (id, merchant_name, mcc) VALUES
    (random_uuid(), 'UBER TRIP                   SAO PAULO BR', '4121'),
    (random_uuid(), 'UBER EATS                   SAO PAULO BR', '5812'),
    (random_uuid(), 'PAG*JoseDaSilva             RIO DE JANEI BR', '5411'),
    (random_uuid(), 'PICPAY*BILHETEUNICO         GOIANIA BR', '4111');

INSERT INTO ACCOUNT (id, food_balance, meal_balance, cash_balance) VALUES ('123', 100.00, 200.00, 300.00);
INSERT INTO ACCOUNT (id, food_balance, meal_balance, cash_balance) VALUES ('456', 150.00, 250.00, 350.00);
INSERT INTO ACCOUNT (id, food_balance, meal_balance, cash_balance) VALUES ('789', 200.00, 300.00, 400.00);

