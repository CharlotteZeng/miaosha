package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.MiaoshaUserDao;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.util.MD5Util;
import com.imooc.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoshaUserService {
    @Autowired
    MiaoshaUserDao miaoshaUserDao;
    public MiaoshaUser getByid(long id){
        return miaoshaUserDao.getById(id);
    }

    public CodeMsg login(LoginVo loginVo) {
        if (null == loginVo){
            return CodeMsg.SERVER_ERROR;
        }
        //form表单提交已经进行过一次md5的密码
        String formPass = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        MiaoshaUser miaoshaoUser = getByid(Long.parseLong(mobile));
        if (null == miaoshaoUser){
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        String dbPass= miaoshaoUser.getPassword();
        String saltDB = miaoshaoUser.getSalt();
        String formPassToDBPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!dbPass.equals(formPassToDBPass)){
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }
}
