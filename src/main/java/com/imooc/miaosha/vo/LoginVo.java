package com.imooc.miaosha.vo;

public class LoginVo {
    private String mobile;
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LoginVo{");
        sb.append("mobile='").append(mobile).append('\'');
        sb.append(", passwrod='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
