package com.haco.mobile_service.DAO;

import com.haco.mobile_service.entity.DeductionRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DeductionMapper {
    ArrayList<DeductionRecord> getDeductions(String userID);          //获取指定用户所有扣费记录

    void addDeduction(DeductionRecord deduction);

    void updateDeduction(DeductionRecord deduction);

    void deleteDeduction(Integer deID);
}
