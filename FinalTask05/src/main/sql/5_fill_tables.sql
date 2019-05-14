
INSERT INTO `users`
(`id`,`login`,`name`,`level`,`password`,`photo_path`,`role`) VALUES
(2, 'user2@a.com', 'Bruce', 0, MD5('user2pass'), 'avatars/Bruce.jpg',1),
(3, 'user3@b.com', 'Robert', 0, MD5('user3pass'), 'avatars/Robert.jpg', 1),
(4, 'user4@c.com', 'Brad', 0, MD5('user4pass'), 'avatars/Brad.jpg', 1),
(5, 'user5@d.com', 'Julce', 0, MD5('user5pass'), 'avatars/Julce.jpg', 1),
(6, 'user6@e.com', 'Sasha', 0, MD5('user6pass'), 'avatars/Sasha.jpg', 1),
(7, 'user7@f.com', 'Salvador', 0, MD5('user7pass'), 'avatars/Salvador.jpg', 1),
(8, 'user8@g.com', 'Kevin', 0, MD5('user8pass'), 'avatars/Kevin.jpg', 1),
(9, 'user9@h.com', 'Donald', 0, MD5('user9pass'), 'avatars/Donald.jpg', 1),
(10, 'user10@i.com', 'Butch', 0, MD5('user10pass'), '', 1),
(11, 'user11@k.com', 'Ocean', 0, MD5('user11pass'), '', 1),
(12, 'user12@l.com', 'Marcelace', 0, MD5('user12pass'), '', 1),
(13, 'user13@m.com', 'Archibald', 0, MD5('user13pass'), 'avatars/Archibald.jpg', 1),
(14, 'user14@n.com', 'Jackie', 0, MD5('user14pass'), 'avatars/Jackie.jpg', 1);

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
(20, 4, 'imagesdir/sculpture/scseventeen.jpg', 4),
(21, 4, 'imagesdir/forest/frstthree.jpg', 3),
(22, 5, 'imagesdir/fog/fognine.jpg', 2),
(23, 2, 'imagesdir/waterfalls/wfsix.jpg', 1),
(24, 2, 'imagesdir/waterfalls/wfeigth.jpg', 1),
(25, 2, 'imagesdir/waterfalls/wfnine.jpg', 1),
(26, 2, 'imagesdir/waterfalls/wften.jpg', 1),
(27, 3, 'imagesdir/waterfalls/wfeleven.jpg', 1),
(28, 2, 'imagesdir/waterfalls/wfsix.jpg', 1),
(29, 4, 'imagesdir/sculpture/scone.jpg', 4),
(30, 4, 'imagesdir/sculpture/sctwo.jpg', 4),
(31, 4, 'imagesdir/sculpture/scthree.jpg', 4),
(32, 4, 'imagesdir/sculpture/scfour.jpg', 4),
(33, 4, 'imagesdir/sculpture/scfive.jpg', 4),
(34, 4, 'imagesdir/sculpture/scsix.jpg', 4),
(35, 4, 'imagesdir/sculpture/scseven.jpg', 4),
(36, 7, 'imagesdir/sculpture/sceight.jpg', 4),
(37, 7, 'imagesdir/sculpture/scnine.jpg', 4),
(38, 7, 'imagesdir/sculpture/scten.jpg', 4),
(39, 7, 'imagesdir/sculpture/sceleven.jpg', 4),
(40, 3, 'imagesdir/forest/frstfour.jpg', 3),
(41, 3, 'imagesdir/forest/frstfive.jpg', 3),
(42, 3, 'imagesdir/forest/frstsix.jpg', 3),
(43, 3, 'imagesdir/forest/frstseven.jpg', 3),
(44, 3, 'imagesdir/forest/frsteight.jpg', 3),
(45, 2, 'imagesdir/forest/frsttwelve.jpg', 3),
(46, 2, 'imagesdir/forest/frstthirteen.jpg', 3),
(47, 2, 'imagesdir/forest/frstfourteen.jpg', 3),
(48, 3, 'imagesdir/fog/fogone.jpg', 2),
(49, 3, 'imagesdir/fog/fogtwo.jpg', 2),
(50, 3, 'imagesdir/fog/fogthree.jpg', 2),
(51, 3, 'imagesdir/fog/fogfour.jpg', 2),
(52, 3, 'imagesdir/fog/fogten.jpg', 2),
(53, 3, 'imagesdir/fog/fogeleven.jpg', 2),
(54, 3, 'imagesdir/fog/fogtwelve.jpg', 2),
(55, 2, 'imagesdir/fog/fogfourteen.jpg', 2),
(56, 2, 'imagesdir/fog/fogfifteen.jpg', 2),
(57, 2, 'imagesdir/fog/fogsixteen.jpg', 2);



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
