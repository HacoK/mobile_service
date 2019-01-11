package com.haco.mobile_service.service;

import com.haco.mobile_service.DAO.DetailedPlanMapper;
import com.haco.mobile_service.DAO.PlanMapper;
import com.haco.mobile_service.DAO.PlanSubMapper;
import com.haco.mobile_service.entity.DetailedPlan;
import com.haco.mobile_service.entity.Plan;
import com.haco.mobile_service.entity.PlanSubItem;
import com.haco.mobile_service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.sql.Date;

@Service
public class PlanService {
    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private PlanSubMapper planSubMapper;

    @Autowired
    private DetailedPlanMapper detailedPlanMapper;

    @Autowired
    private DeductionService deductionService;

    @Autowired
    private UserService userService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String subscribePlan(String userID,Integer planID){
        User user=userService.searchUser(userID);
        Plan plan=planMapper.getPlan(planID);
        if(user!=null){
            if(plan!=null){
                Double balance=user.getBalance();
                Double price=plan.getPlanPrice();
                {
                    //更新用户余额及套餐
                    balance-=price;
                    user.setCurPay(user.getCurPay()+price);
                    user.setBalance(balance);
                    String curPlans=user.getCurPlans();
                    if(curPlans.equals("")){
                        curPlans+=planID;
                    }else{
                        curPlans+=(","+planID);
                    }
                    user.setCurPlans(curPlans);
                    //新建套餐订购记录
                    PlanSubItem subItem=new PlanSubItem(userID,planID,new Date(new java.util.Date().getTime()),price,1);
                    //新建套餐详细信息记录
                    DetailedPlan detailedPlan=new DetailedPlan(userID,planID,plan.getCallMins(),plan.getMesCnt(),plan.getLocalData(),plan.getDomData(),0);
                    //更新数据库中对应表
                    userService.updateUser(user);
                    planSubMapper.addPlanSubItem(subItem);
                    detailedPlanMapper.addDetailedPlan(detailedPlan);
                    return "Subscribe "+plan.getPlanName()+" for User"+user.getMobileNum()+" Successfully!";
                }
            }else{
                return "Plan Not Exists!!!";
            }
        }
        else{
            return "User Not Exists!!!";
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String cancalPlan(String userID,Integer planID,Boolean immediate){
        User user=userService.searchUser(userID);
        String target=String.valueOf(planID);
        Plan plan=planMapper.getPlan(planID);
        if(plan==null)
            return "Plan Not Exists!!!";
        if(user!=null){
            String[] curPlans=user.getCurPlans().split(",");
            ArrayList<String> planList=new ArrayList<>();
            boolean exist=false;
            for(String s:curPlans){
                if(s.equals(target)){
                    exist=true;
                }else{
                    planList.add(s);
                }
            }
            if(exist){
                if(!immediate){
                    DetailedPlan targetPlan = detailedPlanMapper.getDetailedPlan(userID,planID);
                    targetPlan.setCalNextMonth(1);
                    detailedPlanMapper.updateDetailedPlan(targetPlan);
                    return "User"+user.getMobileNum()+" will cancal "+plan.getPlanName()+" next month!";
                }
                else{
                    user.setCurPlans(String.join(",",planList));
                    DetailedPlan targetPlan = detailedPlanMapper.getDetailedPlan(userID,planID);
                    Integer callMinsSpent=plan.getCallMins()-targetPlan.getCallMinsLeft();
                    Integer mesCntSpent=plan.getMesCnt()-targetPlan.getMesCntLeft();
                    Double localDataSpent=plan.getLocalData()-targetPlan.getLocalDataLeft();
                    Double domDataSpent=plan.getDomData()-targetPlan.getDomDataLeft();
                    user.setBalance(user.getBalance()+plan.getPlanPrice());
                    user.setCurPay(user.getCurPay()-plan.getPlanPrice());
                    if(callMinsSpent!=0)
                        deductionService.callDeduction(userID,callMinsSpent);
                    if(mesCntSpent!=0)
                        deductionService.mesDeduction(userID,mesCntSpent);
                    if(localDataSpent!=0)
                        deductionService.localDataDeduction(userID,localDataSpent);
                    if(domDataSpent!=0)
                        deductionService.domDataDeduction(userID,domDataSpent,false);
                    userService.updateUser(user);

                    PlanSubItem planSubItem=planSubMapper.getRecentPlanSubItem(userID,planID);
                    planSubItem.setValidity(0);
                    planSubMapper.updatePlanSubItem(planSubItem);

                    detailedPlanMapper.deleteDetailedPlan(userID,planID);
                    return "Cancel "+plan.getPlanName()+" for User"+user.getMobileNum()+" Successfully!\n"
                            +"Spent "+callMinsSpent+" callMins,"+mesCntSpent+" mesCnt,"
                            +localDataSpent+" M localData,"+domDataSpent+" M domData in canceled plan...";
                }
            }else{
                return "User Not Subscribe this plan yet!!!";
            }
        }else{
            return "User Not Exists!!!";
        }
    }
}
