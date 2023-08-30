select version(), current_date, now() from dual;

-- 수학 함수도 사용할 수 있다.(사칙 연산도 된다)
select sin(pi() / 4), 1 + 2 * 3 - 4 /5 from dual;

-- 대소문자 구분 안한다.
sELECT VERSION(), current_DATE, NOW() froM duAl;

-- table 생성: DDL
create table pet(
	name varchar(100),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
);

-- schema 확인
show tables;
describe pet;
desc pet;

-- table 삭제: DDL
drop table pet;
show tables;

-- insert: DML(C)
insert into pet values('충시기', '신유경', 'dog', 'm', '2019-12-25', null);
insert into pet values('Buffy', 'Gwen', 'dog', 'f', '1994-03-17',null);


-- select: DML(R)
select * from pet;
SET GLOBAL local_infile = 1;

-- update: DML(U)
update pet set name='춘식이' where name='충시기';

LOAD DATA LOCAL INFILE '‪C:/pet.txt' INTO TABLE pet  IGNORE 1 LINES;

-- select 연습
-- 문) bowser의 주인이름은?
select owner from pet where name = 'bowser';

-- 문2) 1998 이후에 태어난 애들은?
select * from pet where birth >='1998-01-01';

-- 문3) 종이 뱀이거나 새인 애들은?
select * from pet where species = 'snake' or species = 'bird';

-- 문4)  order by
select name, birth from pet order by birth asc;

-- 예8) 집계함수 count,avg,sum,max,min ..
select count(*) from pet;
select max(birth) from pet;

select * from pet;
