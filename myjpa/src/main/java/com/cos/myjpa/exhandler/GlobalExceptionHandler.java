package com.cos.myjpa.exhandler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.web.dto.CommonRespDto;

@RestController
@ControllerAdvice // 모든 Exception을 낚아챔.
public class GlobalExceptionHandler {
	
	// 그 중 IllegalArgumentException이 발생하면 해당 함수 실행 됨.
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public CommonRespDto<?>  dataIntegrityViolation(Exception e) {
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public CommonRespDto<?> illegalArgument(Exception e) {
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}
	
	@ExceptionHandler(value = EmptyResultDataAccessException.class)
	public CommonRespDto<?> emptyResultDataAccess(Exception e) {
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}
}
