package com.imooc.miaosha.exception;

import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import org.springframework.http.HttpRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    //不加注解 异常无法拦截到
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
        if (e instanceof BindException){
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError objectError = errors.get(0);
            String args = objectError.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(args));
        }else  if (e instanceof GlobalException){
            CodeMsg cm = ((GlobalException) e).getCm();

            return Result.error(cm);
        }else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
