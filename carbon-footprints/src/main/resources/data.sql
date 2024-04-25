INSERT INTO addresses (building_type, city, country, flat, house, postal_code, status, street)
VALUES ('HOUSE_HOLD', 'Belgrade', 'Serbia', '11', '5', '11223344', 'ACTIVE', 'Zivka Petrovica');

INSERT INTO _user (first_name, last_name, email, password, role, status, created_dt)
VALUES ('Azat', 'Nabiev', 'azat.nabiev991@gmail.com', '$2a$10$cp6FFz5aI8pZOjtt1L5blu0vVI4EElbDLLl9zCXDO5245Jxpznz7.', 'USER', 'ACTIVE', '2024-04-25 10:02:13.986003');

INSERT INTO coef (name, status, ncv, coc, cef)
VALUES ('Raw oil', 'ACTIVE', 40.12, 0.99, 20.31),
       ('Gas condensate', 'ACTIVE', 40.12, 0.995, 20.31),
       ('Aviation gasoline', 'ACTIVE', 44.21, 0.99, 19.13),
       ('Gasoline for auto', 'ACTIVE', 44.21, 0.99, 19.13),
       ('Jet fuel type gasoline', 'ACTIVE', 44.21, 0.99, 19.13),
       ('Jet fuel type kerosene', 'ACTIVE', 43.32, 0.99, 19.78),
       ('Kerosene for lighting and other', 'ACTIVE', 44.75, 0.99, 19.6),
       ('Diesel fuel', 'ACTIVE', 43.02, 0.99, 19.98),
       ('Household heating fuel', 'ACTIVE', 42.54, 0.99, 20.29),
       ('Fuel for low-speed diesel engines', 'ACTIVE', 42.34, 0.99, 20.22),
       ('Petroleum fuel (mazut)', 'ACTIVE', 41.15, 0.99, 20.84),
       ('Naval fuel oil', 'ACTIVE', 41.15, 0.99, 20.84),
       ('Liquefied propane and butane', 'ACTIVE', 47.31, 0.995, 17.2),
       ('Liquefied hydrocarbon gas', 'ACTIVE', 47.31, 0.995, 17.2),
       ('Petroleum and shale bitumen', 'ACTIVE', 40.19, 0.99, 22),
       ('Used oils', 'ACTIVE', 40.19, 0.99, 20),
       ('Petroleum and shake coke', 'ACTIVE', 31.0, 0.98, 27.5),
       ('Other fuels', 'ACTIVE', 29.309, 0.99, 20),
       ('Coking coal', 'ACTIVE', 24.01, 0.98, 24.89),
       ('Coal', 'ACTIVE', 17.62, 0.98, 25.58),
       ('Lignite (brown coal)', 'ACTIVE', 15.73, 0.98, 25.15),
       ('Coke and semi-coke from coal', 'ACTIVE', 25.12, 0.98, 29.5),
       ('Coke gas', 'ACTIVE', 16.73, 0.995, 13),
       ('Blast gas', 'ACTIVE', 4.19, 0.995, 66),
       ('Natural gas', 'ACTIVE', 34.78, 0.995, 15.04),
       ('Firewood', 'ACTIVE', 10.22, 0.98, 29.48);

INSERT INTO user_address_assn(address_id, user_id)
VALUES (1, 1);
