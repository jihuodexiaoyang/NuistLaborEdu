package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.SysKaohe;
import com.ruoyi.system.domain.SysTouxi;
import com.ruoyi.system.domain.SysUserClass;

/**
 * 学生选课Service接口
 * 
 * @author ruoyi
 * @date 2021-12-27
 */
public interface ISysUserClassService 
{
    /**
     * 查询学生选课
     * 
     * @param userId 学生选课主键
     * @return 学生选课
     */
    public SysUserClass selectSysUserClassByUserId(Long userId);

    /**
     * 查询学生选课列表
     * 
     * @param sysUserClass 学生选课
     * @return 学生选课集合
     */
    public List<SysUserClass> selectSysUserClassList(SysUserClass sysUserClass);

    /**
     * 新增学生选课
     * 
     * @param sysUserClass 学生选课
     * @return 结果
     */
    public int insertSysUserClass(SysUserClass sysUserClass);

    /**
     * 修改学生选课
     * 
     * @param sysUserClass 学生选课
     * @return 结果
     */
    public int updateSysUserClass(SysUserClass sysUserClass);

    /**
     * 批量删除学生选课
     * 
     * @param userIds 需要删除的学生选课主键集合
     * @return 结果
     */
    public int deleteSysUserClassByUserIds(String userIds);

    /**
     * 删除学生选课信息
     * 
     * @param userId 学生选课主键
     * @return 结果
     */
    public int deleteSysUserClassByUserId(Long userId);

    /**
     * 取消选课
     * @param sysUserClass
     * @return
     */
    public int deSelectClass(SysUserClass sysUserClass);
    public List<SysKaohe> selectSysUserKaoheList(SysKaohe sysKaohe);
    public List<SysKaohe> selectSysUserYujingList(SysKaohe sysKaohe);
    public List<SysTouxi> selectSysUserTouxiList(SysTouxi sysTouxi);
}
