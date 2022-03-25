show databases;
use dms;


create table `manager` (
`mg_id` int not null auto_increment,
`mg_name` varchar(20) not null,
`mg_email` varchar(30) not null unique,
`mg_password` varchar(20) not null,
primary key(`mg_id`)
);
alter table `manager` auto_increment = 100;

create table `branch` (
`br_id` int not null auto_increment,
`br_name` varchar(20) not null,
`br_address` varchar(30) not null,
primary key(`br_id`)
);
alter table `branch` auto_increment = 500;


create table `staff` (
`st_id` int not null auto_increment,
`st_name` varchar(20) not null,
`st_email` varchar(30) not null ,
`st_password` varchar(20) not null,
`st_phone` varchar(14) not null,
`br_id` int not null,
primary key(`st_id`)
);
alter table `staff` auto_increment = 900,
add constraint fk_staffbr_id_branchid foreign key(`br_id`) references `branch`(`br_id`) on delete cascade;
-- alter table `staff` drop constraint fk_staffbr_id_branchid;
-- alter table `customer` modify column `cust_id` int not null auto_increment;
create table `customer` (
`cust_id` int not null auto_increment,
`cust_name` varchar(20) not null,
`cust_phone` varchar(14) not null,
`br_id` int not null,
primary key(`cust_id`)
);
alter table `customer` auto_increment = 1200,
add constraint fk_customerbr_id_branchid foreign key(`br_id`) references `branch`(`br_id`);



create table `milk` (
`m_id` int not null auto_increment,
`m_type` varchar(20) not null,
`amt_per_liter` decimal(10,2) not null,
primary key(`m_id`)
);
alter table `milk` auto_increment = 1500;


create table `bill` (
`b_id` int not null auto_increment,
`st_id` int not null,
`cust_id` int not null,
`m_id` int not null,
`quantity` decimal(10,2) not null,
`amount` decimal(10,2) not null,
`date` date not null,
primary key(`b_id`)
);
alter table `bill` auto_increment = 2000,
add constraint fk_billmilk_id_milkid foreign key(`m_id`) references `milk`(`m_id`),
add constraint fk_billst_id_staffid foreign key(`st_id`) references `staff`(`st_id`),
add constraint fk_billcust_id_custid foreign key(`cust_id`) references `customer`(`cust_id`) on delete cascade;

-- create trigger duplicate_trigger before insert on staff for each row begin if(exists(Select st_email from staff)) then signal sqlstate '45000'; end if; end //
-- create trigger duplicate_trigger before insert on staff for each row begin if(exists(Select * from staff where st_email=new.st_email)) then signal sqlstate '45000'; end if; end //

