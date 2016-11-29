USE db_java2_survey;

DROP TABLE IF EXISTS t_student;

CREATE TABLE t_student(id BIGINT PRIMARY KEY AUTO_INCREMENT,
											name VARCHAR(255),
											email VARCHAR(255),
											subject_id INT)