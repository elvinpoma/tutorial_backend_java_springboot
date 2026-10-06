INSERT INTO category(name)
VALUES ('Eurogames');
INSERT INTO category(name)
VALUES ('Ameritrash');
INSERT INTO category(name)
VALUES ('Familiar');


INSERT INTO author(name, nationality)
VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality)
VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality)
VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality)
VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality)
VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality)
VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id)
VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id)
VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id)
VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id)
VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id)
VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id)
VALUES ('Azul', '8', 3, 5);


INSERT INTO client(name)
VALUES ('Hasan');
INSERT INTO client(name)
VALUES ('Elvin');
INSERT INTO client(name)
VALUES ('Daniel');
INSERT INTO client(name)
VALUES ('Aroa');
INSERT INTO client(name)
VALUES ('Aurora');


INSERT INTO loan(game_id, client_id, start_date, end_date)
VALUES (1, 1, '2020-10-10', '2021-10-10');
INSERT INTO loan(game_id, client_id, start_date, end_date)
VALUES (2, 2, '2020-10-10', '2021-03-18');
INSERT INTO loan(game_id, client_id, start_date, end_date)
VALUES (3, 3, '2024-01-29', '2027-05-01');
INSERT INTO loan(game_id, client_id, start_date, end_date)
VALUES (4, 4, '2022-08-10', '2022-09-01');
INSERT INTO loan(game_id, client_id, start_date, end_date)
VALUES (6, 5, '2020-10-10', '2021-10-10');
