package com.imooc.miaosha.result;

public class CodeMsg {
    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(0,"success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500000,"error");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101,"参数校验异常: %s");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211,"登陆密码不能为俄空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500211,"登陆手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500211,"登陆手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500211,"登陆手机号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500211,"登陆密码错误");
    private CodeMsg(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }
    public CodeMsg fillArgs(Object... args){
        int code = this.code;
        String message = String.format(msg,args);
        return new CodeMsg(code,message);
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
