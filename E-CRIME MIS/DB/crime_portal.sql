-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 17, 2018 at 04:19 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 5.6.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `crime_portal`
--

-- --------------------------------------------------------

--
-- Table structure for table `head`
--

CREATE TABLE `head` (
  `h_id` varchar(50) NOT NULL,
  `h_pass` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `head`
--
/*
INSERT INTO `head` (`h_id`, `h_pass`) VALUES
('admin', 'admin');
*/
-- --------------------------------------------------------

--
-- Table structure for table `police`
--

CREATE TABLE `police` (
  `p_id` varchar(10) NOT NULL,
  `p_name` varchar(30) NOT NULL,
  `p_pass` varchar(30) NOT NULL,
  `spec` enum("Murder","All","Robbery") NOT NULL,
  `location` varchar(30) NOT NULL,
  primary key(`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `police`
--
/*
INSERT INTO `police` (`p_name`, `p_id`, `spec`, `location`, `p_pass`) VALUES
('Manish Singh', 'a101', 'Murder', 'Anandapur', 'manish'),
('Jay Singh', 'a102', 'All', 'Anandapur', 'jay'),
('Suvendu Ghosh', 't101', 'Robbery', 'Tollygunge', 'suvendu');
*/

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `u_id` varchar(30) NOT NULL,
  `u_name` varchar(30) NOT NULL,
  `u_pass` varchar(15) NOT NULL,
  `u_addr` varchar(30) NOT NULL,
  `gen` enum("MALE","FEMALE") NOT NULL,
  primary key(`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--
/*
INSERT INTO `user` (`u_name`, `u_id`, `u_pass`, `u_addr`, `a_no`, `gen`, `mob`) VALUES
('Satyansh Kumar', 'satyansh123@gmail.com', 'satyansh', 'Ranchi', 123214521452, 'Male', 9854123654);
*/

-- --------------------------------------------------------


--
-- Table structure for table `police_station`
--

CREATE TABLE `police_station` (
  `SN` int unsigned not null auto_increment,
  `i_id` varchar(30) NOT NULL,
  `i_pass` varchar(15) NOT NULL,
  `i_name` varchar(30) NOT NULL,
  `location` varchar(30) NOT NULL,
  primary key(`SN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `police_station`
--
/*
INSERT INTO `police_station` (`i_id`, `i_name`, `location`, `i_pass`) VALUES
('shah@anandapur', 'Shahbaz', 'Anandapur', 'shahbaz'),
('shivam@tollygunge', 'Shivam', 'Tollygunge', 'shivam');
*/
-- --------------------------------------------------------

--
-- Table structure for table `complaint`
--

CREATE TABLE `complaint` (
  `c_id` int unsigned NOT NULL auto_increment,
  `u_id` varchar(30) NOT NULL,
  `location` varchar(30) NOT NULL,
  `type_crime` varchar(30) NOT NULL,
  `d_o_c` date NOT NULL,
  `description` tinytext NOT NULL,
  `inc_status` enum("ASSIGNED","UNASSIGNED") null DEFAULT "UNASSIGNED",
  `pol_status` tinytext null DEFAULT 'Null',
  `p_id` varchar(50) DEFAULT 'Null',
  primary key(`c_id`),
  foreign key(`u_id`) references user(`u_id`),
  foreign key(`p_id`) references police(`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `complaint`
--
/*
INSERT INTO `complaint` (`c_id`, `a_no`, `location`, `type_crime`, `d_o_c`, `description`, `inc_status`, `pol_status`, `p_id`) VALUES
(1, 123214521452, 'Tollygunge', 'Robbery', '2018-12-06', 'My Home has been Robbed.', 'Assigned', 'ChargeSheet Filed', 't101');
*/


-- --------------------------------------------------------

--
-- Table structure for table `update_case`
--

CREATE TABLE `update_case` (
  `d_o_u` timestamp NOT NULL,
  `c_id` int unsigned NOT NULL, 
  `case_update` tinytext NOT NULL,
  primary key(`d_o_u`),
  foreign key(`c_id`) references complaint(`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `update_case`
--

/*INSERT INTO `update_case` (`c_id`, `d_o_u`, `case_update`) VALUES
(1, '2018-12-17 10:32:06', 'Criminal Verified'),
(1, '2018-12-17 10:32:12', 'Criminal Caught'),
(1, '2018-12-17 10:32:15', 'Criminal Interrogated'),
(1, '2018-12-17 10:32:21', 'Criminal Accepted the Crime'),
(1, '2018-12-17 10:32:26', 'Criminal Charged'),
(1, '2018-12-17 10:32:51', 'The case has been moved to Court.'),
(1, '2018-12-17 10:32:59', 'Criminal Verified');
*/

--
-- Indexes for dumped tables
--

--
-- Indexes for table `complaint`
--
/*ALTER TABLE `complaint`
  ADD PRIMARY KEY (`c_id`);

--
-- Indexes for table `police`
--
ALTER TABLE `police`
  ADD PRIMARY KEY (`p_id`);

--
-- Indexes for table `police_station`
--
ALTER TABLE `police_station`
  ADD PRIMARY KEY (`i_id`),
  ADD UNIQUE KEY `location` (`location`);

--
-- Indexes for table `update_case`
--
ALTER TABLE `update_case`
  ADD UNIQUE KEY `d_o_u` (`d_o_u`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`a_no`),
  ADD UNIQUE KEY `u_id` (`u_id`),
  ADD UNIQUE KEY `mob` (`mob`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `complaint`
--
ALTER TABLE `complaint`
  MODIFY `c_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

/* Run these statements as well /
alter table complaint add u_id varchar(30) not null after c_id;
alter table complaint add foreign key(u_id) references user(u_id);
alter table complaint add foreign key(p_id) references police(p_id);
alter table update_case add foreign key(c_id) references complaint(c_id);
alter table complaint modify pol_status enum("In process","Charge sheet filed") null;
*/
