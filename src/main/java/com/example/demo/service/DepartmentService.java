package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.entity.Department;
import com.example.demo.model.entity.Employee;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository dRepository;
	
	
	public List<Department> readAll(){
		List<Department> result = new ArrayList<>();
		Iterable<Department> depts = dRepository.findAll();
		depts.forEach(result::add);
		return result;
	}
	
	public Department readById(Integer deptno) throws Exception {		
		Department dept = null;		
		if(deptno != null) {
			dept = dRepository.findByDeptno(deptno);
		}
		return dept;
	}
	
	@Transactional
	public void deleteById(Integer deptno) throws Exception {
		if(deptno != null) {
			dRepository.deleteById(deptno);
		}
	}
	
	@Transactional
	public void save(Department dept) throws Exception {
		if(dept != null) {
			dRepository.save(dept);
		}
	}
	
	public Page<Department> getAllPages(Pageable pageable){
		return dRepository.findAll(pageable);
	}
	
}
