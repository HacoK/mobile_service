package com.haco.mobile_service.entity;

import java.sql.Date;

public class DeductionRecord {
    private Integer deID;    //扣费记录id
    private String userID;   //用户id，即手机号
    private String srvType;  //扣费类型，通话/短信/流量("call, mes, localData,domData")
    private Double amount;   //服务使用量，用来计算cost
    private Double cost;     //本次扣费金额
    private Date deTime;     //扣费日期

    public Integer getDeID() {
        return deID;
    }

    public void setDeID(Integer deID) {
        this.deID = deID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSrvType() { return srvType; }

    public void setSrvType(String srvType) {
        this.srvType = srvType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Date getDeTime() {
        return deTime;
    }

    public void setDeTime(Date deTime) {
        this.deTime = deTime;
    }

    public DeductionRecord(Integer deID, String userID, String srvType, Double amount, Double cost, Date deTime) {
        this.deID = deID;
        this.userID = userID;
        this.srvType = srvType;
        this.amount = amount;
        this.cost = cost;
        this.deTime = deTime;
    }
}
