package com.example.demo.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository< Employee, Integer> {
	
	Employee findByEmpno(Integer empno);
	
	Employee findByEname(String ename);
	
	Page<Employee> findByJobAndSalGreaterThan(String job, BigDecimal sal, Pageable pageabke);
	
	
}
