package com.mzx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mzx.dao.CheckGroupDao;
import com.mzx.entiry.PageResult;
import com.mzx.message_constant.MessageConstant;
import com.mzx.pojo.CheckGroup;
import com.mzx.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 分页查找具体实现
     * @param currentPage 当前页数
     * @param pageSize  每页条数
     * @param queryString 筛选条件
     * @return
     */
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        // 交由pageHelper对象管理分页
        PageHelper.startPage(currentPage,pageSize);
        //根据查询条件筛序出checkGroup,pageHelper会自动实现分页功能
        List<CheckGroup> rows =  checkGroupDao.findByCondition(queryString);
        PageInfo<CheckGroup> pageInfo = new PageInfo<>(rows);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加checkGroup的方法
     * @param checkitemIds 新增checkGroup项关联的checkItem id
     * @param checkGroup checkGroup 的信息
     */
    @Override
    public void add(Integer[] checkitemIds, CheckGroup checkGroup) {
        // 在表中添加新的checkGroup项,并返回完整的数据(id)
         checkGroupDao.add(checkGroup);
         //添加checkItem关联
        addRelationShip(checkitemIds,checkGroup.getId());
    }

    private void addRelationShip(Integer[] checkitemIds,Integer checkGrroupId) {
        if (checkitemIds != null && checkitemIds.length > 0) {
            //创建存储查询条件的map集合
            HashMap<String, Integer> params = new HashMap<>();
            params.put("checkGroupId",checkGrroupId);
            for (Integer checkitemId : checkitemIds) {
                params.put("checkItemId",checkitemId);
                checkGroupDao.addRelationShip(params);
            }
        }
    }

    /**
     * 跟i据id查找checkGroup对象
     * @param id
     * @return checkGroup对象
     */
    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    /**
     * 根据checkGroupId获得所有与之关联的checkItemId
     * @param id
     * @return checkItem的 id集合
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    /**
     * 修改checkGroup项信息及其关联的checkItem
     * @param checkitemIds
     * @param checkGroup
     */
    @Override
    public void edit(Integer[] checkitemIds, CheckGroup checkGroup) {
        //修改对应的checkGroup项
        checkGroupDao.edit(checkGroup);
        //删除该checkGroup所有的关联checkItem
        checkGroupDao.deleteRelationShip(checkGroup.getId());
        //添加关联信息
        addRelationShip(checkitemIds,checkGroup.getId());
    }

    /**
     * 根据id删除对应的checkGroup对象
     * @param id
     */
    @Override
    public void delete(Integer id) {
        //查询与checkItem关联数
        int checkItemCount = checkGroupDao.findCheckItemCountByCheckGroupId(id);
        if (checkItemCount > 0) {
            throw new RuntimeException(MessageConstant.DELETE_CHECKGROUP_CONFILCT_FAIL);
        }
        //查询与setMeal关联数
        int setMealCount = checkGroupDao.findSetMealCountByCheckGroupId(id);
        if (setMealCount > 0) {
            throw new RuntimeException(MessageConstant.DELETE_CHECKGROUP_SETMEAL_CONFILCT_FAIL);
        }
        //删除checkGroup对象
        checkGroupDao.delete(id);
    }


}
