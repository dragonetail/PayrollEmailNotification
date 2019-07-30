package com.github.dragonetail.payroll.emailnotification.configuration.bean;

public class Configuration {
    private String payrollMonth = "";
    private String pdfOwnerPassword = "XAHI";
    private String emailForTest = "abc@aixahc.com";
    private String emailFromAddress = "abc@aixahc.com";

    private String smtpHost = "smtp.exmail.qq.com";
    private int smtpPort = 587;
    private String smtpUsername = "abc@aixahc.com";
    private String smtpPassword = "XAHI_Password";
    private boolean smtpAuth = true;

//    private String mailBody;
//    private String mailAttached;

    public String getPayrollMonth() {
        return payrollMonth;
    }

    public void setPayrollMonth(String payrollMonth) {
        this.payrollMonth = payrollMonth;
    }

    public String getPdfOwnerPassword() {
        return pdfOwnerPassword;
    }

    public void setPdfOwnerPassword(String pdfOwnerPassword) {
        this.pdfOwnerPassword = pdfOwnerPassword;
    }

    public String getEmailFromAddress() {
        return emailFromAddress;
    }

    public void setEmailFromAddress(String emailFromAddress) {
        this.emailFromAddress = emailFromAddress;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpUsername() {
        return smtpUsername;
    }

    public void setSmtpUsername(String smtpUsername) {
        this.smtpUsername = smtpUsername;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public boolean isSmtpAuth() {
        return smtpAuth;
    }

    public void setSmtpAuth(boolean smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    public String getEmailForTest() {
        return emailForTest;
    }

    public void setEmailForTest(String emailForTest) {
        this.emailForTest = emailForTest;
    }
}
