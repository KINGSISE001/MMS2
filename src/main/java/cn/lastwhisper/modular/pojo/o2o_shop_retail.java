package cn.lastwhisper.modular.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class o2o_shop_retail {
    private Long itemId;

    private Integer actType;

    private BigDecimal actPrice;

    private BigDecimal originPrice;

    private String appFoodCode;

    private Long dayLimit;

    private Long startTime;

    private Long endTime;

    private Long orderLimit;

    private String poiCode;

    private String period;

    private Long userType;

    private String weeksTime;

    private String name;

    private Long stock;

    private Long status;

    private Long settingType;

    private Long discountCoefficient;

    private Long sequence;

    private Date modifydate;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getActType() {
        return actType;
    }

    public void setActType(Integer actType) {
        this.actType = actType;
    }

    public BigDecimal getActPrice() {
        return actPrice;
    }

    public void setActPrice(BigDecimal actPrice) {
        this.actPrice = actPrice;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public String getAppFoodCode() {
        return appFoodCode;
    }

    public void setAppFoodCode(String appFoodCode) {
        this.appFoodCode = appFoodCode;
    }

    public Long getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(Long dayLimit) {
        this.dayLimit = dayLimit;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getOrderLimit() {
        return orderLimit;
    }

    public void setOrderLimit(Long orderLimit) {
        this.orderLimit = orderLimit;
    }

    public String getPoiCode() {
        return poiCode;
    }

    public void setPoiCode(String poiCode) {
        this.poiCode = poiCode;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public String getWeeksTime() {
        return weeksTime;
    }

    public void setWeeksTime(String weeksTime) {
        this.weeksTime = weeksTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getSettingType() {
        return settingType;
    }

    public void setSettingType(Long settingType) {
        this.settingType = settingType;
    }

    public Long getDiscountCoefficient() {
        return discountCoefficient;
    }

    public void setDiscountCoefficient(Long discountCoefficient) {
        this.discountCoefficient = discountCoefficient;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}