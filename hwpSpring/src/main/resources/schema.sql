-- Members 테이블
CREATE TABLE Members (
    ID VARCHAR(255) PRIMARY KEY NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(255),
    Name VARCHAR(255),
    Age INT,
    Gender VARCHAR(10)
);

-- ExerciseInfo 테이블
CREATE TABLE ExerciseInfo (
    ExerciseInfoID INT PRIMARY KEY,
    ExerciseType VARCHAR(255),
    ExerciseName VARCHAR(255),
    CaloriesPerMinutes INT
);

-- ExerciseRecord 테이블
CREATE TABLE ExerciseRecord (
    RecordID INT PRIMARY KEY,
    MemberID VARCHAR(255),
    ExerciseInfoID INT,
    RecordDate DATE,
    DurationMinutes INT,
    Weight INT,
    CountPerSets INT,
    Sets INT,
    TotalCalories INT,
    FOREIGN KEY (MemberID) REFERENCES Members(ID),
    FOREIGN KEY (ExerciseInfoID) REFERENCES ExerciseInfo(ExerciseInfoID)
);

-- DietInfo 테이블
CREATE TABLE DietInfo (
    DietInfoID INT PRIMARY KEY,
    DietName VARCHAR(255),
    Calories INT,
    Carbohydrate INT,
    Protein INT,
    Fats INT
);

-- DietRecord 테이블
CREATE TABLE DietRecord (
    DietRecordID INT PRIMARY KEY,
    MemberID VARCHAR(255),
    DietInfoID INT,
    Record DATE,
    TimeOfMeal VARCHAR(255),
    TotalCalories INT,
    FOREIGN KEY (MemberID) REFERENCES Members(ID),
    FOREIGN KEY (DietInfoID) REFERENCES DietInfo(DietInfoID)
);
