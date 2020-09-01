package com.seed.ssm.domain;

/**
 * 游客
 */
public class Traveller {
    private int id;
    private String name;
    private String sex;
    private String phoneNum;
    private int credentialsType;//证件类型 0身份证 1护照 2军官证
    private String credentialsTypeStr;
    private String credentialsNum;
    private int travellerType;
    private String travellerTypeStr;//旅客类型(人群) 0成人 1儿童

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(int credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsTypeStr() {
        //证件类型 0 身份证 1 护照 2 军官证
        switch (getCredentialsType()){
            case 0:
                setCredentialsTypeStr("身份证");
                break;
            case 1:
                setCredentialsTypeStr("护照");
                break;
            case 2:
                setCredentialsTypeStr("军官证");
                break;
        }
        return credentialsTypeStr;
    }

    public void setCredentialsTypeStr(String credentialsTypeStr) {
        this.credentialsTypeStr = credentialsTypeStr;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public int getTravellerType() {
        return travellerType;
    }

    public void setTravellerType(int travellerType) {
        this.travellerType = travellerType;
    }

    public String getTravellerTypeStr() {
        //旅客类型(人群) 0 成人 1 儿童
        switch (getTravellerType()){
            case 0:
                setTravellerTypeStr("成人");
                break;
            case 1:
                setTravellerTypeStr("儿童");
                break;
        }
        return travellerTypeStr;
    }

    public void setTravellerTypeStr(String travellerTypeStr) {
        this.travellerTypeStr = travellerTypeStr;
    }
}
