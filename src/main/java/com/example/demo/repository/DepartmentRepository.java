package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository< Department, Integer> {
	
	Department findByDeptno(Integer deptno);
	
	Department findByDname(String dname);
}
