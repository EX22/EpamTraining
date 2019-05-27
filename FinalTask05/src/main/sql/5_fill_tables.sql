
INSERT INTO `users`
(`id`,`login`,`name`,`level`,`password`,`photo_path`,`role`) VALUES
(2, 'user2@b.com', 'Bruce', 0, MD5('user2pass'), 'avatars/Bruce.jpg',1),
(3, 'user3@c.com', 'Robert', 0, MD5('user3pass'), 'avatars/Robert.jpg', 1),
(4, 'user4@d.com', 'Brad', 0, MD5('user4pass'), 'avatars/Brad.jpg', 1),
(5, 'user5@e.com', 'Julce', 0, MD5('user5pass'), 'avatars/Julce.jpg', 1),
(6, 'user6@f.com', 'Sasha', 0, MD5('user6pass'), 'avatars/Sasha.jpg', 1),
(7, 'user7@g.com', 'Salvador', 0, MD5('user7pass'), 'avatars/Salvador.jpg', 1),
(8, 'user8@h.com', 'Kevin', 0, MD5('user8pass'), 'avatars/Kevin.jpg', 1),
(9, 'user9@i.com', 'Donald', 0, MD5('user9pass'), 'avatars/Donald.jpg', 1),
(10, 'user10@k.com', 'Butch', 0, MD5('user10pass'), '', 1),
(11, 'user11@l.com', 'Ocean', 0, MD5('user11pass'), '', 1),
(12, 'user12@m.com', 'Marcelace', 0, MD5('user12pass'), '', 1),
(13, 'user13@o.com', 'Archibald', 0, MD5('user13pass'), 'avatars/Archibald.jpg', 1),
(14, 'user14@p.com', 'Jackie', 0, MD5('user14pass'), 'avatars/Jackie.jpg', 1);

INSERT INTO `categories`
(`id`, `name`, `image_path`, `question`) VALUES
(1, 'waterfalls', 'categories/category1.jpg', 'Is there any waterfall on this image?'),
(2, 'fog', 'categories/category2.jpg', 'Is there any fog on this image?'),
(3, 'forest', 'categories/category3.jpg', 'Is there any forest on this image?'),
(4, 'sculpture', 'categories/category4.jpg', 'Is there any sculpture on this image?'),
(5, 'map', 'categories/category5.jpg', 'Is there any map on this image?');

INSERT INTO `images`
(`id`, `user_id`, `path`, `category_id`) VALUES
(1, 2,  'imagesdir/wfone.jpg', 1),
(2, 3, 'imagesdir/wftwo.jpg', 1),
(3, 3, 'imagesdir/wfthree.jpg', 1),
(4, 3, 'imagesdir/wffour.jpg', 1),
(5, 2,'imagesdir/fogfive.jpg', 2),
(6, 3, 'imagesdir/fogsix.jpg', 2),
(7, 2, 'imagesdir/fogseven.jpg', 2),
(8, 3, 'imagesdir/fogeight.jpg', 2),
(9, 2, 'imagesdir/frstnine.jpg', 3),
(10, 4, 'imagesdir/frstten.jpg', 3),
(11, 4, 'imagesdir/frsteleven.jpg', 3),
(12, 4, 'imagesdir/sctwelve.jpg', 4),
(13, 5, 'imagesdir/scthirteen.jpg', 4),
(14, 5, 'imagesdir/scfourteen.jpg', 4),
(15, 5, 'imagesdir/scfifteen.jpg', 4),
(16, 5, 'imagesdir/scsixteen.jpg', 4),
(17, 2, 'imagesdir/frstone.jpg', 3),
(18, 2, 'imagesdir/frsttwo.jpg', 3),
(19, 4, 'imagesdir/wffive.jpg', 1),
(20, 4, 'imagesdir/scseventeen.jpg', 4),
(21, 4, 'imagesdir/frstthree.jpg', 3),
(22, 5, 'imagesdir/fognine.jpg', 2),
(23, 2, 'imagesdir/wfsix.jpg', 1),
(24, 2, 'imagesdir/wfeigth.jpg', 1),
(25, 2, 'imagesdir/wfnine.jpg', 1),
(26, 2, 'imagesdir/wften.jpg', 1),
(27, 3, 'imagesdir/wfeleven.jpg', 1),
(28, 2, 'imagesdir/wfsix.jpg', 1),
(29, 4, 'imagesdir/scone.jpg', 4),
(30, 4, 'imagesdir/sctwo.jpg', 4),
(31, 4, 'imagesdir/scthree.jpg', 4),
(32, 4, 'imagesdir/scfour.jpg', 4),
(33, 4, 'imagesdir/scfive.jpg', 4),
(34, 4, 'imagesdir/scsix.jpg', 4),
(35, 4, 'imagesdir/scseven.jpg', 4),
(36, 7, 'imagesdir/sceight.jpg', 4),
(37, 7, 'imagesdir/scnine.jpg', 4),
(38, 7, 'imagesdir/scten.jpg', 4),
(39, 7, 'imagesdir/sceleven.jpg', 4),
(40, 3, 'imagesdir/frstfour.jpg', 3),
(41, 3, 'imagesdir/frstfive.jpg', 3),
(42, 3, 'imagesdir/frstsix.jpg', 3),
(43, 3, 'imagesdir/frstseven.jpg', 3),
(44, 3, 'imagesdir/frsteight.jpg', 3),
(45, 2, 'imagesdir/frsttwelve.jpg', 3),
(46, 2, 'imagesdir/frstthirteen.jpg', 3),
(47, 2, 'imagesdir/frstfourteen.jpg', 3),
(48, 3, 'imagesdir/fogone.jpg', 2),
(49, 3, 'imagesdir/fogtwo.jpg', 2),
(50, 3, 'imagesdir/fogthree.jpg', 2),
(51, 3, 'imagesdir/fogfour.jpg', 2),
(52, 3, 'imagesdir/fogten.jpg', 2),
(53, 3, 'imagesdir/fogeleven.jpg', 2),
(54, 3, 'imagesdir/fogtwelve.jpg', 2),
(55, 2, 'imagesdir/fogfourteen.jpg', 2),
(56, 2, 'imagesdir/fogfifteen.jpg', 2),
(57, 2, 'imagesdir/fogsixteen.jpg', 2),
(58, 7, 'imagesdir/mapone.jpg', 5),
(59, 7, 'imagesdir/maptwo.jpg', 5),
(60, 7, 'imagesdir/mapthree.jpg', 5),
(61, 7, 'imagesdir/mapfour.jpg', 5),
(62, 7, 'imagesdir/mapfive.jpg', 5),
(63, 7, 'imagesdir/mapsix.jpg', 5),
(64, 7, 'imagesdir/mapseven.jpg', 5),
(65, 7, 'imagesdir/mapeight.jpg', 5),
(66, 7, 'imagesdir/mapnine.jpg', 5),
(67, 7, 'imagesdir/mapten.jpg', 5),
(68, 7, 'imagesdir/mapeleven.jpg', 5),
(69, 7, 'imagesdir/maptwelve.jpg', 5);


INSERT INTO `favorites`
(`user_id`, `category_id`) VALUES
(2, 3),
(3, 1),
(4, 4),
(5, 2),
(6, 2),
(7, 5);

INSERT INTO `blacklist`
(`user_id`, `login`) VALUES
(4, 'user4@d.com'),
(6, 'user6@f.com');

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
