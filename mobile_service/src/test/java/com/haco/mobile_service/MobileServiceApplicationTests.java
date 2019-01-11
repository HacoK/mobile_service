package com.haco.mobile_service;


import com.haco.mobile_service.DAO.*;
import com.haco.mobile_service.entity.*;
import com.haco.mobile_service.service.DeductionService;
import com.haco.mobile_service.service.PlanService;
import com.haco.mobile_service.service.UserService;
import com.haco.mobile_service.util.DateHandler;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MobileServiceApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MobileServiceApplicationTests {

    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private PlanSubMapper planSubMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private PlanService planService;
    @Autowired
    private DetailedPlanMapper detailedPlanMapper;
    @Autowired
    private DeductionService deductionService;
    @Autowired
    private DeductionMapper deductionMapper;
    @Autowired
    private HstUsageMapper hstUsageMapper;


    @Test
    public void A_insertPlans(){
        //1.话费套餐：月功能费20元，最多可拨打100分钟电话，超出时间按照0.5元/分钟计费。
        if(planMapper.getPlan(1)==null){
            planMapper.addPlan(new Plan(1,"callMins",100,0,0.0,0.0,20.0));
        }
        //2.短信套餐：月功能费10元，最多可发送200条短信，超出条数按0.1元/条计费。
        if(planMapper.getPlan(2)==null){
            planMapper.addPlan(new Plan(2,"mesCnt",0,200,0.0,0.0,10.0));
        }
        //3.本地流量套餐：月功能费20元，最多可获得2G流量，仅在本地使用，超出流量按2元/M计费。
        if(planMapper.getPlan(3)==null){
            planMapper.addPlan(new Plan(3,"localDataUsg",0,0,2048.0,0.0,20.0));
        }
        //4.国内流量套餐：月功能费30元，最多可获得2G流量，超出流量按5元/M计费。
        if(planMapper.getPlan(4)==null){
            planMapper.addPlan(new Plan(4,"domDataUsg",0,0,0.0,2048.0,30.0));
        }
        //5.一个套餐中带有多种资费优惠类型：月功能费40元，含50分钟电话，100条短信，1G本地流量以及1G国内流量
        if(planMapper.getPlan(5)==null){
            planMapper.addPlan(new Plan(5,"combinedPlan",50,100,1024.0,1024.0,40.0));
        }
        Assert.assertTrue(planMapper.getPlan(1).getCallMins()==100);
        Assert.assertTrue(planMapper.getPlan(2).getMesCnt()==200);
        Assert.assertTrue(planMapper.getPlan(3).getLocalData()==2048.0);
        Assert.assertTrue(planMapper.getPlan(4).getDomData()==2048.0);
        Assert.assertTrue(planMapper.getPlan(5).getPlanPrice()==40.0);
    }

    @Test
    public void B_insertUsers(){
        User user=userService.searchUser("13528677777");
        if(user==null){
            Assert.assertTrue(userService.registerUser("13528677777").equals("Register Successfully!"));
        }else{
            Assert.assertTrue(userService.registerUser("13528677777").equals("Mobile number Exists!!!"));
        }
        user=userService.searchUser("13528677777");
        user.setBalance(2000.88);
        userService.updateUser(user);
        user=userService.searchUser("13528677777");
        Assert.assertTrue(user.getBalance()==2000.88);
    }

    @Test
    public void C_planOps(){
        User user=userService.searchUser("13528677777");
        try {
            PlanSubItem planSubItem1=new PlanSubItem("13528677777",1,DateHandler.transDate("2018-9-1"),20.0,0);
            PlanSubItem planSubItem2=new PlanSubItem("13528677777",2,DateHandler.transDate("2018-9-8"),10.0,0);
            planSubMapper.addPlanSubItem(planSubItem1);
            planSubMapper.addPlanSubItem(planSubItem2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Double balance_old=user.getBalance();
        planService.subscribePlan("13528677777",3);
        planService.subscribePlan("13528677777",4);
        planService.cancalPlan("13528677777",3,true);
        planService.cancalPlan("13528677777",4,false);
        user=userService.searchUser("13528677777");
        Double balance_new=user.getBalance();
        Assert.assertTrue(balance_old-balance_new==30.0);
        ArrayList<PlanSubItem> subLogs=planSubMapper.getPlanSubItems("13528677777");
        Assert.assertTrue(subLogs.size()==4);
        for(PlanSubItem item:subLogs){
            if(item.getPlanID()!=4){
                Assert.assertTrue(item.getValidity()==0);
            }else{
                Assert.assertTrue(item.getValidity()==1);
            }
        }
        Assert.assertTrue(user.getCurPlans().equals("4"));
        ArrayList<DetailedPlan> plans=detailedPlanMapper.getDetailedPlans("13528677777");
        Assert.assertTrue(plans.size()==1);
        Assert.assertTrue(plans.get(0).getDomDataLeft()==2048.0);
    }

    @Test
    public void D_deductionCheck(){
        planService.subscribePlan("13528677777",5);
        User user=userService.searchUser("13528677777");
        Double before_balance=user.getBalance();
        deductionService.callDeduction("13528677777",52);
        deductionService.mesDeduction("13528677777",20);
        deductionService.localDataDeduction("13528677777",1500.0);
        deductionService.domDataDeduction("13528677777",2000.0,false);
        ArrayList<DeductionRecord> deductionRecords=deductionMapper.getDeductions("13528677777");
        Assert.assertTrue(deductionRecords.size()==5);
        for(DeductionRecord d:deductionRecords){
            if(d.getSrvType().equals("call")){
                Assert.assertTrue(d.getAmount()==52);
                Assert.assertTrue(d.getCost()==(52-50)*0.5);
            }else if(d.getSrvType().equals("mes")){
                Assert.assertTrue(d.getAmount()==20);
                Assert.assertTrue(d.getCost()==0.0);
            }else if(d.getSrvType().equals("localData")){
                Assert.assertTrue(d.getAmount()==1024.0);
                Assert.assertTrue(d.getCost()==0);
            }else{
                if(d.getAmount()==2000.0)
                    Assert.assertTrue(d.getCost()==0.0);
            }
        }
        DetailedPlan p=detailedPlanMapper.getDetailedPlan("13528677777",5);
        Assert.assertTrue(p.getCallMinsLeft()==0);
        Assert.assertTrue(p.getMesCntLeft()==80);
        Assert.assertTrue(p.getLocalDataLeft()==0.0);
        Assert.assertTrue(p.getDomDataLeft()==596.0);
        user=userService.searchUser("13528677777");
        Double after_balance=user.getBalance();
        Assert.assertTrue(before_balance-after_balance==1);
    }

    //应用时会使用BillReview，其通过Usage2Biil将HstUsage转换为易于理解的字符串
    //测试时为方便，测试HstUsage的DAO层即可
    @Test
    public void E_billCheck(){
        try {
            Date beg=DateHandler.transDate("2018-7-1");
            Date end=DateHandler.transDate("2018-8-1");
            Usage testU=new Usage("13528677777","3,5",52,20,2500.0,1000.0,beg,end,61.0);
            hstUsageMapper.addHstUsage(testU);
            ArrayList<Usage> usages=hstUsageMapper.getHstUsage("13528677777");
            Assert.assertTrue(usages.size()==1);
            Assert.assertTrue(usages.get(0).getPay()==61.0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
