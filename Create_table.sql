DROP TABLE IF EXISTS Tours;

CREATE TABLE Tours (
        Name VARCHAR(50) PRIMARY KEY,
        Description TEXT,
        SeatsAvailable INT,
        Date DATE,
        Duration INT,
        Rating FLOAT,
	NumberOfRatings INT,
	Price INT
);

INSERT INTO Tours(
	Name,Description,SeatsAvailable,Date,Duration,Rating,NumberOfRatings,Price)
	VALUES(
	'SnowMobile Adventure','We ride snowmobiles up on Vatnajökull',16,'2016-06-22',10,4.5,55,24000);

INSERT INTO Tours(
	Name,Description,SeatsAvailable,Date,Duration,Rating,NumberOfRatings,Price)
	VALUES(
	'Silfra scuba diving','We dive into Silfra',8,'2016-07-12',7,4.2,45,17000);