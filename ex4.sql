-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 04, 2022 at 07:22 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `ex4`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
    (48);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
                           `id` bigint(20) NOT NULL,
                           `discount` double NOT NULL,
                           `image` varchar(255) DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `price` double NOT NULL,
                           `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `discount`, `image`, `name`, `price`, `quantity`) VALUES
                                                                                   (3, 18, 'https://d3m9l0v76dty0.cloudfront.net/system/photos/5893967/large/7cc41c28cb1dcd0eae2c70bba272b43d.jpg', 'tanach', 20, 0),
                                                                                   (5, 10, '/images/default_product_cover_image.jpg', 'mishna', 60.9, 64),
                                                                                   (6, 13, '/images/default_product_cover_image.jpg', 'talmud bavli', 45, 70),
                                                                                   (7, 15, '/images/default_product_cover_image.jpg', 'midrash rabh', 66, 61),
                                                                                   (8, 0, '/images/default_product_cover_image.jpg', 'shulchn aruch', 95, 89),
                                                                                   (9, 30, 'https://d3m9l0v76dty0.cloudfront.net/system/photos/5895832/original/45e89d40e1601578f1a4e84c2b838a0f.jpg', 'hazoahr', 26, 64),
                                                                                   (10, 8.5, '/images/default_product_cover_image.jpg', 'baal shm tov', 35, 49),
                                                                                   (18, 3, '/images/default_product_cover_image.jpg', 'darky moshe', 100, 0),
                                                                                   (24, 0, '/images/default_product_cover_image.jpg', 'arbha turim', 300, 9),
                                                                                   (34, 0, '/images/default_product_cover_image.jpg', 'beit yosef', 200, 29);

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

CREATE TABLE `purchase` (
                            `id` bigint(20) NOT NULL,
                            `amount` double NOT NULL,
                            `timestamp` datetime(6) DEFAULT NULL,
                            `purchaser_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `purchase`
--

INSERT INTO `purchase` (`id`, `amount`, `timestamp`, `purchaser_name`) VALUES
                                                                           (1, 46.9, '2022-07-27 23:41:25.000000', ''),
                                                                           (2, 106.5, '2022-07-28 00:53:25.000000', ''),
                                                                           (3, 50.4, '2022-07-28 02:02:42.000000', ''),
                                                                           (4, 44.8, '2022-07-28 02:16:32.000000', ''),
                                                                           (5, 83.94999999999999, '2022-07-28 02:29:17.000000', ''),
                                                                           (6, 44.8, '2022-07-28 04:04:03.000000', ''),
                                                                           (11, 100.9, '2022-07-31 10:54:11.000000', ''),
                                                                           (12, 54.81, '2022-07-31 13:53:10.000000', ''),
                                                                           (13, 5.6, '2022-07-31 21:59:29.000000', ''),
                                                                           (14, 100.9, '2022-07-24 12:12:57.000000', ''),
                                                                           (15, 44.8, '2022-07-24 14:59:33.000000', ''),
                                                                           (17, 5.6, '2022-07-31 23:41:40.000000', ''),
                                                                           (21, 44.8, '2022-08-01 18:48:20.000000', ''),
                                                                           (22, 56.1, '2022-08-01 18:49:09.000000', ''),
                                                                           (23, 95.25, '2022-08-01 18:56:04.000000', ''),
                                                                           (25, 95.25, '2022-08-01 19:39:14.000000', 'user1'),
                                                                           (26, 56.1, '2022-08-01 19:44:40.000000', 'user3'),
                                                                           (31, 300, '2022-08-01 21:29:20.000000', 'user2'),
                                                                           (32, 43.12, '2022-08-01 22:43:52.000000', 'user1'),
                                                                           (33, 95.25, '2022-08-01 23:24:08.000000', 'user3'),
                                                                           (35, 50.224999999999994, '2022-08-02 00:31:20.000000', 'user3'),
                                                                           (36, 168.26, '2022-08-02 00:42:23.000000', 'user2'),
                                                                           (37, 224.35999999999999, '2022-08-02 00:47:41.000000', 'user2'),
                                                                           (38, 224.35999999999999, '2022-08-02 00:50:55.000000', 'user2'),
                                                                           (40, 113.44999999999999, '2022-08-02 13:23:55.000000', 'user1'),
                                                                           (43, 218.2, '2022-08-02 13:38:31.000000', 'user1'),
                                                                           (45, 168.26, '2022-08-02 14:22:16.000000', 'user2');

-- --------------------------------------------------------

--
-- Table structure for table `spring_session`
--

CREATE TABLE `spring_session` (
                                  `PRIMARY_ID` char(36) NOT NULL,
                                  `SESSION_ID` char(36) NOT NULL,
                                  `CREATION_TIME` bigint(20) NOT NULL,
                                  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
                                  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
                                  `EXPIRY_TIME` bigint(20) NOT NULL,
                                  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `spring_session_attributes`
--

CREATE TABLE `spring_session_attributes` (
                                             `SESSION_PRIMARY_ID` char(36) NOT NULL,
                                             `ATTRIBUTE_NAME` varchar(200) NOT NULL,
                                             `ATTRIBUTE_BYTES` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `purchase`
--
ALTER TABLE `purchase`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `spring_session`
--
ALTER TABLE `spring_session`
    ADD PRIMARY KEY (`PRIMARY_ID`),
  ADD UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  ADD KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  ADD KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`);

--
-- Indexes for table `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
    ADD PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
    ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE;
COMMIT;
