package com.mzx.dao;

import com.mzx.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    List<CheckGroup> findListByCondition(String queryString);

    void add(CheckGroup formData);

    void addRelation(Map<String,Integer> addParams);

    int findCheckItemNumByList(List<Integer> checkitemIds);

    void deleteRelation(Integer id);

    void edit(CheckGroup checkGroup);

    int findSetMealNumByCheckGroupId(Integer id);

    void delete(Integer id);
}
