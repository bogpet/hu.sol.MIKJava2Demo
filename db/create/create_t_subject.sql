USE db_java2_survey;

DROP TABLE IF EXISTS t_subject;

CREATE TABLE t_subject(subject_id INT PRIMARY KEY,
											subject_name VARCHAR(255));
											
INSERT INTO t_subject(subject_id,subject_name) VALUES (1,"Nagyvállalati rendszerek, Java elhelyezkedése");
INSERT INTO t_subject(subject_id,subject_name) VALUES (2,"Általánosan a J2EE-ről, alkalmazásszerverek");
INSERT INTO t_subject(subject_id,subject_name) VALUES (3,"Adatbáziskezelés az Enterprise Javaban");
INSERT INTO t_subject(subject_id,subject_name) VALUES (4,"Spring, mint multifunkcionális keretrendszer");

INSERT INTO t_subject(subject_id,subject_name) VALUES (5,"Security, és jogosultságkezelés");
INSERT INTO t_subject(subject_id,subject_name) VALUES (6,"Vaadin, és a UI keretrendszerek létjogosultsága");
INSERT INTO t_subject(subject_id,subject_name) VALUES (7,"Rest szolgáltatások, SOA architektúra, Bootstrap, mint responsive kliens oldali keretrendszer");