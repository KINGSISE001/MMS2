package cn.lastwhisper.modular.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class o2o_operate_monitor_goods {
    private Integer id;

    private Integer shopId;

    private String upc;

    private String name;

    private BigDecimal price;

    private BigDecimal stock;

    private String plv;

    private Integer ztai;

    private String from;

    private String bzhu;

    private Date modifydate;

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

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public String getPlv() {
        return plv;
    }

    public void setPlv(String plv) {
        this.plv = plv;
    }

    public Integer getZtai() {
        return ztai;
    }

    public void setZtai(Integer ztai) {
        this.ztai = ztai;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getBzhu() {
        return bzhu;
    }

    public void setBzhu(String bzhu) {
        this.bzhu = bzhu;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}