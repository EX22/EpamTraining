INSERT INTO `settings`
(`settings_name`, `settings_value`) VALUES
('File size', '8 Mb'),
('File extension', 'jpg, png, bmp');

INSERT INTO `users`
(`id`,`login`,`name`,`level`,`password`,`photo_path`,`role`) VALUES
(2, 'a@a.com', 'Bruce', 0, MD5('mypass'), 'avatars/Bruce.jpg',1),
(3, 'b@b.com', 'Robert', 0, MD5('user2pass'), 'avatars/Robert.jpg', 1),
(4, 'c@c.com', 'Brad', 0, MD5('user3pass'), 'avatars/Brad.jpg', 1),
(5, 'd@d.com', 'Julce', 0, MD5('user4pass'), 'avatars/Julce.jpg', 1),
(6, 'e@e.com', 'Sasha', 0, MD5('user5pass'), 'avatars/Sasha.jpg', 1),
(7, 'f@f.com', 'Salvador', 0, MD5('user6pass'), 'avatars/Salvador.jpg', 1);

INSERT INTO `categories`
(`id`, `name`, `image_path`, `question`) VALUES
(1, 'waterfalls', 'categories/category1.jpg', 'Is there any waterfall on this image?'),
(2, 'fog', 'categories/category2.jpg', 'Is there any fog on this image?'),
(3, 'forest', 'categories/category3.jpg', 'Is there any forest on this image?'),
(4, 'sculpture', 'categories/category4.jpg', 'Is there any sculpture on this image?');

INSERT INTO `images`
(`id`, `user_id`, `path`, `category_id`) VALUES
(1, 2,  'imagesdir/waterfalls/wfone.jpg', 1),
(2, 3, 'imagesdir/waterfalls/wftwo.jpg', 1),
(3, 3, 'imagesdir/waterfalls/wfthree.jpg', 1),
(4, 3, 'imagesdir/waterfalls/wffour.jpg', 1),
(5, 2,'imagesdir/fog/fogfive.jpg', 2),
(6, 3, 'imagesdir/fog/fogsix.jpg', 2),
(7, 2, 'imagesdir/fog/fogseven.jpg', 2),
(8, 3, 'imagesdir/fog/fogeight.jpg', 2),
(9, 2, 'imagesdir/forest/frstnine.jpg', 3),
(10, 4, 'imagesdir/forest/frstten.jpg', 3),
(11, 4, 'imagesdir/forest/frsteleven.jpg', 3),
(12, 4, 'imagesdir/sculpture/sctwelve.jpg', 4),
(13, 5, 'imagesdir/sculpture/scthirteen.jpg', 4),
(14, 5, 'imagesdir/sculpture/scfourteen.jpg', 4),
(15, 5, 'imagesdir/sculpture/scfifteen.jpg', 4),
(16, 5, 'imagesdir/sculpture/scsixteen.jpg', 4),
(17, 2, 'imagesdir/forest/frstone.jpg', 3),
(18, 2, 'imagesdir/forest/frsttwo.jpg', 3),
(19, 4, 'imagesdir/waterfalls/wffive.jpg', 1),
(20, 4, 'imagesdir/sculpture/scone.jpg', 4),
(21, 4, 'imagesdir/sculpture/sctwo.jpg', 4),
(22, 5, 'imagesdir/fog/fognine.jpg', 2);

INSERT INTO `favorites`
(`user_id`, `category_id`) VALUES
(2, 3),
(3, 1),
(4, 4),
(5, 2),
(6, 2),
(7, 1);

INSERT INTO `blacklist`
(`user_id`, `login`) VALUES
(4, 'c@c.com'),
(6, 'e@e.com');

INSERT INTO `recognizedimgs`
(`image_id`, `user_id`, `answer`) VALUES
(9, 2, 'yes'),
(10, 4, 'yes'),
(11, 4, 'yes'),
(5, 2, 'yes'),
(6, 3, 'yes'),
(7, 2, 'no'),
(8, 3, 'yes'),
(1, 2, 'yes'),
(2, 3, 'yes'),
(3, 3, 'yes'),
(4, 3, 'no');
