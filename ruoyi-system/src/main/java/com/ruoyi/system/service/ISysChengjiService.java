package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysChengji;

/**
 * 成绩统计Service接口
 * 
 * @author ruoyi
 * @date 2022-03-15
 */
public interface ISysChengjiService 
{
    /**
     * 查询成绩统计
     * 
     * @param userId 成绩统计主键
     * @return 成绩统计
     */
    public SysChengji selectSysChengjiByUserId(Long userId);

    /**
     * 查询成绩统计列表
     * 
     * @param sysChengji 成绩统计
     * @return 成绩统计集合
     */
    public List<SysChengji> selectSysChengjiList(SysChengji sysChengji);

    /**
     * 新增成绩统计
     * 
     * @param sysChengji 成绩统计
     * @return 结果
     */
    public int insertSysChengji(SysChengji sysChengji);

    /**
     * 修改成绩统计
     * 
     * @param sysChengji 成绩统计
     * @return 结果
     */
    public int updateSysChengji(SysChengji sysChengji);

    /**
     * 批量删除成绩统计
     * 
     * @param userIds 需要删除的成绩统计主键集合
     * @return 结果
     */
    public int deleteSysChengjiByUserIds(String userIds);

    /**
     * 删除成绩统计信息
     * 
     * @param userId 成绩统计主键
     * @return 结果
     */
    public int deleteSysChengjiByUserId(Long userId);
}
