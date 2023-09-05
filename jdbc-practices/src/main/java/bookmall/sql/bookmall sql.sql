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
select * from cart where mem_no =8;

select m.name, c.quantity, b.title, b.price
from member m inner join cart c on m.no = c.mem_no
inner join book b on b.no = c.book_no;

update cart
set quantity = 9
where mem_no = 9 and title = '';
