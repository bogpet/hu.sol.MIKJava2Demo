USE db_java2_survey;

DROP TABLE IF EXISTS t_subject;

CREATE TABLE t_subject(subject_id INT,
											name VARCHAR(255));
											
INSERT INTO t_subject(id,name) VALUES (1,"Spring");
INSERT INTO t_subject(id,name) VALUES (2,"Spring");
INSERT INTO t_subject(id,name) VALUES (3,"Spring");
INSERT INTO t_subject(id,name) VALUES (4,"Vaadin");