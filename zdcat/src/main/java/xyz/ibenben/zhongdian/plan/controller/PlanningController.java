package xyz.ibenben.zhongdian.plan.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.ibenben.zhongdian.common.utils.RequestUtil;
import xyz.ibenben.zhongdian.plan.entity.PlanRedirect;

@RestController
@RequestMapping("/planning")
public class PlanningController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private PlanRedirect planRedirect;

    @RequestMapping(value = "/redpng/{name:.+}")
    public void redpng(@PathVariable String name, HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("请求体1[{}],[{}]", RequestUtil.getAllParams(request), name);
        response.setStatus(301);
        String directUrlPre = "http://hq.keydosoft.com/scheme/plan/";
        response.sendRedirect(directUrlPre + name);
        return;
    }

    @RequestMapping(value = "/common/{project}/{folder}/{name:.+}")
    public void common(@PathVariable String project, @PathVariable String folder, @PathVariable String name, HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String filename = project + "/" + folder + "/" + name;
        logger.info("common:[{}]", filename);
        response.setStatus(301);
        String directUrlPre = planRedirect.getUrlPre();
        response.sendRedirect(directUrlPre + filename);
        return;
    }

    /**
     * CDN任意跳转
     */
    @RequestMapping(value = "/commonred/**")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String filename = request.getRequestURI().replaceAll("/planning/commonred/", "");
        response.setStatus(301);
        String directUrlPre = planRedirect.getUrlPre();
        logger.info("common:[{}],preUrl[{}]", filename, directUrlPre);
        response.sendRedirect(directUrlPre + filename);
        return;
    }

}
