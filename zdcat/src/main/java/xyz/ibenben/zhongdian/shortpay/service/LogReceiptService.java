package xyz.ibenben.zhongdian.shortpay.service;

import java.util.List;

import xyz.ibenben.zhongdian.shortpay.entity.LogReceipt;

public interface LogReceiptService {

    public void saveLogReceipt(LogReceipt log);
    
    /**
     * 查询列表信息
     * @return
     */
    public List<LogReceipt> listLogReceiptByTime(String begindate, String enddate, String result);
}
