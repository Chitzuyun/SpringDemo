package com.example.demo.model.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "分頁物件")
public class PageParam {
	
	@NotNull
	@Min(value = 0, message = "期望頁數至少為0")
	private Integer pageNum;
	
	@NotNull
	@Min(value = 5, message = "每頁筆數需大於等於5筆")
	private Integer pageSize;
		
	public static Pageable of(PageParam pageParam) {
		return PageRequest.of(pageParam.getPageNum(), pageParam.getPageSize());
	}
	
	public static Pageable sort(PageParam pageParam, Sort sort) {
		return PageRequest.of(pageParam.getPageNum(), pageParam.getPageSize(), sort);
	}
}
