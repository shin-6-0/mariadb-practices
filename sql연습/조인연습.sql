-- inner join
-- 예제1: 현재, 근무하고 있는 직원 사번, 이름과 직책을 모두 출력
select a.emp_no, a.first_name, b.title
  from employees a, titles b
 where a.emp_no = b.emp_no       -- join 조건 (n-1)
   and b.to_date = '9999-01-01'; -- row 선택 조건
   
-- 예제 2: 현재, 근무하고 잇는 직원 사번, 이름과 직책을 모두 출력하되, 여성 엔지니어(Engineer)만 출력
select a.emp_no, a.first_name, a.gender, b.title
  from employees a, titles b
 where a.emp_no = b.emp_no       -- join 조건 (n-1)
   and b.to_date = '9999-01-01'	-- row 선택 조건1
   and a.gender='F'				-- row 선택 조건2
   and b.title = 'Engineer'; 	-- row 선택 조건3

-- ANSI / ISO SQL1999 JOIN 표준문법

-- 1) join ~ on *
-- 예제 : 현재, 직책별 평균 연봉을 큰 순서대로 출력
select a.title, avg(salary)
from titles a join salaries b on a.emp_no=b.emp_no
where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
group by a.title
order by avg(b.salary) desc;

-- 2) Natural Join
-- 조인 대상이 되는 테이블들에 이름이 같은 공통 컬럼이 있는 경우
-- 조인 조건을 명시적으로 암묵적으로 조인이 된다.
-- 예제: 현재 근무하고 있는 직원의 이름과 직책을 출력
select a.first_name, b.title
from   employees a,titles b
where a.emp_no=b.emp_no
and   b.to_date='9999-01-01'
order by a.first_name asc;

select a.first_name, b.title
from   employees a natural join titles b
where   b.to_date='9999-01-01'
order by a.first_name asc;

-- 3) Join ~ using
-- natural join의 문제점
-- 예제: 현재, 근무하고 있는 직원의 직책과 연봉을 출력

-- natural join 
select count(*)
 from  titles a  natural join salaries b
where  a.to_date='9999-01-01' 
	and b.to_date='9999-01-01';

-- 해결 1: join ~ using
select count(*)
 from  titles a join salaries b using(emp_no)
where  a.to_date='9999-01-01' 
	and b.to_date='9999-01-01';

-- 해결 2: join ~ on
select count(*)
 from  titles a join salaries b on a.emp_no = b.emp_no
where  a.to_date='9999-01-01' 
	and b.to_date='9999-01-01';


-- 실습문제1
-- 현재, 직원별 근무 부서를 출력 해보세요.
-- 사번, 직원이름(first_name), 부서명만 출력
select  e.emp_no as 사번, e.first_name as 직원이름, d.dept_name as 부서명
from employees e 
inner join dept_emp de on e.emp_no = de.emp_no
inner join departments d on d.dept_no = de.dept_no
where de.to_date>now();

-- 실습문제2
-- 현재, 직책별 평균연봉과 직원수를 구하되 직책별 직원수가 100명 이상인 직책만 출력하세요.
-- 직책, 평균연봉, 직원만 출력
select t.title as 직책 , avg(salary) as 평균연봉, count(e.emp_no) as 직원수
from employees e 
inner join titles t on e.emp_no=t.emp_no
inner join salaries s on e.emp_no = s.emp_no
where t.to_date>now() and s.to_date>now()
group by t.title
having count(e.emp_no)>=100;



-- 실습문제3
-- 현재, 부서별로 직책이 Engineer인 지원즐에 대해서만 평균연봉을 구하세요.
-- 부서이름, 평균급여로 출력하고 평균연봉이 높은 순으로 정렬 하세요. 
select d.dept_name as 부서이름, avg(s.salary) as 평균급여
from employees e 
inner join dept_emp de on e.emp_no=de.emp_no
inner join departments d on d.dept_no = de.dept_no
inner join salaries s on e.emp_no=s.emp_no and s.emp_no = de.emp_no
inner join titles t on e.emp_no=t.emp_no
where de.to_date>now() and t.to_date>now() and s.to_date>now() and t.title='Engineer'
group by de.dept_no
order by avg(s.salary) desc;




