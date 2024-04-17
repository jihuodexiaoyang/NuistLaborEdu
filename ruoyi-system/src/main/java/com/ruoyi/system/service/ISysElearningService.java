package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysElearning;

/**
 * 在线学习Service接口
 * 
 * @author ruoyi
 * @date 2021-12-19
 */
public interface ISysElearningService 
{
    /**
     * 查询在线学习
     * 
     * @param classId 在线学习主键
     * @return 在线学习
     */
    public SysElearning selectSysElearningByClassId(Long classId);

    /**
     * 查询在线学习列表
     * 
     * @param sysElearning 在线学习
     * @return 在线学习集合
     */
    public List<SysElearning> selectSysElearningList(SysElearning sysElearning);
    /**
     * 查询在线学习列表(选课)
     *
     * @param userId 在线学习
     * @return 在线学习集合
     */
    public List<SysElearning> selectSysElearningListSelect(SysElearning sysElearning);
    public List<SysElearning> selectSysElearningListSelected(SysElearning sysElearning);

    /**
     * 新增在线学习
     * 
     * @param sysElearning 在线学习
     * @return 结果
     */
    public int insertSysElearning(SysElearning sysElearning);

    /**
     * 修改在线学习
     * 
     * @param sysElearning 在线学习
     * @return 结果
     */
    public int updateSysElearning(SysElearning sysElearning);

    /**
     * 批量删除在线学习
     * 
     * @param classIds 需要删除的在线学习主键集合
     * @return 结果
     */
    public int deleteSysElearningByClassIds(String classIds);

    /**
     * 删除在线学习信息
     * 
     * @param classId 在线学习主键
     * @return 结果
     */
    public int deleteSysElearningByClassId(Long classId);
    public int changeStatus(SysElearning sysElearning);
    public int updateSelected(SysElearning sysElearning);
    public int updateDeSelected(SysElearning sysElearning);
}
