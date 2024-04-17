package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.SysTheory;

/**
 * 理论学习Mapper接口
 * 
 * @author ruoyi
 * @date 2021-12-25
 */
public interface SysTheoryMapper 
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
    /**
     * 查询理论学习列表(选课)
     *
     * @param userId 理论学习
     * @return 理论学习集合
     */
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
     * 删除理论学习
     * 
     * @param classId 理论学习主键
     * @return 结果
     */
    public int deleteSysTheoryByClassId(Long classId);

    /**
     * 批量删除理论学习
     * 
     * @param classIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysTheoryByClassIds(String[] classIds);
    /**
     * 更新课程已选人数
     * @param sysTheory
     * @return
     */
    public int updateSelected(SysTheory sysTheory);

    /**
     * 取消选课 更新人数
     * @param sysTheory
     * @return
     */
    public int updateDeSelected(SysTheory sysTheory);
}
