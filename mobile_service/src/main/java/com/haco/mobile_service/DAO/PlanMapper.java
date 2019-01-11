package com.haco.mobile_service.DAO;

import com.haco.mobile_service.entity.Plan;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PlanMapper {
    ArrayList<Plan> getAllPlans();  //获取所有套餐

    Plan getPlan(Integer planID);    //获取指定套餐

    void addPlan(Plan plan);

    void updatePlan(Plan plan);

    void deletePlan(Integer planID);
}
