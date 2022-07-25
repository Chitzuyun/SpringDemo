package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository< Employee, Integer> {
	
	Employee findByEmpno(Integer empno);
	
	Employee findByEname(String ename);
}
