package xyz.ibenben.zhongdian.shortpay.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;

import xyz.ibenben.zhongdian.common.utils.RequestUtil;
import xyz.ibenben.zhongdian.shortpay.dto.ReceiptRequestDTO;
import xyz.ibenben.zhongdian.shortpay.entity.LogReceipt;
import xyz.ibenben.zhongdian.shortpay.service.LogReceiptService;

@Controller
@RequestMapping("/pay")
public class PayInterfaceController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogReceiptService logReceiptService;

    @ResponseBody
    @RequestMapping("/list-logreceipt.do")
    public List<LogReceipt> listLogReceipt(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestJson = RequestUtil.getRequestJsonString(request);
        logger.info("请求体[{}]", requestJson);
        logger.info("请求体1[{}]", RequestUtil.getAllParams(request));
        Date currentDate = new Date();
        String begindate = StringUtils.defaultIfEmpty(request.getParameter("begindate"), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(currentDate));
        String enddate = StringUtils.defaultIfEmpty(request.getParameter("enddate"), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(currentDate));
        String result = StringUtils.trimToEmpty(request.getParameter("result"));
        // ObjectMapper mapper = new ObjectMapper();
        response.setHeader("Access-Control-Allow-Origin", "*"); // 为了支持跨域请求
        logger.info("begindate:[{}],enddate[{}],result[{}]", begindate, enddate, result);
        return logReceiptService.listLogReceiptByTime(begindate, enddate, result);
    }
    
    /**
     * 回执接口
     */
    @ResponseBody
    @RequestMapping("/receipt.do")
    public Map<String, Object> receipt(HttpServletRequest request, HttpServletResponse response) {
        logger.info("请求头[{}]", RequestUtil.getAllHeader(request));
        String contentType = request.getHeader("content-type"); // 获取请求类型：兼容postjson形式
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ReceiptRequestDTO receiptRequest = null;
        if ("application/json".equals(contentType)) {
            try {
                String requestJson = RequestUtil.getRequestJsonString(request);
                logger.info("请求体[{}]", requestJson);
                ObjectMapper mapper = new ObjectMapper();
                receiptRequest = mapper.readValue(requestJson, ReceiptRequestDTO.class);
                saveLog(receiptRequest, request, resultMap);
                resultMap.put("status", 200);
                return resultMap;
            } catch (IOException e) {
                logger.error("回执接口异常：", e);
                resultMap.put("status", 202);
                resultMap.put("msg", "请求错误!");
            }
        }
        logger.info("请求体[{}]", RequestUtil.getAllParams(request));
        saveLog(receiptRequest, request, resultMap);
        // logger.info(receiptDTO.toString());
        if (CollectionUtils.isEmpty(resultMap)) {
            resultMap.put("status", 200);
        }
        return resultMap;
    }

    private void saveLog(ReceiptRequestDTO receiptRequest, HttpServletRequest request, Map<String, Object> resultMap) {
        try {
            String orderid = "";
            String param = "";
            String result = "";
            String excode = "";
            String exmsg = "";
            if (receiptRequest != null) {
                orderid = receiptRequest.getData().getOrderid();
                param = receiptRequest.getData().getParam();
                result = receiptRequest.getData().getResult();
                excode = receiptRequest.getData().getExcode();
                exmsg = receiptRequest.getData().getExmsg();
            } else {
                orderid = Strings.nullToEmpty(request.getParameter("orderid"));
                param = Strings.nullToEmpty(request.getParameter("param"));
                result = Strings.nullToEmpty(request.getParameter("result"));
                excode = Strings.nullToEmpty(request.getParameter("excode"));
                exmsg = Strings.nullToEmpty(request.getParameter("exmsg"));
            }
            // 保存数据
            if (Strings.isNullOrEmpty(orderid) || Strings.isNullOrEmpty(param) || Strings.isNullOrEmpty(result)) {
                logger.info("数据校验不通过：orderid[{}],param[{}],result[{}]", orderid, param, result);
                resultMap.put("status", 201);
                resultMap.put("msg", "数据校验不通过：orderid[{}],param[{}],result[{}]");
            } else {
                LogReceipt log = new LogReceipt(new Timestamp(System.currentTimeMillis()), orderid, param, result, excode, exmsg);
                logReceiptService.saveLogReceipt(log);
            }
        } catch (Exception e) {
            logger.error("回执接口异常：", e);
        }
    }
}
