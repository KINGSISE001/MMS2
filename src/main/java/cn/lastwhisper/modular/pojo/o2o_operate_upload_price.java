package cn.lastwhisper.modular.pojo;

import java.math.BigDecimal;

public class o2o_operate_upload_price {
    private Integer id;
    private Integer actType;
    private String poiCode;
    private String poiNmae;
    private String item_ids;
    private String upc;
    private String goods_code;
    private String app_medicine_code;
    private String app_food_code;
    private String goodsName;

    private BigDecimal discountedPrice;
    private BigDecimal act_price;
    private BigDecimal price;

    private BigDecimal priceOld;

    private Integer isSoldOut;

    private Integer baoPin;

    private String laiYuan;

    private Integer status;
    
    private String updateResult;

    private String result;

    private Integer start_time;
    private Integer end_time;
    private Integer order_limit;
    private Integer day_limit;
    private Integer setting_type;
    
    
    
    public String getPoiNmae() {
		return poiNmae;
	}

	public void setPoiNmae(String poiNmae) {
		this.poiNmae = poiNmae;
	}

	public Integer getActType() {
		return actType;
	}

	public void setActType(Integer actType) {
		this.actType = actType;
	}

	public String getGoods_code() {
		return goods_code;
	}

	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}

	public BigDecimal getAct_price() {
		return act_price;
	}

	public void setAct_price(BigDecimal act_price) {
		this.act_price = act_price;
	}

	public String getItem_ids() {
		return item_ids;
	}

	public void setItem_ids(String item_ids) {
		this.item_ids = item_ids;
	}

	public Integer getStart_time() {
		return start_time;
	}

	public void setStart_time(Integer start_time) {
		this.start_time = start_time;
	}

	public Integer getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Integer end_time) {
		this.end_time = end_time;
	}

	public Integer getOrder_limit() {
		return order_limit;
	}

	public void setOrder_limit(Integer order_limit) {
		this.order_limit = order_limit;
	}

	public Integer getDay_limit() {
		return day_limit;
	}

	public void setDay_limit(Integer day_limit) {
		this.day_limit = day_limit;
	}

	public Integer getSetting_type() {
		return setting_type;
	}

	public void setSetting_type(Integer setting_type) {
		this.setting_type = setting_type;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoiCode() {
        return poiCode;
    }

    public void setPoiCode(String poiCode) {
        this.poiCode = poiCode;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    

    public String getApp_food_code() {
		return app_food_code;
	}

	public void setApp_food_code(String app_food_code) {
		this.app_food_code = app_food_code;
	}

	public String getApp_medicine_code() {
		return app_medicine_code;
	}

	public void setApp_medicine_code(String app_medicine_code) {
		this.app_medicine_code = app_medicine_code;
	}

	public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(BigDecimal priceOld) {
        this.priceOld = priceOld;
    }

    public Integer getIsSoldOut() {
        return isSoldOut;
    }

    public void setIsSoldOut(Integer isSoldOut) {
        this.isSoldOut = isSoldOut;
    }

    public Integer getBaoPin() {
        return baoPin;
    }

    public void setBaoPin(Integer baoPin) {
        this.baoPin = baoPin;
    }

    public String getLaiYuan() {
        return laiYuan;
    }

    public void setLaiYuan(String laiYuan) {
        this.laiYuan = laiYuan;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpdateResult() {
        return updateResult;
    }

    public void setUpdateResult(String updateResult) {
        this.updateResult = updateResult;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}