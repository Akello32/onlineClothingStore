INSERT INTO `users` (
	`identity`,
	`login`,
	`password`,
	`role`
) VALUES (
	2,
	"user1",
	"EE11CBB19052E40B07AAC0CA060C23EE", 
	1
), (
	3,
	"user2",
	"EE11CBB19052E40B07AAC0CA060C23EE",
	2
);

INSERT INTO `clothes_type` 
(`identity`, `name`)
VALUES
(1, 		   "HOODIES"),
(2, 		   "DENIM"),
(3, 		   "WINDBREAKER"),
(4, 		   "T-SHIRT"),
(5, 		   "SHIRT"),
(6, 		   "PANTS"),
(7, 		   "SHORTS"),
(8, 		   "WINTER JACKET"),
(9, 		   "JACKET");

UPDATE `clothes_type` SET `name` = "JACKET" WHERE `identity` = 9;

INSERT INTO `brands` 
(`identity`, `name`)
VALUES
(1, 		   "Gildan"),
(2, 		   "Edwin"),
(3, 		   "Carhartt"),
(4,			   "DC"),
(5,			   "GLOBE"),
(6,			   "Element"),
(7,			   "Dickies"),
(8,			   "Vans"),
(9,			   "QuickSilver"),
(10,		   "Etnies");

INSERT INTO `clothes` 
(`identity`, `price`, `numbers`, `size`, `color`, `typeID`, `brandID`, `image`)
VALUES
(1, 		   12,		3,		   	"XL",	"Grey",	 	1,		  1,  "61omgfjytkl._ac_ux466_.jpg"),
(2, 		   34,		1,	 		"L",	"Black", 	2,		  2,   "i026609_01_kr-st-01-removebg-preview.png"),
(3, 		   43,		7,  		"M",	"Red",	 	3,		  3,   "modular-jacket-winter-black-rinsed-4962-1.jpg"),
(4, 		   19,		4,		   	"L",	"Grey",	 	1,		  1,	"modular-jacket-winter-black-rinsed-4962-1.png"),
(5, 		   17,		6,		   	"XL",	"Grey",	 	1,		  1,  "61omgfjytkl._ac_ux466_.jpg"),
(6, 		   33,		2,	 		"L",	"Black", 	2,		  2,   "i026609_01_kr-st-01-removebg-preview.png"),
(7, 		   49,		6,  		"M",	"Red",	 	3,		  3,   "modular-jacket-winter-black-rinsed-4962-1.jpg"),
(8, 		   43,		1,		   	"L",	"Gray",	 	1,		  1,	"modular-jacket-winter-black-rinsed-4962-1.jpg");

UPDATE `clothes` SET `price` = 50, `numbers` = 7, `size` = "M", `color` = "Black", `typeID` = 3, `brandID` = 3, `image` = "modular-jacket-winter-black-rinsed-4962-1.jpg" WHERE `identity` = 3;

