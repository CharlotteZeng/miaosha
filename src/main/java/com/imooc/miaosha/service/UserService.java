package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.UserDao;
import com.imooc.miaosha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User getById(int id){
        return userDao.getUserById(id);
    }

    /**
     * 事务测试
     * @return
     */
    @Transactional
    public boolean tx() {
        User u2 = new User(2, "222222");
        User u1 = new User(1,"11111");
        userDao.insert(u1);
        userDao.insert(u2);

        return true;

    }
}
