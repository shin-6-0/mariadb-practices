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
insert into book(no,title,author_no) values (1,'트와일라잇',1);
insert into book(no,title,author_no) values (2,'뉴문',1);
insert into book(no,title,author_no) values (3,'이클립스',1);
insert into book(no,title,author_no) values (4,'브레이킹던',1);
insert into book(no,title,author_no) values (5,'아리랑',2);
insert into book(no,title,author_no) values (6,'젊은그들',3);
insert into book(no,title,author_no) values (7,'아프니깐 청춘이다',4);
insert into book(no,title,author_no) values (8,'귀천',5);
insert into book(no,title,author_no) values (9,'태백산맥',2);
insert into book(no,title,author_no) values (10,'풀하우스',6);