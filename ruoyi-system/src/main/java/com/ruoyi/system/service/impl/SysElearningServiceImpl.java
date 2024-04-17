package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysElearningMapper;
import com.ruoyi.system.domain.SysElearning;
import com.ruoyi.system.service.ISysElearningService;
import com.ruoyi.common.core.text.Convert;

/**
 * 在线学习Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-12-19
 */
@Service
public class SysElearningServiceImpl implements ISysElearningService 
{
    @Autowired
    private SysElearningMapper sysElearningMapper;

    /**
     * 查询在线学习
     * 
     * @param classId 在线学习主键
     * @return 在线学习
     */
    @Override
    public SysElearning selectSysElearningByClassId(Long classId)
    {
        return sysElearningMapper.selectSysElearningByClassId(classId);
    }

    /**
     * 查询在线学习列表
     * 
     * @param sysElearning 在线学习
     * @return 在线学习
     */
    @Override
    public List<SysElearning> selectSysElearningList(SysElearning sysElearning)
    {
        return sysElearningMapper.selectSysElearningList(sysElearning);
    }

    /**
     * 新增在线学习
     * 
     * @param sysElearning 在线学习
     * @return 结果
     */
    @Override
    public int insertSysElearning(SysElearning sysElearning)
    {
        sysElearning.setCreateTime(DateUtils.getNowDate());
        return sysElearningMapper.insertSysElearning(sysElearning);
    }

    /**
     * 修改在线学习
     * 
     * @param sysElearning 在线学习
     * @return 结果
     */
    @Override
    public int updateSysElearning(SysElearning sysElearning)
    {
        sysElearning.setUpdateTime(DateUtils.getNowDate());
        return sysElearningMapper.updateSysElearning(sysElearning);
    }

    @Override
    public List<SysElearning> selectSysElearningListSelect(SysElearning sysElearning) {
        return sysElearningMapper.selectSysElearningListSelect(sysElearning);
    }

    @Override
    public int updateDeSelected(SysElearning sysElearning) {
        return sysElearningMapper.updateDeSelected(sysElearning);
    }

    @Override
    public int updateSelected(SysElearning sysElearning) {
        return sysElearningMapper.updateSelected(sysElearning);
    }

    @Override
    public List<SysElearning> selectSysElearningListSelected(SysElearning sysElearning) {
        return sysElearningMapper.selectSysElearningListSelected(sysElearning);
    }

    @Override
    public int changeStatus(SysElearning sysElearning) {
        return sysElearningMapper.updateSysElearning(sysElearning);
    }

    /**
     * 批量删除在线学习
     * 
     * @param classIds 需要删除的在线学习主键
     * @return 结果
     */
    @Override
    public int deleteSysElearningByClassIds(String classIds)
    {
        return sysElearningMapper.deleteSysElearningByClassIds(Convert.toStrArray(classIds));
    }

    /**
     * 删除在线学习信息
     * 
     * @param classId 在线学习主键
     * @return 结果
     */
    @Override
    public int deleteSysElearningByClassId(Long classId)
    {
        return sysElearningMapper.deleteSysElearningByClassId(classId);
    }
}
