package com.common.api.handler;

import com.common.ResultEntity;
import com.common.enums.ErrCodeEnums;
import com.common.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局api异常处理
 */
@ControllerAdvice
class GlobalApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalApiExceptionHandler.class);

	/**
	 * 处理@RequestParam错误, 即参数不足
	 * 
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.error("开始进入参数错误异常返回:", ex);
		return new ResponseEntity<>(new ResultEntity(ErrCodeEnums.PARAM_INVALID.getCode(), ex.getMessage()), status);
	}

	/**
	 * 处理500错误
	 * 
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
		LOGGER.error("开始进入服务器内异常返回:", ex);
		// 请求方式不支持
		if (ex instanceof HttpRequestMethodNotSupportedException) {
			return new ResponseEntity<>(new ResultEntity(ErrCodeEnums.REQUEST_METHOD_NOT_SPORT.getCode(), ex.getMessage()), status);
		}

		return new ResponseEntity<>(new ResultEntity(ErrCodeEnums.EXCEPTION.getCode()), status);
	}

	/**
	 * 处理参数类型转换失败
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
		LOGGER.error("开始进入错误转化失败异常返回:", ex);
		return new ResponseEntity<>(new ResultEntity(ErrCodeEnums.TYPE_MIS_MATCH.getCode(), ex.getMessage()), status);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		//  hibernate Validator验证框架抛出的业务逻辑异常
		BindingResult bindingResult = ex.getBindingResult();
		String message = bindingResult.getFieldError().getField() + bindingResult.getFieldError().getDefaultMessage();
		LOGGER.error("开始进入请求参数校验异常返回:{}", message);
		return new ResponseEntity<>(new ResultEntity(ErrCodeEnums.PARAM_INVALID.getCode(), message), status);
	}

	protected ResponseEntity<Object> handleBindException(
            BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		String message = bindingResult.getFieldError().getField() + bindingResult.getFieldError().getDefaultMessage();
		LOGGER.error("开始进入请求参数校验异常返回:{}", message);
		return new ResponseEntity<>(new ResultEntity(ErrCodeEnums.PARAM_INVALID.getCode(), message), status);
	}
	
	@ResponseBody
    @ExceptionHandler(value = {CommonException.class})
    public ResultEntity handleCommonException(CommonException ex) {
        LOGGER.info("CommonException异常:{}",ex.getCode()+"-"+ex.getMessage());
		return ResultEntity.setResult(ex.getCode(), ex.getMessage());
    }

	@ExceptionHandler(value =Exception.class)
	@ResponseBody
	public ResultEntity exceptionHandler(HttpServletRequest req, Exception ex){
		LOGGER.error("服务内部异常:", ex);
		return ResultEntity.setResult(ErrCodeEnums.EXCEPTION.getCode(), ErrCodeEnums.EXCEPTION.getDesc());
	}
}