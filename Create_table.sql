DROP TABLE IF EXISTS Tours;

CREATE TABLE Tours (
        Name VARCHAR(50) PRIMARY KEY,
        Description TEXT,
        SeatsAvailable INT,
        Date DATE,
        Duration INT,
        Rating FLOAT,
	NumberOfRatings INT,
	Price INT,
	Destination VARCHAR(50),
	Departure VARCHAR(50),
	Type VARCHAR(50)
);

INSERT INTO Tours(
	Name,Description,SeatsAvailable,Date,Duration,Rating,NumberOfRatings,Price,Destination,Departure,Type)
	VALUES(
	'SnowMobile Adventure','We ride snowmobiles up on Vatnajokull',16,'2016-06-22',10,4.5,55,24000,'Vatnajokull','Vik','Adventure');

INSERT INTO Tours(
	Name,Description,SeatsAvailable,Date,Duration,Rating,NumberOfRatings,Price,Destination,Departure,Type)
	VALUES(
	'Silfra scuba diving','We dive into Silfra',8,'2016-07-12',7,4.2,45,17000,'Silfra','Reykjavik','Adventure');