-- Insert data
INSERT INTO merchant (id, merchant_name, mcc) VALUES
    (UUID(), 'UBER TRIP                   SAO PAULO BR', '5999'),
    (UUID(), 'UBER EATS                   SAO PAULO BR', '5812'),
    (UUID(), 'PAG*JoseDaSilva             RIO DE JANEI BR', '5411'),
    (UUID(), 'PICPAY*BILHETEUNICO         GOIANIA BR', '5999');

INSERT INTO ACCOUNT (id, food_balance, meal_balance, cash_balance) VALUES ('123', 100.00, 200.00, 300.00);
INSERT INTO ACCOUNT (id, food_balance, meal_balance, cash_balance) VALUES ('456', 150.00, 250.00, 350.00);
INSERT INTO ACCOUNT (id, food_balance, meal_balance, cash_balance) VALUES ('789', 200.00, 300.00, 400.00);

