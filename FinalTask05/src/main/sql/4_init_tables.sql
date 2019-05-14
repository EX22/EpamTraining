INSERT INTO `users` (
	`id`,
	`login`,
	`name`,
	`password`,
	`role`
) VALUES (
	1,
	"admin@admin.com",
	"AdminName",
	MD5("admin"),
	0
);

INSERT INTO `settings`
(`settings_name`, `settings_value`) VALUES
('fileSize', '8 Mb'),
('fileExtensions', 'jpg, png, bmp');