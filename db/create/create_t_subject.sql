USE db_java2_survey;

DROP TABLE IF EXISTS t_subject;

CREATE TABLE t_subject(
    subject_id   INT PRIMARY KEY,
    subject_name VARCHAR(255)
);

INSERT INTO t_subject(subject_id,subject_name) VALUES (1, "Nagyvállalati rendszerek");
INSERT INTO t_subject(subject_id,subject_name) VALUES (2, "Általánosan a J2EE-ről, alkalmazásszerverekről");
INSERT INTO t_subject(subject_id,subject_name) VALUES (3, "Adatbáziskezelés az Enterprise Javaban");
INSERT INTO t_subject(subject_id,subject_name) VALUES (4, "Szolgáltatások");
INSERT INTO t_subject(subject_id,subject_name) VALUES (5, "Spring és UI keretrendszerek");
INSERT INTO t_subject(subject_id,subject_name) VALUES (6, "Security, jogosultságkezelés és verziókezelés");
