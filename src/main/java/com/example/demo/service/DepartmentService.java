package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository dRepository;
	
//	@Cacheable(cacheNames = "Dept")
	public List<Department> readAll(){
		System.out.println("執行Service readAll方法");
		List<Department> result = new ArrayList<>();
		Iterable<Department> depts = dRepository.findAll();
		depts.forEach(result::add);
		return result;
	}
	
	@Cacheable(cacheNames = "Dept", key = "#id")
	public Department readById(Integer deptno) throws Exception {
		System.out.println("執行Service readById方法");
		Department dept = null;		
		if(deptno != null) {
			dept = dRepository.findByDeptno(deptno);
		}
		return dept;
	}
	
//	@CacheEvict(cacheNames = "Dept")
	@Transactional
	public void deleteById(Integer deptno) throws Exception {
		System.out.println("執行Service deleteById方法");
		if(deptno != null) {
			dRepository.deleteById(deptno);
		}
	}
		
//	@CachePut(cacheNames = "Dept")
	@Transactional
	public void save(Department dept) throws Exception {
		System.out.println("執行Service save方法");
		if(dept != null) {
			dRepository.save(dept);
		}
	}
	
//	@Cacheable(cacheNames = "Dept")
	public Page<Department> getAllPages(Pageable pageable){
		System.out.println("執行Service readAll方法");
		return dRepository.findAll(pageable);
	}
	
}
