package com.nicefish.common.constant;


public enum ResponseState {

    /**
     * 系统通用响应代码段 1xxxx
     */
    RESPONSE_SUCCESS(0,                                             "success", "成功"),
    RESPONSE_SYSTEM_ERROR(10001,                                    "System error", "系统错误"),
    RESPONSE_UNSUPPORTED_MEDIA_TYPE(10002,                             "Unsupported Media Type", "不支持的服务类型"),
    RESPONSE_ILLEGAL_REQUEST(10003,                                 "Illegal request", "非法请求");



    public int code;

    public String englishMessage;

    public String chineseMessage;

    ResponseState(int code, String englishMessage, String chineseMessage){
        this.code = code;
        this.englishMessage = englishMessage;
        this.chineseMessage = chineseMessage;
    }


}
