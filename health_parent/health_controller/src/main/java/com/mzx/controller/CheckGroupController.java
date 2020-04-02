package com.mzx.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mzx.entiry.PageResult;
import com.mzx.entiry.QueryPageBean;
import com.mzx.entiry.Result;
import com.mzx.message_constant.MessageConstant;
import com.mzx.pojo.CheckGroup;

import com.mzx.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 分页查询checkGroup
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult data = checkGroupService.findPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
            return  new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    /**
     * 添加checkGroup的方法
     * @param checkitemIds 新增checkGroup项关联的checkItem id
     * @param checkGroup checkGroup 的信息
     * @return 结果集
     */
    @RequestMapping("/add")
    public Result add(Integer[] checkitemIds,CheckGroup checkGroup){
        try {
            checkGroupService.add(checkitemIds,checkGroup);
            return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    /**
     * 根据id查询CheckGroup对象
     * @param id
     * @return 结果集
     */
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    /**
     * 根据checkGroupId获得所有与之关联的checkItemId
     * @param id
     * @return 结果集
     */
    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer id) {
        try {
            List<Integer> checkitemIds =checkGroupService.findCheckItemIdsByCheckGroupId(id);
             return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    /**
     * 修改checkGroup项信息及其关联的checkItem
     * @param checkitemIds
     * @param checkGroup
     * @return
     */
    @RequestMapping("/edit")
    public Result edit(Integer[] checkitemIds,@RequestBody CheckGroup checkGroup) {
        try {
            checkGroupService.edit(checkitemIds,checkGroup);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    /**
     * 根据id删除对应的checkGroup对象
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            checkGroupService.delete(id);
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        }catch(RuntimeException e){
            e.printStackTrace();
            return new Result(false,e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }
}
