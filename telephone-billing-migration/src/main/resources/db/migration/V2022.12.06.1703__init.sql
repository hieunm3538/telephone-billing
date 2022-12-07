DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id` int NOT NULL,
    `username` varchar(32) NOT NULL,
    created_date datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    updated_date datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    PRIMARY KEY (`id`)
);