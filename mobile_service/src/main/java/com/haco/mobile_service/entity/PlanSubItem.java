package com.haco.mobile_service.entity;

import java.sql.Date;

public class PlanSubItem {
    private String userID;     //用户id，即手机号
    private Integer planID;    //开通套餐id
    private Date subDate;      //开通日期
    private Double cost;       //付费金额
    private Integer validity;  //是否有效，退订则失效

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

    public Date getSubDate() {
        return subDate;
    }

    public void setSubDate(Date subDate) {
        this.subDate = subDate;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }


    public PlanSubItem(String userID, Integer planID, Date subDate, Double cost, Integer validity) {
        this.userID = userID;
        this.planID = planID;
        this.subDate = subDate;
        this.cost = cost;
        this.validity = validity;
    }
}
