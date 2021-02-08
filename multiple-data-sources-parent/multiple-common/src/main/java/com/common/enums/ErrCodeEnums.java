package com.common.enums;

public enum ErrCodeEnums {

    SUCCESS(0, "成功"),
    FAILURE(-1, "失败"),
    EXCEPTION(-2, "内部服务异常"),

    // 认证类编码 (100, 1000)
    PARAM_INVALID(101, "参数不合法"),
    ILLEGAL_REQUEST(102, "非法请求"),
    EXPIRE(103, "已过期"),
    SIGN_VERIFY_FAILED(104, "签名认证失败"),
    REQUEST_METHOD_NOT_SPORT(105, "不支持的请求方式"),
    TYPE_MIS_MATCH(106, "参数类型转换错误"),
    BUSINESS_IS_CLOSED(107, "业务已关闭"),


    // 业务错误编码  (1000 , 4000)
    USER_ALREADY_EXIST(10001, "用户已存在"),
    LINE_STOP_SELL_TICKET(10002, "停止售票"),
    FREQUENT_OPERATION(10003, "频繁操作"),
    EXIST_ORDER_NOT_PAY(10004, "存在未完成订单"),
    TICKET_SOLD_OUT(10005, "票已售罄"),
    OUT_TICKET_REFUND_TIME(10006,"已过退票时间"),
    HAVE_INVOICE(10007, "已开发票"),
    REFUND_TICKET_PRICE_ERROR(10008, "退票金额有误"),
    TICKET_INSUFFICIENT(10009, "余票不足"),
    ;

    private int code;
    private String desc;

     ErrCodeEnums(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
