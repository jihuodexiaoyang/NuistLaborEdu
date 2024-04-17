package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SysKaohe;
import com.ruoyi.system.domain.SysTouxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserClassMapper;
import com.ruoyi.system.domain.SysUserClass;
import com.ruoyi.system.service.ISysUserClassService;
import com.ruoyi.common.core.text.Convert;

/**
 * 学生选课Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-12-27
 */
@Service
public class SysUserClassServiceImpl implements ISysUserClassService 
{
    @Autowired
    private SysUserClassMapper sysUserClassMapper;

    /**
     * 查询学生选课
     * 
     * @param userId 学生选课主键
     * @return 学生选课
     */
    @Override
    public SysUserClass selectSysUserClassByUserId(Long userId)
    {
        return sysUserClassMapper.selectSysUserClassByUserId(userId);
    }

    /**
     * 查询学生选课列表
     * 
     * @param sysUserClass 学生选课
     * @return 学生选课
     */
    @Override
    public List<SysUserClass> selectSysUserClassList(SysUserClass sysUserClass)
    {
        return sysUserClassMapper.selectSysUserClassList(sysUserClass);
    }

    /**
     * 新增学生选课
     * 
     * @param sysUserClass 学生选课
     * @return 结果
     */
    @Override
    public int insertSysUserClass(SysUserClass sysUserClass)
    {
        sysUserClass.setCreateTime(DateUtils.getNowDate());
        return sysUserClassMapper.insertSysUserClass(sysUserClass);
    }

    /**
     * 修改学生选课
     * 
     * @param sysUserClass 学生选课
     * @return 结果
     */
    @Override
    public int updateSysUserClass(SysUserClass sysUserClass)
    {
        return sysUserClassMapper.updateSysUserClass(sysUserClass);
    }

    /**
     * 批量删除学生选课
     * 
     * @param userIds 需要删除的学生选课主键
     * @return 结果
     */
    @Override
    public int deleteSysUserClassByUserIds(String userIds)
    {
        return sysUserClassMapper.deleteSysUserClassByUserIds(Convert.toStrArray(userIds));
    }

    /**
     * 删除学生选课信息
     * 
     * @param userId 学生选课主键
     * @return 结果
     */
    @Override
    public int deleteSysUserClassByUserId(Long userId)
    {
        return sysUserClassMapper.deleteSysUserClassByUserId(userId);
    }

    @Override
    public int deSelectClass(SysUserClass sysUserClass) {
        return sysUserClassMapper.deSelectClass(sysUserClass);
    }

    @Override
    public List<SysKaohe> selectSysUserKaoheList(SysKaohe sysKaohe) {
        return sysUserClassMapper.selectSysUserKaoheList(sysKaohe);
    }

    @Override
    public List<SysKaohe> selectSysUserYujingList(SysKaohe sysKaohe) {
        return sysUserClassMapper.selectSysUserYujingList(sysKaohe);
    }

    @Override
    public List<SysTouxi> selectSysUserTouxiList(SysTouxi sysTouxi) {
        return sysUserClassMapper.selectSysUserTouxiList(sysTouxi);
    }
}
