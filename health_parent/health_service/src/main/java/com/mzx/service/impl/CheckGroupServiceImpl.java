package com.mzx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mzx.dao.CheckGroupDao;
import com.mzx.entiry.PageResult;
import com.mzx.message_constant.MessageConstant;
import com.mzx.pojo.CheckGroup;
import com.mzx.service.CheckGroupService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        List<CheckGroup>  rows = checkGroupDao.findListByCondition(queryString);
        PageInfo<CheckGroup> pageInfo = new PageInfo<>(rows);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public void add(Map params) throws InvocationTargetException, IllegalAccessException {
        JSONObject formData = (JSONObject) params.get("formData");
        CheckGroup checkGroup = new CheckGroup();
        BeanUtils.populate(checkGroup,formData);
        checkGroupDao.add(checkGroup);
        List<Integer> checkitemIds = (List<Integer>) params.get("checkitemIds");
        HashMap<String, Integer> addParams = new HashMap<>();
        addParams.put("checkGroupId",checkGroup.getId());
        if (checkitemIds != null && checkitemIds.size() > 0) {
            int checkItemNum = checkGroupDao.findCheckItemNumByList(checkitemIds);
            if (checkItemNum != checkitemIds.size()) {
                throw new RuntimeException(MessageConstant.EDIT_CHECKGROUP_CONFILCT_FAIL);
            }
            for (Integer checkitemId : checkitemIds) {
                addParams.put("checkItemId",checkitemId);
                checkGroupDao.addRelation(addParams);
            }
        }
    }
    @Override
    public void edit(Map params) {
        JSONObject formData = (JSONObject) params.get("formData");
        CheckGroup checkGroup = new CheckGroup();
        try {
            BeanUtils.populate(checkGroup,formData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("参数转换异常");
        }
        checkGroupDao.edit(checkGroup);
        List<Integer> checkitemIds = (List<Integer>) params.get("checkitemIds");
        HashMap<String, Integer> addParams = new HashMap<>();
        addParams.put("checkGroupId",checkGroup.getId());
        checkGroupDao.deleteRelation(checkGroup.getId());
        if (checkitemIds != null && checkitemIds.size() > 0) {
            int checkItemNum = checkGroupDao.findCheckItemNumByList(checkitemIds);
            if (checkItemNum != checkitemIds.size()) {
                throw new RuntimeException(MessageConstant.EDIT_CHECKGROUP_CONFILCT_FAIL);
            }
            for (Integer checkitemId : checkitemIds) {
                addParams.put("checkItemId",checkitemId);
                checkGroupDao.addRelation(addParams);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        int setMealNum = checkGroupDao.findSetMealNumByCheckGroupId(id);
        if (setMealNum > 0) {
            throw new RuntimeException(MessageConstant.DELETE_CHECKITEM_CONFILCT_FAIL);
        }
        checkGroupDao.deleteRelation(id);
        checkGroupDao.delete(id);

    }
}
