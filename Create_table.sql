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

INSERT INTO Tours(
	Name,Description,SeatsAvailable,Date,Duration,Rating,NumberOfRatings,Price,Destination,Departure,Type)
	VALUES(
	'Vikings of Thingvellir','We will be walking through Thingvellir where we will see remnants
of the oldest parliament in the world. This is where the Vikings
of Iceland decided laws and passed judgements for crimes.

This is a must see for any Vikings tv-show fan.',30,'2016-06-24',8,3.7,14,12000,'Thingvellir','Reykjavik','Adventure');

INSERT INTO Tours(
	Name,Description,SeatsAvailable,Date,Duration,Rating,NumberOfRatings,Price,Destination,Departure,Type)
	VALUES(
	'Geysirs gone wild','We take a bus to see Geysir the famous Geyser which all Geysers are named after.
Bring a rain coat because we are going in!',23,'2016-07-12',10,4.6,32,15000,'Geysir','Reykjavik','Golden Circle');


INSERT INTO Tours(
	Name,Description,SeatsAvailable,Date,Duration,Rating,NumberOfRatings,Price,Destination,Departure,Type)
	VALUES(
	'Whale killahs!','This isnt your regular whale watching tour. Oh no.. 
First we find them, and then we watch them play for a while, and then..
WE KILL THEM!
We will be gunning for whales on our badass whale killing seaMonster (our ship)
and when we have caught a whale, we eat it.. raw..

Afterwards we will have a kids festival on the peer where we will eat marshmallows.'
	,14,'2016-08-06',14,2.2,43,32000,'The Ocean','Husavik','Adventure');

INSERT INTO Tours(
	Name,Description,SeatsAvailable,Date,Duration,Rating,NumberOfRatings,Price,Destination,Departure,Type)
	VALUES(
	'Sky on Fire ','Come with us to see the magnificence of the night sky in Iceland.
We will be riding a bus into the wilderness until we see some spectacular sights in the sky,
in which case we will step out and eat some sheeps heads together.

Disclaimer: The likelihood of finding any northern lights is slim to none. We therefore do
not guarantee finding any.',26,'2017-02-12',14,3.6,201,23000,'Depends on weather','Reykjavik','Northern Lights');

