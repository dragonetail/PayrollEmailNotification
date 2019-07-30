package com.github.dragonetail.payroll.emailnotification.payrolls.bean;

import java.util.Date;

public class Payroll {
    private long id;
    private String no;
    private String name;
    private String department;
    private String level;
    private String baseSalary;
    private String jobSalary;
    private String awardSalary;
    private String otherSalary;
    private String socialInsurance;
    private String housingFund;
    private String specialAdditionalDeduction;
    private String leavingDeduction;
    private String otherDeduction;
    private String givingWithTax;
    private String tax;
    private String giving;
    private String remarks;
    //序号	工号	姓名	部门	级别	基本工资	职位工资	评价工资	其他应发	社会保险	住房公积金
    // 专项附加扣除	请假	其他应扣	税前应付	个人所得税	实发金额	备注

    private String sentStatus;
    private String sentTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(String baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getAwardSalary() {
        return awardSalary;
    }

    public void setAwardSalary(String awardSalary) {
        this.awardSalary = awardSalary;
    }

    public String getOtherSalary() {
        return otherSalary;
    }

    public void setOtherSalary(String otherSalary) {
        this.otherSalary = otherSalary;
    }

    public String getSocialInsurance() {
        return socialInsurance;
    }

    public void setSocialInsurance(String socialInsurance) {
        this.socialInsurance = socialInsurance;
    }

    public String getHousingFund() {
        return housingFund;
    }

    public void setHousingFund(String housingFund) {
        this.housingFund = housingFund;
    }

    public String getSpecialAdditionalDeduction() {
        return specialAdditionalDeduction;
    }

    public void setSpecialAdditionalDeduction(String specialAdditionalDeduction) {
        this.specialAdditionalDeduction = specialAdditionalDeduction;
    }

    public String getLeavingDeduction() {
        return leavingDeduction;
    }

    public void setLeavingDeduction(String leavingDeduction) {
        this.leavingDeduction = leavingDeduction;
    }

    public String getOtherDeduction() {
        return otherDeduction;
    }

    public void setOtherDeduction(String otherDeduction) {
        this.otherDeduction = otherDeduction;
    }

    public String getGivingWithTax() {
        return givingWithTax;
    }

    public void setGivingWithTax(String givingWithTax) {
        this.givingWithTax = givingWithTax;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getGiving() {
        return giving;
    }

    public void setGiving(String giving) {
        this.giving = giving;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(String sentStatus) {
        this.sentStatus = sentStatus;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

    @Override
    public String toString() {
        return no + " " + name;
    }
}
