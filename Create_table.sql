DROP TABLE IF EXISTS Tours;

CREATE TABLE Tours (
        Name VARCHAR(50) PRIMARY KEY,
        Description TEXT,
        SeatsAvailable INT,
        Date DATE,
        Duration CHAR(5),
        Rating FLOAT,
	NumberOfRatings INT,
	Price INT
);

INSERT INTO Tours(
	Name,Description,SeatsAvailable,Date,Duration,Rating,NumberOfRatings,Price)
	VALUES(
	'SnowMobile Adventure','We ride snowmobiles up on Vatnajökull',16,'2016-06-22','10hrs',4.5,55,24000);