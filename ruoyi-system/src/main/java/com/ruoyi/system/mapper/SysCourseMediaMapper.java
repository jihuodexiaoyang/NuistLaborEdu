package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysCourseMedia;

/**
 * 视频文件Mapper接口
 * 
 * @author ruoyi
 * @date 2021-12-26
 */
public interface SysCourseMediaMapper 
{
    /**
     * 查询视频文件
     * 
     * @param fileUuid 视频文件主键
     * @return 视频文件
     */
    public SysCourseMedia selectSysCourseMediaByFileUuid(String fileUuid);

    /**
     * 查询视频文件列表
     * 
     * @param sysCourseMedia 视频文件
     * @return 视频文件集合
     */
    public List<SysCourseMedia> selectSysCourseMediaList(SysCourseMedia sysCourseMedia);

    /**
     * 新增视频文件
     * 
     * @param sysCourseMedia 视频文件
     * @return 结果
     */
    public int insertSysCourseMedia(SysCourseMedia sysCourseMedia);

    /**
     * 修改视频文件
     * 
     * @param sysCourseMedia 视频文件
     * @return 结果
     */
    public int updateSysCourseMedia(SysCourseMedia sysCourseMedia);

    /**
     * 删除视频文件
     * 
     * @param fileUuid 视频文件主键
     * @return 结果
     */
    public int deleteSysCourseMediaByFileUuid(String fileUuid);

    /**
     * 批量删除视频文件
     * 
     * @param fileUuids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCourseMediaByFileUuids(String[] fileUuids);
    public int truncateTable();
    public int insertBatch(List<SysCourseMedia> list);
}
