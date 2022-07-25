package com.example.demo.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ServiceException;
import com.example.demo.model.entity.Department;
import com.example.demo.model.entity.Employee;
import com.example.demo.model.vo.DepartmentVo;
import com.example.demo.model.vo.EmployeeVo;
import com.example.demo.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "員工管理", description = "員工數據增刪改查")
@RestController
@RequestMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
	
	@Autowired
	private EmployeeService eService;
	
	@Operation(summary = "依員工編號查找", description = "返回指定員工數據")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "請求成功", content = {@Content(
					mediaType = "application/json"
					)}),
			@ApiResponse(responseCode = "404", description = "無此員工編號", content = @Content)
	})
	@GetMapping("/{id}")
	public EmployeeVo readById(@Parameter(description = "員工編號", example = "1", required = true) @PathVariable("id") final Integer id) throws Exception {		
		Employee result = eService.readById(id);
		if(result == null) throw new ServiceException("無此員工編號! 請輸入正確員工編號", "/employee/id");
		EmployeeVo empVo = this.transformEmpVo(result);
		return empVo;		
	}
	
	@Operation(summary = "獲取所有員工列表", description = "返回所有員工數據")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "請求成功", content = {@Content(
					mediaType = "application/json"
					)}),
			@ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content = @Content)
	})
	@GetMapping("/readAll")
	public List<EmployeeVo> readAll() throws Exception {		
		List<Employee> emps = eService.readAll();
		List<EmployeeVo> empVos = new ArrayList<>();		
		for(Employee emp : emps) {
			EmployeeVo empVo = this.transformEmpVo(emp);
			empVos.add(empVo);
		}		
		return empVos;		
	}
	
	@Operation(summary = "獲取所有員工列表並分頁")
	@GetMapping("/readAllPage")
	public Page<EmployeeVo> readAllPage(@Parameter(description = "查詢開始頁數", example = "0", required = true) @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@Parameter(description = "每頁期望筆數", example = "5", required = true) @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
		Page<EmployeeVo> empVos = eService.getAllPages(PageRequest.of(pageNum, pageSize)).map(this::transformEmpVo);		
		return empVos;		
	}
	
	
	@Operation(summary = "刪除指定員工編號資訊")
	@DeleteMapping("/delete/{id}")
	public String delete(@Parameter(description = "員工編號", example = "1", required = true) @PathVariable("id") final Integer id) throws Exception {
		eService.deleteById(id);
		return "success";
	}
	
	@Operation(summary = "新建員工資訊")
	@PostMapping("/create")
	public String create(@Valid @RequestBody final EmployeeVo empVo, BindingResult bindingResult) throws Exception {						
		BindingResult errBindingResult = this.validation(empVo, bindingResult);
		
		if (bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException(null, errBindingResult);
		}
		eService.save(transformEmp(empVo));
		return "success";
	}
	
	@Operation(summary = "更新指定員工編號資訊")
	@PutMapping("/update/{id}")
	public void update(@Parameter(description = "員工編號", example = "1", required = true) @PathVariable("id") final Integer id,
								@Valid @RequestBody final EmployeeVo empVo, BindingResult bindingResult) throws Exception {		
		BindingResult errBindingResult = this.validation(empVo, bindingResult);
		
		if (bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException(null, errBindingResult);
		}		
		Employee target = eService.readById(id);
		if(target == null) throw new ServiceException("無此員工編號! 請輸入正確員工編號", "/employee/update/id");
		EmployeeVo result = this.transformEmpVo(target);
		result.setEmpno(empVo.getEmpno());
		result.setEname(empVo.getEname());
		result.setJob(empVo.getJob());
		result.setHireDate(empVo.getHireDate());
		result.setSal(empVo.getSal());
		result.setComm(empVo.getComm());
		result.setDeptno(empVo.getDeptno());
		eService.save(transformEmp(result));
	}
	
	private EmployeeVo transformEmpVo(Employee emp) {
		EmployeeVo empVo = new EmployeeVo();
		empVo.setEmpno(emp.getEmpno());
		empVo.setEname(emp.getEname());
		empVo.setJob(emp.getJob());
		empVo.setHireDate(format(emp.getHireDate()));
		empVo.setSal(emp.getSal());
		empVo.setComm(emp.getComm());
		empVo.setDeptVo(transformDeptVo(emp.getDept()));
		return empVo;
	}		
	
	private Employee transformEmp(EmployeeVo empVo) {
		Employee emp = new Employee();
		emp.setEmpno(empVo.getEmpno());
		emp.setEname(empVo.getEname());
		emp.setJob(empVo.getJob());
		System.out.println("111: "+ empVo.getHireDate());
		emp.setHireDate(parse(empVo.getHireDate()));
		emp.setSal(empVo.getSal());
		emp.setComm(empVo.getComm());
		
		Department dept = new Department();
		dept.setDeptno(empVo.getDeptno());
		emp.setDept(dept);
		return emp;
	}
	
	private DepartmentVo transformDeptVo(Department dept) {
		DepartmentVo deptVo = new DepartmentVo();
		deptVo.setDeptno(dept.getDeptno());
		deptVo.setDname(dept.getDname());
		deptVo.setLoc(dept.getLocation());		
		return deptVo;
	}
	
	private List<DepartmentVo> transformDeptVos(List<Department> depts){
		return depts.stream().map(this::transformDeptVo).collect(Collectors.toList());
	}
	
    private String format(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(localDate);
    }

    private LocalDate parse(String LocalDataString) {
        return LocalDate.parse(LocalDataString);
    }
    
    public BindingResult validation(EmployeeVo empVo, BindingResult bindingResult) {
    	BigDecimal num1 = new BigDecimal("25000");
    	BigDecimal num2 = new BigDecimal("500");      
        
        if (StringUtils.isEmpty(empVo.getEname())) {           
    		FieldError error = new FieldError("EmployeeVo", "ename", "不能輸入空字符串");
    		bindingResult.addError(error);
        }
        if (StringUtils.isEmpty(empVo.getJob())) {           
    		FieldError error = new FieldError("EmployeeVo", "job", "不能輸入空字符串");
    		bindingResult.addError(error);       	
        }
        if (StringUtils.isEmpty(empVo.getHireDate())) {           
    		FieldError error = new FieldError("EmployeeVo", "hireDate", "不能輸入空字符串");
    		bindingResult.addError(error);       	
        }
        if (empVo.getSal().compareTo(num1) != 1) {
        	FieldError error = new FieldError("EmployeeVo", "sal", "薪資必須大於2萬5");
    		bindingResult.addError(error);
        }
        if (empVo.getComm().compareTo(num2) != 1) {
        	FieldError error = new FieldError("EmployeeVo", "comm", "獎金必須大於5百");
    		bindingResult.addError(error);
        }
        if (this.parse(empVo.getHireDate()).isBefore(LocalDate.now()) ) {
        	FieldError error = new FieldError("EmployeeVo", "hireDate", "雇用日期需小於今日");
    		bindingResult.addError(error);
        }
		return bindingResult;
    }
	
}
