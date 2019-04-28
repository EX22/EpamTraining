INSERT INTO `settings`
(`settings_name`, `settings_value`) VALUES
('File size', '8 Mb'),
('File extension', 'jpeg, png, bmp');

INSERT INTO `users`
(`id`,`login`,`name`,`level`,`password`,`photo_path`,`role`) VALUES
(2, 'a@a.com', 'user1', 0, MD5('mypass'), '/avatars/user1.jpg',1),
(3, 'b@b.com', 'user2', 0, MD5('user2pass'), '/avatars/user2.jpg', 1),
(4, 'c@c.com', 'user3', 0, MD5('user3pass'), '/avatars/user3.jpg', 1),
(5, 'd@d.com', 'user4', 0, MD5('user4pass'), '/avatars/user4.jpg', 1),
(6, 'e@e.com', 'user5', 0, MD5('user5pass'), '/avatars/user5.jpg', 1),
(7, 'f@f.com', 'user5', 0, MD5('user5pass'), '/avatars/user5.jpg', 1);

INSERT INTO `categories`
(`id`, `name`, `image_path`, `question`) VALUES
(1, 'waterfalls', '/categories/category1.jpg', 'Is there any waterfall on this image?'),
(2, 'fog', '/categories/category2.jpg', 'Is there any fog on this image?'),
(3, 'forest', '/categories/category3.jpg', 'Is there any forest on this image?'),
(4, 'sculpture', '/categories/category4.jpg', 'Is there any sculpture on this image?');

INSERT INTO `images`
(`id`, `path`, `category_id`) VALUES
(1, '/imagesDir/imageOne.jpg', 1),
(2, '/imagesDir/imageTwo.jpg', 1),
(3, '/imagesDir/imageThree.jpg', 1),
(4, '/imagesDir/imageFour.jpg', 1),
(5, '/imagesDir/imageFive.jpg', 2),
(6, '/imagesDir/imageSix.jpg', 2),
(7, '/imagesDir/imageSeven.jpg', 2),
(8, '/imagesDir/imageEight.jpg', 2),
(9, '/imagesDir/imageNine.jpg', 3),
(10, '/imagesDir/imageTen.jpg', 3),
(11, '/imagesDir/imageEleven.jpg', 3),
(12, '/imagesDir/imageTwelve.jpg', 4),
(13, '/imagesDir/imageThirteen.jpg', 4),
(14, '/imagesDir/imageFourteen.jpg', 4);

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
(10, 2, 'yes'),
(11, 2, 'no'),
(5, 5, 'no'),
(6, 5, 'no'),
(7, 5, 'yes'),
(8, 5, 'yes'),
(1, 7, 'yes'),
(2, 7, 'yes'),
(3, 7, 'no'),
(4, 7, 'yes');
