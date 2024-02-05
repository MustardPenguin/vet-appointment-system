
INSERT INTO "appointment".accounts (id, email, first_name, last_name) VALUES
(
 '92ac1a01-86a6-4c7c-b8c9-13094f21627e',
 'test@gmail.com', 'test1', 'test2'
);

INSERT INTO "appointment".pets (id, owner_id, name, species, birth_date) VALUES
(
 '3ddf2488-72c4-4fe3-9412-b17732777090',
 '92ac1a01-86a6-4c7c-b8c9-13094f21627e',
 'testpet', 'dog', '2019-01-01'
);