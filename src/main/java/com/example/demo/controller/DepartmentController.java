package com.example.demo.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ServiceException;
import com.example.demo.model.entity.Department;
import com.example.demo.model.entity.Employee;
import com.example.demo.model.vo.DepartmentVo;
import com.example.demo.model.vo.EmployeeVo;
import com.example.demo.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "部門管理", description = "部門數據增刪改查")
@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService dService;
	
	@Operation(summary = "依部門編號查找", description = "返回指定部門數據")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "請求成功", content = {@Content(
					mediaType = "application/json"
					)}),
			@ApiResponse(responseCode = "404", description = "無此部門編號", content = @Content)
	})
	@GetMapping("/{id}")
	public DepartmentVo readById(@Parameter(description = "部門編號", example = "10", required = true) @PathVariable("id") final Integer id) throws Exception {		
		Department result = dService.readById(id);
		if(result == null) throw new ServiceException("無此部門編號! 請輸入正確部門編號", "/department/id");
		DepartmentVo deptVo = this.transformDeptVo(result);
		return deptVo;		
	}
	
	@Operation(summary = "獲取所有部門列表", description = "返回所有部門數據")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "請求成功", content = {@Content(
					mediaType = "application/json"
					)}),
			@ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content = @Content)
	})
	@GetMapping("/readAll")
	public List<DepartmentVo> readAll() throws Exception {		
		List<Department> result = dService.readAll();
		List<DepartmentVo> deptVos = this.transformDeptVos(result);
		return deptVos;		
	}
	
	@Operation(summary = "刪除指定部門編號資訊")
	@DeleteMapping("/delete/{id}")
	public void delete(@Parameter(description = "部門編號", example = "10", required = true)@PathVariable("id") final Integer id) throws Exception {
		dService.deleteById(id);
	}
	
	@Operation(summary = "新建部門資訊")
	@PostMapping("/create")
	public void create(@Valid @RequestBody final DepartmentVo deptVo) throws Exception {
		dService.save(transformDept(deptVo));
	}
	
	@Operation(summary = "更新指定部門編號資訊")
	@PutMapping("/update/{id}")
	public void update(@Parameter(description = "部門編號", example = "10", required = true) @PathVariable("id") final Integer id,
								@Valid @RequestBody final DepartmentVo deptVo) throws Exception {
		Department target = dService.readById(id);
		if(target == null) throw new ServiceException("無此部門編號! 請輸入正確部門編號", "/department/update/id");
		DepartmentVo result = this.transformDeptVo(target);
		result.setDeptno(deptVo.getDeptno());
		result.setDname(deptVo.getDname());
		result.setLoc(deptVo.getLoc());
		dService.save(transformDept(result));
	}
	
	private DepartmentVo transformDeptVo(Department dept) {
		DepartmentVo deptVo = new DepartmentVo();
		deptVo.setDeptno(dept.getDeptno());
		deptVo.setDname(dept.getDname());
		deptVo.setLoc(dept.getLocation());
//		deptVo.setEmpVOs(transformEmpVos(dept.getEmps()));
		return deptVo;
	}
	
	private List<DepartmentVo> transformDeptVos(List<Department> depts){
		return depts.stream().map(this::transformDeptVo).collect(Collectors.toList());
	}
	
	private Department transformDept(DepartmentVo deptVo) {
		Department dept = new Department();
		dept.setDeptno(deptVo.getDeptno());
		dept.setDname(deptVo.getDname());
		dept.setLocation(deptVo.getLoc());
		return dept;		
	}
	
	private List<EmployeeVo> transformEmpVos(List<Employee> emps){
		return emps.stream().map(emp -> {
			EmployeeVo empVo = new EmployeeVo();
			empVo.setEmpno(emp.getEmpno());
			empVo.setEname(emp.getEname());
			empVo.setJob(emp.getJob());
			empVo.setHireDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(emp.getHireDate()));
			empVo.setSal(emp.getSal());
			empVo.setComm(emp.getComm());
			empVo.setDeptVo(transformDeptVo(emp.getDept()));
			return empVo;
 		}).collect(Collectors.toList());
	}
	
}
