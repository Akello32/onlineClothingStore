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
(1, 		   "hoodies"),
(2, 		   "denim"),
(3, 		   "windbreaker"),
(4, 		   "T-shirt");

INSERT INTO `brands` 
(`identity`, `name`)
VALUES
(1, 		   "Gildan"),
(2, 		   "Edwin"),
(3, 		   "Carhartt");

INSERT INTO `clothes` 
(`identity`, `price`, `numbers`, `size`, `color`, `typeID`, `brandID`, `image`)
VALUES
(1, 		   12,		3,		   	"XL",	"Grey",	 	1,		  1, 	"image"),	
(2, 		   34,		1,	 		"L",	"Black", 	2,		  2, 	"image"),
(3, 		   43,		7,  		"M",	"Red",	 	3,		  3,	"image"),
(4, 		   19,		4,		   	"L",	"Gray",	 	1,		  1,	"image");




