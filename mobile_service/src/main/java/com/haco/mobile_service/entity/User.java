package com.haco.mobile_service.entity;

import java.sql.Date;

public class User {
    private String mobileNum;      //手机号，用户唯一标志
    private Date  validDate;        //用户下一月结日（初始为入网日期下个月一号）
    private Double balance;        //账户余额
    private String curPlans;       //当前使用套餐，以，分隔的int
    private Integer curCall;       //目前通话时长（月结）
    private Integer curMes;        //目前发送信息条数（月结）
    private Double curLocalData;   //目前使用本地流量（月结）
    private Double curDomData;     //目前使用国内流量（月结）
    private Double curPay;         //目前已付金额


    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCurPlans() {
        return curPlans;
    }

    public void setCurPlans(String curPlans) {
        this.curPlans = curPlans;
    }

    public Integer getCurCall() {
        return curCall;
    }

    public void setCurCall(Integer curCall) {
        this.curCall = curCall;
    }

    public Integer getCurMes() {
        return curMes;
    }

    public void setCurMes(Integer curMes) {
        this.curMes = curMes;
    }

    public Double getCurLocalData() {
        return curLocalData;
    }

    public void setCurLocalData(Double curLocalData) {
        this.curLocalData = curLocalData;
    }

    public Double getCurDomData() {
        return curDomData;
    }

    public void setCurDomData(Double curDomData) {
        this.curDomData = curDomData;
    }

    public Double getCurPay() {
        return curPay;
    }

    public void setCurPay(Double curPay) {
        this.curPay = curPay;
    }

    public User(String mobileNum, Date validDate, Double balance, String curPlans, Integer curCall, Integer curMes, Double curLocalData, Double curDomData, Double curPay) {
        this.mobileNum = mobileNum;
        this.validDate = validDate;
        this.balance = balance;
        this.curPlans = curPlans;
        this.curCall = curCall;
        this.curMes = curMes;
        this.curLocalData = curLocalData;
        this.curDomData = curDomData;
        this.curPay = curPay;
    }
}
