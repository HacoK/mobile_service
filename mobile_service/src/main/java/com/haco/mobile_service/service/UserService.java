package com.haco.mobile_service.service;

import com.haco.mobile_service.DAO.UserMapper;
import com.haco.mobile_service.entity.User;
import com.haco.mobile_service.util.DateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public String registerUser(String mobileNum){
        if(userMapper.getUser(mobileNum)==null){
            Date validDate=DateHandler.addAndSubtractMonthsByCalendar(new Date(new java.util.Date().getTime()),1);
            validDate=DateHandler.initDateByMonth(validDate);
            userMapper.addUser(new User(mobileNum,validDate,0.0,"",0,0,0.0,0.0,0.0));
            return "Register Successfully!";
        }
        else
            return "Mobile number Exists!!!";
    }

    public User searchUser(String mobileNum){
        return userMapper.getUser(mobileNum);
    }

    public String updateUser(User user){
        try{
            userMapper.updateUser(user);
            return "Update User Successfully!";
        }catch(Exception e){
            e.printStackTrace();
            return "Failed!!!Unexpected Error...";
        }
    }
}
