package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.MiaoshaUserService;
import com.imooc.miaosha.service.UserService;
import com.imooc.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;
    @Autowired
    private MiaoshaUserService miaoshaUserService;
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";

    }
    @ResponseBody
    @RequestMapping("/doLogin")
    public Result<Object> login(HttpServletResponse response,@Valid LoginVo loginVo){
        logger.info("登陆开始。。。。。。");
        logger.info("登陆参数："+loginVo.toString());
        logger.info("登陆结束。。。。。。");
       /* String passwordInput = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        if (StringUtils.isEmpty(passwordInput)){
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (StringUtils.isEmpty(mobile)){
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if (!ValidatorUtil.isMobile(mobile)){
            return Result.error(CodeMsg.MOBILE_ERROR);
        }*/
        miaoshaUserService.login(response,loginVo);
        return Result.success(true);
    }

}
