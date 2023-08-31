-- 테이블간 조인(JOIN) SQL 문제입니다.

-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select s.emp_no as 사번, e.first_name as 이름, s.salary as 연봉 
from salaries s inner join employees e on s.emp_no=e.emp_no where to_date>now();

-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
select e.emp_no as 사번, e.first_name as 이름, t.title as 직책 
from employees e 
inner join (select * from titles where from_date<=now()) t on e.emp_no=t.emp_no ;

-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요..
select e.emp_no as 사번, e.first_name as 이름, d.dept_name as 현재부서 from employees e inner join (select * from dept_emp where from_date<=now())de on e.emp_no = de.emp_no inner join departments d on d.dept_no=de.dept_no ;

-- 문제4.
-- 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.
select e.emp_no as 사번, e.first_name as 이름, s.salary as 연봉, t.title as 직책 ,d.dept_name as 부서 
from employees e 
	inner join dept_emp de 
		on e.emp_no = de.emp_no 
	inner join departments d 
		on d.dept_no=de.dept_no  
	inner join salaries s
		on e.emp_no = s.emp_no
	inner join titles t
		on e.emp_no = t.emp_no;

-- 문제5.
-- ‘Technique Leader’의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요. 
-- (현재 ‘Technique Leader’의 직책(으로 근무하는 사원은 고려하지 않습니다.) 
-- 이름은 first_name과 last_name을 합쳐 출력 합니다.
select e.emp_no as 사번, concat(e.first_name,' ',e.last_name) as 이름 -- , t.title, t.to_date
from titles t inner join employees e 
	on t.emp_no = e.emp_no
where title='Technique Leader' and to_date<now();

-- 문제6.
-- 직원 이름(last_name) 중에서 S(대문자)로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
select e.first_name, e.last_name, d.dept_name as 부서명, t.title as 직책
from employees e 
inner join titles t on e.emp_no=t.emp_no
inner join dept_emp de 
		on e.emp_no = de.emp_no 
	inner join departments d 
		on d.dept_no=de.dept_no  
where substring(e.last_name,1,1)='S'; -- and t.to_date>=now()

-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가 큰 순서대로 출력하세요.
-- 이름, 급여 출력
-- 급여가 큰 순서대로 출력하세요
select e.emp_no as 사번, e.first_name as 이름, e.last_name as 성, s.salary as 급여, t.title as 직책
from salaries s inner join titles t
	on s.emp_no=t.emp_no
    inner join employees e
    on e.emp_no = s.emp_no and e.emp_no=t.emp_no
where t.title='Engineer' and s.salary>=40000 and t.to_date>=now() and s.to_date>=now() ;


-- 문제8.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.
select * from dept_emp;
select d.dept_name as 부서 , avg(salary) as 평균연봉
from departments d 
inner join dept_emp de on d.dept_no=de.dept_no
inner join salaries s on de.emp_no = s.emp_no
where de.to_date='9999-01-01' and s.to_date='9999-01-01'
group by de.dept_no;

-- 문제9.
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.
select t.title as 직책, avg(s.salary) as 연봉
from titles t inner join salaries s on t.emp_no = s.emp_no
where t.to_date='9999-01-01' and s.to_date='9999-01-01'
group by t.title
order by avg(s.salary) desc;