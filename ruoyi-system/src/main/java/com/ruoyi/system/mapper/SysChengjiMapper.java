package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysChengji;

/**
 * 成绩统计Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-15
 */
public interface SysChengjiMapper 
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
     * 删除成绩统计
     * 
     * @param userId 成绩统计主键
     * @return 结果
     */
    public int deleteSysChengjiByUserId(Long userId);

    /**
     * 批量删除成绩统计
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysChengjiByUserIds(String[] userIds);
}
