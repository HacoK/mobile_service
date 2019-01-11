package com.haco.mobile_service.entity;

import java.sql.Date;

public class Usage {
    private String userID;          //用户id，即手机号码
    private String plans;           //某月使用套餐
    private Integer totalCall;      //总通话时长
    private Integer totalMes;       //总发送信息条数
    private Double totalLocalData;  //使用本地流量总量
    private Double totalDomData;    //使用国内流量总量
    private Date begDate;           //起始日期
    private Date endDate;           //截止日期
    private Double pay;             //月消费金额

    public String getUsgID() {
        return userID;
    }

    public void setUsgID(String userID) {
        this.userID = userID;
    }

    public String getPlans() {
        return plans;
    }

    public void setPlans(String plans) {
        this.plans = plans;
    }

    public Integer getTotalCall() {
        return totalCall;
    }

    public void setTotalCall(Integer totalCall) {
        this.totalCall = totalCall;
    }

    public Integer getTotalMes() {
        return totalMes;
    }

    public void setTotalMes(Integer totalMes) {
        this.totalMes = totalMes;
    }

    public Double getTotalLocalData() {
        return totalLocalData;
    }

    public void setTotalLocalData(Double totalLocalData) {
        this.totalLocalData = totalLocalData;
    }

    public Double getTotalDomData() {
        return totalDomData;
    }

    public void setTotalDomData(Double totalDomData) {
        this.totalDomData = totalDomData;
    }

    public Date getBegDate() {
        return begDate;
    }

    public void setBegDate(Date begDate) {
        this.begDate = begDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public Usage(String userID, String plans, Integer totalCall, Integer totalMes, Double totalLocalData, Double totalDomData, Date begDate, Date endDate, Double pay) {
        this.userID = userID;
        this.plans = plans;
        this.totalCall = totalCall;
        this.totalMes = totalMes;
        this.totalLocalData = totalLocalData;
        this.totalDomData = totalDomData;
        this.begDate = begDate;
        this.endDate = endDate;
        this.pay = pay;
    }
}
