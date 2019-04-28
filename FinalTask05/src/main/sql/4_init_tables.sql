INSERT INTO `users` (
	`id`,
	`login`,
	`name`,
	`password`,
	`role`
) VALUES (
	1,
	"admin",
	"AdminName",
	MD5("admin"),
	0
);