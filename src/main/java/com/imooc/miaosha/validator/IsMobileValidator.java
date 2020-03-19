package com.imooc.miaosha.validator;

import com.imooc.miaosha.util.ValidatorUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {
    private boolean required = false;
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required){

               return ValidatorUtil.isMobile(value);
        }else {
            if (StringUtils.isEmpty(value)){
                //如果为空返回允许
                return true;
            }else {
                //如果不为空 验证手机格式
                return ValidatorUtil.isMobile(value);
            }
        }

    }
}
