package com.First.service;

import com.First.dao.UserMapper;
import com.First.pojo.User;


import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    List<User> listUser();

    User queryUserByName(String name);
}
