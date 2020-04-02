package com.mzx.dao;

import com.mzx.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    List<CheckItem> findListByCondition(String queryString);

    void edit(CheckItem checkItem);

    void add(CheckItem checkItem);

    void delete(Integer id);

    int findCheckGroupNumBycheckItemId(Integer Id);

    List<CheckItem> findCheckListByCheckGroupId(Integer checkGroupId);
}
