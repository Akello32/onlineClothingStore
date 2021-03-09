	INSERT INTO `users` (
	`identity`,
	`login`,
	`password`,
	`role`
) VALUES (
	1,
	"admin",
	"5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5", 
	0
), (
	2,
	"manager",
	"5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5", 
	1
), (
	3,
	"user",
	"5994471abb01	112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5",
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

INSERT INTO `sizes` 
(`identity`, `name`, `clothesId`, `number`)
VALUES
(1, 		   "XL",  		1, 		   14),
(2, 		   "L",  		1, 		   19),
(3, 		   "L",			2,		   23),
(4, 		   "S",			2,		    3),
(5, 		   "XXL",		2,		   43),
(6, 		   "M",			3,		   10),
(7, 		   "XXL",		3,		    8),
(8,			   "XS",		4, 		   16),
(9,			   "M",			5,			4),
(10,		   "L",			6,			0),
(11,		   "S",			6,		   10),
(12,		   "XL",		7,		   51),
(13,		   "XXL",		7,		   38),
(14,		   "S",			8,		   41),
(15,	       "L",			8,		    3),
(16,	       "XL",		8,		   34),
(17,     	   "XXL",		8,		   14),
(18,		   "L",			9,			2),
(19,		   "XS",		10,			9),
(20,		   "XS",		11,			6),
(21,		   "XS",		12,		   34);


INSERT INTO `clothes` 
(`identity`, `price`, `color`, `typeID`, `brandID`,	`gender`,	    `image`, 												  		   `name`, 						  `description`)
VALUES
(1, 		   65,	   "Red",	 1,		   1,  		"women",		"61omgfjytkl._ac_ux466_.jpg",								   	"Heavy Blend",    	  	  		"Худи Gildan Heavy Blend, 50% Хлопок / 50% Полиэстер"),
(2, 		   120,	   "Black",  8,		   3,   	"men",			"modular-jacket-winter-black-rinsed-4962-1.jpg", 		   	   	"Modular Jacket (Winter)", 		"Куртка Carhartt Modular Jacket. Тёплая подкладка, 65% Полиэстер 35% Хлопок"),
(3, 		   250,	   "Black",	 2,		   2,   	"men",			"edwin-i022482_01_99-st-02-500x500-c0c-removebg-preview.png",  	"ED-55 Regular Tapered", 		"Джинсы ED-55 Regular Tapered, классический дизайн. Оригинальный Японский деним. 100% Хлопок"),
(4, 		   245,	   "Blue",	 2,		   2,   	"men",			"i026609_01_kr-st-01-removebg-preview.png",						"ED-45 Loose Tapered",    		"Джинсы EDWIN ED-45 Loose Tapered. Классический дизайн. Оригинальный Японский деним. 100% Хлопок"),
(5, 		   100,	   "Gray", 	 9,		   5,   	"men",			"Stussy Solid Canvas.png",					   		   			"Solid Canvas Work Jacket",     "Куртка из канваса. Манжеты на рукавах и внизу. 100% Хлопок"),
(6, 		   95,	   "Brown",  3,		   9,   	"men",			"quiksilver-light-brooks-removebg-preview.png",	   				"QUIKSILVER LIGHT BROOKS",      "КУРТКА QUIKSILVER, Водонепроницаемость благодаря специальной обработке, состав: 100% хлопок"),
(7, 		   89.10,  "Green",  3,		   4,   	"men",			"image-removebg-preview.png",					   		   		"DC HAZLEY",   			      	"КОЛЛЕКЦИЯ ВЕСНА 2018, 100% ХЛОПОК,"),
(8, 		   50.13,  "Blue", 	 4,		   5,   	"men",			"stussy-t-shirt-blue-removebg-preview.png",	   					"Basic Tee",   					"Футболка Stussy Basic Tee, Маленькое лого на груди,  Большое лого на спине, состав: 100% хлопок"),
(9, 		   125,	   "Pink", 	 1,		   3,   	"women",		"image-removebg-preview(3).png",	   							"WIP W' Hooded Carhartt Sweatshirt",    "Женское худи Carhartt WIP W' Hooded Carhartt Sweatshirt, Маленькое лого на груди, 100% хлопок"),
(10, 		   13,	   "Blue", 	 2,		   3,  		"women",		"women-denim.png",	 											"W' Page Carrot Ankle Pant",    "Женские джинсы Carhartt W' Page Carrot Ankle Pant"),
(11, 		   87,	   "Beige",  5,		   8,   	"men",			"image-removebg-preview(1).png",	   							"BOX FLANNEL",  			    "РУБАШКА VANS КОЛЛЕКЦИЯ ОСЕНЬ 2020, состав: 100% ХЛОПОК"),
(12, 		   57,	   "Red", 	 5,		   8,   	"men",			"image-removebg-preview(2).png",	 							"BOX FLANNEL",    				"РУБАШКА VANS КОЛЛЕКЦИЯ ОСЕНЬ 2020, состав: 100% ХЛОПОК");

UPDATE `brands` SET `name` = "Stussy" WHERE `identity` = 5;

UPDATE `clothes` SET `price` = 125, `numbers` = 110, `size` = "S", `color` = "Pink", `typeID` = 1, `brandID` = 5, 
`image` = "image-removebg-preview(3).png", `name` = "WIP W' Hooded Carhartt Sweatshirt",
`description` = "Женское худи Carhartt WIP W' Hooded Carhartt Sweatshirt, Маленькое лого на груди, 100% хлопок" WHERE `identity` = 15;
