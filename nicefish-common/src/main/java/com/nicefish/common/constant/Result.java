package com.nicefish.common.constant;

/**
 * Created by sunye on 16/1/20.
 */

public class Result {

    private String  msg;
    private boolean success;
    private int  code;
    private Object  data;


    public Result(){}
    public Result(boolean success,String data,String msg,int code){
        this.success=success;
        this.data=data;
        this.msg=msg;
        this.code=code;
    }
    public Result(boolean success,String data){
        this.success=success;
        this.data=data;
    }
    public Result(boolean success,String msg,int code){
        this.success=success;
        this.msg=msg;
        this.code=code;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
