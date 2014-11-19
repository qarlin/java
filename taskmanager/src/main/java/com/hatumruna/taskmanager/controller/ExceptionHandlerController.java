package com.hatumruna.taskmanager.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hatumruna.taskmanager.exception.BusinessException;

//@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(BusinessException.class)
	public String handleBusinessError(BusinessException ex) {
		ex.printStackTrace();
		return "Error";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleError(Exception ex) {
		ex.printStackTrace();
		return "Error 2";
	}
}
