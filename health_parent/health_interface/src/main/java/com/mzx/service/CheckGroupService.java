package com.mzx.service;

import com.mzx.entiry.PageResult;
import com.mzx.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    /**
     * 分页查询checkGroup
     * @param currentPage 当前页数
     * @param pageSize  每页条数
     * @param queryString 筛选条件
     * @return
     */
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    /**
     * 添加checkGroup的方法
     * @param checkitemIds 新增checkGroup项关联的checkItem id
     * @param checkGroup checkGroup 的信息
     */
    void add(Integer[] checkitemIds, CheckGroup checkGroup);

    /**
     * 根据id查找对应的checkGroup对象
     * @param id
     * @return checkGroup对象
     */
    CheckGroup findById(Integer id);

    /**
     * 根据checkGroupId获得所有与之关联的checkItemId
     * @param id
     * @return checkItem的 id集合
     */
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    /**
     * 修改checkGroup项信息及其关联的checkItem
     * @param checkitemIds
     * @param checkGroup
     */
    void edit(Integer[] checkitemIds, CheckGroup checkGroup);

    /**
     * 根据id删除对应的checkGroup对象
     * @param id
     */
    void delete(Integer id);
}
