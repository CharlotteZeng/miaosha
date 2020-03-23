package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.MiaoshaUserDao;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.exception.GlobalException;
import com.imooc.miaosha.redis.MiaoshaUserKey;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.util.MD5Util;
import com.imooc.miaosha.util.UUIDUtil;
import com.imooc.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserService {

    public static  final  String COOKIE_NAME_TOKEN="token";

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;

    public MiaoshaUser getByid(long id){
        return miaoshaUserDao.getById(id);
    }

    public boolean login(HttpServletResponse response,LoginVo loginVo) {
        if (null == loginVo){
//            return CodeMsg.SERVER_ERROR;
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        //form表单提交已经进行过一次md5的密码
        String formPass = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        MiaoshaUser miaoshaUser = getByid(Long.parseLong(mobile));
        if (null == miaoshaUser){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        String dbPass= miaoshaUser.getPassword();
        String saltDB = miaoshaUser.getSalt();
        String formPassToDBPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!dbPass.equals(formPassToDBPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成Cookie
        addCookie(response,miaoshaUser);
        return true;
    }

    public MiaoshaUser getByToken(HttpServletResponse response,String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //延长有效期
        addCookie(response,user);
        return user;
    }
    private void addCookie(HttpServletResponse response,MiaoshaUser miaoshaUser){
        String token = UUIDUtil.getUUID();
        redisService.set(MiaoshaUserKey.token,token,miaoshaUser);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
