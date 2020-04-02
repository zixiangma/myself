package com.mzx.service;

import com.mzx.entiry.PageResult;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface CheckGroupService {
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void add(Map params) throws InvocationTargetException, IllegalAccessException;

    void edit(Map params);

    void delete(Integer id);
}
