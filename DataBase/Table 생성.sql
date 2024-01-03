USE healthcare_web_backend_database;

drop table Members;
drop table HealthData;
drop table ExerciseInfo;
drop table MedicationsSupplements;
drop table IdPassword;

-- 회원 정보 테이블
CREATE TABLE Members (
    MemberCode VARCHAR(255) PRIMARY KEY,
    Id VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Name VARCHAR(255),
    Age INT,
    Gender VARCHAR(10),
    CONSTRAINT UC_Members_Id UNIQUE (Id) -- 회원 ID는 중복되지 않도록 유니크 제약조건 추가
);

-- 건강 정보 테이블
CREATE TABLE HealthData (
    MemberCode VARCHAR(255) PRIMARY KEY,
    Id VARCHAR(255),
    Height FLOAT,
    Weight FLOAT,
    SmokingFrequency INT,	-- 주간 평균 흡연 횟수
    DrinkingFrequency INT,	-- 주간 평균 음주 횟수
    SleepStartTime TIME,	-- 수면 시작 시간
    SleepEndTime TIME,		-- 기상 시간
    FOREIGN KEY (MemberCode) REFERENCES Members(MemberCode) ON DELETE CASCADE
);

-- 운동 정보 테이블
CREATE TABLE ExerciseInfo (
    ExerciseInfoID INT PRIMARY KEY,
    MemberCode VARCHAR(255),
    ExerciseType VARCHAR(50),
    WeeklyFrequency INT,		-- 주간 운동 빈도
    CaloriesPerSession FLOAT,	-- 회당 소비 칼로리
    WeeklyGoalFrequency INT,	-- 주간 운동 빈도 목표
    FOREIGN KEY (MemberCode) REFERENCES Members(MemberCode) ON DELETE CASCADE
);

-- 운동 기록 테이블
CREATE TABLE ExerciseRecord (
    ExerciseRecordID INT PRIMARY KEY,
    MemberCode VARCHAR(255),
    ExerciseID INT,
    RecordDate DATE,		-- 기록 날짜
    DurationMinutes INT,	-- 운동 시간(분단위)
    CaloriesBurned FLOAT,	-- 소모 칼로리
    FOREIGN KEY (MemberCode) REFERENCES Members(MemberCode) ON DELETE CASCADE
);

-- 복용중인 의약품 및 보충제 정보 테이블
CREATE TABLE MedicationsSupplements (
    MedicationID INT PRIMARY KEY,
    MemberCode VARCHAR(255),
    MedicationType VARCHAR(100),	-- 의약품 및 보충제 정보
    FOREIGN KEY (MemberCode) REFERENCES Members(MemberCode) ON DELETE CASCADE
);

-- IdPassword 테이블
CREATE TABLE IdPassword (
    Id VARCHAR(255) PRIMARY KEY,
    Password VARCHAR(255) NOT NULL
);





SHOW TABLES;
