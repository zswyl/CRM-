package com.jiangqi.crm.controller;

import com.jiangqi.crm.vo.CustomerServe;
import com.jiangqi.crm.base.BaseController;
import com.jiangqi.crm.base.ResultInfo;
import com.jiangqi.crm.query.CustomerServeQuery;
import com.jiangqi.crm.service.CustomerServeService;
import com.jiangqi.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**

 */
@RequestMapping("customer_serve")
@Controller
public class CustomerServeController extends BaseController {

    @Resource
    private CustomerServeService customerServeService;


    /**
     * 多条件分页查询服务数据的列表
     *
     *

     * @param customerServeQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCustomerServeByParams(CustomerServeQuery customerServeQuery,
                                                         Integer flag, HttpServletRequest request) {

        // 判断是否执行服务处理，如果是则查询分配给当前登录用户的服务记录
        if (flag != null && flag == 1) {
            // 设置查询条件：分配人
            customerServeQuery.setAssigner(LoginUserUtil.releaseUserIdFromCookie(request));
        }

        return customerServeService.queryCustomerServeByParams(customerServeQuery);
    }


    /**
     * 通过不同的类型进入不同的服务页面
     *
     *

     * @param type
     * @return java.lang.String
     */
    @RequestMapping("index/{type}")
    public String index(@PathVariable Integer type) {
        // 判断类型是否为空
        if (type != null) {
            if (type == 1) {

                // 服务创建
                return "customerServe/customer_serve";

            } else if (type == 2) {

                // 服务分配
                return "customerServe/customer_serve_assign";

            } else if (type == 3) {

                // 服务处理
                return "customerServe/customer_serve_proce";

            } else if (type == 4) {

                // 服务反馈
                return "customerServe/customer_serve_feed_back";

            } else if (type == 5) {

                // 服务归档
                return "customerServe/customer_serve_archive";

            } else {
                return "";
            }

        } else {
            return "";
        }
    }


    /**
     * 打开创建服务页面
     *
     *

     * @param
     * @return java.lang.String
     */
    @RequestMapping("toAddCustomerServePage")
    public String toAddCustomerServePage() {
        return "customerServe/customer_serve_add";
    }


    /**
     * 创建服务
     *
     *

     * @param customerServe
     * @return com.jiangqi.crm.base.ResultInfo
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addCustomerServe(CustomerServe customerServe) {
        customerServeService.addCustomerServe(customerServe);
        return success("添加服务成功！");
    }


    /**
     * 服务更新
     *     1. 服务分配
     *     2. 服务处理
     *     3.服务反馈
     *
     *

     * @param customerServe
     * @return com.jiangqi.crm.base.ResultInfo
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateCustomerServe(CustomerServe customerServe) {
        customerServeService.updateCustomerServe(customerServe);
        return success("服务更新成功！");
    }


    /**
     * 打开服务分配页面
     *
     *

     * @param
     * @return java.lang.String
     */
    @RequestMapping("toCustomerServeAssignPage")
    public String toCustomerServeAssignPage(Integer id, Model model) {
        // 通过id查询服务记录，并设置到请求域中
        model.addAttribute("customerServe", customerServeService.selectByPrimaryKey(id));
        return "customerServe/customer_serve_assign_add";
    }


    /**
     * 打开服务处理页面
     *
     *

     * @param
     * @return java.lang.String
     */
    @RequestMapping("toCustomerServeProcePage")
    public String toCustomerServeProcePage(Integer id, Model model) {
        // 通过id查询服务记录，并设置到请求域中
        model.addAttribute("customerServe", customerServeService.selectByPrimaryKey(id));
        return "customerServe/customer_serve_proce_add";
    }

    /**
     * 打开服务反馈页面
     *
     *

     * @param id
     * @param model
     * @return java.lang.String
     */
    @RequestMapping("toCustomerServeFeedBackPage")
    public String toCustomerServeFeedBackPage(Integer id, Model model) {
        // 通过id查询服务记录，并设置到请求域中
        model.addAttribute("customerServe", customerServeService.selectByPrimaryKey(id));
        return "customerServe/customer_serve_feed_back_add";
    }

}
