package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysCourseCover;

/**
 * 封面图片Service接口
 * 
 * @author ruoyi
 * @date 2021-12-26
 */
public interface ISysCourseCoverService 
{
    /**
     * 查询封面图片
     * 
     * @param fileUuid 封面图片主键
     * @return 封面图片
     */
    public SysCourseCover selectSysCourseCoverByFileUuid(String fileUuid);

    /**
     * 查询封面图片列表
     * 
     * @param sysCourseCover 封面图片
     * @return 封面图片集合
     */
    public List<SysCourseCover> selectSysCourseCoverList(SysCourseCover sysCourseCover);

    /**
     * 新增封面图片
     * 
     * @param sysCourseCover 封面图片
     * @return 结果
     */
    public int insertSysCourseCover(SysCourseCover sysCourseCover);

    /**
     * 修改封面图片
     * 
     * @param sysCourseCover 封面图片
     * @return 结果
     */
    public int updateSysCourseCover(SysCourseCover sysCourseCover);

    /**
     * 批量删除封面图片
     * 
     * @param fileUuids 需要删除的封面图片主键集合
     * @return 结果
     */
    public int deleteSysCourseCoverByFileUuids(String fileUuids);

    /**
     * 删除封面图片信息
     * 
     * @param fileUuid 封面图片主键
     * @return 结果
     */
    public int deleteSysCourseCoverByFileUuid(String fileUuid);
    public int truncateTable();
    public int insertBatch(List<SysCourseCover> list);
}
