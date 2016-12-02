USE db_java2_survey;

DROP TABLE IF EXISTS t_user;

CREATE TABLE t_user (
	user_id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL,
	password VARCHAR(60) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
	enabled BIT NOT NULL DEFAULT 1,
	PRIMARY KEY (user_id));
											
-- Password (hashed with bcrypt): admin
INSERT INTO t_user(username,password) VALUES ('admin','$2a$06$23Y8jncKstzkm7SjZNnT5OvLVVIuOY5DUvxZAe.ZpJUJwSgXjGrbe');
