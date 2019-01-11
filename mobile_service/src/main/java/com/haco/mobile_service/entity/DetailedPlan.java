package com.haco.mobile_service.entity;

public class DetailedPlan {
    private String userID;              //用户id，即手机号
    private Integer planID;             //套餐id
    private Integer callMinsLeft;       //套餐剩余通话时长
    private Integer mesCntLeft;         //套餐剩余信息条数
    private Double localDataLeft;       //套餐剩余本地流量
    private Double domDataLeft;         //套餐剩余国内流量
    private Integer calNextMonth;       //下个月是否取消套餐,默认续订，即为false

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getPlanID() {
        return planID;
    }

    public void setPlanID(Integer planID) {
        this.planID = planID;
    }

    public Integer getCallMinsLeft() {
        return callMinsLeft;
    }

    public void setCallMinsLeft(Integer callMinsLeft) {
        this.callMinsLeft = callMinsLeft;
    }

    public Integer getMesCntLeft() {
        return mesCntLeft;
    }

    public void setMesCntLeft(Integer mesCntLeft) {
        this.mesCntLeft = mesCntLeft;
    }

    public Double getLocalDataLeft() {
        return localDataLeft;
    }

    public void setLocalDataLeft(Double localDataLeft) {
        this.localDataLeft = localDataLeft;
    }

    public Double getDomDataLeft() {
        return domDataLeft;
    }

    public void setDomDataLeft(Double domDataLeft) {
        this.domDataLeft = domDataLeft;
    }

    public Integer getCalNextMonth() {
        return calNextMonth;
    }

    public void setCalNextMonth(Integer calNextMonth) {
        this.calNextMonth = calNextMonth;
    }

    public DetailedPlan(String userID, Integer planID, Integer callMinsLeft, Integer mesCntLeft, Double localDataLeft, Double domDataLeft, Integer calNextMonth) {
        this.userID = userID;
        this.planID = planID;
        this.callMinsLeft = callMinsLeft;
        this.mesCntLeft = mesCntLeft;
        this.localDataLeft = localDataLeft;
        this.domDataLeft = domDataLeft;
        this.calNextMonth = calNextMonth;
    }
}
