package com.haco.mobile_service.DAO;

import com.haco.mobile_service.entity.DetailedPlan;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DetailedPlanMapper {
    ArrayList<DetailedPlan> getDetailedPlans(String userID);          //获取指定用户当前使用套餐详情

    DetailedPlan getDetailedPlan(@Param("userID") String userID,@Param("planID")Integer planID);

    void addDetailedPlan(DetailedPlan detailedPlan);

    void updateDetailedPlan(DetailedPlan detailedPlan);

    void deleteDetailedPlans(String userID);

    void deleteDetailedPlan(@Param("userID")String userID,@Param("planID")Integer planID);
}
