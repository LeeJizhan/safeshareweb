package stu.edu.jack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stu.edu.jack.entity.User;
import stu.edu.jack.mapper.UserMapper;

/**
 * Created by Asus- on 2019/4/4.
 */
@Service
public class UserLoginService {
    /**
     * 注入dao
     */
    @Autowired
    private UserMapper userMapper;

    //用户登录
    public User userLogin(String idCardNum, String password) {
        User user = userMapper.userLogin(idCardNum, password);
        return user;
    }

    //注册新用户
    public int addUser(String username, String password, String idCardNumber,
                       String realName, String phoneNumber) {

        return userMapper.addUser(username, password, idCardNumber, realName, phoneNumber);
    }

    //检查用户名是否已被注册
    public User checkUsername(String username) {
        return userMapper.checkUsername(username);
    }

    //检查身份证是否已被注册
    public User checkIDCard(String idCard) {
        return userMapper.checkIDCard(idCard);
    }

    //检查手机号是否已被注册
    public User checkPhoneNumber(String phoneNumber) {
        return userMapper.checkPhoneNumber(phoneNumber);
    }

    //查找用户信息
    public User getUserInfo(String username) {
        return userMapper.getUserInfo(username);
    }
}
