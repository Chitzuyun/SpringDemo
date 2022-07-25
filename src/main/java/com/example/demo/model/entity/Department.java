package com.example.demo.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dept")
public class Department implements Serializable{

	private static final long serialVersionUID = 2058605358695946500L;
	
	/**
	 * 部門代號
	 */
	@Id
	@SequenceGenerator(name = "deptSeq", sequenceName = "dept2_seq", allocationSize = 10,initialValue = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deptSeq")
	private Integer deptno;
	
	private String dname;
	
	@Column(name = "loc")
	private String location;
	
	@OneToMany(mappedBy = "dept", cascade = CascadeType.REMOVE)
//	@JsonManagedReference
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "deptno", referencedColumnName = "deptno")
	private List<Employee> emps= new ArrayList<>(); 
		
}
