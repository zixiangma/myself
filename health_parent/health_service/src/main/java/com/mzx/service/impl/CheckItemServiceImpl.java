package com.mzx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mzx.dao.CheckItemDao;
import com.mzx.entiry.PageResult;
import com.mzx.message_constant.MessageConstant;
import com.mzx.pojo.CheckItem;
import com.mzx.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        List<CheckItem> rows = checkItemDao.findListByCondition(queryString);
        PageInfo<CheckItem> pageInfo = new PageInfo<>(rows);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public void delete(Integer id) {
        int count= checkItemDao.findCheckGroupNumBycheckItemId(id);
        if (count > 0) {
            throw new RuntimeException(MessageConstant.DELETE_CHECKITEM_CONFILCT_FAIL);
        }
        checkItemDao.delete(id);
    }

}
