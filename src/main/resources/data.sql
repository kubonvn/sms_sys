-- create table phone_info
CREATE TABLE `phone_info` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(50),
  `password` varchar(50),
  `email` varchar(100),
  `number_phone` varchar(16),
  `auth` varchar(255),
  `client_id` varchar(255),
  `created_by` varchar(50),
  `updated_by` varchar(50),
  `created_date` datetime,
  `updated_date` datetime
);
-- permission
insert into permission
values (1, now(),now(), now(), now(), 'ALL');
insert into permission
values (2, now(),now(), now(), now(), 'NORMAL');
-- role
insert into role
values (1, 'kubon',now(), 'kubon',now(), 'ADMIN'),
       (2, 'kubon',now(), 'kubon', now(), 'USER');
-- role permission
insert into role_permission
values (1,1),(2,2);

