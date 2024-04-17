package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCourseMapper;
import com.ruoyi.system.domain.SysCourse;
import com.ruoyi.system.service.ISysCourseService;
import com.ruoyi.common.core.text.Convert;

/**
 * 在线学习课程Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-12-25
 */
@Service
public class SysCourseServiceImpl implements ISysCourseService 
{
    @Autowired
    private SysCourseMapper sysCourseMapper;

    /**
     * 查询在线学习课程
     * 
     * @param courseId 在线学习课程主键
     * @return 在线学习课程
     */
    @Override
    public SysCourse selectSysCourseByCourseId(Long courseId)
    {
        return sysCourseMapper.selectSysCourseByCourseId(courseId);
    }

    /**
     * 查询在线学习课程列表
     * 
     * @param sysCourse 在线学习课程
     * @return 在线学习课程
     */
    @Override
    public List<SysCourse> selectSysCourseList(SysCourse sysCourse)
    {
        return sysCourseMapper.selectSysCourseList(sysCourse);
    }

    @Override
    public int changeStatus(SysCourse sysCourse) {
        return sysCourseMapper.updateSysCourse(sysCourse);
    }

    /**
     * 新增在线学习课程
     * 
     * @param sysCourse 在线学习课程
     * @return 结果
     */
    @Override
    public int insertSysCourse(SysCourse sysCourse)
    {
        sysCourse.setCreateTime(DateUtils.getNowDate());
        return sysCourseMapper.insertSysCourse(sysCourse);
    }

    /**
     * 修改在线学习课程
     * 
     * @param sysCourse 在线学习课程
     * @return 结果
     */
    @Override
    public int updateSysCourse(SysCourse sysCourse)
    {
        sysCourse.setUpdateTime(DateUtils.getNowDate());
        return sysCourseMapper.updateSysCourse(sysCourse);
    }

    /**
     * 批量删除在线学习课程
     * 
     * @param courseIds 需要删除的在线学习课程主键
     * @return 结果
     */
    @Override
    public int deleteSysCourseByCourseIds(String courseIds)
    {
        return sysCourseMapper.deleteSysCourseByCourseIds(Convert.toStrArray(courseIds));
    }

    /**
     * 删除在线学习课程信息
     * 
     * @param courseId 在线学习课程主键
     * @return 结果
     */
    @Override
    public int deleteSysCourseByCourseId(Long courseId)
    {
        return sysCourseMapper.deleteSysCourseByCourseId(courseId);
    }
}
