ALTER TABLE Lab
DROP
FOREIGN KEY Lab_ibfk_1;

ALTER TABLE Reservation
DROP
FOREIGN KEY Reservation_ibfk_1;

ALTER TABLE Reservation
DROP
FOREIGN KEY Reservation_ibfk_2;

ALTER TABLE Reservation
DROP
FOREIGN KEY Reservation_ibfk_3;

CREATE TABLE course
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255) NOT NULL,
    stu_number INT          NOT NULL,
    study_hour INT          NOT NULL,
    class      VARCHAR(255) NOT NULL,
    teacher_id BIGINT       NOT NULL,
    CONSTRAINT pk_course PRIMARY KEY (id)
);

ALTER TABLE lab
    ADD admin_id BIGINT NULL;

ALTER TABLE lab
    MODIFY admin_id BIGINT NOT NULL;

ALTER TABLE reservation
    ADD course_id BIGINT NULL;

ALTER TABLE reservation
    ADD create_time datetime NULL;

ALTER TABLE reservation
    ADD lab_id BIGINT NULL;

ALTER TABLE reservation
    ADD period_id INT NULL;

ALTER TABLE reservation
    ADD reserve_date date NULL;

ALTER TABLE reservation
    ADD status VARCHAR(255) NULL;

ALTER TABLE reservation
    ADD update_time datetime NULL;

ALTER TABLE reservation
    ADD week_day INT NULL;

ALTER TABLE reservation
    ADD week_number INT NULL;

ALTER TABLE reservation
    MODIFY course_id BIGINT NOT NULL;

ALTER TABLE reservation
    MODIFY create_time datetime NOT NULL;

ALTER TABLE `period`
    ADD end_time time NULL;

ALTER TABLE `period`
    ADD start_time time NULL;

ALTER TABLE `period`
    MODIFY end_time time NOT NULL;

ALTER TABLE reservation
    MODIFY lab_id BIGINT NOT NULL;

ALTER TABLE reservation
    MODIFY period_id INT NOT NULL;

ALTER TABLE reservation
    MODIFY reserve_date date NOT NULL;

ALTER TABLE `period`
    MODIFY start_time time NOT NULL;

ALTER TABLE reservation
    MODIFY update_time datetime NOT NULL;

ALTER TABLE reservation
    MODIFY week_day INT NOT NULL;

ALTER TABLE reservation
    MODIFY week_number INT NOT NULL;

ALTER TABLE reservation
    ADD CONSTRAINT uc_5a2119dca0e32ee277c41351b UNIQUE (week_number, week_day);

ALTER TABLE lab
    ADD CONSTRAINT uc_lab_name UNIQUE (name);

ALTER TABLE user
    ADD CONSTRAINT uc_user_username UNIQUE (username);

ALTER TABLE course
    ADD CONSTRAINT FK_COURSE_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES user (id);

ALTER TABLE lab
    ADD CONSTRAINT FK_LAB_ON_ADMIN FOREIGN KEY (admin_id) REFERENCES user (id);

ALTER TABLE reservation
    ADD CONSTRAINT FK_RESERVATION_ON_COURSE FOREIGN KEY (course_id) REFERENCES course (id);

ALTER TABLE reservation
    ADD CONSTRAINT FK_RESERVATION_ON_LAB FOREIGN KEY (lab_id) REFERENCES lab (id);

ALTER TABLE reservation
    ADD CONSTRAINT FK_RESERVATION_ON_PERIOD FOREIGN KEY (period_id) REFERENCES `period` (id);

ALTER TABLE Lab
DROP
COLUMN adminId;

ALTER TABLE Reservation
DROP
COLUMN createTime;

ALTER TABLE Reservation
DROP
COLUMN labId;

ALTER TABLE Reservation
DROP
COLUMN periodId;

ALTER TABLE Reservation
DROP
COLUMN reservationDate;

ALTER TABLE Reservation
DROP
COLUMN state;

ALTER TABLE Reservation
DROP
COLUMN updateTime;

ALTER TABLE Reservation
DROP
COLUMN userId;

ALTER TABLE Reservation
DROP
COLUMN weekDay;

ALTER TABLE Reservation
DROP
COLUMN weekNumber;

ALTER TABLE `Period`
DROP
COLUMN endTime;

ALTER TABLE `Period`
DROP
COLUMN startTime;

ALTER TABLE lab
    MODIFY `description` VARCHAR (255) NULL;

ALTER TABLE lab
DROP
COLUMN id;

ALTER TABLE lab
    ADD id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY;

ALTER TABLE lab
    MODIFY id BIGINT AUTO_INCREMENT;

ALTER TABLE `period`
    MODIFY id INT AUTO_INCREMENT;

ALTER TABLE reservation
DROP
COLUMN id;

ALTER TABLE reservation
    ADD id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY;

ALTER TABLE reservation
    MODIFY id BIGINT AUTO_INCREMENT;

ALTER TABLE user
DROP
COLUMN id;

ALTER TABLE user
    ADD id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY;

ALTER TABLE user
    MODIFY id BIGINT AUTO_INCREMENT;