package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysChengjiMapper;
import com.ruoyi.system.domain.SysChengji;
import com.ruoyi.system.service.ISysChengjiService;
import com.ruoyi.common.core.text.Convert;

/**
 * 成绩统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-15
 */
@Service
public class SysChengjiServiceImpl implements ISysChengjiService 
{
    @Autowired
    private SysChengjiMapper sysChengjiMapper;

    /**
     * 查询成绩统计
     * 
     * @param userId 成绩统计主键
     * @return 成绩统计
     */
    @Override
    public SysChengji selectSysChengjiByUserId(Long userId)
    {
        return sysChengjiMapper.selectSysChengjiByUserId(userId);
    }

    /**
     * 查询成绩统计列表
     * 
     * @param sysChengji 成绩统计
     * @return 成绩统计
     */
    @Override
    public List<SysChengji> selectSysChengjiList(SysChengji sysChengji)
    {
        return sysChengjiMapper.selectSysChengjiList(sysChengji);
    }

    /**
     * 新增成绩统计
     * 
     * @param sysChengji 成绩统计
     * @return 结果
     */
    @Override
    public int insertSysChengji(SysChengji sysChengji)
    {
        return sysChengjiMapper.insertSysChengji(sysChengji);
    }

    /**
     * 修改成绩统计
     * 
     * @param sysChengji 成绩统计
     * @return 结果
     */
    @Override
    public int updateSysChengji(SysChengji sysChengji)
    {
        return sysChengjiMapper.updateSysChengji(sysChengji);
    }

    /**
     * 批量删除成绩统计
     * 
     * @param userIds 需要删除的成绩统计主键
     * @return 结果
     */
    @Override
    public int deleteSysChengjiByUserIds(String userIds)
    {
        return sysChengjiMapper.deleteSysChengjiByUserIds(Convert.toStrArray(userIds));
    }

    /**
     * 删除成绩统计信息
     * 
     * @param userId 成绩统计主键
     * @return 结果
     */
    @Override
    public int deleteSysChengjiByUserId(Long userId)
    {
        return sysChengjiMapper.deleteSysChengjiByUserId(userId);
    }
}
