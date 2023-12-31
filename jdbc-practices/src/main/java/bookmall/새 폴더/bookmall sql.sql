-- 
-- category
select * from category;

insert into category (name) values ('');
select no from category where name = 'IT서적';
delete from category where name ='';

--
-- Member 
desc member;
select * from member;
insert into member (name,phone,email,password) values ('춘식이','010-1111-2222','choonsik@gmail.com','cnstlr');
insert into member (name,phone,email,password) values ('라이언','010-7777-5555','lian@gmail.com','fkdldjs');
insert into member (name,phone,email,password) values ('어피치','010-2222-6666','apeach@naver.com','djvlcl1234');
insert into member (name,phone,email,password) values ('프로도','010-3333-4444','prodo1234@naver.com','vmfheh0101');

update member
set password = ''
where phone ='' and email='' and password='';

--
-- book
desc book;
select no,title,price from book;
insert into book (title,price,category_no) values ('토비의 스프링',38000,1);
insert into book (title,price,category_no) values ('어린왕자',22000,2);
insert into book (title,price,category_no) values ('마지막 잎새',32500,5);
insert into book (title,price,category_no) values ('스티브 잡스',30000,4);
insert into book (title,price,category_no) values ('만델라 자서전',44000,4);

update book
set price = 40000
where title = '';

--
-- cart
desc cart;
select * from cart;
insert into cart (book_no,mem_no,quantity) values (1,8,2);
insert into cart (book_no,mem_no,quantity) values (11,8,3);
insert into cart (book_no,mem_no,quantity) values (12,8,2);

select * from cart where mem_no =8;


select m.name, m.no, c.quantity, b.title, b.no, b.price
from member m inner join cart c on m.no = c.mem_no
inner join book b on b.no = c.book_no;

update cart
set quantity = 2
where mem_no = 8 and book_no=12;

delete from cart where mem_no=8 and book_no=2;

--
-- order , orderbook
desc orders;
desc order_book;

select * from orders;
select * from order_book;

insert into orders (mem_no,total_price,address) values (8,76000,'서울 서초구 서초대로74길 33');
insert into orders (mem_no,total_price,address) values (8,382500,'서울특별시 강남구 강남대로 396');


