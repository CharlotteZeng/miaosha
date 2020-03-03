package com.imooc.miaosha.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
    private static final Pattern moblie_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src){
        if (StringUtils.isEmpty(src)){
            return false;
        }
        Matcher matcher = moblie_pattern.matcher(src);
        return matcher.matches();
    }
    /*public static void main(String[] args){
        System.out.println(isMobile("18912341234"));
    }*/
}
