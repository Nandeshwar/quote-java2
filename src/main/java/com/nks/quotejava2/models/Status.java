package com.nks.quotejava2.models;

import io.swagger.annotations.ApiModelProperty;

public class Status {
    @ApiModelProperty(notes = "Name of the application")
    private String appName;
    private String appVer;
    private String appStatus;
    private UpTime upTime;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public UpTime getUpTime() {
        return upTime;
    }

    public void setUpTime(UpTime upTime) {
        this.upTime = upTime;
    }
}


