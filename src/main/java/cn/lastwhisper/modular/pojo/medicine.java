package cn.lastwhisper.modular.pojo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class medicine {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();
    private String id;

    private String poiCode;

    private String poiName;

    private String name;

    private String appMedicineCode;

    private String upc;

    private String medicineNo;

    private String spec;

    private String discountedPrice;

    private String price;

    private String stock;

    private String categoryCode;

    private String categoryName;

    private String isSoldOut;
    
    private String updateTime;
    private String baoPin;

    private String laiYuan;

    
    public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime() {
		this.updateTime = formatter.format(date.getTime());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPoiCode() {
		return poiCode;
	}

	public void setPoiCode(String poiCode) {
		this.poiCode = poiCode;
	}

	public String getPoiName() {
		return poiName;
	}

	public void setPoiName(String poiName) {
		this.poiName = poiName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppMedicineCode() {
		return appMedicineCode;
	}

	public void setAppMedicineCode(String appMedicineCode) {
		this.appMedicineCode = appMedicineCode;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getMedicineNo() {
		return medicineNo;
	}

	public void setMedicineNo(String medicineNo) {
		this.medicineNo = medicineNo;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(String discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getIsSoldOut() {
		return isSoldOut;
	}

	public void setIsSoldOut(String isSoldOut) {
		this.isSoldOut = isSoldOut;
	}

	public String getBaoPin() {
		return baoPin;
	}

	public void setBaoPin(String baoPin) {
		this.baoPin = baoPin;
	}

	public String getLaiYuan() {
		return laiYuan;
	}

	public void setLaiYuan(String laiYuan) {
		this.laiYuan = laiYuan;
	}

	public String getUpdateResult() {
		return updateResult;
	}

	public void setUpdateResult(String updateResult) {
		this.updateResult = updateResult;
	}

	private String updateResult;

}