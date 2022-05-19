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

