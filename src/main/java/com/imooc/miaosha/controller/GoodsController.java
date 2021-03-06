package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品列表页
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {


    @Autowired
    RedisService redisService;
//    @Autowired
//    private MiaoshaUserService miaoshaUserService;
    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);

    /**
     *
     * @param model
     * @param cookieToken 传统web形式的cookie
     * @param paramToken 兼容app入参形式的cookie
     * @param user 是使用Spring注入进来的对象 详见webConfig 使用此参数注入后不用再使用 cookieToken和paramToken两个注解了
     * @required 标记的参数是否必须有值 false不需要有值 true需要有值
     * @return
     */
    @RequestMapping("/to_list")
    public String list(//HttpServletResponse response,
                       Model model,MiaoshaUser user
//                          @CookieValue(value=MiaoshaUserService.COOKIE_NAME_TOKEN,required = false)String cookieToken,
//                          @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN,required = false)String paramToken
    ){
        /*if(StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)){
            return "login";
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        MiaoshaUser user = miaoshaUserService.getByToken(response,token);*/
        model.addAttribute("user",user);
        return "goods_list";

    }


}
