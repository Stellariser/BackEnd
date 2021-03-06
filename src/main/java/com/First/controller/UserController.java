package com.First.controller;

import com.First.pojo.User;
import com.First.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/allUser",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String list() throws JsonProcessingException{
        List<User> list = userService.listUser();

        return JSONObject.toJSONString(list);
    }

    @RequestMapping(value = "/login", produces = "text/html;charset=utf-8")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public String login(String username,String password) throws JsonProcessingException {
        /*String username=loginForm.getUsername();
        String password=loginForm.getPassword();*/
        User user = userService.queryUserByName(username);
        Boolean result = checkLogin(username,password);
        Map<String, Object> map = new HashMap<>();
        Map<String,Object> loginMap = new HashMap<>();
        if (result) {
            map.put("status", 200);
            map.put("msg", "登陆成功");
            map.put("name",username);
        }else{
            map.put("status", 0);
            map.put("errorInfo","登录失败，请重新登录");
        }
        loginMap.put("meta",map);
        //String maps = loginMap.toString();

        return JSONObject.toJSONString(loginMap) ;
    }

    public boolean checkLogin(String username,String password){
        User user = userService.queryUserByName(username);
        if (user == null || "".equals(user)){
            return  false;
        }
        if (user.getPassword().equals(password)){
            return true;
        }else {
            return  false;
        }


    }
}
