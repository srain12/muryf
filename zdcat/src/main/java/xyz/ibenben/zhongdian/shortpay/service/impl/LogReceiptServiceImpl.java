package xyz.ibenben.zhongdian.shortpay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.ibenben.zhongdian.shortpay.dao.LogReceiptDao;
import xyz.ibenben.zhongdian.shortpay.entity.LogReceipt;
import xyz.ibenben.zhongdian.shortpay.service.LogReceiptService;

@Service
public class LogReceiptServiceImpl implements LogReceiptService {

    @Autowired
    private LogReceiptDao logReceiptDao;
    
    public void saveLogReceipt(LogReceipt log) {
        logReceiptDao.insert(log);
    }

    public List<LogReceipt> listLogReceiptByTime(String begindate, String enddate, String result) {
        return logReceiptDao.listLogReceiptByTime(begindate, enddate, result);
    }

}
