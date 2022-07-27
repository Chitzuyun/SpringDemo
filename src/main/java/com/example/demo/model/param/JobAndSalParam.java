package com.example.demo.model.param;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Pageable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "職業薪水查詢物件")
public class JobAndSalParam {
	
	@NotBlank(message = "請輸入職業")
	@Schema(description = "職業", example = "manager")
	private String job;
	
	@NotNull
	@Schema(description = "薪水", example = "2000")
	private BigDecimal sal;	
	
	@Schema(description = "分頁物件")
	private PageParam pageParam;
}
