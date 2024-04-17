package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCourseMediaMapper;
import com.ruoyi.system.domain.SysCourseMedia;
import com.ruoyi.system.service.ISysCourseMediaService;
import com.ruoyi.common.core.text.Convert;

/**
 * 视频文件Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-12-26
 */
@Service
public class SysCourseMediaServiceImpl implements ISysCourseMediaService 
{
    @Autowired
    private SysCourseMediaMapper sysCourseMediaMapper;

    /**
     * 查询视频文件
     * 
     * @param fileUuid 视频文件主键
     * @return 视频文件
     */
    @Override
    public SysCourseMedia selectSysCourseMediaByFileUuid(String fileUuid)
    {
        return sysCourseMediaMapper.selectSysCourseMediaByFileUuid(fileUuid);
    }

    /**
     * 查询视频文件列表
     * 
     * @param sysCourseMedia 视频文件
     * @return 视频文件
     */
    @Override
    public List<SysCourseMedia> selectSysCourseMediaList(SysCourseMedia sysCourseMedia)
    {
        return sysCourseMediaMapper.selectSysCourseMediaList(sysCourseMedia);
    }

    /**
     * 新增视频文件
     * 
     * @param sysCourseMedia 视频文件
     * @return 结果
     */
    @Override
    public int insertSysCourseMedia(SysCourseMedia sysCourseMedia)
    {
        return sysCourseMediaMapper.insertSysCourseMedia(sysCourseMedia);
    }

    /**
     * 修改视频文件
     * 
     * @param sysCourseMedia 视频文件
     * @return 结果
     */
    @Override
    public int updateSysCourseMedia(SysCourseMedia sysCourseMedia)
    {
        return sysCourseMediaMapper.updateSysCourseMedia(sysCourseMedia);
    }

    /**
     * 批量删除视频文件
     * 
     * @param fileUuids 需要删除的视频文件主键
     * @return 结果
     */
    @Override
    public int deleteSysCourseMediaByFileUuids(String fileUuids)
    {
        return sysCourseMediaMapper.deleteSysCourseMediaByFileUuids(Convert.toStrArray(fileUuids));
    }

    /**
     * 删除视频文件信息
     * 
     * @param fileUuid 视频文件主键
     * @return 结果
     */
    @Override
    public int deleteSysCourseMediaByFileUuid(String fileUuid)
    {
        return sysCourseMediaMapper.deleteSysCourseMediaByFileUuid(fileUuid);
    }

    @Override
    public int insertBatch(List<SysCourseMedia> list) {
        return sysCourseMediaMapper.insertBatch(list);
    }

    @Override
    public int truncateTable() {
        return sysCourseMediaMapper.truncateTable();
    }
}
