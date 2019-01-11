package com.haco.mobile_service.service;

import com.haco.mobile_service.DAO.*;
import com.haco.mobile_service.entity.*;
import com.haco.mobile_service.util.DateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.sql.Date;

@Component
public class ScheduleService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DetailedPlanMapper detailedPlanMapper;
    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private PlanSubMapper planSubMapper;
    @Autowired
    private HstUsageMapper hstUsageMapper;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(cron = "0 0 0 1 * ?")
    public void refreshInfo(){
        ArrayList<User> users=userMapper.getAllUsers();
        for(User user:users){
            hstUsageMapper.addHstUsage(new Usage(user.getMobileNum(),user.getCurPlans(),user.getCurCall(),user.getCurMes(),user.getCurLocalData(),user.getCurDomData(),DateHandler.addAndSubtractMonthsByCalendar(new Date(new java.util.Date().getTime()),-1),new Date(new java.util.Date().getTime()),user.getCurPay()));
            user.setCurPay(0.0);
            ArrayList<DetailedPlan> cur_plans=detailedPlanMapper.getDetailedPlans(user.getMobileNum());
            ArrayList<String> new_plans=new ArrayList<>();
            for(DetailedPlan p:cur_plans){
                if(p.getCalNextMonth()==1){
                    detailedPlanMapper.deleteDetailedPlan(p.getUserID(),p.getPlanID());
                }else{
                    new_plans.add(String.valueOf(p.getPlanID()));
                    Plan temp=planMapper.getPlan(p.getPlanID());
                    user.setBalance(user.getBalance()-temp.getPlanPrice());
                    user.setCurPay(user.getCurPay()+temp.getPlanPrice());
                    p.setCallMinsLeft(temp.getCallMins());
                    p.setMesCntLeft(temp.getMesCnt());
                    p.setLocalDataLeft(temp.getLocalData());
                    p.setDomDataLeft(temp.getDomData());
                    planSubMapper.addPlanSubItem(new PlanSubItem(user.getMobileNum(),p.getPlanID(),new Date(new java.util.Date().getTime()),temp.getPlanPrice(),1));
                    detailedPlanMapper.updateDetailedPlan(p);
                }
            }
            String planList=String.join(",",new_plans);
            user.setCurPlans(planList);
            user.setValidDate(DateHandler.addAndSubtractMonthsByCalendar(new Date(new java.util.Date().getTime()),1));
            user.setCurCall(0);
            user.setCurMes(0);
            user.setCurLocalData(0.0);
            user.setCurDomData(0.0);
            userMapper.updateUser(user);
        }
    }
}
