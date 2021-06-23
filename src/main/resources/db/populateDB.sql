DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2015-06-01 14:00:00', 'Админ ланч', 510, 100001),
       ('2015-06-01 21:00:00', 'Админ ужин', 1500, 100001),
       ('2015-06-01 14:00:00', 'user ланч', 490, 100000),
       ('2015-06-01 22:00:00', 'user ужин', 1500, 100000),
       ('2021-06-20 11:00:00', 'Админ завтрак', 700, 100001),
       ('2021-06-20 11:00:00', 'user завтрак', 700, 100000),
       ('2021-06-20 13:59:06', 'Админ обед', 1200, 100001),
       ('2021-06-20 13:00:10', 'user обед', 1200, 100000);
