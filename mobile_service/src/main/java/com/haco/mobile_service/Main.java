package com.haco.mobile_service;

import com.haco.mobile_service.DAO.*;
import com.haco.mobile_service.entity.PlanSubItem;
import com.haco.mobile_service.service.BillReview;
import com.haco.mobile_service.service.DeductionService;
import com.haco.mobile_service.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class Main {
    @Autowired
    private PlanSubMapper planSubMapper;
    @Autowired
    private PlanService planService;
    @Autowired
    private DeductionService deductionService;
    @Autowired
    private BillReview billReview;

    public void main(){
        //对某个用户进行套餐的查询（包括历史记录）、订购、退订（考虑立即生效和次月生效）操作
        //利用userID对表plansublog进行查询，获取用户所有套餐订购记录（包括历史记录）
        ArrayList<PlanSubItem> subLogs=planSubMapper.getPlanSubItems("");
        //利用planService进行套餐订购操作，内含对plansublog,user,curplan三个表的修改操作
        planService.subscribePlan("",5);
        //利用planService进行套餐订购操作，内含对plansublog,user,curplan三个表的修改操作,immediate参数指定立即生效/次月生效
        planService.cancalPlan("",3,true);
        planService.cancalPlan("",4,false);

        //------------------------------------------------------------------------------------------------------
        //某个用户在通话/短信情况下的资费生成,利用deductionService完成资费生成（修改表deductionrecord及user）
        deductionService.callDeduction("",52); //通话扣费
        deductionService.mesDeduction("",20);  //短信扣费

        //------------------------------------------------------------------------------------------------------
        //某个用户在使用流量情况下的资费生成,利用deductionService完成资费生成（修改表deductionrecord及user）
        deductionService.localDataDeduction("13528677777",1500.0); //本地流量
        deductionService.domDataDeduction("13528677777",2000.0,false);   //国内流量

        //-------------------------------------------------------------------------------------------------------
        //某个用户月账单的生成，billReview在获取ArrayList<Usage>后，将其格式化为ArrayList<String>
        //billReview提供了不限制时间及限制时间两种获取月账单的方式（历史用量数据通过定时器ScheduleService在每月1号完成存储）
        billReview.getAllMonthlyBill("");

    }
}
