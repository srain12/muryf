package xyz.ibenben.zhongdian.shortpay.dto;

public class ReceiptRequestDTO {

    private Integer status;
    private ReceiptDTO data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ReceiptDTO getData() {
        return data;
    }

    public void setData(ReceiptDTO data) {
        this.data = data;
    }
}
