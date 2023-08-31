-- 혼합 SQL 문제입니다.

-- 문제1.
-- 현재, 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(salary)
from salaries 
where to_date>now() and (select avg(salary) from salaries)<salary; -- 154543

-- 문제2. (x) 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 
select e.first_name, e.emp_no, dept_name, s.salary
from employees e 
inner join (select salary, emp_no from salaries where to_date>now()) s 
		on e.emp_no=s.emp_no
inner join (select dept_no, emp_no from dept_emp where to_date>now()) de 
		on e.emp_no = de.emp_no
inner join departments d 
		on d.dept_no = de.dept_no
group by dept_name
having max(s.salary);

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select s.salary as 연봉 , e.emp_no as 사번, e.first_name as 이름 , d.dept_name as 부서
from employees e 
inner join salaries s on e.emp_no = s.emp_no
inner join dept_emp de on de.emp_no = e.emp_no and s.emp_no = de.emp_no
inner join departments d on de.dept_no = d.dept_no
where s.to_date>now() and de.to_date>now()
	and s.salary>(
				select avg(s.salary)
				from salaries s inner join dept_emp sde on s.emp_no = sde.emp_no
				where s.to_date>now() and sde.to_date>now()
				group by sde.dept_no
                having sde.dept_no = de.dept_no
			);

-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select e.emp_no as 사번, e.first_name as 이름, 
		(select first_name from employees e where d.emp_no = e.emp_no) as '매니저 이름',
        (select dept_name from departments d where de.dept_no = d.dept_no) as '부서 이름'
from employees e, dept_emp de, dept_manager d
where e.emp_no = de.emp_no and de.dept_no=d.dept_no and de.to_date>now() and d.to_date>now();


-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
select e.emp_no as 사번, e.first_name as 이름, t.title as 직책, s.salary as 연봉
from employees e 
inner join dept_emp de on e.emp_no = de.emp_no
inner join departments d on de.dept_no=d.dept_no
inner join salaries s on e.emp_no = s.emp_no and de.emp_no =s.emp_no
inner join titles t on e.emp_no = t.emp_no
where 	s.to_date>now() 
	and de.to_date>now()
	and t.to_date>now()
	and de.dept_no = (select dept_no
						from salaries s 
						inner join dept_emp sde on s.emp_no = sde.emp_no 
						group by sde.dept_no 
						having sde.dept_no =de.dept_no
						order by avg(s.salary) desc limit 0,1);

-- 문제6.
-- 평균 연봉이 가장 높은 부서는?
-- 총무 20000 
select max(salary), d.dept_name
from employees e 
inner join salaries s on e.emp_no = s.emp_no
inner join dept_emp de on e.emp_no = de.emp_no
inner join departments d on d.dept_no = de.dept_no
where s.to_date>now() and de.to_date>now()
group by d.dept_no 
order by max(salary) desc limit 0,1;



-- 문제7.
-- 평균 연봉이 가장 높은 직책?
-- Engineer 40000
select max(salary), t.title
from employees e 
inner join salaries s on e.emp_no = s.emp_no
inner join titles t on e.emp_no =t.emp_no
where s.to_date>now() and t.to_date>now()
group by t.title 
order by max(salary) desc limit 0,1;




-- 문제8. (순수 join 문제)
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
select 	d.dept_name as 부서이름,e.first_name as 사원이름, s.salary as 연봉 , de2.dept_no, dm.emp_no
from employees e 
inner join salaries s on s.emp_no = e.emp_no
inner join dept_emp de on de.emp_no = e.emp_no
inner join departments d on de.dept_no = d.dept_no
left join dept_emp de2 on de2.dept_no = de.dept_no
left join dept_manager dm on dm.emp_no=e.emp_no
where s.to_date>now() 
	and de.to_date>now()
    and de2.to_date>now()
	and dm.to_date>now();
-- 문제8. (순수 join 문제)
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.

