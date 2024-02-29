package cn.lastwhisper.modular.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class o2o_operate_shop {
    private Integer id;

    private Integer shopId;

    private String dwdzhi;

    private String dwcity;

    private String dwsquan;

    private String qymcheng;
    
    private String bzhu;

    

    public String getBzhu() {
		return bzhu;
	}

	public void setBzhu(String bzhu) {
		this.bzhu = bzhu;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getDwdzhi() {
        return dwdzhi;
    }

    public void setDwdzhi(String dwdzhi) {
        this.dwdzhi = dwdzhi;
    }

    public String getDwcity() {
        return dwcity;
    }

    public void setDwcity(String dwcity) {
        this.dwcity = dwcity;
    }

    public String getDwsquan() {
        return dwsquan;
    }

    public void setDwsquan(String dwsquan) {
        this.dwsquan = dwsquan;
    }

    public String getQymcheng() {
        return qymcheng;
    }

    public void setQymcheng(String qymcheng) {
        this.qymcheng = qymcheng;
    }

}