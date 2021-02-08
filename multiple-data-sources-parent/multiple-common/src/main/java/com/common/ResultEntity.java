package com.common;

import com.alibaba.fastjson.JSON;
import com.common.enums.ErrCodeEnums;

/**
 * 返回结果
 */
public class ResultEntity {

	/**
	 * 返回的错误码
	 */
	private Integer code;

	/**
	 * 返回的数据内容
	 */
	private Object data;

	/**
	 * 返回的信息
	 */
	private String message;

	private Long timestamp = System.currentTimeMillis();

	public ResultEntity() {

	}

	public ResultEntity(Integer code) {
		this.code = code;
		this.message = "";
	}

	public ResultEntity(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message == null ? "" : message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data == null ? "" : data;
	}

	public void setData(Object data) {
		this.data = data == null ? "" : data;
	}

	public static ResultEntity setResult(int code, String message) {
		ResultEntity result = new ResultEntity();
		result.setCode(code);
		result.setMessage(message);
		return result;
	}

	public static String setResultStr(int code, String message) {
		ResultEntity result = new ResultEntity();
		result.setCode(code);
		result.setMessage(message);
		return JSON.toJSONString(result);
	}

	public static String setResultStr(int code, String message, Object obj) {
		ResultEntity result = new ResultEntity();
		result.setCode(code);
		result.setMessage(message);
		result.setData(obj);
		return JSON.toJSONString(result);
	}

	public static ResultEntity setResult(int code, String message, Object data) {
		ResultEntity result = new ResultEntity();
		result.setCode(code);
		result.setMessage(message);
		result.setData(data);
		return result;
	}

	public static String setSuccessJson(Object data) {
		return setResultStr(ErrCodeEnums.SUCCESS.getCode(), "请求成功", data);
	}

	public static String setSuccessJson() {
		return setResultStr(ErrCodeEnums.SUCCESS.getCode(), "请求成功");
	}

	public static ResultEntity setSuccess(Object data) {
		return setResult(ErrCodeEnums.SUCCESS.getCode(), "请求成功", data);
	}

	public static ResultEntity setSuccess() {
		return setResult(ErrCodeEnums.SUCCESS.getCode(), "请求成功");
	}

	public static ResultEntity setSuccess(Integer code, String msg, Object data) {
		return setResult(code, msg, data);
	}

	public static String setFailJson(Integer code, String msg) {
		return setResultStr(code, msg);
	}

	public static String setFailJson(String msg) {
		return setResultStr(ErrCodeEnums.FAILURE.getCode(), msg);
	}

	public static ResultEntity setFail(int code, String msg) {
		return setResult(code, msg);
	}

	public static ResultEntity setFail(String msg) {
		return setResult(ErrCodeEnums.FAILURE.getCode(), msg);
	}

	public static ResultEntity setFail(int code, String msg, Object data) {
		return setResult(code, msg, data);
	}

	public static ResultEntity setFailData( Object data) {
		return setResult(ErrCodeEnums.FAILURE.getCode(), ErrCodeEnums.FAILURE.getDesc(), data);
	}

	public static ResultEntity setException() {
		return setResult(ErrCodeEnums.EXCEPTION.getCode(), ErrCodeEnums.EXCEPTION.getDesc());
	}

	public static ResultEntity setException(String msg) {
		return setResult(ErrCodeEnums.EXCEPTION.getCode(), ErrCodeEnums.EXCEPTION.getDesc());
	}

	public boolean isSuccess() {
		return this.getCode() == ErrCodeEnums.SUCCESS.getCode();
	}
	
	public boolean isFailure() {
		return this.getCode() == ErrCodeEnums.FAILURE.getCode();
	}
	
	public boolean isException() {
		return this.getCode() == ErrCodeEnums.EXCEPTION.getCode();
	}

}
