package com.haco.mobile_service.DAO;

import com.haco.mobile_service.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserMapper {
    ArrayList<User> getAllUsers();       //获取所有用户

    User getUser(String userID);    //获取指定用户

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(String userID);
}
