USE db_java2_survey;

DROP TABLE IF EXISTS t_user_role;

CREATE TABLE t_user_role (
  user_role_id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_user_role (role,user_id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES t_user (user_id));
  
INSERT INTO t_user_role(user_id, role)
	VALUES (
		(SELECT user_id FROM t_user WHERE username='admin'),
		"ROLE_ADMIN");
