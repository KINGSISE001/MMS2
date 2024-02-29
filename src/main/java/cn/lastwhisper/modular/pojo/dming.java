package cn.lastwhisper.modular.pojo;

public class dming {
    private Integer id;
    private String  leixing;
    private String weizhi;
    private String sousuo;
    private String dizhi;
	private String dm;
    private Integer zhuangtai;
    private String bzhu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getLeixing() {
 		return leixing;
 	}

 	public void setLeixing(String leixing) {
 		this.leixing = leixing;
 	}

 	public String getBzhu() {
 		return bzhu;
 	}

 	public void setBzhu(String bzhu) {
 		this.bzhu = bzhu;
 	}

    public String getWeizhi() {
        return weizhi;
    }

    public void setWeizhi(String weizhi) {
        this.weizhi = weizhi;
    }

    public String getSousuo() {
        return sousuo;
    }

    public void setSousuo(String sousuo) {
        this.sousuo = sousuo;
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }

    public Integer getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(Integer zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

	@Override
	public String toString() {
		return "dming [id=" + id + ", weizhi=" + weizhi + ", sousuo=" + sousuo + ", dizhi=" + dizhi + ", dm=" + dm
				+ ", zhuangtai=" + zhuangtai + ", getId()=" + getId() + ", getWeizhi()=" + getWeizhi()
				+ ", getSousuo()=" + getSousuo() + ", getDizhi()=" + getDizhi() + ", getDm()=" + getDm()
				+ ", getZhuangtai()=" + getZhuangtai() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}