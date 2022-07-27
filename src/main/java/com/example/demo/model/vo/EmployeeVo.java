package com.example.demo.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "員工參數實體")
public class EmployeeVo implements Serializable{
	
	private static final long serialVersionUID = -7573111926212199116L;
	
	@Schema(description = "員工編號", example = "1")
	@NotNull(message = "請輸入員工編號")
	private Integer empno;
	
	@Schema(description = "員工姓名", example = "王大明")
	@NotBlank(message = "請輸入員工姓名")
	@Size(min = 2, message = "員工姓名最少必須輸入二個字")
    private String ename;
	
	@Schema(description = "職業", example = "manager")
    private String job;
    
	@Schema(description = "雇用日期", example = "2022/07/21")
    @NotBlank(message = "請輸入雇用日期")
    private String hireDate;
    
	@Schema(description = "薪水", example = "2000")
    @NotNull
    @DecimalMin(value = "2000")
    private BigDecimal sal;
    
	@Schema(description = "獎金")
    @NotNull
    @DecimalMin(value = "500")
    private BigDecimal comm;
    
    @JsonIgnore
    private Integer deptno;
    
    @Schema(description = "部門參數實體")
    private DepartmentVo deptVo;
	
}
