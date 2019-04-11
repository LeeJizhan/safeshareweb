package stu.edu.jack.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import stu.edu.jack.entity.MessageCode;
import stu.edu.jack.entity.User;
import stu.edu.jack.service.UserLoginService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Asus- on 2019/4/4.
 */
@Controller
@RequestMapping(value = {"/user"})
public class UserLoginController {
    /**
     * 注入service
     */
    @Autowired
    private UserLoginService userLoginService;

    /**
     * 跳转到用户登录页面
     *
     * @return 登录页面
     */
    @RequestMapping(value = {"/loginHtml"})
    public String loginHtml() {
        return "login";
    }

    /**
     * 跳转到用户注册页面
     *
     * @return 注册页面
     */
    @RequestMapping(value = {"/registerPage"})
    public String registerPage() {
        return "register";
    }

    /**
     * 获取用户名与密码，用户登录
     *
     * @return 登录成功页面
     */
    @ResponseBody
    @RequestMapping(value = {"/userLogin"})
    public MessageCode userLogin(@RequestParam("idCardNum") String idCardNum,
                                 @RequestParam("password") String password, HttpServletRequest request) {

        User user = userLoginService.userLogin(idCardNum, password);
        MessageCode messageCode = new MessageCode();
        //登陆成功
        if (user != null) {
            //将用户信息放入session
            request.getSession().setAttribute("session_user", user);
            messageCode.setCode(1);
            messageCode.setMsg("success");
            return messageCode;
        }
        messageCode.setCode(-1);
        messageCode.setMsg("fail");
        return messageCode;
    }

    /**
     * 注册新用户
     *
     * @return 注册结果
     */
    @ResponseBody
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public MessageCode addUser(@RequestParam("username") String username,
                               @RequestParam("idCardNumber") String idCardNumber,
                               @RequestParam("realName") String realName,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("password") String password) {
        User user;
        user = userLoginService.checkUsername(username);
        MessageCode messageCode = new MessageCode();
        if (user != null) {
            messageCode.setCode(-1);
            messageCode.setMsg("用户名已存在");
            return messageCode;
        }

        user = userLoginService.checkIDCard(idCardNumber);
        if (user != null) {
            messageCode.setCode(-1);
            messageCode.setMsg("身份证已被注册");
            return messageCode;
        }

        user = userLoginService.checkPhoneNumber(phoneNumber);
        if (user != null) {
            messageCode.setCode(-1);
            messageCode.setMsg("手机号已被注册");
            return messageCode;
        }

        //对信息进行加密再保存

        int res = userLoginService.addUser(username, password, idCardNumber, realName, phoneNumber);
        if (res == 0) {
            messageCode.setCode(-1);
            messageCode.setMsg("注册失败");
            return messageCode;
        } else {
            messageCode.setCode(1);
            messageCode.setMsg("success");
            return messageCode;
        }
    }

    @ResponseBody
    @RequestMapping(value = {"/info"}, method = RequestMethod.GET)
    public String getInfo(@RequestParam("username") String username) {
        User user = userLoginService.getUserInfo(username);
        Gson gson = new Gson();
        if (user != null)
            return gson.toJson(user);
        else {
            MessageCode messageCode = new MessageCode();
            messageCode.setCode(-1);
            messageCode.setMsg("User: " + username + " not found!");
            return gson.toJson(messageCode);
        }
    }
}
