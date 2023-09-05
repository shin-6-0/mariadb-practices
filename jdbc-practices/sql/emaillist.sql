-- emaillist

-- insert
insert into emaillist values(null, '신', '유경2', 'chemi0313@gmail.com');

-- findAll
select no, first_name, last_name, email from emaillist order by no desc;

-- delete
delete from emaillist where email='kickscar@gmail.com';

-- findAll
select no, first_name, last_name, email from emaillist order by no desc;