package com.imooc.miaosha.result;

public class CodeMsg {
    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(500100,"success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500000,"error");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211,"登陆密码不能为俄空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500211,"登陆手机号不能为空");
    private CodeMsg(int code, String msg) {
        this.code=code;
        this.msg=msg;
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
}
