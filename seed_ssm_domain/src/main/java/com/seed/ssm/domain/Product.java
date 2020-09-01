package com.seed.ssm.domain;

import com.seed.ssm.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 产品实体类
 */
public class Product {
    private int id; //主键
    private String productNum;//产品编号
    private String productName;//产品名称
    private String cityName;//出发城市
    @DateTimeFormat(pattern = "yy-MM-dd HH:mm")
    private Date departureTime;//出发时间
    private String departureTimeStr;
    private double productPrice;//产品价格
    private String productDesc;//产品描述
    private int productStatus;//产品状态 0:关闭 1：开启
    private String productStatusStr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureTimeStr() {
        if (getDepartureTime() != null) {
            String dateStr = DateUtils.date2String(getDepartureTime(), "yyy-MM-dd hh:mm:ss");
            setDepartureTimeStr(dateStr);
        }
        return departureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        this.departureTimeStr = departureTimeStr;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductStatusStr() {
        //产品状态 0:关闭 1：开启
        if (getProductStatus() == 0)
            setProductStatusStr("关闭");
        else if (getProductStatus() == 1)
            setProductStatusStr("开启");
        return productStatusStr;
    }

    public void setProductStatusStr(String productStatusStr) {

        this.productStatusStr = productStatusStr;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productNum='" + productNum + '\'' +
                ", productName='" + productName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", departureTime=" + departureTime +
                ", departureTimeStr='" + departureTimeStr + '\'' +
                ", productPrice=" + productPrice +
                ", productDesc='" + productDesc + '\'' +
                ", productStatus=" + productStatus +
                ", productStatusStr='" + productStatusStr + '\'' +
                '}';
    }
}
