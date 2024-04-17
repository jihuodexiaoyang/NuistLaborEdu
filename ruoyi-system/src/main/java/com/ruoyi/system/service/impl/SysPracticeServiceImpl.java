package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SysClassDept;
import com.ruoyi.system.domain.SysTheory;
import com.ruoyi.system.mapper.SysClassDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysPracticeMapper;
import com.ruoyi.system.domain.SysPractice;
import com.ruoyi.system.service.ISysPracticeService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 实践学习Service业务层处理
 *
 * @author ruoyi
 * @date 2021-12-19
 */
@Service
public class SysPracticeServiceImpl implements ISysPracticeService {
    @Autowired
    private SysPracticeMapper sysPracticeMapper;
    @Autowired
    private SysClassDeptMapper sysClassDeptMapper;
    @Override
    public int changeStatus(SysPractice sysPractice) {
        return sysPracticeMapper.updateSysPractice(sysPractice);
    }
    /**
     * 查询实践学习
     *
     * @param classId 实践学习主键
     * @return 实践学习
     */
    @Override
    public SysPractice selectSysPracticeByClassId(Long classId)
    {
        return sysPracticeMapper.selectSysPracticeByClassId(classId);
    }

    @Override
    public int updateSelected(SysPractice sysPractice) {
        return sysPracticeMapper.updateSelected(sysPractice);
    }

    @Override
    public List<SysPractice> selectSysPracticeListSelected(SysPractice sysPractice) {
        return sysPracticeMapper.selectSysPracticeListSelected(sysPractice);
    }

    @Override
    public int updateDeSelected(SysPractice sysPractice) {
        return sysPracticeMapper.updateDeSelected(sysPractice);
    }

    /**
     * 查询实践学习列表
     *
     * @param sysPractice 实践学习
     * @return 实践学习
     */
    @Override
    public List<SysPractice> selectSysPracticeList(SysPractice sysPractice)
    {
        return sysPracticeMapper.selectSysPracticeList(sysPractice);
    }

    @Override
    public List<SysPractice> selectSysPracticeListSelect(SysPractice sysPractice) {
        return sysPracticeMapper.selectSysPracticeListSelect(sysPractice);
    }

    /**
     * 新增实践学习
     *
     * @param sysPractice 实践学习
     * @return 结果
     */
    @Override
    @Transactional
    public int insertSysPractice(SysPractice sysPractice)
    {
        sysPractice.setCreateTime(DateUtils.getNowDate());
        sysPracticeMapper.insertSysPractice(sysPractice);
        return insertClassDept(sysPractice);
    }
    public int insertClassDept(SysPractice sysPractice)
    {
        int rows = 1;
        // 新增用户与角色管理
        List<SysClassDept> list = new ArrayList<SysClassDept>();
        for (Long deptId : sysPractice.getDeptIds())
        {
            SysClassDept cd = new SysClassDept();
            cd.setClassId(sysPractice.getClassId());
            cd.setClassType("1");
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
     * 修改实践学习
     *
     * @param sysPractice 实践学习
     * @return 结果
     */
    @Override
    @Transactional
    public int updateSysPractice(SysPractice sysPractice)
    {
        sysPractice.setUpdateTime(DateUtils.getNowDate());
        sysPracticeMapper.updateSysPractice(sysPractice);
        sysClassDeptMapper.deleteSysClassDeptByClassId2(sysPractice.getClassId());
        return insertClassDept(sysPractice);
    }

    /**
     * 批量删除实践学习
     *
     * @param classIds 需要删除的实践学习主键
     * @return 结果
     */
    @Override
    public int deleteSysPracticeByClassIds(String classIds)
    {
        return sysPracticeMapper.deleteSysPracticeByClassIds(Convert.toStrArray(classIds));
    }

    /**
     * 删除实践学习信息
     *
     * @param classId 实践学习主键
     * @return 结果
     */
    @Override
    public int deleteSysPracticeByClassId(Long classId)
    {
        return sysPracticeMapper.deleteSysPracticeByClassId(classId);
    }
}
