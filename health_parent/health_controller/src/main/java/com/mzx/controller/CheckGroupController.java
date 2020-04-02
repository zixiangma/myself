package com.mzx.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mzx.entiry.PageResult;
import com.mzx.entiry.QueryPageBean;
import com.mzx.entiry.Result;
import com.mzx.message_constant.MessageConstant;
import com.mzx.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult data = checkGroupService.findPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Map params){
        try {
            checkGroupService.add(params);
            return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            if (MessageConstant.EDIT_CHECKGROUP_CONFILCT_FAIL.equals(e.getMessage())) {
                return new Result(false,MessageConstant.EDIT_CHECKGROUP_CONFILCT_FAIL);
            }
            return new Result(false,MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Map params){
        try {
            checkGroupService.edit(params);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            if (MessageConstant.EDIT_CHECKGROUP_CONFILCT_FAIL.equals(e.getMessage())) {
                return new Result(false,MessageConstant.EDIT_CHECKGROUP_CONFILCT_FAIL);
            }
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            checkGroupService.delete(id);
            return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            if (MessageConstant.DELETE_CHECKITEM_CONFILCT_FAIL.equals(e.getMessage())) {
                return new Result(false,MessageConstant.DELETE_CHECKITEM_CONFILCT_FAIL);
            }
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }
}
