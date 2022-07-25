package com.example.demo.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "部門參數實體")
public class DepartmentVo implements Serializable{
	
	private static final long serialVersionUID = 5647233085537843945L;
	
	@Schema(description = "部門編號", example = "10")
	@NotNull(message = "請輸入部門編號")
	private Integer deptno;
	
	@Schema(description = "部門名稱", example = "警備部")
	@NotBlank(message = "請輸入部門名稱")
    private String dname;
    
	@Schema(description = "部門位置", example = "臺灣新北")
	@NotBlank(message = "請輸入部門位置")
    private String loc;
    
    @JsonIgnore
    private List<EmployeeVo> empVOs = new ArrayList<>();	
	
}
