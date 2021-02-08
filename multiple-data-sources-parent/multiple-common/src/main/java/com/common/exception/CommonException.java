package com.common.exception;


import com.common.enums.ErrCodeEnums;

public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    /**  业务状态码，例如：COMM_INVALID_USER **/
    private String statusCode;
    
    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CommonException(String message) {
        super(message);
        this.code = ErrCodeEnums.EXCEPTION.getCode();
    }

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CommonException(ErrCodeEnums resultEnums) {
        super(resultEnums.getDesc());
        this.code = resultEnums.getCode();
    }

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
}
