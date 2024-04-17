package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysCourse;

/**
 * 在线学习课程Mapper接口
 * 
 * @author ruoyi
 * @date 2021-12-25
 */
public interface SysCourseMapper 
{
    /**
     * 查询在线学习课程
     * 
     * @param courseId 在线学习课程主键
     * @return 在线学习课程
     */
    public SysCourse selectSysCourseByCourseId(Long courseId);

    /**
     * 查询在线学习课程列表
     * 
     * @param sysCourse 在线学习课程
     * @return 在线学习课程集合
     */
    public List<SysCourse> selectSysCourseList(SysCourse sysCourse);

    /**
     * 新增在线学习课程
     * 
     * @param sysCourse 在线学习课程
     * @return 结果
     */
    public int insertSysCourse(SysCourse sysCourse);

    /**
     * 修改在线学习课程
     * 
     * @param sysCourse 在线学习课程
     * @return 结果
     */
    public int updateSysCourse(SysCourse sysCourse);

    /**
     * 删除在线学习课程
     * 
     * @param courseId 在线学习课程主键
     * @return 结果
     */
    public int deleteSysCourseByCourseId(Long courseId);

    /**
     * 批量删除在线学习课程
     * 
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCourseByCourseIds(String[] courseIds);
}
