package xyz.ibenben.zhongdian.shortpay.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 回执日志
 *
 */
@Entity
@Table(name="pay_log_receipt")
public class LogReceipt implements Serializable {

    private static final long serialVersionUID = 8317356442362992115L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp accesstime;
    private String orderid; // 平台订单号
    private String param; // 合作方透传参数（渠道号）
    private String result; // 判断订购成功标识，result 成功 succ, 失败 fail
    private String excode; // 状态码
    private String exmsg; // 状态说明

    public LogReceipt() {
        super();
    }

    public LogReceipt(Timestamp accesstime, String orderid, String param, String result, String excode, String exmsg) {
        super();
        this.accesstime = accesstime;
        this.orderid = orderid;
        this.param = param;
        this.result = result;
        this.excode = excode;
        this.exmsg = exmsg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getAccesstime() {
        return accesstime;
    }

    public void setAccesstime(Timestamp accesstime) {
        this.accesstime = accesstime;
    }

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

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
