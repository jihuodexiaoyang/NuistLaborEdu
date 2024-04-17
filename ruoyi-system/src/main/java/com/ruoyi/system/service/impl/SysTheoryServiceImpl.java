package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SysClassDept;
import com.ruoyi.system.domain.SysRoleMenu;
import com.ruoyi.system.mapper.SysClassDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysTheoryMapper;
import com.ruoyi.system.domain.SysTheory;
import com.ruoyi.system.service.ISysTheoryService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 理论学习Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-12-19
 */
@Service
public class SysTheoryServiceImpl implements ISysTheoryService 
{
    @Autowired
    private SysTheoryMapper sysTheoryMapper;
    @Autowired
    private SysClassDeptMapper sysClassDeptMapper;

    @Override
    public int changeStatus(SysTheory sysTheory) {
        return sysTheoryMapper.updateSysTheory(sysTheory);
    }

    /**
     * 查询理论学习
     * 
     * @param classId 理论学习主键
     * @return 理论学习
     */
    @Override
    public SysTheory selectSysTheoryByClassId(Long classId)
    {
        return sysTheoryMapper.selectSysTheoryByClassId(classId);
    }

    @Override
    public int updateSelected(SysTheory sysTheory) {
        return sysTheoryMapper.updateSelected(sysTheory);
    }

    @Override
    public List<SysTheory> selectSysTheoryListSelected(SysTheory sysTheory) {
        return sysTheoryMapper.selectSysTheoryListSelected(sysTheory);
    }

    @Override
    public int updateDeSelected(SysTheory sysTheory) {
        return sysTheoryMapper.updateDeSelected(sysTheory);
    }

    /**
     * 查询理论学习列表
     * 
     * @param sysTheory 理论学习
     * @return 理论学习
     */
    @Override
    public List<SysTheory> selectSysTheoryList(SysTheory sysTheory)
    {
        return sysTheoryMapper.selectSysTheoryList(sysTheory);
    }

    @Override
    public List<SysTheory> selectSysTheoryListSelect(SysTheory sysTheory) {
        return sysTheoryMapper.selectSysTheoryListSelect(sysTheory);
    }

    /**
     * 新增理论学习
     * 
     * @param sysTheory 理论学习
     * @return 结果
     */
    @Override
    public int insertSysTheory(SysTheory sysTheory)
    {
        sysTheory.setCreateTime(DateUtils.getNowDate());
        sysTheoryMapper.insertSysTheory(sysTheory);
        return insertClassDept(sysTheory);
    }
    /**
     * 新增课程部门信息
     *
     */

    public int insertClassDept(SysTheory sysTheory)
    {
        int rows = 1;
        // 新增用户与角色管理
        List<SysClassDept> list = new ArrayList<SysClassDept>();
        for (Long deptId : sysTheory.getDeptIds())
        {
            SysClassDept cd = new SysClassDept();
            cd.setClassId(sysTheory.getClassId());
            cd.setClassType("0");
            cd.setClassDept(deptId);
            list.add(cd);
        }
        if (list.size() > 0)
        {
            rows = sysClassDeptMapper.batchClassDept(list);
        }
        return rows;
    }
    /**
     * 修改理论学习
     * 
     * @param sysTheory 理论学习
     * @return 结果
     */
    @Override
    @Transactional
    public int updateSysTheory(SysTheory sysTheory)
    {
        sysTheory.setUpdateTime(DateUtils.getNowDate());
        sysTheoryMapper.updateSysTheory(sysTheory);
        sysClassDeptMapper.deleteSysClassDeptByClassId1(sysTheory.getClassId());
        return insertClassDept(sysTheory);
    }

    /**
     * 批量删除理论学习
     * 
     * @param classIds 需要删除的理论学习主键
     * @return 结果
     */
    @Override
    public int deleteSysTheoryByClassIds(String classIds)
    {
        return sysTheoryMapper.deleteSysTheoryByClassIds(Convert.toStrArray(classIds));
    }

    /**
     * 删除理论学习信息
     * 
     * @param classId 理论学习主键
     * @return 结果
     */
    @Override
    public int deleteSysTheoryByClassId(Long classId)
    {
        return sysTheoryMapper.deleteSysTheoryByClassId(classId);
    }
}
