package xyz.ibenben.zhongdian.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.ibenben.zhongdian.system.entity.User;
import xyz.ibenben.zhongdian.system.service.UserService;

@Controller
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/regiester")
    public String regiester(Map<String, Object> model) {
        userService.saveUser(null);
        model.put("msg", "张三丰");
        return "/user/regiester";
    }

    @ResponseBody
    @RequestMapping("/listuser")
    public List<User> listUser(HttpServletRequest request, HttpServletResponse response) {
        return userService.listByState(1);
    }

    @ResponseBody
    @RequestMapping("singleuser")
    public User getUser(HttpServletRequest request, HttpServletResponse response) {
        int id = NumberUtils.toInt(request.getParameter("id"), 1);
        return userService.singleById(id);
    }
}
