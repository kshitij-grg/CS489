-- CS489 Lab5a - ADS Dental Surgery Database Script
-- MySQL 8.x / localhost

-- Safety: recreate the database for a clean run
DROP DATABASE IF EXISTS ads_dental_surgery_db;
CREATE DATABASE ads_dental_surgery_db;
USE ads_dental_surgery_db;

-- -----------------------------------------------------
-- Core tables
-- -----------------------------------------------------

CREATE TABLE dentists (
    dentist_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    contact_phone VARCHAR(30) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    specialization VARCHAR(120) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    contact_phone VARCHAR(30) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    mailing_address VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE surgeries (
    surgery_id INT AUTO_INCREMENT PRIMARY KEY,
    surgery_name VARCHAR(120) NOT NULL,
    location_address VARCHAR(255) NOT NULL,
    telephone VARCHAR(30) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE appointments (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    dentist_id INT NOT NULL,
    surgery_id INT NOT NULL,
    appointment_datetime DATETIME NOT NULL,
    status ENUM('BOOKED', 'CANCELLED', 'COMPLETED', 'RESCHEDULED') NOT NULL DEFAULT 'BOOKED',
    notes VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_appointment_patient FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    CONSTRAINT fk_appointment_dentist FOREIGN KEY (dentist_id) REFERENCES dentists(dentist_id),
    CONSTRAINT fk_appointment_surgery FOREIGN KEY (surgery_id) REFERENCES surgeries(surgery_id),
    UNIQUE KEY uq_dentist_datetime (dentist_id, appointment_datetime),
    UNIQUE KEY uq_patient_datetime (patient_id, appointment_datetime)
);

CREATE TABLE bills (
    bill_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    appointment_id INT,
    amount DECIMAL(10,2) NOT NULL,
    is_paid BOOLEAN NOT NULL DEFAULT FALSE,
    billed_on DATE NOT NULL,
    paid_on DATE,
    CONSTRAINT fk_bill_patient FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    CONSTRAINT fk_bill_appointment FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id)
);

CREATE INDEX idx_dentists_last_name ON dentists(last_name);
CREATE INDEX idx_appointments_dentist_date ON appointments(dentist_id, appointment_datetime);
CREATE INDEX idx_appointments_patient_date ON appointments(patient_id, appointment_datetime);

-- -----------------------------------------------------
-- Business rules
-- 1) A dentist cannot have more than 5 appointments in any given week
-- 2) A patient with unpaid bills cannot request a new appointment
-- -----------------------------------------------------

DELIMITER $$

CREATE TRIGGER trg_appointments_before_insert
BEFORE INSERT ON appointments
FOR EACH ROW
BEGIN
    DECLARE weekly_count INT DEFAULT 0;
    DECLARE unpaid_count INT DEFAULT 0;

    SELECT COUNT(*)
      INTO weekly_count
      FROM appointments a
     WHERE a.dentist_id = NEW.dentist_id
       AND YEARWEEK(a.appointment_datetime, 1) = YEARWEEK(NEW.appointment_datetime, 1)
       AND a.status IN ('BOOKED', 'RESCHEDULED', 'COMPLETED');

    IF weekly_count >= 5 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Rule violation: Dentist cannot have more than 5 appointments in a week.';
    END IF;

    SELECT COUNT(*)
      INTO unpaid_count
      FROM bills b
     WHERE b.patient_id = NEW.patient_id
       AND b.is_paid = FALSE;

    IF unpaid_count > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Rule violation: Patient has an outstanding unpaid bill.';
    END IF;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- Dummy data
-- -----------------------------------------------------

INSERT INTO dentists (first_name, last_name, contact_phone, email, specialization) VALUES
('Tony', 'Smith', '515-100-0001', 'tony.smith@ads.com', 'Orthodontics'),
('Helen', 'Pearson', '515-100-0002', 'helen.pearson@ads.com', 'Endodontics'),
('Robin', 'Plevin', '515-100-0003', 'robin.plevin@ads.com', 'General Dentistry');

INSERT INTO patients (patient_id, first_name, last_name, contact_phone, email, mailing_address, date_of_birth) VALUES
(100, 'Gillian', 'White', '515-200-0100', 'gillian.white@email.com', '100 Sample St, Des Moines, IA', '1985-02-10'),
(105, 'Jill', 'Bell', '515-200-0105', 'jill.bell@email.com', '105 Sample St, Ames, IA', '1990-07-18'),
(108, 'Ian', 'MacKay', '515-200-0108', 'ian.mackay@email.com', '108 Sample St, Cedar Rapids, IA', '1988-11-22'),
(110, 'John', 'Walker', '515-200-0110', 'john.walker@email.com', '110 Sample St, Waterloo, IA', '1992-04-06');

INSERT INTO surgeries (surgery_id, surgery_name, location_address, telephone) VALUES
(10, 'S10', '10 Main St, Des Moines, IA', '515-300-0010'),
(13, 'S13', '13 Main St, Ames, IA', '515-300-0013'),
(15, 'S15', '15 Main St, West Des Moines, IA', '515-300-0015');

INSERT INTO appointments (patient_id, dentist_id, surgery_id, appointment_datetime, status, notes) VALUES
(100, 1, 15, '2013-09-12 10:00:00', 'BOOKED', 'Sample appointment'),
(105, 1, 15, '2013-09-12 12:00:00', 'BOOKED', 'Sample appointment'),
(108, 2, 10, '2013-09-12 10:00:00', 'BOOKED', 'Sample appointment'),
(108, 2, 10, '2013-09-14 14:00:00', 'BOOKED', 'Sample appointment'),
(105, 3, 15, '2013-09-14 16:30:00', 'BOOKED', 'Sample appointment'),
(110, 3, 13, '2013-09-15 18:00:00', 'BOOKED', 'Sample appointment');

-- Add one unpaid bill (for testing business rule #2)
INSERT INTO bills (patient_id, appointment_id, amount, is_paid, billed_on, paid_on) VALUES
(105, 2, 180.00, FALSE, '2013-09-13', NULL),
(100, 1, 90.00, TRUE, '2013-09-12', '2013-09-13');

-- -----------------------------------------------------
-- Required queries for submission screenshots
-- -----------------------------------------------------

-- Q1: Display ALL Dentists sorted by lastName ascending
SELECT dentist_id, first_name, last_name, contact_phone, email, specialization
FROM dentists
ORDER BY last_name ASC, first_name ASC;

-- Q2: Display ALL Appointments for a given Dentist by dentist_Id, including Patient information
-- Change the value as needed
SET @dentist_id = 1;
SELECT
    a.appointment_id,
    a.appointment_datetime,
    a.status,
    d.dentist_id,
    d.first_name AS dentist_first_name,
    d.last_name AS dentist_last_name,
    p.patient_id,
    p.first_name AS patient_first_name,
    p.last_name AS patient_last_name,
    p.contact_phone,
    p.email
FROM appointments a
JOIN dentists d ON d.dentist_id = a.dentist_id
JOIN patients p ON p.patient_id = a.patient_id
WHERE a.dentist_id = @dentist_id
ORDER BY a.appointment_datetime;

-- Q3: Display ALL Appointments that have been scheduled at a Surgery Location
SELECT
    a.appointment_id,
    a.appointment_datetime,
    a.status,
    s.surgery_id,
    s.surgery_name,
    s.location_address,
    d.first_name AS dentist_first_name,
    d.last_name AS dentist_last_name,
    p.first_name AS patient_first_name,
    p.last_name AS patient_last_name
FROM appointments a
JOIN surgeries s ON s.surgery_id = a.surgery_id
JOIN dentists d ON d.dentist_id = a.dentist_id
JOIN patients p ON p.patient_id = a.patient_id
ORDER BY s.surgery_name, a.appointment_datetime;

-- Q4: Display Appointments booked for a given Patient on a given Date
-- Change values as needed
SET @patient_id = 100;
SET @appt_date = '2013-09-12';
SELECT
    a.appointment_id,
    a.appointment_datetime,
    a.status,
    p.patient_id,
    p.first_name AS patient_first_name,
    p.last_name AS patient_last_name,
    d.first_name AS dentist_first_name,
    d.last_name AS dentist_last_name,
    s.surgery_name,
    s.location_address
FROM appointments a
JOIN patients p ON p.patient_id = a.patient_id
JOIN dentists d ON d.dentist_id = a.dentist_id
JOIN surgeries s ON s.surgery_id = a.surgery_id
WHERE a.patient_id = @patient_id
  AND DATE(a.appointment_datetime) = @appt_date
ORDER BY a.appointment_datetime;
