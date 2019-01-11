package com.haco.mobile_service.DAO;

import com.haco.mobile_service.entity.PlanSubItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface PlanSubMapper {
    ArrayList<PlanSubItem> getPlanSubItems(String userID);          //获取指定用户所有套餐购买记录

    PlanSubItem getRecentPlanSubItem(@Param("userID") String userID, @Param("planID")Integer planID);

    void addPlanSubItem(PlanSubItem planSubItem);

    void updatePlanSubItem(@Param("subItem") PlanSubItem subItem);

    void deletePlanSubItems(Date subDate);
}
