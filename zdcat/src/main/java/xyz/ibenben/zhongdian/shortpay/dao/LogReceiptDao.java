package xyz.ibenben.zhongdian.shortpay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import xyz.ibenben.zhongdian.common.BaseDao;
import xyz.ibenben.zhongdian.common.utils.Constants;
import xyz.ibenben.zhongdian.shortpay.entity.LogReceipt;

public interface LogReceiptDao extends BaseDao<LogReceipt> {

    @Select("select * from pay_log_receipt where 1=1 and accesstime>=#{0} and accesstime<#{1} limit " + Constants.LIST_MAX)
    public List<LogReceipt> listLogReceiptByTime(String begindate, String enddate, String result);
}
