package com.mzx.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.mzx.entiry.PageResult;
import com.mzx.entiry.QueryPageBean;
import com.mzx.entiry.Result;
import com.mzx.message_constant.MessageConstant;
import com.mzx.pojo.CheckItem;
import com.mzx.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkItem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;

    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        try {
            PageResult pageResult = checkItemService.findPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.edit(checkItem);
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_SUCCESS);
        }
    }

    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.add(checkItem);
            return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_CHECKITEM_SUCCESS);
        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            checkItemService.delete(id);
            return new Result(false,MessageConstant.DELETE_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            if (MessageConstant.DELETE_CHECKITEM_CONFILCT_FAIL.equals(e.getMessage())) {
                return new Result(false,MessageConstant.DELETE_CHECKITEM_CONFILCT_FAIL);
            }
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }

}
