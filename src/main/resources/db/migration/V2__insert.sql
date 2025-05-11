INSERT INTO users (name, surname, email) VALUES
                                             ('Иван', 'Иванов', 'ivan.ivanov@example.com'),
                                             ('Петр', 'Петров', 'petr.petrov@example.com'),
                                             ('Анна', 'Сидорова', 'anna.sidorova@example.com'),
                                             ('Мария', 'Смирнова', 'maria.smirnova@example.com'),
                                             ('Алексей', 'Кузнецов', 'alexey.kuznetsov@example.com'),
                                             ('Елена', 'Попова', 'elena.popova@example.com'),
                                             ('Дмитрий', 'Васильев', 'dmitry.vasiliev@example.com'),
                                             ('Ольга', 'Новикова', 'olga.novikova@example.com'),
                                             ('Сергей', 'Федоров', 'sergey.fedorov@example.com'),
                                             ('Татьяна', 'Морозова', 'tatyana.morozova@example.com');

-- Вставка 10 тестовых подписок
INSERT INTO subscriptions (name, user_id, start_date, end_date, period) VALUES
                                                                            ('Netflix', 1, '2023-01-01', '2024-01-01', 'YEAR'),
                                                                            ('Spotify', 2, '2023-02-15', '2023-03-15', 'MONTH'),
                                                                            ('YouTube Premium', 3, '2023-03-01', '2023-06-01', 'QUARTER'),
                                                                            ('Apple Music', 4, '2023-04-10', '2023-10-10', 'HALF_YEAR'),
                                                                            ('Amazon Prime', 5, '2023-05-05', '2024-05-05', 'YEAR'),
                                                                            ('Disney+', 6, '2023-06-20', '2023-07-20', 'MONTH'),
                                                                            ('Microsoft 365', 7, '2023-07-01', '2023-10-01', 'QUARTER'),
                                                                            ('PlayStation Plus', 8, '2023-08-15', '2024-02-15', 'HALF_YEAR'),
                                                                            ('Xbox Game Pass', 9, '2023-09-01', '2024-09-01', 'YEAR'),
                                                                            ('Telegram Premium', 10, '2023-10-10', '2023-11-10', 'MONTH');