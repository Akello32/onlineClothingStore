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
(5,			   "Stussy"),
(6,			   "Element"),
(7,			   "Dickies"),
(8,			   "Vans"),
(9,			   "QuickSilver"),
(10,		   "Etnies");

INSERT INTO `clothes` 
(`identity`, `price`, `numbers`,	`size`, `color`, `typeID`, `brandID`,	`gender`,	 `image`, 												  		`name`, 				`description`)
VALUES
(1, 		   65,		20,			"XL",	"Red",	 	1,		  1,  		"women",		"61omgfjytkl._ac_ux466_.jpg",								   "Heavy Blend",    	  	  "Худи Gildan Heavy Blend, 50% Хлопок / 50% Полиэстер"),
(2, 		   245,		14,			"L",	"Blue", 	2,		  2,   		"men",			"i026609_01_kr-st-01-removebg-preview.png",	   			   "ED-45 Loose Tapered",     "Джинсы EDWIN ED-45 Loose Tapered. Классический дизайн. Оригинальный Японский деним. 100% Хлопок"),	  
(3, 		   120,		2,			"M",	"Black",	8,		  3,   		"men",			"modular-jacket-winter-black-rinsed-4962-1.jpg", 		   "Modular Jacket (Winter)", "Куртка Carhartt Modular Jacket. Тёплая подкладка, 65% Полиэстер 35% Хлопок"),
(4, 		   250,		56,			"XXL",	"Black",	2,		  2,   		"men",			"edwin-i022482_01_99-st-02-500x500-c0c-removebg-preview.png", "ED-55 Regular Tapered", "Джинсы ED-55 Regular Tapered, классический дизайн. Оригинальный Японский деним. 100% Хлопок"),
(5, 		   245,		33,			"XL",	"Blue",	 	2,		  2,   		"men",			"i026609_01_kr-st-01-removebg-preview.png",					"ED-45 Loose Tapered",    "Джинсы EDWIN ED-45 Loose Tapered. Классический дизайн. Оригинальный Японский деним. 100% Хлопок"),
(6, 		   245,		10,			"S",	"Blue", 	2,		  2,   		"men",			"i026609_01_kr-st-01-removebg-preview.png", 	   				"ED-45 Loose Tapered",    "Джинсы EDWIN ED-45 Loose Tapered. Классический дизайн. Оригинальный Японский деним. 100% Хлопок"),	 
(7, 		   120,		10,			"M",	"Gray",	 	8,		  3,   		"men",			"modular-jacket-winter-black-rinsed-4962-1.jpg",  			"Modular Jacket (Winter)", "Куртка Carhartt Modular Jacket. Тёплая подкладка, 65% Полиэстер 35% Хлопок"),
(8, 		   120,		13,			"L",	"Gray",	 	8,		  3,   		"men",			"modular-jacket-winter-black-rinsed-4962-1.jpg",  			"Modular Jacket (Winter)", "Куртка Carhartt Modular Jacket. Тёплая подкладка, 65% Полиэстер 35% Хлопок"),
(9, 		   100,		5,			"L",	"Gray", 	9,		  5,   		"men",			"Stussy Solid Canvas.png",					   		   		"Solid Canvas Work Jacket",    "Куртка из канваса. Манжеты на рукавах и внизу. 100% Хлопок"),
(10, 		   100,		13,			"M",	"Gray", 	9,		  5,   		"men",			"Stussy Solid Canvas.png",					   		   		"Solid Canvas Work Jacket",    "Куртка из канваса. Манжеты на рукавах и внизу. 100% Хлопок"),
(11, 		   95,		11,			"XL",	"Brown", 	3,		  9,   		"men",			"quiksilver-light-brooks-removebg-preview.png",	   			"QUIKSILVER LIGHT BROOKS",    "КУРТКА QUIKSILVER, Водонепроницаемость благодаря специальной обработке, состав: 100% хлопок"),
(12, 		   87,		26,			"XXL",	"Brown", 	3,		  9,   		"men",			"quiksilver-light-brooks-removebg-preview.png",	 			"QUIKSILVER LIGHT BROOKS",    "КУРТКА QUIKSILVER, Водонепроницаемость благодаря специальной обработке, состав: 100% хлопок"),
(13, 		   89.10,	13,			"M",	"Green", 	3,		  4,   		"men",			"image-removebg-preview.png",					   		   	"DC HAZLEY",   			      "КОЛЛЕКЦИЯ ВЕСНА 2018, 100% ХЛОПОК,"),
(14, 		   50.13,	110,		"S",	"Blue", 	4,		  5,   		"men",			"stussy-t-shirt-blue-removebg-preview.png",	   			"Basic Tee",   					 "Футболка Stussy Basic Tee, Маленькое лого на груди,  Большое лого на спине, состав: 100% хлопок"),
(15, 		   50.13,	23,			"L",	"Blue", 	4,		  3,   		"men",			"stussy-t-shirt-blue-removebg-preview.png",	 			"Basic Tee",   					 "Футболка Stussy Basic Tee, Маленькое лого на груди,  Большое лого на спине, состав: 100% хлопок"),
(16, 		   125,		110,		"S",	"Pink", 	1,		  5,   		"women",			"image-removebg-preview(3).png",	   					"WIP W' Hooded Carhartt Sweatshirt",    "Женское худи Carhartt WIP W' Hooded Carhartt Sweatshirt, Маленькое лого на груди, 100% хлопок"),
(17, 		   23,		23,			"L",	"Blue", 	2,		  3,   		"women",			"women-denim.png",	 									"W' Page Carrot Ankle Pant",    "Женские джинсы Carhartt W' Page Carrot Ankle Pant"),
(18, 		   13,		23,			"M",	"Blue", 	2,		  3,   		"women",			"women-denim.png",	 									"W' Page Carrot Ankle Pant",    "Женские джинсы Carhartt W' Page Carrot Ankle Pant"),
(19, 		   87,		110,		"XL",	"Beige", 	5,		  8,   		"men",			"image-removebg-preview(1).png",	   					"BOX FLANNEL",  				  "РУБАШКА VANS КОЛЛЕКЦИЯ ОСЕНЬ 2020, состав: 100% ХЛОПОК"),
(20, 		   57,		45,			"L",	"Red", 		5,		  8,   		"men",			"image-removebg-preview(2).png",	 					"BOX FLANNEL",    				"РУБАШКА VANS КОЛЛЕКЦИЯ ОСЕНЬ 2020, состав: 100% ХЛОПОК");


(15, 		   50.13,	23,			"L",	"Blue", 	4,		  3,   		"men",			"stussy-t-shirt-blue-removebg-preview.png",	 			"Basic Tee",   					 "Футболка Stussy Basic Tee, Маленькое лого на груди,  Большое лого на спине, состав: 100% хлопок"),
(16, 		   125,		110,		"S",	"Pink", 	1,		  5,   		"women",			"image-removebg-preview(3).png",	   					"WIP W' Hooded Carhartt Sweatshirt",    "Женское худи Carhartt WIP W' Hooded Carhartt Sweatshirt, Маленькое лого на груди, 100% хлопок"),

UPDATE `brands` SET `name` = "Stussy" WHERE `identity` = 5;

UPDATE `clothes` SET `price` = 125, `numbers` = 110, `size` = "S", `color` = "Pink", `typeID` = 1, `brandID` = 5, 
`image` = "image-removebg-preview(3).png", `name` = "WIP W' Hooded Carhartt Sweatshirt",
`description` = "Женское худи Carhartt WIP W' Hooded Carhartt Sweatshirt, Маленькое лого на груди, 100% хлопок" WHERE `identity` = 15;
