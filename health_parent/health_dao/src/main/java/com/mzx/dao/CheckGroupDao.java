package com.mzx.dao;

import com.mzx.pojo.CheckGroup;

import java.util.HashMap;
import java.util.List;

public interface CheckGroupDao {
    /**
     * 根据给定条件查询checkGroup
     * @param queryString 条件的值
     * @return checkGroup结果集合
     */
    List<CheckGroup> findByCondition(String queryString);

    /**
     * 添加checkGroup项的方法
     * @param checkGroup 新增checkGroup的信息
     */
    void add(CheckGroup checkGroup);

    /**
     * 添加checkGroup的与checkItem的关联关系
     * @param params 参数
     */
    void addRelationShip(HashMap<String, Integer> params);

    /**
     * 根据id获得checkGroup对象
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
     * 根据id修改对应checkGroup对象的信息
     * @param checkGroup
     */
    void edit(CheckGroup checkGroup);

    /**
     * 根据id删除对应checkGroup项的所有checkItem关联关系
     * @param id
     */
    void deleteRelationShip(Integer id);

    /**
     * 根据id删除对应的checkGroup对象
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据checkGroupId获得所有关联checkItem的数量
     * @param id
     * @return
     */
    int findCheckItemCountByCheckGroupId(Integer id);

    /**
     * 根据checkGroupId获得所有关联setMeal的数量
     * @param id
     * @return
     */
    int findSetMealCountByCheckGroupId(Integer id);
}
