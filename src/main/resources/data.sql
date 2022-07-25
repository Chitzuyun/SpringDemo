INSERT INTO dept(deptno, dname, loc)
VALUES (dept2_seq.nextval, '財務部', '臺灣台北');
INSERT INTO dept(deptno, dname, loc)
VALUES (dept2_seq.nextval, '研發部', '臺灣新竹');
INSERT INTO dept(deptno, dname, loc)
VALUES (dept2_seq.nextval, '業務部', '美國紐約');
INSERT INTO dept(deptno, dname, loc)
VALUES (dept2_seq.nextval, '生管部', '日本東京');

INSERT INTO emp(empno, ename, job, hiredate, sal, comm, deptno)
VALUES (emp2_seq.nextval, 'king', 'president', to_date('1981-11-17', 'yyyy-MM-dd'), 5000.5, 0.0, 10);
INSERT INTO emp(empno, ename, job, hiredate, sal, comm, deptno)
VALUES (emp2_seq.nextval, 'blake', 'manager', to_date('1981-05-01', 'yyyy-MM-dd'), 2850, 0.0, 30);
INSERT INTO emp(empno, ename, job, hiredate, sal, comm, deptno)
VALUES (emp2_seq.nextval, 'clark', 'manager', to_date('1981-01-09', 'yyyy-MM-dd'), 2450, 0.0, 10);
INSERT INTO emp(empno, ename, job, hiredate, sal, comm, deptno)
VALUES (emp2_seq.nextval, 'jones', 'manager', to_date('1981-04-02', 'yyyy-MM-dd'), 2975, 0.0, 20);
INSERT INTO emp(empno, ename, job, hiredate, sal, comm, deptno)
VALUES (emp2_seq.nextval, 'martin', 'salesman', to_date('1981-09-28', 'yyyy-MM-dd'), 1250, 1400, 30);
INSERT INTO emp(empno, ename, job, hiredate, sal, comm, deptno)
VALUES (emp2_seq.nextval, 'allen', 'salesman', to_date('1981-02-2', 'yyyy-MM-dd'), 1600, 300, 30);
INSERT INTO emp(empno, ename, job, hiredate, sal, comm, deptno)
VALUES (emp2_seq.nextval, 'turner', 'salesman', to_date('1981-09-28', 'yyyy-MM-dd'), 1500, 0, 30);
INSERT INTO emp(empno, ename, job, hiredate, sal, comm, deptno)
VALUES (emp2_seq.nextval, 'james', 'clerk', to_date('1981-12-03', 'yyyy-MM-dd'), 950, 0.0, 30);
INSERT INTO emp(empno, ename, job, hiredate, sal, comm, deptno)
VALUES (emp2_seq.nextval, 'ward', 'salesman', to_date('1981-02-22', 'yyyy-MM-dd'), 1250, 500, 30);
INSERT INTO emp(empno, ename, job, hiredate, sal, comm, deptno)
VALUES (emp2_seq.nextval, 'ford', 'analyst', to_date('1981-12-03', 'yyyy-MM-dd'), 3000, 0.0, 20);
INSERT INTO emp(empno, ename, job, hiredate, sal, comm, deptno)
VALUES (emp2_seq.nextval, 'smith', 'clerk', to_date('1980-12-17', 'yyyy-MM-dd'), 800, 0.0, 20);
INSERT INTO emp(empno, ename, job, hiredate, sal, comm, deptno)
VALUES (emp2_seq.nextval, 'scott', 'analyst', to_date('1981-12-09', 'yyyy-MM-dd'), 3000, 0.0, 20);
INSERT INTO emp(empno, ename, job, hiredate, sal, comm, deptno)
VALUES (emp2_seq.nextval, 'adams', 'clerk', to_date('1983-01-12', 'yyyy-MM-dd'), 1100, 0.0, 20);
INSERT INTO emp(empno, ename, job, hiredate, sal, comm, deptno)
VALUES (emp2_seq.nextval, 'miller', 'clerk', to_date('1982-01-23', 'yyyy-MM-dd'), 1300, 0.0, 10);