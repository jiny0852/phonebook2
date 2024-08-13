#######################################################
################# phonebook_db 계정 ####################

drop user 'phonebook'@'%';

create user 'phonebook'@'%' identified by 'phonebook';

grant all privileges on phonebook_db.* to 'phonebook'@'%';

flush privileges;


#######################################################
################# phonebook_db DB생성 ##################


drop database phonebook_db;

create database phonebook_db
	default character set utf8mb4
    collate utf8mb4_general_ci
    default encryption='n'
;

show databases;

use phonebook_db;



#######################################################
############## phonebook_db table 생성 #################

use phonebook_db;

drop table person;

create table person(
	person_id  	  integer 		  primary key     auto_increment,
    name		  varchar(30) 	  not null,
    hp 			  varchar(20),
    company 	  varchar(20)
);

-- 조회
select * from person;

select  person_id,
		name,
        hp,
        company
from person
;

-- 등록
insert into person
values(null, '정우성', '010-1111-1111', '02-1111-1111')
;

insert into person
values(null, '이효리', '010-2222-2222', '02-2222-2222')
;

insert into person
values(null, '이창섭', '010-3333-3333', '02-3333-3333')
;

insert into person
values(null, '변요한', '010-4444-4444', '02-4444-4444')
;

insert into person
values(null, '문준원', '010-5555-5555', '02-5555-5555')
;

insert into person
values(null, '한노아', '010-6666-6666', '02-6666-6666')
;

insert into person
values(null, '남예준', '010-7777-7777', '02-7777-7777')
;

insert into person
values(null, '채봉구', '010-8888-8888', '02-8888-8888')
;

insert into person
values(null, '도은호', '010-9999-9999', '02-9999-9999')
;

insert into person
values(null, '유하민', '010-0000-0000', '02-0000-0000')
;

-- 수정
update person
set name = '강호동',
	hp = '010-9999-9999',
    company = '02-9999-9999'
where person_id = 2
;


-- 삭제
delete from person
where person_id = 2
;






