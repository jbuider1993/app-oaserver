package com.app.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.common.util.ToolUtil;

/**
 * 全局异常捕获
 * @author 卫志强
 *
 */
@RestControllerAdvice
public class ConsumerExceptionHandler {

	@ExceptionHandler(value = Exception.class) // 指定拦截的异常
	public String errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
		if (e instanceof RuntimeException){
			RuntimeException runtimeException = (RuntimeException) e;
			return ToolUtil.getMessageToPageCom(runtimeException.getCause() == null ? e.getMessage() : runtimeException.getCause().getMessage(), "-9999");
		}else{
			return ToolUtil.getMessageToPageCom(e.getMessage(), "-9999");
		}
	}

}
