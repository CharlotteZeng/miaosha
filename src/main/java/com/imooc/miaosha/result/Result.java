package com.imooc.miaosha.result;

import com.imooc.miaosha.domain.User;

public class Result<T> {
    private int code;
    private String msg;
    private  T data;

    private Result(T data) {
        this.code = CodeMsg.SUCCESS.getCode();
        this.msg = CodeMsg.SUCCESS.getMsg();
        this.data = data;
    }

    public static <T> Result success(T data){

        return new Result<T>(data);
    }

    public static <T> Result<T> error(CodeMsg cm){

        return new Result<T>(cm);
    }

    public Result(CodeMsg cm) {
        if (cm==null)
            return;
        this.code=cm.getCode();
        this.msg=cm.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
