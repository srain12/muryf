package xyz.ibenben.zhongdian.shortpay.dto;

public class ReceiptDTO {

    private String orderid; // 平台订单号
    private String param; // 合作方透传参数（渠道号）
    private String result; // 判断订购成功标识，result 成功 succ, 失败 fail
    private String excode; // 状态码
    private String exmsg; // 状态说明

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getExcode() {
        return excode;
    }

    public void setExcode(String excode) {
        this.excode = excode;
    }

    public String getExmsg() {
        return exmsg;
    }

    public void setExmsg(String exmsg) {
        this.exmsg = exmsg;
    }
}
