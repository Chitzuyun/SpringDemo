package com.example.demo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository eRepository;
	
	
	public List<Employee> readAll() throws Exception {
		List<Employee> result = new ArrayList<>();
		Iterable<Employee> emps = eRepository.findAll();
		emps.forEach(result::add);
		return result;
	}
	
	public Employee readById(Integer empno) throws Exception {		
		Employee emp = null;		
		if(empno != null) {
			emp = eRepository.findByEmpno(empno);
		}
		return emp;
	}
	
	@Transactional
	public void deleteById(Integer empno) throws Exception {
		if(empno != null) {
			eRepository.deleteById(empno);
		}
	}
	
	@Transactional
	public void save(Employee emp) throws Exception {
		if(emp != null) {
			eRepository.save(emp);
		}
	}

	public Page<Employee> getAllPages(Pageable pageable){
		return eRepository.findAll(pageable);
	}
	
	public Page<Employee> getJobAndSalPages(String job, BigDecimal sal, Pageable pageable){
		return eRepository.findByJobAndSalGreaterThan(job, sal, pageable);
	}
	
}
