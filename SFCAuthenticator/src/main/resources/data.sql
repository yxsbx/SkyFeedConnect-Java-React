INSERT INTO users (ID, USERNAME, LOGIN, PASSWORD, EMAIL, STATUS)
VALUES (10, 'Yasmin', 'yasmin@g.com', '$2a$10$HW44.1EHeDoPygjNc/sPM.uOE4YoBXq6XxVEPWSGN16bxLRnOb4KW','rogerionj@gmail.com', 'ACTIVE');
INSERT INTO users_verifier (ID, IDENTIFIER, EXPIRATION_DATE, user_id)
VALUES (10, '0c7f04ba-738f-42e9-aeb6-fdb3eb9a260f', '2025-01-01 00:00:00', 10);

