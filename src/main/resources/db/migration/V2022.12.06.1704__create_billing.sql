DROP TABLE IF EXISTS `billing`;
CREATE TABLE `billing`
(
    `id` int NOT NULL,
    `user_id` int NOT NULL,
    `call_count` int NOT NULL,
    `block_count` int NOT NULL,
    created_date datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    updated_date datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    PRIMARY KEY (`id`)
);