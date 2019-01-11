package com.haco.mobile_service.util;

import com.haco.mobile_service.entity.Usage;

public class Usage2Bill {
    public static String trans(Usage usage){
        String userID="UserID:"+usage.getUserID()+"\n";
        String duration="Duration:"+usage.getBegDate()+"--"+usage.getEndDate()+"\n";
        String plans="Plans(ID):"+usage.getPlans()+"\n";
        String call="TotalCall:"+usage.getTotalCall()+"\n";
        String mes="TotalMes:"+usage.getTotalMes()+"\n";
        String local="TotalLocalData(M):"+String.format("%1$.2f", usage.getTotalLocalData())+"\n";
        String dom="TotalDomData(M):"+String.format("%1$.2f", usage.getTotalDomData())+"\n";
        String pay="Payment(RMB):"+String.format("%1$.2f", usage.getPay())+"\n";
        return userID+duration+plans+call+mes+local+dom+pay;
    }
}
