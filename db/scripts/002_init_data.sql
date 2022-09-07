INSERT INTO authorities(authority)
VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN');

INSERT INTO users(username, password)
VALUES
    ('admin', '$2a$10$E3cvToPXSfYBluwjvZ1dcukNNYez4NFnWzVn048/UHhZQJ9cXdTHi'), --123456
    ('user_1', '$2a$10$E3cvToPXSfYBluwjvZ1dcukNNYez4NFnWzVn048/UHhZQJ9cXdTHi'), --123456
    ('user_2', '$2a$10$E3cvToPXSfYBluwjvZ1dcukNNYez4NFnWzVn048/UHhZQJ9cXdTHi'); --123456

INSERT INTO users_authorities(user_id, authority_id)
VALUES
    ((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM authorities WHERE authority = 'ROLE_ADMIN')),
    ((SELECT id FROM users WHERE username = 'user_1'), (SELECT id FROM authorities WHERE authority = 'ROLE_USER')),
    ((SELECT id FROM users WHERE username = 'user_2'), (SELECT id FROM authorities WHERE authority = 'ROLE_USER'));

INSERT INTO posts(name, description, user_id)
VALUES
    ('Флудилка', 'Общение на свободные темы', (SELECT id FROM users WHERE username = 'admin')),
    ('Продам гараж', 'Большой, просторный, светлый, уютный', (SELECT id FROM users WHERE username = 'user_1')),
    ('Продам машину Москвич', 'На ходу, почти новая', (SELECT id FROM users WHERE username = 'user_2'));

INSERT INTO messages(text, post_id, user_id)
VALUES
    ('Всем привет', (SELECT id FROM posts WHERE name = 'Флудилка'), (SELECT id FROM users WHERE username = 'user_1')),
    ('Как дела?', (SELECT id FROM posts WHERE name = 'Флудилка'), (SELECT id FROM users WHERE username = 'user_1')),
    ('Отлично', (SELECT id FROM posts WHERE name = 'Флудилка'), (SELECT id FROM users WHERE username = 'user_2')),
    ('Покупаю', (SELECT id FROM posts WHERE name = 'Продам гараж'), (SELECT id FROM users WHERE username = 'user_2')),
    ('Дорого', (SELECT id FROM posts WHERE name = 'Продам машину Москвич'), (SELECT id FROM users WHERE username = 'user_1')),
    ('Скину 1500', (SELECT id FROM posts WHERE name = 'Продам машину Москвич'), (SELECT id FROM users WHERE username = 'user_2')),
    ('Беру', (SELECT id FROM posts WHERE name = 'Продам машину Москвич'), (SELECT id FROM users WHERE username = 'user_1'));