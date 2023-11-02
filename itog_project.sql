-- Database: bd_for_itog_proekt_ycheb_praktik

-- DROP DATABASE IF EXISTS bd_for_itog_proekt_ycheb_praktik;

CREATE DATABASE bd_for_itog_proekt_ycheb_praktik
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
	
	Select * from Employe
	SELECT e.Login, p.Name_post as roles FROM Employe e 
                JOIN Post_Employe pe ON e.ID_Employe = pe.Employe_ID
                JOIN Post p ON pe.Post_ID = p.ID_Post
                WHERE e.Login = 'nikita_2045'
	
CREATE TABLE Employe (
    ID_Employe SERIAL PRIMARY KEY,
    SOLI VARCHAR(50) NOT NULL,
    Name VARCHAR(50) NOT NULL,
    Last_name VARCHAR(50) NOT NULL,
    Middle_name VARCHAR(50),
    Date_of_employment DATE NOT NULL,
    Login VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(50) NOT NULL,
    Phone_number VARCHAR(17) NOT NULL UNIQUE CHECK (Phone_number ~ '^[+][7][(][0-9][0-9][0-9][)][0-9][0-9][0-9][-][0-9][0-9][-][0-9][0-9]$'),
    CONSTRAINT CHK_Password_Upper CHECK (Password ~ '[A-Z]'),
    CONSTRAINT CHK_Password_Lower CHECK (Password ~ '[a-z]'),
    CONSTRAINT CHK_Password_Number CHECK (Password ~ '[0-9]'),
    CONSTRAINT CHK_Password_Length CHECK (LENGTH(Password) >= 5),
    CONSTRAINT CHK_Login_Length CHECK (LENGTH(Login) >= 5)
);

CREATE TABLE Education (
    ID_Education SERIAL PRIMARY KEY,
    Type_of_education VARCHAR(50) NOT NULL,
    Specialization VARCHAR(50) NOT NULL,
    Institution VARCHAR(50) NOT NULL
);

CREATE TABLE Education_employe (
    ID_Education_employe SERIAL PRIMARY KEY,
    Education_ID INT NOT NULL,
    Employe_ID INT NOT NULL,
    CONSTRAINT FK_Employe_Education_employe FOREIGN KEY (Employe_ID) REFERENCES Employe (ID_Employe),
    CONSTRAINT FK_Education_education_employe FOREIGN KEY (Education_ID) REFERENCES Education (ID_Education)
);

CREATE TABLE Department (
    ID_Department SERIAL PRIMARY KEY,
    Name_departament VARCHAR(50) NOT NULL UNIQUE
);


CREATE TABLE Post (
    ID_Post SERIAL PRIMARY KEY,
    Name_post VARCHAR(50) NOT NULL UNIQUE,
    Department_ID INT NOT NULL,
    CONSTRAINT FK_Post_department FOREIGN KEY (Department_ID) REFERENCES Department (ID_Department)
);

select * from Post_Employe

CREATE TABLE Post_Employe (
    ID_Post_employe SERIAL PRIMARY KEY,
    Post_ID INT NOT NULL,
    Employe_ID INT NOT NULL,
    CONSTRAINT FK_Post_post_employe FOREIGN KEY (Post_ID) REFERENCES Post (ID_Post),
    CONSTRAINT FK_Employe_post_employe FOREIGN KEY (Employe_ID) REFERENCES Employe (ID_Employe)
);

CREATE TABLE Stake (
    ID_Stake SERIAL PRIMARY KEY,
    Count_days_worked INT NOT NULL,
    Payment_per_day FLOAT NOT NULL,
    Date_of_salary DATE NOT NULL,
    Payment_per_shift FLOAT NOT NULL
);

CREATE TABLE Sick (
    ID_Sick SERIAL PRIMARY KEY,
    Ill BOOLEAN NOT NULL,
    Sick_for_days INT NOT NULL
);

CREATE TABLE Sallary (
    ID_Sallary SERIAL PRIMARY KEY,
    Post_Employe_ID INT NOT NULL,
    Sick_ID INT NOT NULL,
    Stake_ID INT NOT NULL,
    CONSTRAINT FK_Sick_sallary FOREIGN KEY (Sick_ID) REFERENCES Sick (ID_Sick),
    CONSTRAINT FK_Stake_sallary FOREIGN KEY (Stake_ID) REFERENCES Stake (ID_Stake),
    CONSTRAINT FK_Post_Employe_sallary FOREIGN KEY (Post_Employe_ID) REFERENCES Post_Employe (ID_Post_employe)
);

CREATE TABLE Type_day (
    ID_Type_day SERIAL PRIMARY KEY,
    Type_of_day VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Work_schedule (
    ID_Work_schedule SERIAL PRIMARY KEY,
    Day DATE NOT NULL,
    Time_start_working_day TIME NOT NULL,
    Time_end_working_day TIME NOT NULL,
    Full_time BOOLEAN NOT NULL,
    Available_places INT NOT NULL,
    break INT NOT NULL,
    Type_day_ID INT NOT NULL,
    CONSTRAINT FK_Type_day_work_schedule FOREIGN KEY (Type_day_ID) REFERENCES Type_day (ID_Type_day)
);

CREATE TABLE Work_schedule_Employe (
    ID_Work_schedule_employe SERIAL PRIMARY KEY,
    Work_schedule_ID INT NOT NULL,
    Employe_ID INT NOT NULL,
    CONSTRAINT FK_Work_schedule_work_schedule_Employe FOREIGN KEY (Work_schedule_ID) REFERENCES Work_schedule (ID_Work_schedule),
    CONSTRAINT FK_Employe_work_schedule FOREIGN KEY (Employe_ID) REFERENCES Employe (ID_Employe)
);

CREATE TABLE Tasks (
    ID_Tasks SERIAL PRIMARY KEY,
    Topic VARCHAR(50) NOT NULL,
    Description VARCHAR(50) NOT NULL,
    Complexity VARCHAR(50) NOT NULL,
    Date_of_issue DATE NOT NULL,
    Date_of_end DATE NOT NULL
);

CREATE TABLE History_tasks (
    ID_History_tasks SERIAL PRIMARY KEY,
    Сompleted BOOLEAN NOT NULL,
    On_time BOOLEAN NOT NULL,
    Tasks_ID INT NOT NULL,
    CONSTRAINT FK_Tasks_history_tasks FOREIGN KEY (Tasks_ID) REFERENCES Tasks (ID_Tasks)
);

CREATE TABLE Employe_Tasks (
    ID_Employe_tasks SERIAL PRIMARY KEY,
    Tasks_ID INT NOT NULL,
    Employe_ID INT NOT NULL,
    CONSTRAINT FK_Tasks_employe_tasks FOREIGN KEY (Tasks_ID) REFERENCES Tasks (ID_Tasks),
    CONSTRAINT FK_Employe_employe_tasks FOREIGN KEY (Employe_ID) REFERENCES Employe (ID_Employe)
);