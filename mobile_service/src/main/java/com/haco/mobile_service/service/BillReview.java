package com.haco.mobile_service.service;

import com.haco.mobile_service.DAO.HstUsageMapper;
import com.haco.mobile_service.entity.Usage;
import com.haco.mobile_service.util.Usage2Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class BillReview {
    @Autowired
    private HstUsageMapper hstUsageMapper;

    public ArrayList<String> getAllMonthlyBill(String userID){
        ArrayList<Usage> usages=hstUsageMapper.getHstUsage(userID);
        ArrayList<String> result=new ArrayList<>();
        for(Usage usage:usages){
            result.add(Usage2Bill.trans(usage));
        }
        return result;
    }

    public ArrayList<String> getMonthlyBill(String userID,Date beg, Date end){
        ArrayList<Usage> usages=hstUsageMapper.getHstUsageLimited(userID,beg,end);
        ArrayList<String> result=new ArrayList<>();
        for(Usage usage:usages){
            result.add(Usage2Bill.trans(usage));
        }
        return result;
    }
}
