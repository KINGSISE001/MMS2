package cn.lastwhisper.core.haixiang;

import java.util.List;

/**
 * 海翔自定义响应结构
 */
public class SeaFlyResult {
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static SeaFlyResult build(Integer status, String msg, Object data) {
        return new SeaFlyResult(status, msg, data);
    }

    public static SeaFlyResult ok(Object data) {
        return new SeaFlyResult(data);
    }

    public static SeaFlyResult ok() {
        return new SeaFlyResult(null);
    }

    public SeaFlyResult() {

    }

    public static SeaFlyResult build(Integer status, String msg) {
        return new SeaFlyResult(status, msg, null);
    }

    public SeaFlyResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public SeaFlyResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    @Override
    public String toString() {
        return "SeaFlyResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
