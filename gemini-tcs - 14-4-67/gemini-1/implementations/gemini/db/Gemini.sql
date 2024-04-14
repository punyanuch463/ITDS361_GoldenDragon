create database Gemini ;
use Gemini ;
CREATE TABLE users (
    username VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);


CREATE TABLE science_plans (
    id INT PRIMARY KEY AUTO_INCREMENT,
    plan_no INT ,
    creator VARCHAR(255),
    submitter VARCHAR(255),
    funding_inusd double,
    objectives TEXT,
    star_system VARCHAR(255),
    start_date Date,
    end_date Date,
    telescope_location VARCHAR(255),
    status VARCHAR(50)
);

CREATE TABLE observing_program (
    id INT AUTO_INCREMENT PRIMARY KEY,
    calibration_unit VARCHAR(255),
    f_stop DOUBLE,
    gemini_location VARCHAR(255),
    light_type VARCHAR(255),
    module_content INT,
    optics_primary VARCHAR(255),
    optics_secondaryrms INT,
    plan_no INT,
    science_fold_mirror_degree INT,
    science_fold_mirror_type VARCHAR(255),
    validation_status INT
);
DELETE FROM users ;
select * from astronomer ;
select * from science_observer ;
INSERT INTO science_plans (plan_no, creator, submitter, funding_inusd, objectives, star_system, start_Date, end_Date, telescope_location, status)
VALUES (1, 'John Doe', 'John Doe', 1000.0, '1. To explore Neptune. 2. To collect astronomical data for future research.', 'Andromeda', '2021-04-15 09:00:00', '2021-04-15 10:00:00', 'HAWAII', 'SAVED'),
       (2, 'Jane Dunn', 'Andrew Griffin', 2500.0, '1. To explore Mars. 2. To collect astronomical data for future research.', 'Antlia', '2021-05-15 13:00:00', '2021-05-15 15:00:00', 'CHILE', 'SAVED');
INSERT INTO science_plans (plan_no, creator, submitter, funding_inusd, objectives, star_system, start_Date, end_Date, telescope_location, status)
VALUES (5, 'John Doe', 'John Doe', 1000.0, '1. To explore Neptune. 2. To collect astronomical data for future research.', 'Andromeda', '2024-04-12 09:00:00', '2024-04-15 10:00:00', 'HAWAII', 'TESTED');
       
       INSERT INTO science_plans (plan_no, creator, submitter, funding_inusd, objectives, star_system, start_Date, end_Date, telescope_location, status)
VALUES (4, 'astro03', 'astro09', 1800.0, '1. To explore earth. 2. To collect astronomical data for future research.', 'Andromeda', '2024-04-11 09:00:00', '2024-04-13 10:00:00', 'CHILE', 'TESTED');
SET SQL_SAFE_UPDATES = 0;


INSERT INTO observingprogram (id, calibration_unit, f_stop, gemini_location, light_type, module_content, optics_primary, optics_secondaryrms, plan_no, science_fold_mirror_degree, science_fold_mirror_type, validation_status) VALUES
('1', 'Xenon', '5.5', NULL, 'MaunaKeaSkyEmission', '2', 'GSZ', '12', '2', '0', 'REFLECTIVE_CONVERGING_BEAM', '0'),
('2', 'Argon', '5.5', 'S', 'MaunaKeaSkyEmission', '1', 'GSZ', '12', '2', '0', 'REFLECTIVE_CONVERGING_BEAM', '0');



DELETE FROM science_plans WHERE plan_no="3" and creator="astro03";
DELETE FROM users WHERE username = "sciobser01";
select * from observing_program ;
select * from science_plans ;
select * from data_proc_requirements ;
drop table observing_program ;
select * from users ;
drop table science_plans ;
ALTER TABLE data_proc_requirements DROP FOREIGN KEY FKc0lhsh4pv5rieoxr2p9ua1e5f;