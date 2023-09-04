desc author;
desc book;

insert into author values(1,'스테파니메이어');
insert into author values(2,'조정래');
insert into author values(3,'김동인');
insert into author values(4,'김난도');
insert into author values(5,'천상병');
insert into author values(6,'원수연');

select * from author;
select * from book;
insert into book(title,author_no) values ('트와일라잇',1);
insert into book(title,author_no) values ('뉴문',1);
insert into book(title,author_no) values ('이클립스',1);
insert into book(title,author_no) values ('브레이킹던',1);
insert into book(title,author_no) values ('아리랑',2);
insert into book(title,author_no) values ('젊은그들',3);
insert into book(title,author_no) values ('아프니깐 청춘이다',4);
insert into book(title,author_no) values ('귀천',5);
insert into book(title,author_no) values ('태백산맥',2);
insert into book(title,author_no) values ('풀하우스',6);

select b.title, a.name, b.rent 
from book b 
inner join author a on b.author_no=a.no;


update book
set rent="N"
where no='2';