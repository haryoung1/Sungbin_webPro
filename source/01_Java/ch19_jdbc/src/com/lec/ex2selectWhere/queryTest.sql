-- EX1 부서번호 받고 부서정보 출력하기
SELECT * FROM DEPT WHERE DEPTNO=40;

-- EX2 부서번호 받고 부서정보와 사원정보 출력 (사번,이름,급여,상사명)
SELECT * FROM DEPT WHERE DEPTNO=40;
SELECT W.EMPNO, W.ENAME, W.SAL, M.ENAME MANAGER
    FROM EMP W, EMP M
    WHERE W.MGR = M.EMPNO AND W.DEPTNO = 10;
    
-- EX3 부서이름을 입력 받아 부서정보 출력
SELECT * FROM DEPT WHERE DNAME = UPPER ('sales');

-- EX4