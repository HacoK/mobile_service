package com.haco.mobile_service.entity;

public class Plan {
    /*
    ('callMins', 'mesCnt', 'localDataUsg', 'domDataUsg', 'combinedPlan',various plans...)
    暂定：
    1：callMins
    2：mesCnt
    3：localDataUsg
    4：domDataUsg
    5：combinedPlan
    */
    private Integer planID;         //套餐id
    private String planName;        //套餐名
    private Integer callMins;       //套餐内通话时长
    private Integer mesCnt;         //套餐内信息条数
    private Double localData;       //套餐内本地流量
    private Double domData;         //套餐内国内流量
    private Double planPrice;       //套餐价格

    public Integer getPlanID() {
        return planID;
    }

    public void setPlanID(Integer planID) {
        this.planID = planID;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Integer getCallMins() {
        return callMins;
    }

    public void setCallMins(Integer callMins) {
        this.callMins = callMins;
    }

    public Integer getMesCnt() {
        return mesCnt;
    }

    public void setMesCnt(Integer mesCnt) {
        this.mesCnt = mesCnt;
    }

    public Double getLocalData() {
        return localData;
    }

    public void setLocalData(Double localData) {
        this.localData = localData;
    }

    public Double getDomData() {
        return domData;
    }

    public void setDomData(Double domData) {
        this.domData = domData;
    }

    public Double getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(Double planPrice) {
        this.planPrice = planPrice;
    }

    public Plan(Integer planID, String planName, Integer callMins, Integer mesCnt, Double localData, Double domData, Double planPrice) {
        this.planID = planID;
        this.planName = planName;
        this.callMins = callMins;
        this.mesCnt = mesCnt;
        this.localData = localData;
        this.domData = domData;
        this.planPrice = planPrice;
    }
}
