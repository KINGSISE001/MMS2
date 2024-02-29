package cn.lastwhisper.modular.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Completedorder implements Serializable {
	Date dNow = new Date( );
    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");


    private Long orderId;

    private String orderTagList;

    private Long wmOrderIdView;

    private String appPoiCode;

    private String wmPoiName;

    private String wmPoiAddress;

    private String wmPoiPhone;

    private String recipientAddress;

    private String recipientAddressDetail;

    private String recipientPhone;

    private String backupRecipientPhone;

    private String recipientName;

    private Float shippingFee;

    private Double total;

    private Double originalPrice;

    private String caution;

    private String shipperPhone;

    private String status;

    private Integer cityId;

    private Integer hasInvoiced;

    private String invoiceTitle;

    private String taxpayerId;

    private String ctime;

    private String utime;

    private String deliveryTime;

    private Integer isThirdShipping;

    private Integer payType;

    private Integer pickType;

    private String latitude;

    private String longitude;

    private Integer daySeq;

    private String isFavorites;

    private String isPoiFirstOrder;

    private String isPreSaleOrder;

    private Integer dinnersNumber;

    private String logisticsCode;

    private String poiReceiveDetail;

    private String detail;

    private String extras;

    private String skuBenefitDetail;

    private String userMemberInfo;

    private String avgSendTime;

    private BigDecimal packageBagMoney;

    private String estimateArrivalTime;

    private String packageBagMoneyYuan;

    private String poiReceiveDetailYuan;

    private Integer totalWeight;

    private Integer incmpCode;


	private String incmpModules;

    private String orderPhoneNumber;
    
    private  String date;
    private Long TimeMillis;
    
    public Long getTimeMillis() {
		return TimeMillis;
	}

	public void setTimeMillis() {
		TimeMillis = dNow.getTime();
	}

	public String getDate() {
		return date;
	}

	public void setDate() {
		this.date =  ft.format(dNow);
	}

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    
    public String getOrderTagList() {
		return orderTagList;
	}

	public void setOrderTagList(String orderTagList) {
		this.orderTagList = orderTagList;
	}

	public Long getWmOrderIdView() {
        return wmOrderIdView;
    }

    public void setWmOrderIdView(Long wmOrderIdView) {
        this.wmOrderIdView = wmOrderIdView;
    }

    public String getAppPoiCode() {
        return appPoiCode;
    }

    public void setAppPoiCode(String appPoiCode) {
        this.appPoiCode = appPoiCode;
    }

    public String getWmPoiName() {
        return wmPoiName;
    }

    public void setWmPoiName(String wmPoiName) {
        this.wmPoiName = wmPoiName;
    }

    public String getWmPoiAddress() {
        return wmPoiAddress;
    }

    public void setWmPoiAddress(String wmPoiAddress) {
        this.wmPoiAddress = wmPoiAddress;
    }

    public String getWmPoiPhone() {
        return wmPoiPhone;
    }

    public void setWmPoiPhone(String wmPoiPhone) {
        this.wmPoiPhone = wmPoiPhone;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientAddressDetail() {
        return recipientAddressDetail;
    }

    public void setRecipientAddressDetail(String recipientAddressDetail) {
        this.recipientAddressDetail = recipientAddressDetail;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getBackupRecipientPhone() {
        return backupRecipientPhone;
    }

    public void setBackupRecipientPhone(String backupRecipientPhone) {
        this.backupRecipientPhone = backupRecipientPhone;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public Float getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Float shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getCaution() {
        return caution;
    }

    public void setCaution(String caution) {
        this.caution = caution;
    }

    public String getShipperPhone() {
        return shipperPhone;
    }

    public void setShipperPhone(String shipperPhone) {
        this.shipperPhone = shipperPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getHasInvoiced() {
        return hasInvoiced;
    }

    public void setHasInvoiced(Integer hasInvoiced) {
        this.hasInvoiced = hasInvoiced;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getTaxpayerId() {
        return taxpayerId;
    }

    public void setTaxpayerId(String taxpayerId) {
        this.taxpayerId = taxpayerId;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getIsThirdShipping() {
        return isThirdShipping;
    }

    public void setIsThirdShipping(Integer isThirdShipping) {
        this.isThirdShipping = isThirdShipping;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPickType() {
        return pickType;
    }

    public void setPickType(Integer pickType) {
        this.pickType = pickType;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getDaySeq() {
        return daySeq;
    }

    public void setDaySeq(Integer daySeq) {
        this.daySeq = daySeq;
    }

    public String getIsFavorites() {
        return isFavorites;
    }

    public void setIsFavorites(String isFavorites) {
        this.isFavorites = isFavorites;
    }

    public String getIsPoiFirstOrder() {
        return isPoiFirstOrder;
    }

    public void setIsPoiFirstOrder(String isPoiFirstOrder) {
        this.isPoiFirstOrder = isPoiFirstOrder;
    }

    public String getIsPreSaleOrder() {
        return isPreSaleOrder;
    }

    public void setIsPreSaleOrder(String isPreSaleOrder) {
        this.isPreSaleOrder = isPreSaleOrder;
    }

    public Integer getDinnersNumber() {
        return dinnersNumber;
    }

    public void setDinnersNumber(Integer dinnersNumber) {
        this.dinnersNumber = dinnersNumber;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getPoiReceiveDetail() {
        return poiReceiveDetail;
    }

    public void setPoiReceiveDetail(String poiReceiveDetail) {
        this.poiReceiveDetail = poiReceiveDetail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getSkuBenefitDetail() {
        return skuBenefitDetail;
    }

    public void setSkuBenefitDetail(String skuBenefitDetail) {
        this.skuBenefitDetail = skuBenefitDetail;
    }

    public String getUserMemberInfo() {
        return userMemberInfo;
    }

    public void setUserMemberInfo(String userMemberInfo) {
        this.userMemberInfo = userMemberInfo;
    }

    public String getAvgSendTime() {
        return avgSendTime;
    }

    public void setAvgSendTime(String avgSendTime) {
        this.avgSendTime = avgSendTime;
    }

    public BigDecimal getPackageBagMoney() {
        return packageBagMoney;
    }

    public void setPackageBagMoney(BigDecimal packageBagMoney) {
        this.packageBagMoney = packageBagMoney;
    }

    public String getEstimateArrivalTime() {
        return estimateArrivalTime;
    }

    public void setEstimateArrivalTime(String estimateArrivalTime) {
        this.estimateArrivalTime = estimateArrivalTime;
    }

    public String getPackageBagMoneyYuan() {
        return packageBagMoneyYuan;
    }

    public void setPackageBagMoneyYuan(String packageBagMoneyYuan) {
        this.packageBagMoneyYuan = packageBagMoneyYuan;
    }

    public String getPoiReceiveDetailYuan() {
        return poiReceiveDetailYuan;
    }

    public void setPoiReceiveDetailYuan(String poiReceiveDetailYuan) {
        this.poiReceiveDetailYuan = poiReceiveDetailYuan;
    }

    public Integer getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Integer totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Integer getIncmpCode() {
        return incmpCode;
    }

    public void setIncmpCode(Integer incmpCode) {
        this.incmpCode = incmpCode;
    }

    public String getIncmpModules() {
        return incmpModules;
    }

    public void setIncmpModules(String incmpModules) {
        this.incmpModules = incmpModules;
    }

    public String getOrderPhoneNumber() {
        return orderPhoneNumber;
    }

    public void setOrderPhoneNumber(String orderPhoneNumber) {
        this.orderPhoneNumber = orderPhoneNumber;
    }
}