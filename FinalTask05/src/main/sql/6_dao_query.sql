# UserDaoImpl
# Create
INSERT INTO users (login, name, password, role) VALUES ('user_test', 'testName', MD5('passwordtest'), 1);
# Read
SELECT login, password, role FROM users WHERE id = 1;
# Update
UPDATE users SET login = 't@t.com', password = 'updatetest', role = 1 WHERE id = 3;
# Delete
DELETE FROM users WHERE id = 5;
# Read for log in
SELECT id, role FROM users WHERE login = 'user_test' AND password = MD5('passwordtest');

# BlackListDaoImpl
# Create
INSERT INTO blacklist (user_id, login) VALUES (3, 't@t.com');
# Update
UPDATE blacklist SET login = 't1@t1.com' WHERE 	user_id = 3;
# Delete
DELETE FROM blacklist WHERE user_id = 4;
# Read all with pagination
SELECT user_id, login FROM blacklist LIMIT 1 OFFSET 1;

# CategoryDaoImpl
# Create
INSERT INTO categories (name, image_path, question) VALUES ('road', 'categories/categorytest.png', 'Is there any road on this image?');
# Update
UPDATE categories SET name = 'fogTest', image_path = 'categories/testfogimage.bmp', question = 'Is test works?' WHERE id = 2;
# Read
SELECT name, image_path, question FROM categories WHERE id = 4;
# Delete
DELETE FROM categories WHERE id = 1;

# FavoriteDaoImpl
# Create
INSERT INTO favorites (user_id, category_id) VALUES (2, 4);
# Read
SELECT category_id FROM favorites WHERE user_id = 4;
# No need for update query in this Dao
# Delete
DELETE FROM favorites WHERE user_id = 5 AND category_id = 2;

# ImageDaoImpl
# Images unrecognized by user in category
SELECT id, path
FROM images
WHERE category_id = 2
AND id NOT IN
(
	SELECT im.id
	FROM images AS im
	JOIN recognizedimgs AS re
	ON im.id = re.image_id
	WHERE im.category_id = 2
	AND re.user_id = 2);
