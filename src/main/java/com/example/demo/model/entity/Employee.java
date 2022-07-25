package com.example.demo.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "emp")
public class Employee implements Serializable{
	
	private static final long serialVersionUID = -4728795148227689709L;

	/**
	 * 員工代號
	 */
	@Id
	@SequenceGenerator(
		    name = "empSeq",
		    sequenceName = "emp2_seq",
		    allocationSize = 1,
		    initialValue = 7015)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empSeq")
	private Integer empno;
	
	private String ename;
	
	private String job;
	
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(columnDefinition = "DATE", name = "hiredate")
	private LocalDate hireDate;
	
	private BigDecimal sal;
	
	private BigDecimal comm;
	
	@ManyToOne
//	@JsonBackReference
	@JoinColumn(name = "deptno")
	private Department dept;
	
}
