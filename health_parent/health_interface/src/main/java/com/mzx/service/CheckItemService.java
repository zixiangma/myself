package com.mzx.service;

import com.mzx.entiry.PageResult;
import com.mzx.pojo.CheckItem;

public interface CheckItemService {
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void edit(CheckItem checkItem);

    void add(CheckItem checkItem);

    void delete(Integer id);
}
