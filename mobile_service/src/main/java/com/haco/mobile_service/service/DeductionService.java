package com.haco.mobile_service.service;

import com.haco.mobile_service.DAO.DeductionMapper;
import com.haco.mobile_service.DAO.DetailedPlanMapper;
import com.haco.mobile_service.entity.DeductionRecord;
import com.haco.mobile_service.entity.DetailedPlan;
import com.haco.mobile_service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.sql.Date;

@Service
public class DeductionService {
    @Autowired
    private DetailedPlanMapper detailedPlanMapper;
    @Autowired
    private DeductionMapper deductionMapper;
    @Autowired
    private UserService userService;

    //"call, mes, localData,domData"
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String callDeduction(String userID,Integer amount){
        Integer amount_copy=amount;
        User user=userService.searchUser(userID);
        if(user==null)
            return "User Not Exists!!!";
        Double cost=0.0;
        ArrayList<DetailedPlan> planList=detailedPlanMapper.getDetailedPlans(userID);
        for(DetailedPlan p:planList){
            int leftAmount=p.getCallMinsLeft();
            if(leftAmount!=0){
                if(leftAmount>=amount){
                    leftAmount-=amount;
                    amount=0;
                    p.setCallMinsLeft(leftAmount);
                    detailedPlanMapper.updateDetailedPlan(p);
                    break;
                }else{
                    amount-=leftAmount;
                    leftAmount=0;
                    p.setCallMinsLeft(leftAmount);
                    detailedPlanMapper.updateDetailedPlan(p);
                }
            }
        }
        if(amount>0){
            cost+=amount*0.5;
        }
        user.setBalance(user.getBalance()-cost);
        user.setCurPay(user.getCurPay()+cost);
        user.setCurCall(user.getCurCall()+amount_copy);
        userService.updateUser(user);
        DeductionRecord newRecord=new DeductionRecord(0,userID,"call",(double)amount_copy,cost,new Date(new java.util.Date().getTime()));
        deductionMapper.addDeduction(newRecord);
        return "Call Duration:"+amount_copy+" mins\n"+"Call Cost:"+String.format("%1$.2f", cost)+" RMB\n";
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String mesDeduction(String userID,Integer amount){
        Integer amount_copy=amount;
        User user=userService.searchUser(userID);
        if(user==null)
            return "User Not Exists!!!";
        Double cost=0.0;
        ArrayList<DetailedPlan> planList=detailedPlanMapper.getDetailedPlans(userID);
        for(DetailedPlan p:planList){
            int leftAmount=p.getMesCntLeft();
            if(leftAmount!=0){
                if(leftAmount>=amount){
                    leftAmount-=amount;
                    amount=0;
                    p.setMesCntLeft(leftAmount);
                    detailedPlanMapper.updateDetailedPlan(p);
                    break;
                }else{
                    amount-=leftAmount;
                    leftAmount=0;
                    p.setMesCntLeft(leftAmount);
                    detailedPlanMapper.updateDetailedPlan(p);
                }
            }
        }
        if(amount>0){
            cost+=amount*0.1;
        }
        user.setBalance(user.getBalance()-cost);
        user.setCurPay(user.getCurPay()+cost);
        user.setCurMes(user.getCurMes()+amount_copy);
        userService.updateUser(user);
        DeductionRecord newRecord=new DeductionRecord(0,userID,"mes",(double)amount_copy,cost,new Date(new java.util.Date().getTime()));
        deductionMapper.addDeduction(newRecord);
        return "Mes Count:"+amount_copy+" texts\n"+"Mes Cost:"+String.format("%1$.2f", cost)+" RMB\n";
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String localDataDeduction(String userID,Double amount){
        Double amount_copy=amount;
        User user=userService.searchUser(userID);
        if(user==null)
            return "User Not Exists!!!";
        Double cost=0.0;
        ArrayList<DetailedPlan> planList=detailedPlanMapper.getDetailedPlans(userID);
        for(DetailedPlan p:planList){
            double leftAmount=p.getLocalDataLeft();
            if(leftAmount!=0){
                if(leftAmount>=amount){
                    leftAmount-=amount;
                    amount=0.0;
                    p.setLocalDataLeft(leftAmount);
                    detailedPlanMapper.updateDetailedPlan(p);
                    break;
                }else{
                    amount-=leftAmount;
                    leftAmount=0;
                    p.setLocalDataLeft(leftAmount);
                    detailedPlanMapper.updateDetailedPlan(p);
                }
            }
        }
        if(amount>0){
            this.domDataDeduction(userID,amount,true);
        }
        user.setCurLocalData(user.getCurLocalData()+(amount_copy-amount));
        userService.updateUser(user);
        DeductionRecord newRecord=new DeductionRecord(0,userID,"localData",(amount_copy-amount),cost,new Date(new java.util.Date().getTime()));
        deductionMapper.addDeduction(newRecord);
        return "LocalData Amount:"+String.format("%1$.2f", (amount_copy-amount))+" M\n"+"LocalData Cost:"+String.format("%1$.2f", cost)+" RMB\n";
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String domDataDeduction(String userID,Double amount,Boolean local){
        Double amount_copy=amount;
        User user=userService.searchUser(userID);
        if(user==null)
            return "User Not Exists!!!";
        Double cost=0.0;
        ArrayList<DetailedPlan> planList=detailedPlanMapper.getDetailedPlans(userID);
        for(DetailedPlan p:planList){
            double leftAmount=p.getDomDataLeft();
            if(leftAmount!=0){
                if(leftAmount>=amount){
                    leftAmount-=amount;
                    amount=0.0;
                    p.setDomDataLeft(leftAmount);
                    detailedPlanMapper.updateDetailedPlan(p);
                    break;
                }else{
                    amount-=leftAmount;
                    leftAmount=0;
                    p.setDomDataLeft(leftAmount);
                    detailedPlanMapper.updateDetailedPlan(p);
                }
            }
        }
        if(amount>0){
            if(local)
                cost+=amount*2;
            else{
                cost+=amount*5;
            }
        }
        user.setBalance(user.getBalance()-cost);
        user.setCurPay(user.getCurPay()+cost);
        user.setCurDomData(user.getCurDomData()+amount_copy);
        userService.updateUser(user);
        DeductionRecord newRecord=new DeductionRecord(0,userID,"domData",(amount_copy-amount),0.0,new Date(new java.util.Date().getTime()));
        deductionMapper.addDeduction(newRecord);
        if(amount>0){
            if(local)
            {
                newRecord=new DeductionRecord(0,userID,"localData",amount,cost,new Date(new java.util.Date().getTime()));
                deductionMapper.addDeduction(newRecord);
            }else{
                newRecord=new DeductionRecord(0,userID,"domData",amount,cost,new Date(new java.util.Date().getTime()));
                deductionMapper.addDeduction(newRecord);
            }
        }
        return "";
    }
}
