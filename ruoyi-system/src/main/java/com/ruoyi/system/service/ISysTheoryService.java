package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysTheory;

/**
 * 理论学习Service接口
 * 
 * @author ruoyi
 * @date 2021-12-19
 */
public interface ISysTheoryService 
{
    /**
     * 查询理论学习
     * 
     * @param classId 理论学习主键
     * @return 理论学习
     */
    public SysTheory selectSysTheoryByClassId(Long classId);

    /**
     * 查询理论学习列表
     * 
     * @param sysTheory 理论学习
     * @return 理论学习集合
     */
    public List<SysTheory> selectSysTheoryList(SysTheory sysTheory);
    public List<SysTheory> selectSysTheoryListSelect(SysTheory sysTheory);
    public List<SysTheory> selectSysTheoryListSelected(SysTheory sysTheory);

    /**
     * 新增理论学习
     * 
     * @param sysTheory 理论学习
     * @return 结果
     */
    public int insertSysTheory(SysTheory sysTheory);

    /**
     * 修改理论学习
     * 
     * @param sysTheory 理论学习
     * @return 结果
     */
    public int updateSysTheory(SysTheory sysTheory);

    /**
     * 批量删除理论学习
     * 
     * @param classIds 需要删除的理论学习主键集合
     * @return 结果
     */
    public int deleteSysTheoryByClassIds(String classIds);

    /**
     * 删除理论学习信息
     * 
     * @param classId 理论学习主键
     * @return 结果
     */
    public int deleteSysTheoryByClassId(Long classId);
    public int changeStatus(SysTheory sysTheory);
    public int updateSelected(SysTheory sysTheory);
    public int updateDeSelected(SysTheory sysTheory);
}
