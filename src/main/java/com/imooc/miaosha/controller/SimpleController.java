package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SimpleController {

    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","zhc");
        return "hello";
    }
    @RequestMapping("/result")
    @ResponseBody
    public Result<String> hello(Model model){
        return Result.success("result success");
    }
    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(Model model){
        User name = redisService.get(UserKey.getById,"1", User.class);
        return Result.success("result success!  "+name);
    }
    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<String> redisSet(Model model){
        User user = new User();
        user.setId(1);
        user.setName("new name");

        boolean set = redisService.set(UserKey.getById,"1", user);
        if (set)
            return Result.success("result success!  ");
        else
            return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/getUserById")
    @ResponseBody
    public Result<User> getUserById() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    /**
     * 事务测试
     * @return
     */
    @RequestMapping("/tx")
    @ResponseBody
    public Result<String> dbtx() {
        userService.tx();
        return Result.success("success");
    }
}
